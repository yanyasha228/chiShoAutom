package com.chiShoAutom.Dao.ManDaos;

import com.chiShoAutom.Models.HelpModels.GenProductManipulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenProductManipulationDao extends JpaRepository<GenProductManipulation, Long>, ProductManipulationRepExtension {

}

