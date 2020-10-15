package com.chiShoAutom.Handlers.BitrixFlowHandlers;

import com.chiShoAutom.Handlers.HandlersUtils.BitrixDealSalaryCounter;
import com.chiShoAutom.HelpUtils.CustomExceptions.EntityInconsistencyException;
import com.chiShoAutom.Models.BitrixModels.BitrixDeal;
import com.chiShoAutom.Models.ManagerSalaryIncrease;
import com.chiShoAutom.Models.Worker;
import com.chiShoAutom.Services.SalaryIncreaseService;
import com.chiShoAutom.Services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ManagerSalaryIncreaseHandlerImpl implements ManagerSalaryIncreaseHandler {

    @Autowired
    private SalaryIncreaseService<ManagerSalaryIncrease> salaryIncreaseService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private BitrixDealSalaryCounter bitrixDealSalaryCounter;

    @Override
    public void handle(BitrixDeal bitrixDeal) throws EntityInconsistencyException {

        if (!salaryIncreaseService.findByBitrixDealId(bitrixDeal.getId()).isPresent()) {

            Optional<Worker> managerOpt = workerService.findById((long) bitrixDeal.getManager().getId());

            if (!managerOpt.isPresent())
                throw new EntityInconsistencyException("Deal doesnt have a registred manager for salary counting");

            ManagerSalaryIncrease salaryIncrease = new ManagerSalaryIncrease();

            salaryIncrease.setBitrixDealId(bitrixDeal.getId());

            float salaryIncreaseSum = bitrixDealSalaryCounter.count(bitrixDeal);

            bitrixDeal.setActualDealSalaryBonus(salaryIncreaseSum);

            salaryIncrease.setSalaryIncreaseAmount(salaryIncreaseSum);

            salaryIncrease.setWorker(managerOpt.get());

            salaryIncreaseService.save(salaryIncrease);
        }

    }
}
