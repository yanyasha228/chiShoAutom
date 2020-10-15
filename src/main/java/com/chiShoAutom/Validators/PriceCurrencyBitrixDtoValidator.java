package com.chiShoAutom.Validators;

import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.ProductRestBitrixDto;

public interface PriceCurrencyBitrixDtoValidator {

    ProductRestBitrixDto convertProductDtoCurrencyToUAH(ProductRestBitrixDto productRestBitrixDto);
}
