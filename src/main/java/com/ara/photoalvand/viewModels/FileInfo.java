package com.ara.photoalvand.viewModels;

import lombok.Data;
@Data
public class FileInfo {
  private String name;
  private String url;
  private String id;

  public FileInfo(String id,String name, String url) {
    this.name = name;
    this.url = url;
    this.id=id;
  }

  
}
