package com.ara.photoalvand.services;

import java.util.List;

import com.ara.photoalvand.viewModels.VMsearch;

public interface Search {
    public List<VMsearch> byCategory(int id);
    public int numberOfCategoryItems(int id);
}
