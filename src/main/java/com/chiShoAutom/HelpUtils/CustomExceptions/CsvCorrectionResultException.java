package com.chiShoAutom.HelpUtils.CustomExceptions;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CsvCorrectionResultException extends Exception {
    public CsvCorrectionResultException(String message) {
        super(message);
    }

    private List<CsvProductCorrectionException> exceptions = new ArrayList<>();
}
