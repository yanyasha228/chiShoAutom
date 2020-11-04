package com.chiShoAutom.Services.ParseModelsServices;

import com.chiShoAutom.Models.ParseModels.ParseProduct;
import com.chiShoAutom.Models.ParseModels.ParseShop;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParseProductServiceImpl implements ParseProductService {

    @Override
    public Optional<ParseProduct> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ParseProduct> findAll() {
        return null;
    }

    @Override
    public List<ParseProduct> findAllByParseShop(ParseShop parseShop) {
        return null;
    }
}
