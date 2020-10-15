package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.HelpUtils.Mappers.ModelMappers.Mapper;
import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.ProductRestBitrixDto;
import com.chiShoAutom.Models.Product;
import com.chiShoAutom.RestDao.RestBitrixDao.ProductRestBitrixDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductRestBitrixServiceImpl implements ProductRestBitrixService {

    @Autowired
    private ProductRestBitrixDao productRestBitrixDao;

    @Autowired
    private Mapper<Product, ProductRestBitrixDto> mapper;

    @Override
    public List<Product> getAll() {

        return productRestBitrixDao
                .getAll()
                .stream()
                .map(productRestBitrixDto -> mapper.toEntity(productRestBitrixDto))
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Product> getByBitrixId(Long bitrix_id) {


        return productRestBitrixDao.getByBitrixId(bitrix_id)
                .map(productRestBitrixDto -> mapper.toEntity(productRestBitrixDto));

    }

    @Override
    public Optional<Product> update(Product product) throws ImpossibleRestUpdateEntityException {


        return productRestBitrixDao.update(mapper.toDto(product))
                .map(productRestBitrixDto -> mapper.toEntity(productRestBitrixDto));

    }

    @Override
    public List<Product> updateAll(List<Product> productList) throws ImpossibleRestUpdateEntityException {


        return productRestBitrixDao.updateAll(productList
                .stream()
                .map(product -> mapper.toDto(product))
                .collect(Collectors.toList()))
                .stream()
                .map(productRestBitrixDto -> mapper.toEntity(productRestBitrixDto))
                .collect(Collectors.toList());

    }

}
