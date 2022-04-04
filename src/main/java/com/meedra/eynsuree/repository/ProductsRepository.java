package com.meedra.eynsuree.repository;


import com.meedra.eynsuree.model.Product;
import com.meedra.eynsuree.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByproductCategory(ProductCategory productCategory);

    Optional<Product> findByproductType(String productName);
}