package com.chiShoAutom.Dao;

import com.chiShoAutom.Models.BitrixDealDemand;

import java.util.Optional;

public interface BitrixDealDemandDao extends ProductManipulationDao<BitrixDealDemand> {

    Optional<BitrixDealDemand> findByBitrixDealId(long id);

}
