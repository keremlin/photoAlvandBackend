package com.ara.photoalvand.services;

import java.util.stream.Stream;

import com.ara.photoalvand.models.file;
import com.ara.photoalvand.models.fileDataVM;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  public void init();

  public file save(MultipartFile file);

  public Resource load(String filename);

  public void deleteAll();

  public Stream<file> loadAll();

  public boolean saveFilesData(Stream<fileDataVM> fileData);
  public boolean saveFilesData(fileDataVM[] fileData);
  public fileDataVM findFile(int id);
}
