package com.chiShoAutom.FilesUtils;

import com.chiShoAutom.HelpUtils.CustomExceptions.*;
import com.chiShoAutom.Models.Correction;
import com.chiShoAutom.Models.User;

import java.io.FileNotFoundException;
import java.util.List;

public interface ProductStoreCsvCorrectionsUploader {

    List<Correction> doCorrections(String fileName, User user) throws FileNotFoundException,
            ImpossibleEntitySaveUpdateException,
            ImpossibleAmountDecreasingException,
            ImpossibleRestUpdateEntityException,
            IncorrectFileException, CsvCorrectionResultException;

}
