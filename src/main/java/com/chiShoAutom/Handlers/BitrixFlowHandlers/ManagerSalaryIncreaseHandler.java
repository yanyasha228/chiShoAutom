package com.chiShoAutom.Handlers.BitrixFlowHandlers;

import com.chiShoAutom.HelpUtils.CustomExceptions.EntityInconsistencyException;
import com.chiShoAutom.Models.BitrixModels.BitrixDeal;

public interface ManagerSalaryIncreaseHandler {

    void handle(BitrixDeal bitrixDeal) throws EntityInconsistencyException;

}
