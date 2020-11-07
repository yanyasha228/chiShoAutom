package com.chiShoAutom.Services.ParseModelsServices;

import com.chiShoAutom.Dao.ParseModelsDao.ParseShopDao;
import com.chiShoAutom.Models.ParseModels.ParseShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParseShopServiceImpl implements ParseShopService {

    @Autowired
    private ParseShopDao parseShopDao;

    @Override
    public Optional<ParseShop> findByShopUrl(String shopUrl) {
        return parseShopDao.findByShopUrl(shopUrl);
    }

    @Override
    public List<ParseShop> findAll() {
        return parseShopDao.findAll();
    }

}
