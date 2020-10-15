package com.chiShoAutom.Models.HelpRestModels.HelpRestNovapochtaModels;

import com.chiShoAutom.Models.HelpRestModels.HelpRestNovapochtaModels.Requests.TrackingNovaPochtaRequest;
import com.chiShoAutom.Models.NovaPochtaModels.NovaPochtaTrackingDocument;

public interface TrackingNovaPochtaRequestBuilder {

    TrackingNovaPochtaRequest build(NovaPochtaTrackingDocument novaPochtaTrackingDocument);
}
