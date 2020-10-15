package com.chiShoAutom.RestDao.RestBitrixDao;

import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.ContactRestBitrixDto;

import java.util.List;
import java.util.Optional;

public interface ContactRestBitrixDao {

    Optional<ContactRestBitrixDto> get(Integer id);

    List<ContactRestBitrixDto> getAll();

    Optional<ContactRestBitrixDto> add(ContactRestBitrixDto contactRestBitrixDto);

}
