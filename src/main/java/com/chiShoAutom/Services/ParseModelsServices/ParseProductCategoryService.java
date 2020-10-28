package com.chiShoAutom.Services.ParseModelsServices;

import com.chiShoAutom.Models.ParseModels.ParseProductCategory;

import java.util.List;
import java.util.Optional;

public interface ParseProductCategoryService {

    Optional<ParseProductCategory> findById(Long id);

    Optional<ParseProductCategory> findByName(String name);

    List<ParseProductCategory> findAll();

    ParseProductCategory save(ParseProductCategory parseProductCategory);

}
