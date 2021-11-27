package com.ara.photoalvand.viewModels;

import com.ara.photoalvand.models.category;

import lombok.Data;

@Data
public class VMcategory {
    public VMcategory(category category,String imagePath){
        this.category=category;
        this.randomeImagePath=imagePath;
    }
    private category category;
     private String randomeImagePath;
}