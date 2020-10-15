package com.chiShoAutom.Models.HelpRestModels.HelpRestNovapochtaModels.Responses;

import com.chiShoAutom.Models.Dto.RestDto.NovaPochtaDto.TrackingDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TrackingNovapochtaResponse {

    boolean success;

    @JsonProperty(value = "data")
    List<TrackingDto> trackingDtos;
}
