package kitchenpos.order.application;

import kitchenpos.exception.OrderException;
import kitchenpos.menu.application.MenuService;
import kitchenpos.order.domain.OrderRepository;
import kitchenpos.order.domain.Orders;
import kitchenpos.order.dto.OrderRequest;
import kitchenpos.order.dto.OrderResponse;
import kitchenpos.table.application.TableService;
import kitchenpos.table.domain.OrderTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private static final String NOT_EQUAL_MENU_COUNT_ERROR_MESSAGE = "주문 항목 수량과 메뉴 수량이 일치 하지않습니다.";
    private static final String NOT_FOUND_ORDER_ERROR_MESSAGE = "주문 정보를 찾을 수 없습니다.";
    private static final String EMPTY_ORDER_ITEM_ERROR_MESSAGE = "주문 항목이 존재하지 않습니다.";

    private final OrderRepository orderRepository;

    private final MenuService menuService;
    private final TableService tableService;

    public OrderService(OrderRepository orderRepository,
                        MenuService menuService,
                        TableService tableService) {
        this.orderRepository = orderRepository;
        this.menuService = menuService;
        this.tableService = tableService;
    }

    public OrderResponse create(final OrderRequest request) {
        validOrderLineItemCount(request);
        checkValidMenuCount(request);
        OrderTable orderTable = tableService.findOrderTable(request.getOrderTableId());
        orderTable.checkIsEmpty();
        Orders orders = request.createNewOrder();
        orders.addOrderLineItems(request.toOrderLineItems());
        return OrderResponse.of(orderRepository.save(orders));
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> list() {
        List<Orders> orders = orderRepository.findAll();
        return orders.stream().map(OrderResponse::of).collect(Collectors.toList());
    }

    public OrderResponse changeOrderStatus(final Long orderId, final OrderRequest request) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new OrderException(NOT_FOUND_ORDER_ERROR_MESSAGE));
        orders.checkOrderStatus();
        orders.updateOrderStatus(request.getOrderStatus());
        return OrderResponse.of(orders);
    }

    private void checkValidMenuCount(OrderRequest request) {
        long menuCount = menuService.countByMenuId(request.toMenuIds());
        if (request.getOrderLineItemRequests().size() != menuCount) {
            throw new OrderException(NOT_EQUAL_MENU_COUNT_ERROR_MESSAGE);
        }
    }

    private void validOrderLineItemCount(final OrderRequest request) {
        if (CollectionUtils.isEmpty(request.getOrderLineItemRequests())) {
            throw new OrderException(EMPTY_ORDER_ITEM_ERROR_MESSAGE);
        }
    }
}
