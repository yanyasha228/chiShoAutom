package com.chiShoAutom.Handlers.BitrixFlowHandlers;

import com.chiShoAutom.Handlers.HandlersUtils.BitrixDealSalaryCounter;
import com.chiShoAutom.HelpUtils.CustomExceptions.EntityInconsistencyException;
import com.chiShoAutom.Models.BitrixModels.BitrixDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PredictSalaryHandlerImpl implements PredictSalaryHandler {

    @Autowired
    private BitrixDealSalaryCounter bitrixDealSalaryCounter;

    @Override
    public void handle(BitrixDeal bitrixDeal) throws EntityInconsistencyException {

        bitrixDeal.setExpectedDealSalaryBonus(bitrixDealSalaryCounter.count(bitrixDeal));


    }
}
