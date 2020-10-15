package com.chiShoAutom.Services;

import com.chiShoAutom.HelpUtils.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.chiShoAutom.Models.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Group save(Group group) throws ImpossibleEntitySaveUpdateException;

    List<Group> save(List<Group> groupList);

    Optional<Group> findById(int id);

    List<Group> findAll();

}
