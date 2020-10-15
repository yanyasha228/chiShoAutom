package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.Models.BitrixModels.BitrixUser;

import java.util.Optional;

public interface UserRestBitrixService {

    Optional<BitrixUser> get(Long id);

    Optional<BitrixUser> getByEmail(String email);

}
