package com.chiShoAutom.Dao.ManDaos;

import com.chiShoAutom.Models.HelpModels.GenProductManipulation;
import com.chiShoAutom.Models.ModelEnums.ProductManipulationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductManipulationRepExtension {

    List<GenProductManipulation> findAllByProductId(Long id);

    Page<GenProductManipulation> findAllByProductIdWithPagination(Long id, Pageable pageable);

    Page<GenProductManipulation> findAllByProductIdAndTypeWithPagination(Long id, ProductManipulationType productManipulationType, Pageable pageable);

}
