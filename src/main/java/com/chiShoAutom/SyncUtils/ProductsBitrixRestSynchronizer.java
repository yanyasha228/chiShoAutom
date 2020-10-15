package com.chiShoAutom.SyncUtils;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.Models.Product;

import java.util.List;

public interface ProductsBitrixRestSynchronizer {

    Product synchronizeOne(Product productForSync) throws ImpossibleEntitySaveUpdateException, ImpossibleRestUpdateEntityException;

    List<Product> synchronizeProducts(List<Product> productList) throws ImpossibleRestUpdateEntityException;

    List<Product> synchronizeAll() throws ImpossibleRestUpdateEntityException;

}
