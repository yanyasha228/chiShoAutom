package com.chiShoAutom.RestServices.PromRestServices.XmlServices;

import com.chiShoAutom.Models.Dto.XmlDto.PromXmlDto.CatalogXmlPromDto;

import java.util.Optional;

public interface ProductCatalogXmlService {

    Optional<CatalogXmlPromDto> getCatalog(String url);

}
