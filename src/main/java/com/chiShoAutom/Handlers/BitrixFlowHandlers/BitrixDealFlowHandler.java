package com.chiShoAutom.Handlers.BitrixFlowHandlers;

import com.chiShoAutom.HelpUtils.CustomExceptions.EntityInconsistencyException;
import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleRestUpdateEntityException;
import com.itextpdf.text.DocumentException;

import javax.print.PrintException;
import java.io.IOException;

public interface BitrixDealFlowHandler {

    void handle(Integer bitrixDealId) throws DocumentException,
            PrintException,
            IOException,
            ImpossibleRestUpdateEntityException,
            EntityInconsistencyException;

}
