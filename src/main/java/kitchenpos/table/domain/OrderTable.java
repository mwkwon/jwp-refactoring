package kitchenpos.table.domain;

import kitchenpos.exception.OrderTableException;
import kitchenpos.order.domain.Orders;
import kitchenpos.tablegroup.domain.TableGroup;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderTable {

    private static final String NOT_CHANGE_GROUP_TABLE_ERROR_MESSAGE = "그룹핑 되어있는 테이블 상태를 변경할 수 없습니다.";
    private static final String NOT_CHANGE_EMPTY_TABLE_ERROR_MESSAGE = "비어있는 테이블의 인원수를 변경할 수 없습니다.";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_group_id", foreignKey = @ForeignKey(name = "fk_order_table_table_group"))
    private TableGroup tableGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order_table_orders"))
    private Orders order;

    @Embedded
    private NumberOfGuests numberOfGuests = new NumberOfGuests();

    private boolean empty;

    protected OrderTable() {
    }

    public OrderTable(Long id, int numberOfGuests, boolean empty) {
        this.id = id;
        this.numberOfGuests = new NumberOfGuests(numberOfGuests);
        this.empty = empty;
    }

    public void withOrder(Orders order) {
        this.order = order;
    }

    public OrderTable(int numberOfGuests, boolean empty) {
        this(null, numberOfGuests, empty);
    }

    public void withTableGroup(TableGroup tableGroup) {
        this.tableGroup = tableGroup;
    }

    public void checkValidEmptyTableGroup() {
        if (Objects.nonNull(tableGroup)) {
            throw new OrderTableException(NOT_CHANGE_GROUP_TABLE_ERROR_MESSAGE);
        }
    }

    public void checkIsEmpty() {
        if (empty) {
            throw new OrderTableException(NOT_CHANGE_EMPTY_TABLE_ERROR_MESSAGE);
        }
    }

    public void changeEmpty(boolean empty) {
        this.empty = empty;
    }

    public void changeNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = new NumberOfGuests(numberOfGuests);
    }

    public Long getId() {
        return id;
    }

    public Long getTableGroupId() {
        if (Objects.nonNull(tableGroup)) {
            return tableGroup.getId();
        }
        return null;
    }

    public NumberOfGuests getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void ungroup() {
        this.tableGroup = null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OrderTable that = (OrderTable) object;
        return empty == that.empty &&
                Objects.equals(id, that.id) &&
                Objects.equals(tableGroup, that.tableGroup) &&
                Objects.equals(numberOfGuests, that.numberOfGuests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableGroup, numberOfGuests, empty);
    }

    public void checkValidOrderStatusCompletion() {
        if (Objects.nonNull(order)) {
            order.checkOrderStatus();
        }
    }
}
