package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.chiShoAutom.HelpUtils.Mappers.ModelMappers.Mapper;
import com.chiShoAutom.Models.BitrixModels.BitrixDeal;
import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.DealRestBitrixDto;
import com.chiShoAutom.RestDao.RestBitrixDao.DealRestBitrixDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DealRestBitrixServiceImpl implements DealRestBitrixService {

    @Autowired
    private DealRestBitrixDao dealRestBitrixDao;

    @Autowired
    private Mapper<BitrixDeal, DealRestBitrixDto> mapper;

    @Override
    public Optional<BitrixDeal> get(Integer id) {
        return Optional.ofNullable(mapper.toEntity(dealRestBitrixDao.get(id).orElse(null)));
    }

    @Override
    public Optional<BitrixDeal> update(BitrixDeal bitrixDeal) throws ImpossibleRestUpdateEntityException {
        return dealRestBitrixDao.update(mapper.toDto(bitrixDeal))
                .map(dealRestBitrixDto -> mapper.toEntity(dealRestBitrixDto));
    }

}
