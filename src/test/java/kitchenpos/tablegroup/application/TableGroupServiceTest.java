package kitchenpos.tablegroup.application;

import kitchenpos.exception.TableGroupException;
import kitchenpos.table.application.TableService;
import kitchenpos.table.domain.OrderTable;
import kitchenpos.table.domain.OrderTables;
import kitchenpos.table.dto.OrderTableResponse;
import kitchenpos.tablegroup.domain.TableGroup;
import kitchenpos.tablegroup.domain.TableGroupRepository;
import kitchenpos.tablegroup.dto.TableGroupRequest;
import kitchenpos.tablegroup.dto.TableGroupResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("테이블 그룹 관련 기능 테스트")
@ExtendWith(MockitoExtension.class)
class TableGroupServiceTest {

    @Mock
    private TableGroupRepository tableGroupRepository;

    @Mock
    private TableService tableService;

    @InjectMocks
    private TableGroupService tableGroupService;

    private OrderTable 일번_테이블;
    private OrderTable 이번_테이블;

    @BeforeEach
    void setUp() {
        일번_테이블 = new OrderTable(1L, 0, true);
        이번_테이블 = new OrderTable(2L, 0, true);
    }

    @Test
    void 테이블_그룹_생성() {
        LocalDateTime now = LocalDateTime.now();

        OrderTables orderTables = new OrderTables(Arrays.asList(일번_테이블, 이번_테이블));
        when(tableService.findAllByIds(Arrays.asList(1L, 2L))).thenReturn(orderTables);

        TableGroup tableGroup = new TableGroup(1L, now, orderTables);
        when(tableGroupRepository.save(any(TableGroup.class))).thenReturn(tableGroup);
        TableGroupResponse expected = tableGroupService.create(new TableGroupRequest(Arrays.asList(1L, 2L)));
        assertThat(expected.getId()).isEqualTo(tableGroup.getId());
        assertThat(expected.getOrderTableResponses().size()).isEqualTo(2);
    }

    @Test
    void 주문테이블이_1개만있을때_테이블그룹_생성_요청_시_에러_발생() {
        assertThatThrownBy(() -> tableGroupService.create(new TableGroupRequest(Arrays.asList(1L)))).isInstanceOf(TableGroupException.class);
    }

    @Test
    void 테이블_그룹_그룹핑_해제() {
        LocalDateTime now = LocalDateTime.now();
        TableGroup tableGroup = new TableGroup(1L, now);
        일번_테이블.withTableGroup(tableGroup);
        이번_테이블.withTableGroup(tableGroup);
        when(tableGroupRepository.findById(1L)).thenReturn(Optional.of(tableGroup));
        tableGroupService.ungroup(1L);
    }
}
