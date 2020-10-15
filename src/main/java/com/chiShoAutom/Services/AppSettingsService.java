package com.chiShoAutom.Services;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.chiShoAutom.Models.AppSettings;

import java.util.Optional;

public interface AppSettingsService {

    AppSettings update(AppSettings appSettingsModel) throws ImpossibleEntitySaveUpdateException;


    Optional<AppSettings> getSettings();

    boolean exist();
}
