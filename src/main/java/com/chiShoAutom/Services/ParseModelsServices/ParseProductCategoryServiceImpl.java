package com.chiShoAutom.Services.ParseModelsServices;

import com.chiShoAutom.Dao.ParseModelsDao.ParseProductCategoryDao;
import com.chiShoAutom.Models.ParseModels.ParseProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParseProductCategoryServiceImpl implements ParseProductCategoryService {

    @Autowired
    private ParseProductCategoryDao parseProductCategoryDao;

    @Override
    public Optional<ParseProductCategory> findById(Long id) {
        return parseProductCategoryDao.findById(id);
    }

    @Override
    public Optional<ParseProductCategory> findByName(String name) {
        return parseProductCategoryDao.findByName(name);
    }

    @Override
    public List<ParseProductCategory> findAll() {
        return parseProductCategoryDao.findAll();
    }

    @Override
    public ParseProductCategory save(ParseProductCategory parseProductCategory) {
        return parseProductCategoryDao.save(parseProductCategory);
    }

}
