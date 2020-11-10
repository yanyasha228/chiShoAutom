package com.chiShoAutom.Services.ParseModelsServices;

import com.chiShoAutom.Dao.ParseModelsDao.ParseProductDao;
import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.Models.ParseModels.ParseShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParseProductServiceImpl implements ParseProductService {

    @Autowired
    private ParseProductDao parseProductDao;

    @Override
    public Optional<ParseProduct> findById(Long id) {
        return parseProductDao.findById(id);
    }

    @Override
    public Optional<ParseProduct> findByUrl(String url) {
        return parseProductDao.findByUrl(url);
    }

    @Override
    public List<ParseProduct> findAll() {
        return parseProductDao.findAll();
    }

    @Override
    public List<ParseProduct> findAllByParseShop(ParseShop parseShop) {

        return parseProductDao.findAllByParseShop(parseShop);
    }

    @Override
    public ParseProduct save(ParseProduct parseProduct) {
        return parseProductDao.save(parseProduct);
    }

    @Override
    public List<ParseProduct> saveAll(List<ParseProduct> parseProducts) {
        return parseProductDao.saveAll(parseProducts);
    }
}
