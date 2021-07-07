package com.ara.photoalvand.services;

import java.util.List;
import java.util.stream.Collectors;

import com.ara.photoalvand.repository.categoryRepository;
import com.ara.photoalvand.repository.fileRepository;
import com.ara.photoalvand.viewModels.VMsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class search implements Isearch {
    @Autowired
    private fileRepository repoFile;
    @Autowired
    private categoryRepository repoCategory;

    @Override
    public List<VMsearch> byCategory(int id) {
        var category = repoCategory.findCategoryById(id);
        if (category.isPresent())
            return 
                repoFile.findFileBycategories(category.get())
            .stream().map(file -> {
                return new VMsearch(file,category.get());
            })
            .collect(Collectors.toList());
        else
            return null;
    }

}
