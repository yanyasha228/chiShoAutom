package com.chiShoAutom.RestDao.RestBitrixDao;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.DealRestBitrixDto;

import java.util.List;
import java.util.Optional;

public interface DealRestBitrixDao {

    Optional<DealRestBitrixDto> get(Integer id);

    Optional<DealRestBitrixDto> update(DealRestBitrixDto dealRestBitrixDto) throws ImpossibleRestUpdateEntityException;

    List<DealRestBitrixDto> getByStageId(String stageId);
}
