package com.chiShoAutom.Services;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.HelpUtils.CustomExceptions.InsufficientProductAmountException;
import com.chiShoAutom.Models.ProductLine;
import com.chiShoAutom.Models.SupplyDependentDemand;
import com.chiShoAutom.Models.User;

import java.util.List;
import java.util.Optional;

public interface SupplyDependentDemandService {

    Optional<SupplyDependentDemand> findById(long id);

    Optional<SupplyDependentDemand> findBySupplyId(long id);

    SupplyDependentDemand create(User user, List<ProductLine> productLineList) throws InsufficientProductAmountException;

    SupplyDependentDemand save(SupplyDependentDemand demand) throws InsufficientProductAmountException, ImpossibleRestUpdateEntityException;

}
