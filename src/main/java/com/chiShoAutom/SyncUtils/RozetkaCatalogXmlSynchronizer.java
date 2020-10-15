package com.chiShoAutom.SyncUtils;

import com.chiShoAutom.HelpUtils.CustomExceptions.ProductXmlCatalogInaccessibilityException;
import com.chiShoAutom.Models.Dto.XmlDto.PromXmlDto.CatalogXmlPromDto;

public interface RozetkaCatalogXmlSynchronizer {

    CatalogXmlPromDto generate() throws ProductXmlCatalogInaccessibilityException;

    void generateAndSave() throws ProductXmlCatalogInaccessibilityException;

    CatalogXmlPromDto unmarshalSavedCatalog();

}
