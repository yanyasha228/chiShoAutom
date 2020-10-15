package com.chiShoAutom.Dao;

import com.chiShoAutom.Models.ManagerSalaryDecrease;

import java.util.Optional;

public interface ManagerSalaryDecreaseDao extends SalaryDecreaseDao<ManagerSalaryDecrease> {

    Optional<ManagerSalaryDecrease> findByBitrixDealId(Long id);

}
