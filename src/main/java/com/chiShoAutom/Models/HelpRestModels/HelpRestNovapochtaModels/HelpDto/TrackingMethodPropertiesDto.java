package com.chiShoAutom.Models.HelpRestModels.HelpRestNovapochtaModels.HelpDto;

import com.chiShoAutom.Models.NovaPochtaModels.NovaPochtaTrackingDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TrackingMethodPropertiesDto {

    @JsonProperty(value = "Documents")
    List<NovaPochtaTrackingDocument> documents = new ArrayList<>();
}
