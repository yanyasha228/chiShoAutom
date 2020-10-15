package com.chiShoAutom.RestServices.NovaPochtaRestServices;

import com.chiShoAutom.Models.NovaPochtaModels.NovaPochtaAddress;
import com.chiShoAutom.Models.NovaPochtaModels.NovaPochtaTrackingDocument;

import java.util.Optional;

public interface TrackingNovaPochtaRestService {


    Optional<NovaPochtaAddress> getAddress(NovaPochtaTrackingDocument novaPochtaTrackingDocument);


}
