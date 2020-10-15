package com.chiShoAutom.RestControllers;

import com.chiShoAutom.Models.ManagerSalaryDecrease;
import com.chiShoAutom.Models.SalaryDecrease;
import com.chiShoAutom.Models.Worker;
import com.chiShoAutom.Services.SalaryDecreaseService;
import com.chiShoAutom.Services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/rest/salary/manager/decreases")
public class SalaryDecreasesRestController {

    @Autowired
    private SalaryDecreaseService salaryDecreaseService;

    @Autowired
    private WorkerService workerService;

    @PostMapping("add")
    public void addSalaryDecrease(@RequestParam Optional<Float> amount, @AuthenticationPrincipal Worker worker) {

        if (amount.isPresent()) {
            SalaryDecrease salaryDecrease = new ManagerSalaryDecrease();
            salaryDecrease.setWorker(worker);
            salaryDecrease.setSalaryDecreaseAmount(amount.get());

            salaryDecreaseService.save(salaryDecrease);

        }

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("nullify")
    public void nullifySalaryDecreaseBalance(@RequestParam Optional<Long> managerId) {

        if (managerId.isPresent()) {

            Optional<Worker> workerOpt = workerService.findById(managerId.get());

            if (workerOpt.isPresent()) {
                Worker manager = workerOpt.get();
                manager.nullifySalaryDecreaseBalance();
                workerService.save(manager);
            }
        }

    }

}
