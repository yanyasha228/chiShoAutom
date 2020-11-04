package com.chiShoAutom.Services.ParseModelsServices;

import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.Models.ParseModels.ParseShop;

import java.util.List;
import java.util.Optional;

public interface ParseProductService {

    Optional<ParseProduct> findById(Long id);

    List<ParseProduct> findAll();

    List<ParseProduct> findAllByParseShop(ParseShop parseShop);

}
