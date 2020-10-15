package com.chiShoAutom.RestDao.RestBitrixDao;

import com.chiShoAutom.Models.HelpRestModels.HelpRestBitrixModels.Requests.BitrixRestRequest;
import com.chiShoAutom.Models.HelpRestModels.HelpRestBitrixModels.Responses.BitrixRestResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface BitrixRestApiExchanger {

    <REQ extends BitrixRestRequest, RES extends BitrixRestResponse> ResponseEntity<RES> exchange(String url,
                                                                                                 HttpMethod method,
                                                                                                 REQ requestEntity,
                                                                                                 Class<RES> responseEntityClass);

}
