package com.chiShoAutom.Dao;

import com.chiShoAutom.Models.ManagerSalaryIncrease;

import java.util.Optional;

public interface ManagerSalaryIncreaseDao extends SalaryIncreaseDao<ManagerSalaryIncrease> {

    Optional<ManagerSalaryIncrease> findByBitrixDealId(Long id);

}
