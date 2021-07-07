package com.ara.photoalvand.viewModels;

import java.util.Collection;
import java.util.Date;

import com.ara.photoalvand.models.album;
import com.ara.photoalvand.models.category;
import com.ara.photoalvand.models.file;
import com.ara.photoalvand.services.util;

import lombok.Data;

@Data
public class VMsearch {
    public  VMsearch(file file, category currentSearch){
        this.Id=file.getId();
        this.physicalPath=util.convertFileNameToPath(file.getPhysicalPath());
        this.createDate=file.getCreateDate();
        this.description=file.getDescription();
        this.price=file.getPrice();
        this.categories=file.getCategories();
        this.title=file.getTitle();
        this.currentSearch=currentSearch;
    }
    private category currentSearch;
    private int Id;
    private String physicalPath;
    private String title;
    private String description;
    private album album;
    private Collection<category> categories;
    private Date createDate;
    private String photographer;
    private int price;
}
