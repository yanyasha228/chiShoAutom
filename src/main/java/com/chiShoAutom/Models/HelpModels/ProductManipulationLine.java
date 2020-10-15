package com.chiShoAutom.Models.HelpModels;

import com.chiShoAutom.Models.ModelEnums.ProductManipulationType;
import com.chiShoAutom.Models.ProductLine;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductManipulationLine {

    public ProductManipulationLine(ProductManipulationType productManipulationType, ProductLine productLine){
        this.productLine = productLine;
        this.productManipulationType = productManipulationType;
    }

    private ProductManipulationType productManipulationType;

    private ProductLine productLine;
}
