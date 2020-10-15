package com.chiShoAutom.Models.HelpRestModels.HelpRestBitrixModels.Responses;

import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.ProductRowRestBitrixDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class GetProductRowsByDealIdRestResponse implements BitrixRestResponse {

    @JsonProperty(value = "result")
    private List<ProductRowRestBitrixDto> productRowRestBitrixDtoList;

}
