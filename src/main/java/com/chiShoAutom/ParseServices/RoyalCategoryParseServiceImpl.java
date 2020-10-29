package com.chiShoAutom.ParseServices;

import com.chiShoAutom.Models.ParseModels.ParseProductCategory;
import com.chiShoAutom.ParsUtils.CssQueryParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoyalCategoryParseServiceImpl implements CategoryParseService {

    @Autowired
    private CssQueryParser cssQueryParser;


    public List<ParseProductCategory> parseAndSaveCategories() throws IOException {

        Elements elements = cssQueryParser.getElements("https://goodtoys.com.ua/sitemap/", "body > div.main-block > div > div:nth-child(3) > div > div.htmlmap > div > div > div:nth-child(1) > ul > li");

        List<ParseProductCategory> productCategories = new ArrayList<>();

        for (Element el : elements) {

            ParseProductCategory parentCategory = new ParseProductCategory();
            parentCategory.setCategoryUrl(el.selectFirst("a").attr("abs:href"));
            parentCategory.setName(el.selectFirst("a").ownText());

            productCategories.add(parentCategory);
            if (el.childrenSize() > 1) {

                Elements subList = el.selectFirst("ul").children();

                for (Element elSub : subList) {
                    ParseProductCategory parentCategorySub = new ParseProductCategory();
                    parentCategorySub.setCategoryUrl(elSub.selectFirst("a").attr("abs:href"));
                    parentCategorySub.setName(elSub.selectFirst("a").ownText());
                    parentCategorySub.setParentCategory(parentCategory);
                    productCategories.add(parentCategorySub);
                    if (elSub.childrenSize() > 1) {
                        Elements subSubList = elSub.selectFirst("ul").children();

                        for (Element elSubSub : subSubList) {
                            ParseProductCategory parentCategorySubSub = new ParseProductCategory();
                            parentCategorySubSub.setCategoryUrl(elSubSub.selectFirst("a").attr("abs:href"));
                            parentCategorySubSub.setName(elSubSub.selectFirst("a").ownText());
                            parentCategorySubSub.setParentCategory(parentCategorySub);
                            productCategories.add(parentCategorySubSub);

                            if (elSubSub.childrenSize() > 1) {
                                Elements subSubSubList = elSubSub.selectFirst("ul").children();

                                for (Element elSubSubSub : subSubSubList) {
                                    ParseProductCategory parentCategorySubSubSub = new ParseProductCategory();
                                    parentCategorySubSubSub.setCategoryUrl(elSubSubSub.selectFirst("a").attr("abs:href"));
                                    parentCategorySubSubSub.setName(elSubSubSub.selectFirst("a").ownText());
                                    parentCategorySubSubSub.setParentCategory(parentCategorySubSub);
                                    productCategories.add(parentCategorySubSubSub);

                                }
                            }


                        }

                    }
                }

            }
//            Elements elsNodes = el.children();
//            Elements fgf= el.c
//            Element ulElemFirstNode = el.select("ul").first();
//
//            parentCategory.setName(els.first().ownText());
//            parentCategory.setCategoryUrl(els.first().attr("abs:href"));
//
//            if(els.size()>0){
//                for (Element ulElement : ulElems){
//
//                }
//            }


        }
        int dich = 1;
        return null;
    }

}
