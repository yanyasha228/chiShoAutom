package com.chiShoAutom.Services;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleAmountDecreasingException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.Models.Correction;
import com.chiShoAutom.Models.HelpModels.ProductManipulationLine;
import com.chiShoAutom.Models.ModelEnums.CorrectionType;
import com.chiShoAutom.Models.User;

import java.util.List;
import java.util.Optional;

public interface CorrectionService {

    Correction saveStrictCorrection(User user,
                                    CorrectionType correctionType,
                                    String description,
                                    Long id ,
                                    Integer amount) throws ImpossibleEntitySaveUpdateException, ImpossibleRestUpdateEntityException, ImpossibleAmountDecreasingException;

    List<Correction> saveAllWithRelativeCorrection(User user,
                                                   CorrectionType correctionType,
                                                   String description,
                                                   List<ProductManipulationLine> productManipulationLines) throws ImpossibleEntitySaveUpdateException, ImpossibleRestUpdateEntityException, ImpossibleAmountDecreasingException;


    Optional<Correction> findById(Long id);
}
