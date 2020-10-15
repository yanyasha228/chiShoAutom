package com.chiShoAutom.RestServices.NovaPochtaRestServices;

import com.chiShoAutom.HelpUtils.Mappers.ModelMappers.Mapper;
import com.chiShoAutom.Models.Dto.RestDto.NovaPochtaDto.TrackingDto;
import com.chiShoAutom.Models.NovaPochtaModels.NovaPochtaAddress;
import com.chiShoAutom.Models.NovaPochtaModels.NovaPochtaTrackingDocument;
import com.chiShoAutom.RestDao.RestNovaPochtaDao.TrackingRestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackingNovaPochtaRestServiceImpl implements TrackingNovaPochtaRestService {

    @Autowired
    private TrackingRestDao trackingRestDao;

    @Autowired
    private Mapper<NovaPochtaAddress, TrackingDto> trackingDtoToAddressMapper;

    @Override
    public Optional<NovaPochtaAddress> getAddress(NovaPochtaTrackingDocument novaPochtaTrackingDocument) {

        return Optional.of(trackingDtoToAddressMapper.toEntity(trackingRestDao.getInfo(novaPochtaTrackingDocument).orElse(null)));

    }
}
