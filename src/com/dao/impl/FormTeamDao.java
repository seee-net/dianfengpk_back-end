package com.dao.impl;

import com.entity.FormTeam;

import java.util.List;

public interface FormTeamDao {
    boolean createFormTeam(FormTeam ft);
    List<FormTeam> readFormTeam();
}
