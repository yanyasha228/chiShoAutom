package com.chiShoAutom.ParseServices;

import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.Models.Product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductParseService {

     Optional<ParseProduct> getProduct(String productUrl) throws IOException;

     List<ParseProduct> getProducts(List<String> productUrls) throws IOException;

}
