package com.dao.impl;

import com.entity.FormSingle;

import java.util.List;

public interface FormSingleDao {
    boolean createFormSingle(FormSingle fs);
    List<FormSingle> readFormSingle();
}
