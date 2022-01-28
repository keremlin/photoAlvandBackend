package com.ara.photoalvand.controllers;

import java.util.List;

import com.ara.photoalvand.models.orderCase;
import com.ara.photoalvand.services.FilesStorageService;
import com.ara.photoalvand.services.Order;
import com.ara.photoalvand.services.userService;
import com.ara.photoalvand.viewModels.IReturnObject;
import com.ara.photoalvand.viewModels.returnObjectDecorator;
import com.ara.photoalvand.viewModels.vmKart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")

public class orderController {
    
    @Autowired private Order order;
    @Autowired private FilesStorageService storageService;
    @Autowired private userService userservice;

    @PostMapping("/setNewOrder")
    public ResponseEntity<IReturnObject> setNewOrder(@RequestBody int item) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(order.setNewFile(
                        item,
                        getCurrentUserName()));
    }
    @GetMapping("/getUserOrders")
    
    private ResponseEntity<returnObjectDecorator> getUserOrders() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new vmKart(
                    order.getUserOrders(getCurrentUserName()),
                    userservice.findUserByUsername(getCurrentUserName()),
                    storageService) );
    }
    @PostMapping("/deleteOrder")
    private ResponseEntity<List<orderCase>> deleteOrder(@RequestBody int idd){
        return ResponseEntity.status(HttpStatus.OK).body(order.deleteFileFromOrder(idd,getCurrentUserName()));
    }
    @GetMapping("/getRules")
    private ResponseEntity<String> getRules(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("قوانین سایت و خرید ");
    }
    private String getCurrentUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    
}
