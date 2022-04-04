package com.meedra.eynsuree.implementation;

import com.meedra.eynsuree.model.ProductCategory;
import com.meedra.eynsuree.repository.ProductsCategoryRepository;
import com.meedra.eynsuree.repository.ProductsRepository;
import com.meedra.eynsuree.stitch.service.AccessionToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

@Slf4j
@Service
public class ProductsCategoryService {

    @Autowired
    private ProductsCategoryRepository productsCategoryRepository;

    @Autowired
    StitchClientService stitchClientService;


    @Autowired
    AccessionToken accessionToken;


    @Autowired
    ProductsRepository productsRepository;

    public List<ProductCategory> findAll() throws IOException {

        //log.info(productsRepository.findByproductCategory(1L).toString());
        log.info(accessionToken.generateAccessionToken());

        return productsCategoryRepository.findAll();
    }


    public ProductCategory fetchProductCategory(String productCategoryName){

        Supplier<NullPointerException> s = () -> new NullPointerException("No ProductCategory found");
        return productsCategoryRepository
                .findByName(productCategoryName)
                .orElseThrow(s);
    }

}
