package com.meedra.eynsuree.repository;


import com.meedra.eynsuree.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductsCategoryRepository extends JpaRepository<ProductCategory, Long> {


    @Query(value="SELECT name  FROM ProductCategory productsCategory ")
    List<String>  getProductsCategory();

    Optional<ProductCategory> findByName(String productCategoryName);
}