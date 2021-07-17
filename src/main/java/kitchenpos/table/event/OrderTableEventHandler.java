package kitchenpos.table.event;

import kitchenpos.exception.OrderTableException;
import kitchenpos.exception.TableGroupException;
import kitchenpos.order.domain.OrderRepository;
import kitchenpos.order.domain.Orders;
import kitchenpos.table.application.TableService;
import kitchenpos.table.domain.OrderTable;
import kitchenpos.table.domain.OrderTables;
import kitchenpos.table.domain.TableGroup;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class OrderTableEventHandler {

    private static final String NOT_CHANGE_GROUP_TABLE_ERROR_MESSAGE = "그룹핑 되어있는 테이블 상태를 변경할 수 없습니다.";
    private static final String MINIMUM_GROUP_TABLE_COUNT_ERROR_MESSAGE = "2개 이상의 테이블을 그룹핑할 수 있습니다.";

    private final TableService tableService;
    private final OrderRepository orderRepository;

    public OrderTableEventHandler(TableService tableService, OrderRepository orderRepository) {
        this.tableService = tableService;
        this.orderRepository = orderRepository;
    }

    @EventListener
    public void groupOrderTable(OrderTableGroupEvent orderTableGroupEvent) {
        List<Long> orderTableIds = orderTableGroupEvent.getOrderTableIds();
        validateTableIds(orderTableIds);
        OrderTables orderTables = tableService.findAllByIds(orderTableIds);
        validateOrderTables(orderTables, orderTableIds);
        orderTables.updateGrouping(orderTableGroupEvent.getTableGroup());
    }

    @EventListener
    public void ungroupOrderTable(OrderTableUngroupEvent orderTableUngroupEvent) {
        TableGroup tableGroup = orderTableUngroupEvent.getTableGroup();
        OrderTables orderTables = tableGroup.getOrderTables();
        validateUngroup(orderTables.generateOrderTableIds());
        tableGroup.updateUnGroup();
    }

    @EventListener
    public void changeEmptyOrderTable(OrderTableChangeEmptyValidEvent orderTableChangeEmptyValidEvent) {
        OrderTable orderTable = orderTableChangeEmptyValidEvent.getOrderTable();
        Optional<Orders> optionalOrder = orderRepository.findByOrOrderTableId(orderTable.getId());
        optionalOrder.ifPresent(order -> validateChangeEmpty(order, orderTable));
    }

    private void validateTableIds(List<Long> orderTableIds) {
        if (CollectionUtils.isEmpty(orderTableIds) || orderTableIds.size() < 2) {
            throw new TableGroupException(MINIMUM_GROUP_TABLE_COUNT_ERROR_MESSAGE);
        }
    }

    private void validateOrderTables(OrderTables orderTables, List<Long> orderTableIds) {
        orderTables.checkValidEqualToRequestSize(orderTableIds);
        orderTables.checkValidEmptyTableGroup();
    }

    private void validateUngroup(List<Long> orderTableIds) {
        List<Orders> orders = orderRepository.findAllByOrderTableIdIn(orderTableIds);
        if (!orders.stream().anyMatch(order -> order.isCompletion())) {
            throw new TableGroupException("주문 상태가 완료 상태가 아닌 주문 테이블이 존재하여 그룹 해제에 실패하였습니다.");
        }
    }

    private void validateChangeEmpty(Orders order, OrderTable orderTable) {
        if (Objects.nonNull(orderTable.getTableGroup())) {
            throw new OrderTableException(NOT_CHANGE_GROUP_TABLE_ERROR_MESSAGE);
        }
        if (!order.isCompletion()) {
            throw new OrderTableException("주문 상태가 완료 상태가 아닌 경우 테이블 상태를 변경할 수 없습니다.");
        }
    }
}
