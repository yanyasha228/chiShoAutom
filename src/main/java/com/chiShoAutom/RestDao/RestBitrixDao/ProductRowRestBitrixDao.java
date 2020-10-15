package com.chiShoAutom.RestDao.RestBitrixDao;

import com.chiShoAutom.Models.Dto.RestDto.RestBitrixDto.ProductRowRestBitrixDto;

import java.util.List;

public interface ProductRowRestBitrixDao {

    List<ProductRowRestBitrixDto> getByDealId(Long id);

    boolean update(Long bitrixDealId, List<ProductRowRestBitrixDto> productRowList);


}
