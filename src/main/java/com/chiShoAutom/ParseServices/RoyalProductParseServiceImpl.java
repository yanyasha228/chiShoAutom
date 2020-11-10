package com.chiShoAutom.ParseServices;

import com.chiShoAutom.Models.ModelEnums.Currency;
import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.Models.ParseModels.ParseProductCategory;
import com.chiShoAutom.Models.ParseModels.ParseShop;
import com.chiShoAutom.ParsUtils.CssQueryParser;
import com.chiShoAutom.Services.ParseModelsServices.ParseProductCategoryService;
import com.chiShoAutom.Services.ParseModelsServices.ParseProductService;
import com.chiShoAutom.Services.ParseModelsServices.ParseShopService;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoyalProductParseServiceImpl implements ProductParseService {


    public static final String NAME_CSS_QUERY = "#cart-form > div.product-name > h1";
    public static final String AVAILABILITY_FLAG_CSS_QUERY = "#cart-form > div.add2cart.mainPrice > span.price.nowrap.not-aviable";
    public static final String PRICE_CSS_QUERY = "#cart-form > div.add2cart.mainPrice > span.price.nowrap";
    public static final String IMAGES_CSS_QUERY = "body > div.main-block > div > div:nth-child(3) > div > article > div.product-image > div";
    public static final String VENDOR_CODE_CSS_QUERY = "#cart-form > span";
    public static final String PARAMS_TABLE_CSS_QUERY = "#product-features > tbody";
    public static final String DESCRIPTION_CSS_QUERY = "#tabs-description";
    public static final String CATEGORIES_CSS_QUERY = "body > div.main-block > div > div:nth-child(3) > div > div.breadcrumbs.row > ul";

    public static String COUNTRY_OF_ORIGIN_PARAM = "Страна регистрации бренда";
    public static String VENDOR_PARAM = "Бренд";
    public static String COUNTRY_PARAM = "Страна-производитель товара";


    public static final String GOOD_TOYS_URL = "https://goodtoys.com.ua/";
    public static final String ROYAL_TOYS_URL = "https://royaltoys.com.ua/";

    public static String VIDEOS_DIV = "#overview > div.product-video";
    ////!!!!!

    @Autowired
    private ParseServiceUtils parseServiceUtils;

    @Autowired
    private ParseProductCategoryService parseProductCategoryService;

    @Autowired
    private ParseProductService parseProductService;

    @Autowired
    private CssQueryParser cssQueryParser;

    @Autowired
    private ParseShopService parseShopService;

    private ParseShop getOrCreateParseShop() {

        Optional<ParseShop> royalParseShop = parseShopService.findByShopUrl(GOOD_TOYS_URL);

        if (royalParseShop.isPresent()) return royalParseShop.get();

        ParseShop parseShop = new ParseShop();

        parseShop.setShopUrl(GOOD_TOYS_URL);

        parseShop.setWholeShopUrl(ROYAL_TOYS_URL);

        return parseShopService.save(parseShop);

    }


    @Transactional
    @Override
    public Optional<ParseProduct> getProduct(String productUrl) throws IOException {

        ParseShop royalParseShop = getOrCreateParseShop();

        Optional<Document> pageDoc = cssQueryParser.getDocument(productUrl);

        if (pageDoc.isPresent()) {

            ParseProduct parseProduct = parseProductService.findByUrl(productUrl).orElse(new ParseProduct());

            parseProduct.setVendorCode(getVendorCode(pageDoc.get()));

            parseProduct.setUrl(productUrl);

            parseProduct.setName(getName(pageDoc.get(), parseProduct.getVendorCode()));

            parseProduct.getVideos().addAll(Objects.requireNonNull(getVideos(pageDoc.get())));

            parseProduct.getParams().putAll(Objects.requireNonNull(getParams(pageDoc.get())));

            parseProduct.setDescription(getDescription(pageDoc.get()));

            parseProduct.setPrice(getPrice(pageDoc.get()));

            parseProduct.setCurrency(Currency.UAH);

            parseProduct.getPictures().addAll(getImages(pageDoc.get()));

            parseProduct.setVendor(getVendor(parseProduct.getParams()));

            parseProduct.setCountry(getCountry(parseProduct.getParams()));

            parseProduct.setCountryOfOrigin(getCountryOfOrigin(parseProduct.getParams()));

            parseProduct.setAvailability(checkAvailability(pageDoc.get()));

            parseProduct.setParseProductCategory(getCategories(pageDoc.get(), royalParseShop));

            parseProduct.setParseShop(royalParseShop);

            parseProductService.save(parseProduct);

            royalParseShop.getParseProducts().add(parseProduct);

            parseShopService.save(royalParseShop);

            return Optional.of(parseProduct);

        }

        return Optional.empty();

    }

    @Override
    public List<ParseProduct> getProducts(List<String> productUrls) throws IOException {

        for (String productUrl : productUrls) {

        }

        return null;

    }

    private ParseProductCategory getCategories(Document document, ParseShop parseShop) {

        Element categoryBlockElem = document.selectFirst(CATEGORIES_CSS_QUERY);

        if (Objects.nonNull(categoryBlockElem)) {

            Elements categoriesElems = categoryBlockElem.select("li");

            if (Objects.nonNull(categoriesElems) && !categoriesElems.isEmpty()) {

                List<Element> validCategories = categoriesElems.subList(1, categoriesElems.size() - 1);

                List<ParseProductCategory> categories = validCategories.stream().map(element -> {
                    ParseProductCategory parseProductCategory = new ParseProductCategory();

                    parseProductCategory.setName(element.text().replaceAll("\\|", "").trim());
                    parseProductCategory.setParseShop(parseShop);

                    Element linkEl = element.selectFirst("a");

                    if (Objects.nonNull(linkEl)) parseProductCategory.setCategoryUrl(linkEl.attr("abs:href"));

                    return parseProductCategory;

                }).collect(Collectors.toList());

                List<ParseProductCategory> catPersisted = new ArrayList<>();

                for (ParseProductCategory categoryFromPage : categories) {

                    String catName = categoryFromPage.getName();

                    Optional<ParseProductCategory> catOpt = parseProductCategoryService.findByName(catName);

                    if (catOpt.isPresent()) {

                        catPersisted.add(catOpt.get());

                    } else {
                        catPersisted.add(parseProductCategoryService.save(categoryFromPage));
                    }

                }

                ListIterator<ParseProductCategory> iteratorCat = catPersisted.listIterator();


                for (int it = 0; it < catPersisted.size(); it++) {
                    if (it == 0) continue;
                    catPersisted.get(it).setParentCategory(catPersisted.get(it - 1));
                }

                catPersisted = parseProductCategoryService.saveAll(catPersisted);

                return catPersisted.get(catPersisted.size() - 1);
            }
        }

        return null;
    }

    private boolean checkAvailability(Document document) {

        Element notAvailableProductEl = document.selectFirst(AVAILABILITY_FLAG_CSS_QUERY);

        return Objects.isNull(notAvailableProductEl);

    }

    private String getVendor(Map<String, String> params) {

        if (params.containsKey(VENDOR_PARAM)) {
            return params.get(VENDOR_PARAM);
        }

        return "";

    }

    private String getCountry(Map<String, String> params) {
        if (params.containsKey(COUNTRY_PARAM)) {
            return params.get(COUNTRY_PARAM);
        }
        return "";
    }

    private String getCountryOfOrigin(Map<String, String> params) {
        if (params.containsKey(COUNTRY_OF_ORIGIN_PARAM)) {
            return params.get(COUNTRY_OF_ORIGIN_PARAM);
        }
        return "";
    }

    private List<String> getImages(Document document) {

        Element element = document.selectFirst(IMAGES_CSS_QUERY);
        if (Objects.nonNull(element)) {
            Elements imagesElems = element.select("a");
            if (!imagesElems.isEmpty()) {
                List<String> imagesUrls = new ArrayList<>();

                for (Element el : imagesElems) {
                    imagesUrls.add(el.attr("abs:href"));
                }
                return imagesUrls;
            }
        }
        return Collections.emptyList();

    }

    private float getPrice(Document doc) {
        Element priceEl = doc.selectFirst(PRICE_CSS_QUERY);

        if (Objects.nonNull(priceEl)) {
            String strToVal = priceEl.text();
            if (Objects.nonNull(strToVal)) {
                strToVal = ParseServiceUtils.removeTrashCharsFromPriceString(strToVal);
                try {
                    Float.parseFloat(strToVal);
                    return Float.parseFloat(strToVal);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
        return 0;
    }

    private String getDescription(Document document) {

        Element descriptionEl = document.selectFirst(DESCRIPTION_CSS_QUERY);

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

        Optional<String> vendorCodeOpt = cssQueryParser.getFirstElementValue(document, VENDOR_CODE_CSS_QUERY);

        if (vendorCodeOpt.isPresent()) {

            String validVendorCode = vendorCodeOpt.get().replaceAll(strToRemove, "");
            validVendorCode = validVendorCode.trim();
            return validVendorCode;

        }

        return "";

    }

    private List<String> getVideos(Document document) {

        Element videoElem = document.selectFirst(VIDEOS_DIV);
        if (Objects.nonNull(videoElem)) {
            Elements videosFramesEls = videoElem.select("iframe");

            if (Objects.nonNull(videosFramesEls) && !videosFramesEls.isEmpty()) {

                List<String> videos = new ArrayList<>();

                for (Element videoEl : videosFramesEls) {

                    Optional<Attribute> srcAttValue = videoEl.attributes().asList().stream().filter(attribute -> attribute.getValue().contains("www.youtube.com")).findFirst();

                    srcAttValue.ifPresent(attribute -> videos.add(attribute.getValue()));

                }

                return videos;
            }
        }
        return Collections.emptyList();
    }

    private Map<String, String> getParams(Document document) {

        Map<String, String> paramsMap = new HashMap<>();

        Element paramTableElem = document.selectFirst(PARAMS_TABLE_CSS_QUERY);
        if (Objects.nonNull(paramTableElem)) {
            Elements paramsTableTRElems = paramTableElem.select("tr");
            if (Objects.nonNull(paramsTableTRElems) && !paramsTableTRElems.isEmpty()) {
                for (Element el : paramsTableTRElems) {
                    Elements tdElems = el.select("td");
                    paramsMap.put(tdElems.get(0).text().trim(), tdElems.get(1).text().trim());
                }

                return paramsMap;
            }
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
