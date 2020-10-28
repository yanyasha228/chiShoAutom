package com.chiShoAutom.ParseServices;

import com.chiShoAutom.Models.Product;
import com.chiShoAutom.ParsUtils.CssQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoyalProductParseServiceImpl implements ProductParseService {


    @Autowired
    private ParseServiceUtils parseServiceUtils;
    public static String AVAILABILITY_CSS_QUERY = "#cart-form > div.add2cart.mainPrice > span.price.nowrap.not-aviable";
    public static String AVAILABILITY_2_CSS_QUERY = "#cart-form > div.add2cart.mainPrice > div.out-of-stock";
    public static String NAME_CSS_QUERY = "#cart-form > div.product-name > h1";
    public static String PRICE_CSS_QUERY = "#cart-form > div.add2cart.mainPrice > span.price.nowrap";
    public static String PICTURES_CSS_QUERY = "body > div.main-block > div > div:nth-child(3) > div > article > div.product-image > div";
    public static String CHARACTERISTIC_TABLE_CSS_QUERY = "#product-features";
    public static String VENDOR_CODE = "#cart-form > span";
    ////!!!!!
    public static String DESCRIPTION_CSS_QUERY = "#tabs-description > p:nth-child(2)";



    @Autowired
    private CssQueryParser cssQueryParser;

    @Override
    public Optional<Product> getProduct(String productUrl) {
        return Optional.empty();
    }

    @Override
    public List<Product> getProducts(List<String> productUrls) {
        return null;
    }
}
