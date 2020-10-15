package com.chiShoAutom.ReceiptUtils;

import com.chiShoAutom.Models.NonPersistentModels.Receipt;

public interface ReceiptToHtmlConverter {

    String convert(Receipt receipt);

}
