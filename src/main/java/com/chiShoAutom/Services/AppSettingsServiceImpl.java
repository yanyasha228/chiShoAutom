package com.chiShoAutom.Services;

import com.chiShoAutom.Dao.AppSettingsDao;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.chiShoAutom.Models.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppSettingsServiceImpl implements AppSettingsService {

    @Autowired
    private AppSettingsDao appSettingsDao;

    @Override
    public AppSettings update(AppSettings appSettings) throws ImpossibleEntitySaveUpdateException {

        if (appSettings == null || appSettings.getId() != 1)
            throw new ImpossibleEntitySaveUpdateException("Settings Model is NULL or ID is not corresponds to original settings id");

        return appSettingsDao.save(appSettings);
    }

    @Override
    public Optional<AppSettings> getSettings() {
        return appSettingsDao.findById(1);
    }

    @Override
    public boolean exist() {
        return appSettingsDao.existsById(1);
    }
}
