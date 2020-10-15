package com.chiShoAutom.SyncUtils.EntityConnectors;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.Models.Product;
import com.chiShoAutom.RestServices.BitrixRestServices.ProductRestBitrixService;
import com.chiShoAutom.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BitrixProductEntityConnectorImpl implements BitrixProductEntityConnector {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRestBitrixService productRestBitrixService;

    @Override
    public List<Product> tieUnconnectedProductEntities() {

        List<Product> productListForTie = productService.findAll()
                .stream()
                .filter(product -> product.getBitrixId() == 0)
                .collect(Collectors.toList());
        List<Product> unconnectedProductsFromBitrix = productRestBitrixService.getAll();

        List<Product> productListForSave = productListForTie.stream().map(product -> {
            for (Product prodB : unconnectedProductsFromBitrix) {
                if (product.getId() == prodB.getId()) product.setBitrixId(prodB.getBitrixId());
            }
            return product;
        }).filter(product -> product.getBitrixId() != 0)
                .collect(Collectors.toList());

        try {
            productRestBitrixService.updateAll(productListForSave);
        } catch (ImpossibleRestUpdateEntityException e) {
            e.printStackTrace();
        }

        return productService.save(productListForSave);

    }
}
