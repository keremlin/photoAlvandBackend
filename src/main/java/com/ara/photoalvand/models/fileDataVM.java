package com.ara.photoalvand.models;

import java.util.Date;
import lombok.Data;

@Data
public class fileDataVM {
private String formname;
private String formdescription;
private int id;
private Integer[] categories;
private String userName;
private int formprice;
private Date createDate;
private category[] cat;
private String filePath;
}
