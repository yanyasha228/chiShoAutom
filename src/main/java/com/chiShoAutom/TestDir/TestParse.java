package com.chiShoAutom.TestDir;

import com.chiShoAutom.Models.Dto.XmlDto.ParseHelpXmlDto.*;
import com.chiShoAutom.ParsUtils.CssQueryParser;
import org.apache.catalina.connector.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TestParse {

    @Autowired
    private CssQueryParser cssQueryParser;

    private static final Logger log = LoggerFactory.getLogger(TestParse.class);

    public TestParse() {
    }

    private HashMap<String, String> urlTitleMap = new HashMap<>();

    public String getOneEllem(String xpathStr) throws IOException {

        Document doc = Jsoup.connect("https://aquapolis.ua/slinoj-klapan-dlja-parogeneratora-5-var.html").get();
        Elements ells = doc.select(xpathStr);
        Element ell = ells.first();
        String ellTxt = ell.text();
        Double inter = Double.parseDouble(ellTxt.replaceAll(" ", "").replaceAll("грн", "").replaceAll(",", "."));

//        TestParseConverter testParseConverter = new TestParseConverter() {
//            @Override
//            public void testConvert() {
//                System.out.println("testConvert");
//            }
//        };
//
//        testParseConverter.allCon();
        return ellTxt;
    }

    //    @Async
    public void checkProductAvailability(String uri) throws IOException {

        String checkCssQuery = "#cart-form > div.add2cart.mainPrice > span.price.nowrap.not-aviable";
        ProductUrlsMap productUrlsMap = new ProductUrlsMap();

        try {
            JAXBContext context = JAXBContext.newInstance(ProductUrlsMap.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader f1 = new FileReader(uri);
            productUrlsMap = (ProductUrlsMap) unmarshaller.unmarshal(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProductUrlsMap productUrlsMapAvailable = new ProductUrlsMap();

        int totalAv = 0;
        int total = 0;

        for (UrlDto urs :
                productUrlsMap.getProdUrls()) {
            total++;
//            System.out.println("UrlInfo: " + urs.getUrlInfo());
//            System.out.println("UrlMain: " + urs.getUrlMain());
//            System.out.println("Begin---" + LocalDateTime.now() + "---");
            Optional<String> valueOpt = cssQueryParser.getFirstElementValue(urs.getUrlInfo(), checkCssQuery);
//            System.out.println("End---" + LocalDateTime.now() + "---");

            if (valueOpt.isEmpty()) {
                productUrlsMapAvailable.getProdUrls().add(urs);
                totalAv++;
//                System.out.println("-------OK--------Current:" + total + "-------Available:" + totalAv);

            }
        }

        try {
            JAXBContext context1 = JAXBContext.newInstance(ProductUrlsMap.class);
            Marshaller marshaller = context1.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(productUrlsMapAvailable, new File("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemapMatchedRUMapAvailable" + LocalDateTime.now().toString() + ".xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void parseTestEx(String urlToBuildSiteMapIn) {

        try {
            urlTitleMap.clear();

            String urlToBuildSiteMap = urlToBuildSiteMapIn;
            Document doc = Jsoup.connect(urlToBuildSiteMap).get();

            //  Page Title
            String title = doc.title();
            //System.out.println("title: " + title);

            //  Links in page
            Elements links = doc.select("a[href*=aquapolis.ua]");
            List<String> url_array = new ArrayList<String>();
            int i = 0;
            url_array.add(urlToBuildSiteMap);
            String root = urlToBuildSiteMap;
            urlTitleMap.put(urlToBuildSiteMap, title);
            Iterator<String> keySetIterator = urlTitleMap.keySet().iterator();
            while ((i <= urlTitleMap.size())) {
                try {
                    urlToBuildSiteMap = url_array.get(i).toString();
                    doc = Jsoup.connect(urlToBuildSiteMap).get();
                    title = doc.title();
                    links = doc.select("a[href*=aquapolis.ua]");

                    for (Element link : links) {

                        String res = urlTitleMap.putIfAbsent(link.attr("href"), link.text());
                        if (res == null) {
                            if (link.attr("href").contains("https:")) {
                                url_array.add(link.attr("href"));
                            } else {
                                url_array.add(("https:" + link.attr("href")));
                            }
                            System.out.println("\nURL: " + link.attr("href"));
                            System.out.println("CONTENT: " + link.text());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\n" + e);
                }

                log.info(url_array.get(i).toString());
                i++;

            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public void testProdId() {
        String stUrl = "https://www.usilok.com.ua/index.php?route=product/product&product_id=%s";
        for (int c = 100000; c <= 200000; c += 1000) {
            for (int d = 1; d < 10; d++) {
                for (int i = 1; i <= 15; i++) {
                    try {
                        int genId = c + (100 * d) + i;
                        String stUrlFS = String.format(stUrl, genId);
                        URL url = new URL(stUrlFS);

                        HttpURLConnection con = null;
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.setConnectTimeout(15000);
                        con.setReadTimeout(15000);

                        if (con.getResponseCode() != Response.SC_NOT_FOUND)
                            log.info(stUrlFS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    public void checkXmlProductsAllLink() {
        MapUrls mapUrls1 = new MapUrls();
//        MapUrls mapUrls2 = new MapUrls();

        try {
            JAXBContext context1 = JAXBContext.newInstance(MapUrls.class);
            Unmarshaller unmarshaller = context1.createUnmarshaller();

            FileReader f1 = new FileReader("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemapMatched.xml");
//            FileReader f2 = new FileReader("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemapMatchedR.xml");

            mapUrls1 = (MapUrls) unmarshaller.unmarshal(f1);
//            mapUrls2 = (MapUrls) unmarshaller.unmarshal(f2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> matchedUrls = new ArrayList<>();
        HashSet<String> notMatchedSet = new HashSet<>();
        List<String> notMatched = new ArrayList<>();
        List<String> goodToysUrls = mapUrls1.getUrls();
//        List<String> goodToysUrls1 = mapUrls2.getUrls();


        int i = 0;
        for (String uG : goodToysUrls) {
            i++;
            System.out.println(i);
            for (String uR : goodToysUrls) {
                if (uG.equalsIgnoreCase(uR)) ;
            }


        }

        MapUrls matchedMapUrls = new MapUrls();
        matchedMapUrls.setUrls(matchedUrls);

        try {
            JAXBContext context2 = JAXBContext.newInstance(MapUrls.class);
            Marshaller marshaller = context2.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(matchedMapUrls, new File("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemapMatchedTM.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void checkXmlProducts() {
        MapUrls mapUrls1 = new MapUrls();
        MapUrls mapUrls2 = new MapUrls();

        try {
            JAXBContext context1 = JAXBContext.newInstance(MapUrls.class);
            Unmarshaller unmarshaller = context1.createUnmarshaller();

            FileReader f1 = new FileReader("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemap.xml");
            FileReader f2 = new FileReader("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemapR.xml");

            mapUrls1 = (MapUrls) unmarshaller.unmarshal(f1);
            mapUrls2 = (MapUrls) unmarshaller.unmarshal(f2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashSet<String> matchedUrlsSet = new HashSet<>();
        Map<String, String> urlsMap = new HashMap<>();
        List<String> goodToysUrls = mapUrls1.getUrls();
        List<String> royalToysUrls = mapUrls2.getUrls();


        int i = 0;
        for (String uG : royalToysUrls) {

            i++;
            System.out.println(i);

            List<String> uGSp = Arrays.asList(uG.split("/"));
            String uGUrlCode = uGSp.get(uGSp.size() - 1);
            for (String uR : goodToysUrls) {
                List<String> uRSp = Arrays.asList(uR.split("/"));
                String uRUrlCode = uRSp.get(uRSp.size() - 1);

                if (uGUrlCode.equalsIgnoreCase(uRUrlCode)) {

                    matchedUrlsSet.add(uR);
                    if (!urlsMap.containsKey(uR)) urlsMap.put(uR, uG);

                }
            }


        }

        List<UrlDto> urlDtos = new ArrayList<>();

        for (Map.Entry<String, String> item : urlsMap.entrySet()) {
            UrlDto nUrlDto = new UrlDto();
            nUrlDto.setUrlInfo(item.getKey());
            nUrlDto.setUrlMain(item.getValue());
            urlDtos.add(nUrlDto);
        }

        ProductUrlsMap productUrlsMap = new ProductUrlsMap();

        productUrlsMap.setProdUrls(urlDtos);

        List<String> matchedUrls = new ArrayList<>(matchedUrlsSet);

//        MapUrls matchedMapUrls = new MapUrls();
//        matchedMapUrls.setUrls(matchedUrls);

        try {
            JAXBContext context2 = JAXBContext.newInstance(ProductUrlsMap.class);
            Marshaller marshaller = context2.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(productUrlsMap, new File("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemapMatchedRUMap.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int l = 0;
//        try {
//            JAXBContext context2 = JAXBContext.newInstance(MapUrls.class);
//            Marshaller marshaller = context2.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            marshaller.marshal(matchedMapUrls, new File("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemapMatchedRU.xml"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public void filterXmlProduct() {

        SiteMap sM1 = new SiteMap();
        SiteMap sM2 = new SiteMap();
//        SiteMap sM3 = new SiteMap();
//        SiteMap sM4 = new SiteMap();
//        SiteMap sM5 = new SiteMap();

        try {
            FileReader fl1;
            FileReader fl2;
//            FileReader fl3;
//            FileReader fl4;
//            FileReader fl5;

            JAXBContext context = JAXBContext.newInstance(SiteMap.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            fl1 = new FileReader("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemap-shop-1R.xml");
            fl2 = new FileReader("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemap-shop-2R.xml");
//            fl3 = new FileReader("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemap-shop-3.xml");
//            fl4 = new FileReader("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemap-shop-4.xml");
//            fl5 = new FileReader("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemap-shop-5.xml");

            sM1 = (SiteMap) unmarshaller.unmarshal(fl1);
            sM2 = (SiteMap) unmarshaller.unmarshal(fl2);
//            sM3 = (SiteMap) unmarshaller.unmarshal(fl3);
//            sM4 = (SiteMap) unmarshaller.unmarshal(fl4);
//            sM5 = (SiteMap) unmarshaller.unmarshal(fl5);

        } catch (FileNotFoundException | JAXBException e) {
            e.printStackTrace();
        }


        List<SiteMapUrlDto> siteMapUrls1 = sM1.getUrls();
        List<SiteMapUrlDto> siteMapUrls2 = sM2.getUrls();
//        List<SiteMapUrl> siteMapUrls3 = sM3.getUrls();
//        List<SiteMapUrl> siteMapUrls4 = sM4.getUrls();
//        List<SiteMapUrl> siteMapUrls5 = sM5.getUrls();

        List<SiteMapUrlDto> siteMapUrlsFiltered1 = siteMapUrls1.stream().filter(siteMapUrl ->
                siteMapUrl.getLoc().contains("royaltoys.com.ua/ua/product") ||
                        siteMapUrl.getLoc().contains("royaltoys.com.ua/product") ||
                        siteMapUrl.getLoc().contains("royaltoys.com.ua/ru/product")).collect(Collectors.toList());

        List<SiteMapUrlDto> siteMapUrlsFiltered2 = siteMapUrls2.stream().filter(siteMapUrl ->
                siteMapUrl.getLoc().contains("royaltoys.com.ua/ua/product") ||
                        siteMapUrl.getLoc().contains("royaltoys.com.ua/product") ||
                        siteMapUrl.getLoc().contains("royaltoys.com.ua/ru/product")).collect(Collectors.toList());

//        List<SiteMapUrl> siteMapUrlsFiltered3 = siteMapUrls3.stream().filter(siteMapUrl ->
//                siteMapUrl.getLoc().contains("goodtoys.com.ua/ua/product") ||
//                        siteMapUrl.getLoc().contains("goodtoys.com.ua/product") ||
//                        siteMapUrl.getLoc().contains("goodtoys.com.ua/ru/product")).collect(Collectors.toList());
//
//        List<SiteMapUrl> siteMapUrlsFiltered4 = siteMapUrls4.stream().filter(siteMapUrl ->
//                siteMapUrl.getLoc().contains("goodtoys.com.ua/ua/product") ||
//                        siteMapUrl.getLoc().contains("goodtoys.com.ua/product") ||
//                        siteMapUrl.getLoc().contains("goodtoys.com.ua/ru/product")).collect(Collectors.toList());
//
//        List<SiteMapUrl> siteMapUrlsFiltered5 = siteMapUrls5.stream().filter(siteMapUrl ->
//                siteMapUrl.getLoc().contains("goodtoys.com.ua/ua/product") ||
//                        siteMapUrl.getLoc().contains("goodtoys.com.ua/product") ||
//                        siteMapUrl.getLoc().contains("goodtoys.com.ua/ru/product")).collect(Collectors.toList());

        List<SiteMapUrlDto> allUrls = new ArrayList<>();
        allUrls.addAll(siteMapUrlsFiltered1);
        allUrls.addAll(siteMapUrlsFiltered2);
//        allUrls.addAll(siteMapUrlsFiltered3);
//        allUrls.addAll(siteMapUrlsFiltered4);
//        allUrls.addAll(siteMapUrlsFiltered5);

        List<String> urls = new ArrayList<>();

        for (SiteMapUrlDto su : allUrls
        ) {
            urls.add(su.getLoc());
        }

        List<String> validUrls = urls.stream().map(str -> {
            return str.replaceAll("/ua", "");
        }).collect(Collectors.toList());

        MapUrls mapUrls = new MapUrls();
        mapUrls.setUrls(validUrls);

        try {
            JAXBContext context2 = JAXBContext.newInstance(MapUrls.class);
            Marshaller marshaller = context2.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(mapUrls, new File("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/sitemapR.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }


//        HashSet<String> uSet = new HashSet<>(urls);

//        List<SiteMapUrl> siteMapUrlsFiltered = siteMapUrls.stream().filter(siteMapUrl ->
//                siteMapUrl.getLoc().contains("goodtoys.com.ua/ua/product") ||
//                        siteMapUrl.getLoc().contains("goodtoys.com.ua/product") ||
//                        siteMapUrl.getLoc().contains("goodtoys.com.ua/ru/product")).map(url -> {
//            url.setLoc(url.getLoc().replaceAll("/ua", ""));
//            return url;
//        }).collect(Collectors.toList());

//        sM.setUrls(siteMapUrlsFiltered);

        int i = 0;
    }

    public void parseTestExT() {

        String urlToBuildSiteMapT = "https://afk.ua";
        try {
            urlTitleMap.clear();


            String urlToBuildSiteMap = urlToBuildSiteMapT;
            Document doc = Jsoup.connect(urlToBuildSiteMap).get();

            //  Page Title
            String title = doc.title();
            //System.out.println("title: " + title);

            //  Links in page
            Elements links = doc.select("a[href*=https://afk.ua]");
            List url_array = new ArrayList();
            int i = 0;
            url_array.add(urlToBuildSiteMap);
            String root = urlToBuildSiteMap;
            urlTitleMap.put(urlToBuildSiteMap, title);
            Iterator<String> keySetIterator = urlTitleMap.keySet().iterator();
            while ((i <= urlTitleMap.size())) {
                try {
                    urlToBuildSiteMap = url_array.get(i).toString();
                    doc = Jsoup.connect(urlToBuildSiteMap).get();
                    title = doc.title();
                    links = doc.select("a[href*=afk.ua]");

                    for (Element link : links) {

                        String res = urlTitleMap.putIfAbsent(link.attr("href"), link.text());
                        if (res == null) {
                            url_array.add(link.attr("href"));
                            System.out.println("\nURL: " + link.attr("href"));
                            System.out.println("CONTENT: " + link.text());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\n" + e);
                }

                log.info(url_array.get(i).toString());
                i++;

            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getUrlTitleMap() {
        return urlTitleMap;
    }
}
