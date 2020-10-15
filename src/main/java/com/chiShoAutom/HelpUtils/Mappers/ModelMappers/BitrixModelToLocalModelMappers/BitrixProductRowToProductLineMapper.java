package com.chiShoAutom.HelpUtils.Mappers.ModelMappers.BitrixModelToLocalModelMappers;

import com.chiShoAutom.HelpUtils.CustomExceptions.EntityInconsistencyException;
import com.chiShoAutom.Models.BitrixModels.BitrixProductRow;
import com.chiShoAutom.Models.ProductLine;

public interface BitrixProductRowToProductLineMapper {

    ProductLine toProductLine(BitrixProductRow productRow) throws EntityInconsistencyException;

    BitrixProductRow toBitrixProductRow(ProductLine productLine);

}
