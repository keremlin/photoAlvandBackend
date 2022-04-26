package com.ara.photoalvand.viewModels;

import java.util.ArrayList;
import java.util.List;

import com.ara.photoalvand.models.User;
import com.ara.photoalvand.models.orderCase;
import com.ara.photoalvand.services.FilesStorageService;

public class vmKart extends returnObjectDecorator {
    private List<fileDataVM> files;
    private FilesStorageService storageService;
    private int sumOfOrder=0;
    private String userRules="قوانین خرید و سایت";
    private User user;

    public vmKart(List<orderCase> cases,User user,FilesStorageService storageService) {
        this.storageService=storageService;
        this.user=user;
        if (!cases.isEmpty()) {
            returnObject = new vmReturnObject(cases.get(0).getId(), 1, "message", "name");
            setOrder(cases.get(0));
        }

    }

    public void setOrder(orderCase item) {
        files = new ArrayList<fileDataVM>();
        
        item.getFiles().forEach(
                x -> files.add(
                    storageService.findFile(
                        x.getId()
                        )
                    ));
    }
    public List<fileDataVM> getFiles() {
        return files;
    }

    public void setFiles(List<fileDataVM> files) {
        this.files = files;
    }
    public int getSumOfOrder(){
        //TODO:provide a test case;
        for(fileDataVM item:files){
            sumOfOrder+=item.getFormprice();
        }
        return sumOfOrder;
    }
    public String getUserRules(){
        return userRules;
    }
    public User getUser(){
        return this.user;
    }
    
}
