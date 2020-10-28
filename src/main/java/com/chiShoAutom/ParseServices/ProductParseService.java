package com.chiShoAutom.ParseServices;

import com.chiShoAutom.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductParseService {

     Optional<Product> getProduct(String productUrl);

     List<Product> getProducts(List<String> productUrls);

}
