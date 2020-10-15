package com.chiShoAutom.SyncUtils;


import com.chiShoAutom.HelpUtils.Mappers.ModelMappers.XmlPromDtoMappers.ProductPromXmlDtoToProductMapper;
import com.chiShoAutom.Models.Dto.XmlDto.PromXmlDto.CatalogXmlPromDto;
import com.chiShoAutom.Models.Dto.XmlDto.PromXmlDto.CategoryPromXmlDto;
import com.chiShoAutom.Models.Dto.XmlDto.PromXmlDto.ProductXmlPromDto;
import com.chiShoAutom.Models.Group;
import com.chiShoAutom.Models.Product;
import com.chiShoAutom.RestServices.PromRestServices.XmlServices.ProductCatalogXmlService;
import com.chiShoAutom.Services.GroupService;
import com.chiShoAutom.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductsPromXmlSynchronizerImpl implements ProductsPromXmlSynchronizer {

    @Autowired
    private ProductPromXmlDtoToProductMapper productPromXmlDtoToProductMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private GroupService groupService;


    @Autowired
    private ProductCatalogXmlService productCatalogXmlService;


    public List<Product> syncProductsWithPromXml(String syncUrl) {

        CatalogXmlPromDto catalogPromDto = productCatalogXmlService.getCatalog(syncUrl).orElse(null);

        List<Group> groups = new ArrayList<>();
        for (CategoryPromXmlDto catPr : getMainCategories(catalogPromDto)) {
            Group nGroup = new Group();
            nGroup.setId(catPr.getId());
            nGroup.setName(catPr.getName());
            groups.add(nGroup);
        }
        groupService.save(groups);

        setOnlyMainCategoriesInProducts(catalogPromDto);

        List<Product> productList = catalogPromDto.getShopXmlPromDto().getOffersList().stream()
                .map(productXmlPromDto -> productPromXmlDtoToProductMapper.toEntity(productXmlPromDto))
                .collect(Collectors.toList());


        return productService.save(productList);

    }

    private void setOnlyMainCategoriesInProducts(CatalogXmlPromDto catalogXmlPromDto) {

        List<CategoryPromXmlDto> mainCats = getMainCategories(catalogXmlPromDto);

        Map<Integer, CategoryPromXmlDto> mainCategoriesMap = new HashMap<>();

        Map<Integer, CategoryPromXmlDto> categoriesMap = new HashMap<>();

        for (CategoryPromXmlDto mCat : mainCats) {
            mainCategoriesMap.put(mCat.getId(), mCat);
        }

        for (CategoryPromXmlDto cat : catalogXmlPromDto.getShopXmlPromDto().getCategories()) {
            if (cat.getParentId() != 0) {
                categoriesMap.put(cat.getId(), mainCategoriesMap.get(cat.getParentId()));
            } else {
                categoriesMap.put(cat.getId(), cat);
            }
        }

        for (ProductXmlPromDto prod : catalogXmlPromDto.getShopXmlPromDto().getOffersList()) {
            prod.setCategoryId(categoriesMap.get(prod.getCategoryId()).getId());
        }


    }

    private List<CategoryPromXmlDto> getMainCategories(CatalogXmlPromDto catalogXmlPromDto) {

        return catalogXmlPromDto.getShopXmlPromDto().getCategories()
                .stream()
                .filter(categoryPromXmlDto -> categoryPromXmlDto.getParentId() == 0)
                .collect(Collectors.toList());
    }

    private List<CategoryPromXmlDto> getParentCategories(CatalogXmlPromDto catalogXmlPromDto) {

        return catalogXmlPromDto.getShopXmlPromDto().getCategories()
                .stream()
                .filter(categoryPromXmlDto -> categoryPromXmlDto.getParentId() != 0)
                .collect(Collectors.toList());
    }


}
