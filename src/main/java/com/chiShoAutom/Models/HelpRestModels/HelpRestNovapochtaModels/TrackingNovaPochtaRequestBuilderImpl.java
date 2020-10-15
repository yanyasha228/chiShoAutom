package com.chiShoAutom.Models.HelpRestModels.HelpRestNovapochtaModels;

import com.chiShoAutom.Models.HelpRestModels.HelpRestNovapochtaModels.HelpDto.TrackingMethodPropertiesDto;
import com.chiShoAutom.Models.HelpRestModels.HelpRestNovapochtaModels.Requests.TrackingNovaPochtaRequest;
import com.chiShoAutom.Models.NovaPochtaModels.NovaPochtaTrackingDocument;
import org.springframework.stereotype.Component;

@Component
public class TrackingNovaPochtaRequestBuilderImpl implements TrackingNovaPochtaRequestBuilder {

    @Override
    public TrackingNovaPochtaRequest build(NovaPochtaTrackingDocument novaPochtaTrackingDocument) {

        TrackingMethodPropertiesDto trackingMethodPropertiesDto = new TrackingMethodPropertiesDto();
        trackingMethodPropertiesDto.getDocuments().add(novaPochtaTrackingDocument);
        TrackingNovaPochtaRequest trackingNovapochtaRequest = new TrackingNovaPochtaRequest();
        trackingNovapochtaRequest.setMethodProperties(trackingMethodPropertiesDto);
        return trackingNovapochtaRequest;
    }

}
