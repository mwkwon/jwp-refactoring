package kitchenpos.menugroup.application;

import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.menugroup.domain.MenuGroupRepository;
import kitchenpos.menugroup.dto.MenuGroupRequest;
import kitchenpos.menugroup.dto.MenuGroupResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuGroupService {

    private final MenuGroupRepository menuGroupRepository;

    public MenuGroupService(final MenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    public MenuGroupResponse newCreate(final MenuGroupRequest request) {
        MenuGroup menuGroup = menuGroupRepository.save(request.toMenuGroup());
        return MenuGroupResponse.of(menuGroup);
    }

    @Transactional(readOnly = true)
    public List<MenuGroupResponse> newList() {
        List<MenuGroup> menuGroups = menuGroupRepository.findAll();
        return menuGroups.stream().map(MenuGroupResponse::of).collect(Collectors.toList());
    }
}
