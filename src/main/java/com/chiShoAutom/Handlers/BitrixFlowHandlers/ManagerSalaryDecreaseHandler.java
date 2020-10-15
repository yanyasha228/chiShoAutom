package com.chiShoAutom.Handlers.BitrixFlowHandlers;

import com.chiShoAutom.HelpUtils.CustomExceptions.EntityInconsistencyException;
import com.chiShoAutom.Models.BitrixModels.BitrixDeal;

public interface ManagerSalaryDecreaseHandler {

    void handle(BitrixDeal bitrixDeal) throws EntityInconsistencyException;

}
