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
import com.ara.photoalvand.models.FileInfo;
import com.ara.photoalvand.models.fileDataVM;
import com.ara.photoalvand.services.FilesStorageService;
import com.ara.photoalvand.services.util;

@Controller
@CrossOrigin("http://localhost:8081")
public class FilesController {

  @Autowired
  FilesStorageService storageService;

  @PostMapping("/api/file/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.save(file);

      message = "فایل با موفقیت بارگزاری شد  " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
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

  @GetMapping("/api/file/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}
