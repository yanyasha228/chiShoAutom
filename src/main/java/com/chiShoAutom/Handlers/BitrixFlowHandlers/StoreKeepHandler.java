package com.chiShoAutom.Handlers.BitrixFlowHandlers;

import com.chiShoAutom.HelpUtils.CustomExceptions.EntityInconsistencyException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.HelpUtils.CustomExceptions.InsufficientProductAmountException;
import com.chiShoAutom.Models.BitrixModels.BitrixDeal;

public interface StoreKeepHandler {

    void handle(BitrixDeal bitrixDeal) throws InsufficientProductAmountException, EntityInconsistencyException, ImpossibleRestUpdateEntityException;

}
