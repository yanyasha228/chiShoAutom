package com.chiShoAutom.SyncUtils;

import com.chiShoAutom.Models.Product;

import java.util.List;

public interface ProductsPromXmlSynchronizer {

    List<Product> syncProductsWithPromXml(String syncUrl);
}
