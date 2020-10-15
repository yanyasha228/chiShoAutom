package com.chiShoAutom.FilesUtils;


import com.chiShoAutom.Models.Group;

import java.io.IOException;

public interface ProductStoreCsvBuilder {

    String createCsv(Boolean availability) throws IOException;

    String createCsv(Group group, Boolean availability) throws IOException;
}
