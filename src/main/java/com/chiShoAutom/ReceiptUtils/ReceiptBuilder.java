package com.chiShoAutom.ReceiptUtils;

import com.chiShoAutom.Models.BitrixModels.BitrixDeal;
import com.chiShoAutom.Models.NonPersistentModels.Receipt;

public interface ReceiptBuilder {

    Receipt build(BitrixDeal bitrixDeal);

}