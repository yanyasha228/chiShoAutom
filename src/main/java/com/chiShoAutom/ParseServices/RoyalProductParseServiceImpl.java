package com.chiShoAutom.ParseServices;

import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.Models.Product;
import com.chiShoAutom.ParsUtils.CssQueryParser;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<ParseProduct> getProduct(String productUrl) throws IOException {

        Optional<Document> pageDoc = cssQueryParser.getDocument(productUrl);

        if (pageDoc.isPresent()) {

            ParseProduct parseProduct = new ParseProduct();

            parseProduct.setVendorCode(getVendorCode(pageDoc.get()));

            parseProduct.setUrl(productUrl);

            return Optional.of(parseProduct);

        }

        return Optional.empty();

    }

    @Override
    public List<ParseProduct> getProducts(List<String> productUrls) throws IOException {

        for (String productUrl : productUrls) {

            Optional<Document> pageDoc = cssQueryParser.getDocument(productUrl);

            if (pageDoc.isPresent()) {

                ParseProduct parseProduct = new ParseProduct();

                parseProduct.setVendorCode(getVendorCode(pageDoc.get()));

            }

        }

        return null;

    }

    private String getVendorCode(Document document) throws IOException {

        String strToRemove = "Артикул:";

        Optional<String> vendorCodeOpt = cssQueryParser.getFirstElementValue(document, VENDOR_CODE);

        if (vendorCodeOpt.isPresent()) {

            String validVendorCode = vendorCodeOpt.get().replaceAll(strToRemove, "");
            validVendorCode = validVendorCode.trim();
            return validVendorCode;

        }

        return "";
    }

    private String getName(Document document, String vendorCode) throws IOException {

        String[] vendorCodeSplited = vendorCode.split(" ");

        Optional<String> nameOpt = cssQueryParser.getFirstElementValue(document, NAME_CSS_QUERY);

        if(nameOpt.isPresent()){

            String name = nameOpt.get().replaceAll(vendorCode , "");

            String[] splitedName = name.split(" ");

            List<String> string = Arrays.stream(splitedName).filter(s -> {

                return false;

            }).collect(Collectors.toList());

            for (String strN :
                    splitedName) {
                for (String strV :
                        vendorCodeSplited) {

                }
            }
                    
        }

        return "";
    }
}
