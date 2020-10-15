package com.chiShoAutom.RestDao.RestNovaPochtaDao;

import com.chiShoAutom.Models.Dto.RestDto.NovaPochtaDto.TrackingDto;
import com.chiShoAutom.Models.NovaPochtaModels.NovaPochtaTrackingDocument;

import java.util.Optional;

public interface TrackingRestDao {

    Optional<TrackingDto> getInfo(NovaPochtaTrackingDocument novaPochtaTrackingDocument);

}
