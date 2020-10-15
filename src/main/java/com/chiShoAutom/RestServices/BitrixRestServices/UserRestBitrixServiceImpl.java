package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.HelpUtils.Mappers.ModelMappers.Mapper;
import com.chiShoAutom.Models.BitrixModels.BitrixUser;
import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.UserRestBitrixDto;
import com.chiShoAutom.RestDao.RestBitrixDao.UserRestBitrixDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRestBitrixServiceImpl implements UserRestBitrixService {

    @Autowired
    private UserRestBitrixDao userRestBitrixDao;


    @Autowired
    private Mapper<BitrixUser, UserRestBitrixDto> mapper;

    @Override
    public Optional<BitrixUser> get(Long id) {

        return Optional.ofNullable(mapper.toEntity(userRestBitrixDao.get(id).orElse(null)));

    }

    @Override
    public Optional<BitrixUser> getByEmail(String email) {

        return Optional.ofNullable(mapper.toEntity(userRestBitrixDao.getByEmail(email).orElse(null)));

    }

}
