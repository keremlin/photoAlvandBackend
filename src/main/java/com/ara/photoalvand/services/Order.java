package com.ara.photoalvand.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import com.ara.photoalvand.models.User;
import com.ara.photoalvand.models.file;
import com.ara.photoalvand.models.orderCase;
import com.ara.photoalvand.repository.fileRepository;
import com.ara.photoalvand.repository.orderRepository;
import com.ara.photoalvand.viewModels.IReturnObject;
import com.ara.photoalvand.viewModels.vmReturnObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Order {
    @Autowired
    private  orderRepository repo;

    @Autowired
    private  fileRepository fileRepo;

    @Autowired
    private userService userservice;

    public Order(){
        super();
        System.out.println("order service is loaded -----------------------S");
    }
 
    public  orderCase setNewOrder( User owner) {
        return setNewOrder(owner,null);
    }
    public  orderCase setNewOrder( User owner,file fileItem) {
        ArrayList<file> files=new ArrayList<file>();
        files.add(fileItem);
        return repo.save(
                new orderCase(
                        0,
                        new Date(),
                        owner,
                        files,
                        0,
                        false,
                        "0",
                        false))
                ;
    }
    
    public IReturnObject setNewFile(int id, String owner) {
        User user=userservice.findUserByUsername(owner);
        java.util.Optional<orderCase> order = repo.findTopByOwnerAndIsFinished(user, false);
        file item = fileRepo.findById(id);
        if (!order.isPresent()) {
            return new vmReturnObject(1, repo.save(setNewOrder(user, item)).getId()
            ,"به سبد خرید اضافه شد","Code");
        } else if(!order.get().getFiles().contains(item)) {
            order.get().getFiles().add(item);
            return new vmReturnObject(1,repo.save(order.get()).getId(),"به سبد خرید اضافه شد","code");
        }
        else{return new vmReturnObject(-1,-1,"قبلا اضافه شده است","Problem");}
    }
    public  List<orderCase> getUserOrders(String owner) {
        try{
            return repo.findByOwner(userservice.findUserByUsername(owner)).get();
        }catch(NoSuchElementException ex){
            return null;
        }
    }
    public  List<orderCase> deleteOrder(int id,String userName){
        repo.delete(repo.findById(id).get());
        return getUserOrders(userName);
    }
    public List<orderCase> deleteFileFromOrder(int fileId,String userName){
        orderCase order=getUserOrders(userName).get(0);
        order.getFiles().remove(fileRepo.findById(fileId));
        repo.save(order);
        return getUserOrders(userName);
    }
    
}
