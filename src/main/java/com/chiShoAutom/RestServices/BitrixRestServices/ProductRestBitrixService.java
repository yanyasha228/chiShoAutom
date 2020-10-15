package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRestBitrixService {

    List<Product> getAll();

    Optional<Product> getByBitrixId(Long bitrix_id);

    Optional<Product> update(Product product) throws ImpossibleRestUpdateEntityException;

    List<Product> updateAll(List<Product> productList) throws ImpossibleRestUpdateEntityException;


}
