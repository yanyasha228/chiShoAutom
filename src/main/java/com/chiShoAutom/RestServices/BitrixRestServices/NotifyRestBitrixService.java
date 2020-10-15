package com.chiShoAutom.RestServices.BitrixRestServices;

import com.chiShoAutom.Models.BitrixModels.BitrixUser;

public interface NotifyRestBitrixService {

    boolean sentNotify(BitrixUser bitrixUser, String message);
}
