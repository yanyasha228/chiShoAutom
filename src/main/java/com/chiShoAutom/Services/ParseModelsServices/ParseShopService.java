package com.chiShoAutom.Services.ParseModelsServices;

import com.chiShoAutom.Models.ParseModels.ParseShop;

import java.util.List;
import java.util.Optional;

public interface ParseShopService {

    Optional<ParseShop> findByShopUrl(String shopUrl);

    List<ParseShop> findAll();


}
