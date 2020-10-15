package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.Models.BitrixModels.BitrixDeal;

import java.util.Optional;

public interface DealRestBitrixService {

    Optional<BitrixDeal> get(Integer id);

    Optional<BitrixDeal> update(BitrixDeal bitrixDeal) throws ImpossibleRestUpdateEntityException;

}
