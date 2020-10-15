package com.chiShoAutom.RestDao.RestBitrixDao;

import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.UserRestBitrixDto;

import java.util.Optional;

public interface UserRestBitrixDao {

    Optional<UserRestBitrixDto> get(Long id);

    Optional<UserRestBitrixDto> getByEmail(String email);

}
