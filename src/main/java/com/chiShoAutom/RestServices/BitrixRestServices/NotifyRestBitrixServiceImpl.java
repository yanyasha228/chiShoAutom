package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.Models.BitrixModels.BitrixNotify;
import com.chiShoAutom.Models.BitrixModels.BitrixUser;
import com.chiShoAutom.RestDao.RestBitrixDao.NotifyRestBitrixDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyRestBitrixServiceImpl implements NotifyRestBitrixService {

    @Autowired
    private NotifyRestBitrixDao notifyRestBitrixDao;

    @Override
    public boolean sentNotify(BitrixUser bitrixUser, String message) {

        return notifyRestBitrixDao.post(new BitrixNotify(bitrixUser, message));

    }
}
