package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.Models.BitrixModels.BitrixDeal;
import com.chiShoAutom.Models.BitrixModels.BitrixProductRow;

import java.util.List;

public interface ProductRowRestBitrixService {

    List<BitrixProductRow> getByDealId(Long id);

    boolean update(BitrixDeal bitrixDeal);


}
