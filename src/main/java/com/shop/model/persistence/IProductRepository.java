package com.shop.model.persistence;
import com.shop.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {

    public abstract Product findByBrand (String brand);

    public abstract Product findByName (String name);
}
