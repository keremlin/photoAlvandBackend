package com.ara.photoalvand.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.ara.photoalvand.models.category;
import com.ara.photoalvand.models.file;
import com.ara.photoalvand.models.fileDataVM;
import com.ara.photoalvand.repository.categoryRepository;
import com.ara.photoalvand.repository.fileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("uploads");
  @Autowired private fileRepository repo;
  @Autowired private categoryRepository categoryRepo;
  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file) {
    var miliSecondTimeFileName=
     java.time.ZonedDateTime.now().toInstant().toEpochMilli()+
     "--"+file.getOriginalFilename();
    try {
      Files.copy(file.getInputStream(), this.root.resolve(miliSecondTimeFileName));
      var entity=new file();
      entity.setTitle("");
      entity.setPhysicalPath(miliSecondTimeFileName);
      entity.setReviewed(false);
      repo.save(entity);
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<file> loadAll() {
    try {
      return repo.findFileByisReviewed(false).stream();
      //return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (Exception e) {
      throw new RuntimeException("Could not load the files!");
    }
  }
  @Override
  public boolean saveFilesData(Stream<fileDataVM> fileData) {
    fileData.map(item -> {
      var temp = repo.findById(item.getId());
      Arrays.stream(item.getCategories()).map(category -> {
        var cat = categoryRepo.findCategoryById(category);
        if (temp.getCategories().stream().filter(x -> x.getId() == cat.getId()).findFirst().isPresent())
          return 0;
        temp.getCategories().add(cat);
        return 1;
      });
      temp.setTitle(item.getFormname());
      temp.setDescription(item.getFormdescription());
      repo.save(temp);
      return item;
    });
    return false;
  }
  @Override
  public boolean saveFilesData(fileDataVM[] fileData) {
    for (fileDataVM item : fileData) {
      var temp=repo.findById(item.getId());
      for (category itemCategory : temp.getCategories()) {
        if(!IntStream.of(item.getCategories()).anyMatch(x -> x == itemCategory.getId()))
          return false;
      }
    }
    return false;
  }

}
