package com.chiShoAutom.Dao;

import com.chiShoAutom.Models.AppSettings;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppSettingsDao extends JpaRepository<AppSettings, Integer> {

}
