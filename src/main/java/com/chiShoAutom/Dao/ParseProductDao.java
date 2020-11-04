package com.chiShoAutom.Dao;

import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.Models.ParseModels.ParseShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParseProductDao extends JpaRepository<ParseProduct , Long> {

    List<ParseProduct> findAllByParseShop(ParseShop parseShop);

}
