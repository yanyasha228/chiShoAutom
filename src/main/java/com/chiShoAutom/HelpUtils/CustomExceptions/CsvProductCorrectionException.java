package com.chiShoAutom.HelpUtils.CustomExceptions;

import com.chiShoAutom.Models.Product;
import lombok.Data;

@Data
public class CsvProductCorrectionException extends Exception {
    public CsvProductCorrectionException(Product product , Exception exception , String message) {
        super(message);
        this.product = product;
        this.exception = exception;
    }

    private Product product;

    private Exception exception;
}
