package kitchenpos.product.domain;


import java.util.List;
import java.util.Objects;

public class Products {
    private List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public boolean contains(Product product) {
        return this.products.contains(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Products products1 = (Products) object;
        return Objects.equals(products, products1.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
