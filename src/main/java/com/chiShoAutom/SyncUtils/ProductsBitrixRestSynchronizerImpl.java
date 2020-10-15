package com.chiShoAutom.SyncUtils;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.Models.Product;
import com.chiShoAutom.RestServices.BitrixRestServices.ProductRestBitrixService;
import com.chiShoAutom.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductsBitrixRestSynchronizerImpl implements ProductsBitrixRestSynchronizer {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRestBitrixService productRestBitrixService;

    @Override
    public Product synchronizeOne(Product productForSync) throws ImpossibleRestUpdateEntityException {

        return productRestBitrixService.update(productForSync).orElse(null);

    }

    @Override
    public List<Product> synchronizeProducts(List<Product> productList) throws ImpossibleRestUpdateEntityException {
        return productRestBitrixService.updateAll(productList);
    }

    @Override
    public List<Product> synchronizeAll() throws ImpossibleRestUpdateEntityException {

        return productRestBitrixService.updateAll(productService.findAll());

    }

}
