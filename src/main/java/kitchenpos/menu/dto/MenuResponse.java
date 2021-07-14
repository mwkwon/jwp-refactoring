package kitchenpos.menu.dto;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class MenuResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProductResponse> menuProductResponses;

    public MenuResponse() {
    }

    public MenuResponse(Long id, String name, BigDecimal price, Long menuGroupId, List<MenuProductResponse> menuProductResponses) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProductResponses = menuProductResponses;
    }

    public static MenuResponse of(Menu menu) {
        List<MenuProductResponse> menuProductResponses = menu.getMenuProducts().products().stream().map(MenuProductResponse::of).collect(Collectors.toList());
        return new MenuResponse(menu.getId(), menu.getName(), menu.getPrice().price(), menu.getMenuGroupId(), menuProductResponses);
    }

    public static MenuResponse of(Menu menu, List<MenuProduct> menuProducts) {
        List<MenuProductResponse> menuProductResponses = menuProducts.stream().map(MenuProductResponse::of).collect(Collectors.toList());
        return new MenuResponse(menu.getId(), menu.getName(), menu.getPrice().price(), menu.getMenuGroupId(), menuProductResponses);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProductResponse> getMenuProductResponses() {
        return menuProductResponses;
    }
}
