package com.chiShoAutom.Dao.ParseModelsDao;

import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.Models.ParseModels.ParseShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParseProductDao extends JpaRepository<ParseProduct , Long> {

    List<ParseProduct> findAllByParseShop(ParseShop parseShop);

    Optional<ParseProduct> findByUrl(String url);
}
