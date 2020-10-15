package com.chiShoAutom.Models.HelpRestModels.HelpRestBitrixModels.Responses;

import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.DealRestBitrixDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetBitrixDealsByStageIdResponse implements BitrixRestResponse {

    private List<DealRestBitrixDto> result;

}
