package com.chiShoAutom.Dao.ParseModelsDao;

import com.chiShoAutom.Models.ParseModels.ParseShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParseShopDao extends JpaRepository<ParseShop , Long> {

    Optional<ParseShop> findByShopUrl(String url);

}
