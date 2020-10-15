package com.chiShoAutom.TemporalUtils;

import com.chiShoAutom.Models.BitrixDealDemand;
import com.chiShoAutom.Models.BitrixModels.BitrixDeal;
import com.chiShoAutom.RestServices.BitrixRestServices.DealRestBitrixService;
import com.chiShoAutom.Services.DealDemandService;
import com.chiShoAutom.Services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BitrixDealDemandUserTier {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private DealRestBitrixService dealRestBitrixService;

    @Autowired
    private DealDemandService dealDemandService;


    public void tie() {

        List<BitrixDealDemand> dealDemands = dealDemandService.findAll();

        List<BitrixDealDemand> dealDemandsToSave = dealDemands.stream().map(dealDemand -> {
            Optional<BitrixDeal> dealOptional = dealRestBitrixService.get((int) dealDemand.getBitrixDealId());

            dealOptional.ifPresent(bitrixDeal -> dealDemand.setUser(workerService.findById(bitrixDeal.getManager().getId()).orElse(null)));

            return dealDemand;
        }).collect(Collectors.toList());

        dealDemandService.saveAll(dealDemandsToSave);

    }
}
