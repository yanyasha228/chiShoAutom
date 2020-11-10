package com.chiShoAutom.Services.ParseModelsServices;

import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.Models.ParseModels.ParseShop;

import java.util.List;
import java.util.Optional;

public interface ParseProductService {

    Optional<ParseProduct> findById(Long id);

    Optional<ParseProduct> findByUrl(String url);

    List<ParseProduct> findAll();

    List<ParseProduct> findAllByParseShop(ParseShop parseShop);

    ParseProduct save(ParseProduct parseProduct);

    List<ParseProduct> saveAll(List<ParseProduct> parseProducts);

}
