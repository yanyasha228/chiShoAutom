package com.chiShoAutom.Models.HelpRestModels.HelpRestBitrixModels.Responses;

import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.UserRestBitrixDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetUserByEmailRestResponse implements BitrixRestResponse {


    @JsonProperty(value = "result")
    private List<UserRestBitrixDto> users;


}
