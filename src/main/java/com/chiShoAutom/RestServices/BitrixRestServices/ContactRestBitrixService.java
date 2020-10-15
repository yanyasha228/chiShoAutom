package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.Models.BitrixModels.BitrixContact;

import java.util.Optional;

public interface ContactRestBitrixService {

    Optional<BitrixContact> get(Integer id);

}
