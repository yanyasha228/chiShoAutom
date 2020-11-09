package com.chiShoAutom;

import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.ParsUtils.CssQueryParser;
import com.chiShoAutom.ParseServices.RoyalCategoryParseServiceImpl;
import com.chiShoAutom.ParseServices.RoyalProductParseServiceImpl;
import com.chiShoAutom.TestDir.TestParse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChiShoAutomAppTests {

    @Autowired
    private TestParse testParse;

    @Autowired
    private CssQueryParser cssQueryParser;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private RoyalCategoryParseServiceImpl royalCategoryParseService;

    @Autowired
    private RoyalProductParseServiceImpl royalProductParseService;

    @Test
    public void parseRoyalCategoryTest() throws IOException {

        Optional<ParseProduct> parseProduct = royalProductParseService.getProduct("https://goodtoys.com.ua/product/kukla-pups-bebi-born-bl013a/");
        int i = 0;

    }

    @Test
    public void contextLoads() throws IOException, InterruptedException {

//        List<ProductParamXmlPromDto> paramsDtos = new ArrayList<>();
//        Elements els = cssQueryParser.getElements("https://goodtoys.com.ua/product/kartina-po-nomeram-dary-leta-40-40sm-kho5564/", "#product-features > tbody");
//        Element elm = els.first();
//        Elements trEll = elm.children();
//
//        for (Element elIter : trEll
//        ) {
//            if (elIter.children().size() >= 2) {
//                Element paramNameEl = elIter.children().get(0);
//                Element paramValueEl = elIter.children().get(1);
//
////                String testT = paramNameEl.text();
////                String testT1 = paramNameEl.wholeText();
////                String testT2 = paramNameEl.ownText();
////                String testInner = paramNameEl.html();
////                String testT3 = paramValueEl.text();
////                String testT4 = paramValueEl.wholeText();
////                String testT5 = paramValueEl.ownText();
////                String testInner2 = paramValueEl.html();
////                int op = 1;
//
//                paramsDtos.add(new ProductParamXmlPromDto(paramNameEl.text(), paramValueEl.text()));
//
//            }
//        }
//
//        int i = 0;
        testParse.checkProductAvailability("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/frDisc/sitemapMatchedRUMapAvailable.xml");
        int i = 0;
//        testParse.checkProductAvailability("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/disc/sitemapMatchedRUMap-2.xml");
//        testParse.checkProductAvailability("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/disc/sitemapMatchedRUMap-3.xml");
//        testParse.checkProductAvailability("/home/yanyasha228/chiShoAutom/src/main/resources/smaps/disc/sitemapMatchedRUMap-4.xml");
//
//        executor.shutdown();
//        executor.getThreadPoolExecutor().awaitTermination(2, TimeUnit.HOURS);
//    }
    }

}
