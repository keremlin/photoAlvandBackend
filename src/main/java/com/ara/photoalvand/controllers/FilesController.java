package com.ara.photoalvand.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ara.photoalvand.payload.ResponseMessage;
import com.ara.photoalvand.services.FilesStorageService;
import com.ara.photoalvand.services.util;
import com.ara.photoalvand.viewModels.FileInfo;
import com.ara.photoalvand.viewModels.fileDataVM;
import com.ara.photoalvand.viewModels.vmReturnObject;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class FilesController {
 
  
  @Autowired
  FilesStorageService storageService;

  @PostMapping("/api/file/upload")
  public ResponseEntity<vmReturnObject> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      var savedFile=storageService.save(file);
      var response=vmReturnObject.builder()
                                  .message(savedFile.getPhysicalPath())
                                  .id(savedFile.getId())
                                  .build();
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e) {
      var response=vmReturnObject.builder()
                                  .message("Could not upload the file: " + file.getOriginalFilename() + "!")
                                  .id(0)
                                  .code(300)
                                  .build();
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
    }
  }
  
  @PostMapping("/api/file/savefilesdata")
    public ResponseEntity<ResponseMessage> saveFilesData(@RequestBody fileDataVM[] fileInfo){
      storageService.saveFilesData((fileInfo));
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("OK"));
  }
  @GetMapping("/api/file/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getPhysicalPath().toString();
      String fileId=path.getId()+"";
      String url =util.convertFileNameToPath( path.getPhysicalPath());
      return new FileInfo(fileId,filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }
@GetMapping("/api/file/fileInfo/{id}")
public ResponseEntity<fileDataVM> getFileInfo(@PathVariable int id) {
    return ResponseEntity.status(HttpStatus.OK).body(storageService.findFile(id));
}
@PostMapping("/api/file/fileInfos")
public ResponseEntity<List<fileDataVM>> getFileInfo2(@RequestBody int[] ids) {
    return ResponseEntity.status(HttpStatus.OK).body(storageService.findFiles(ids));
}
  @GetMapping("/api/file/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}
