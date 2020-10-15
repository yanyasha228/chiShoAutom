package com.chiShoAutom.Dao;

import com.chiShoAutom.Models.SupplyDependentDemand;

import java.util.Optional;

public interface SupplyDependentDemandDao extends ProductManipulationDao<SupplyDependentDemand> {

    Optional<SupplyDependentDemand> findBySupplyId(Long id);

}
