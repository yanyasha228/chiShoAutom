package com.chiShoAutom.SyncUtils;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.chiShoAutom.Models.Product;

import java.util.List;

public interface ProductsPromRestSynchronizer {

    Product synchronizeOne(Product productForSync) throws ImpossibleEntitySaveUpdateException;

    List<Product> synchronizeAll() throws InterruptedException;
}
