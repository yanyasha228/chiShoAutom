package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.HelpUtils.Mappers.ModelMappers.Mapper;
import com.chiShoAutom.Models.BitrixModels.BitrixContact;
import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.ContactRestBitrixDto;
import com.chiShoAutom.RestDao.RestBitrixDao.ContactRestBitrixDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


//Contact rest service pot joip jorik
@Service
public class ContactRestBitrixServiceImpl implements ContactRestBitrixService {

    @Autowired
    private ContactRestBitrixDao contactRestBitrixDao;

    @Autowired
    private Mapper<BitrixContact, ContactRestBitrixDto> mapper;

    @Override
    public Optional<BitrixContact> get(Integer id) {
        return Optional.ofNullable(mapper.toEntity(contactRestBitrixDao.get(id).orElse(null)));
    }

}
