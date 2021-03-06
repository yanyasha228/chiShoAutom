package com.chiShoAutom.Models.HelpRestModels.HelpRestBitrixModels.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddContactBitrixRestResponse implements BitrixRestResponse {

    @JsonProperty(value = "result")
    private int addedContactId;

}
