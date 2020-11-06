package com.chiShoAutom.ParseServices;

import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.Models.Product;
import com.chiShoAutom.ParsUtils.CssQueryParser;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
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
    public static String PARAMS_TABLE = "#product-features > tbody";
    public static String DESCRIPTION = "#tabs-description";

    public static String VIDEOS_DIV = "#overview > div.product-video";
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

            parseProduct.setName(getName(pageDoc.get(), parseProduct.getVendorCode()));

            parseProduct.getVideos().addAll(Objects.requireNonNull(getVideos(pageDoc.get())));

            parseProduct.getParams().putAll(Objects.requireNonNull(getParams(pageDoc.get())));

            parseProduct.setDescription(getDescription(pageDoc.get()));

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

    private String getDescription(Document document) {

        Element descriptionEl = document.selectFirst(DESCRIPTION);

        if (Objects.nonNull(descriptionEl)) {

            Elements eop = descriptionEl.select("a");

            for (Element elNV : eop) {
                elNV.attr("href", "#");
            }

            return "<![CDATA[" + descriptionEl.html() + "]]>";

        }
        return "";
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

    private List<String> getVideos(Document document) {

        Elements videosFramesEls = document.select("#overview > div.product-video").first().select("iframe");

        if (!videosFramesEls.isEmpty()) {

            List<String> videos = new ArrayList<>();

            for (Element videoEl : videosFramesEls) {

                Optional<Attribute> srcAttValue = videoEl.attributes().asList().stream().filter(attribute -> attribute.getValue().contains("www.youtube.com")).findFirst();

                srcAttValue.ifPresent(attribute -> videos.add(attribute.getValue()));

            }

            return videos;
        }
        return Collections.emptyList();
    }

    private Map<String, String> getParams(Document document) {

        Map<String, String> paramsMap = new HashMap<>();

        Elements paramsTableTRElems = document.select(PARAMS_TABLE).first().select("tr");
        if (!paramsTableTRElems.isEmpty()) {
            for (Element el : paramsTableTRElems) {
                Elements tdElems = el.select("td");
                paramsMap.put(tdElems.get(0).text(), tdElems.get(1).text());
            }

            return paramsMap;
        }

        return null;
    }

    private String getName(Document document, String vendorCode) throws IOException {

        String[] vendorCodeSplited = vendorCode.split(" ");

        Optional<String> nameOpt = cssQueryParser.getFirstElementValue(document, NAME_CSS_QUERY);

        if (nameOpt.isPresent()) {

            String name = nameOpt.get().replaceAll(vendorCode, "");

            String[] splitedName = name.split(" ");

            List<String> stringArr = Arrays.stream(splitedName).filter(s -> {

                for (String str : vendorCodeSplited) {
                    if (str.contains(s)) return false;
                }

                return true;

            }).collect(Collectors.toList());

            return String.join(" ", stringArr);
        }

        return "";
    }


}
