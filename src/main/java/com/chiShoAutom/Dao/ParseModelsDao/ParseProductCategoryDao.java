package com.chiShoAutom.Dao.ParseModelsDao;

import com.chiShoAutom.Models.ParseModels.ParseProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParseProductCategoryDao extends JpaRepository<ParseProductCategory , Long> {

    Optional<ParseProductCategory> findById(Long id);

    Optional<ParseProductCategory> findByName(String name);

    List<ParseProductCategory> findAll();

}
