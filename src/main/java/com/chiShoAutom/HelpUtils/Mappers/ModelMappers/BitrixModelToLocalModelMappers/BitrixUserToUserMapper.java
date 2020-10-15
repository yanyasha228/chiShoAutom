package com.chiShoAutom.HelpUtils.Mappers.ModelMappers.BitrixModelToLocalModelMappers;

import com.chiShoAutom.HelpUtils.Mappers.ModelMappers.Mapper;
import com.chiShoAutom.Models.BitrixModels.BitrixUser;
import com.chiShoAutom.Models.Worker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BitrixUserToUserMapper implements Mapper<Worker, BitrixUser> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Worker toEntity(BitrixUser dto) {
        if (Objects.isNull(dto)) return null;

        return modelMapper.map(dto, Worker.class);
    }

    @Override
    public BitrixUser toDto(Worker entity) {
        return null;
    }

}
