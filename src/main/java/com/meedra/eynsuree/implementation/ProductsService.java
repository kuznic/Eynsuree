package com.meedra.eynsuree.implementation;


import com.meedra.eynsuree.model.Product;
import com.meedra.eynsuree.model.ProductCategory;
import com.meedra.eynsuree.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.function.Supplier;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    public List<Product> fetchProducts(ProductCategory productCategory) {
        Supplier<InvalidParameterException> s = () -> new InvalidParameterException("Invalid Product Category Id provided");
        Product products = productsRepository
                .findByproductCategory(productCategory)
                .orElseThrow(s);

        return (List<Product>) products;
    }

    public Product fetchProduct(String productName){

        Supplier<NullPointerException> s = () -> new NullPointerException("No Product found");
        return productsRepository
                .findByproductType(productName)
                .orElseThrow(s);
    }

}
