package com.ara.photoalvand.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ara.photoalvand.models.category;
import com.ara.photoalvand.models.file;
import com.ara.photoalvand.models.fileDataVM;
import com.ara.photoalvand.repository.UserRepository;
import com.ara.photoalvand.repository.categoryRepository;
import com.ara.photoalvand.repository.fileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FilesStorageServiceImpl implements FilesStorageService {
  
	private String fileUploadPath;

  private final Path root;
  @Autowired private fileRepository repo;
  @Autowired private categoryRepository categoryRepo;
  @Autowired private UserRepository repoUser;
  
  public FilesStorageServiceImpl(@Value("${ara.photoalvand.fileUploadPath}") String fileUploadPath){
    super();
    this.fileUploadPath=fileUploadPath;
    System.out.println(this.fileUploadPath);
    root=Paths.get(fileUploadPath); 
  }

  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public file save(MultipartFile file) {
    var miliSecondTimeFileName=
     java.time.ZonedDateTime.now().toInstant().toEpochMilli()+
     "--"+file.getOriginalFilename();
    try {
      Files.copy(file.getInputStream(), this.root.resolve(miliSecondTimeFileName));
      var entity=new file();
      entity.setTitle("");
      entity.setPhysicalPath(miliSecondTimeFileName);
      entity.setReviewed(false);
      entity.setOtherUse(false);
      entity=repo.save(entity);
      return entity;
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
  public fileDataVM findFile(int id){
    try{
      return convertFileToFileDataVM(repo.findById(id));
    }catch(Exception e){
      e.printStackTrace();
      return null;}
  }
  @Override 
  public List<fileDataVM> findFiles(int[] ids){
    List<fileDataVM> list=new ArrayList<>();
    Arrays.stream(ids).forEach(id->list.add(this.findFile(id)));
    return list;
  }
  @Override
  public boolean saveFilesData(Stream<fileDataVM> fileData) {
    fileData.map(item -> {
      var temp = repo.findById(item.getId());
      Arrays.stream(item.getCategories()).map(category -> {
        var cat = categoryRepo.findCategoryById(category).get();
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
    try {
      for (fileDataVM item : fileData) {
        if (item!=null && (item.getFormname().length() > 0 || item.getFormdescription().length() > 0)) {
          var temp = repo.findById(item.getId());
          var currenUser=repoUser.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
          temp.setCategories(categoryRepo.findCategoryByIds(List.of(item.getCategories())));
          temp.setTitle(item.getFormname());
          temp.setDescription(item.getFormdescription());
          temp.setPrice(item.getFormprice());
          temp.setReviewed(true);
          temp.setOwner(currenUser);
          temp.setCreateDate(new Date());
          repo.save(temp);
        }
      }
    } catch (Exception e) {

    }
   

    return false;
  }
   
  private static fileDataVM convertFileToFileDataVM(file item) {
    if (item != null) {
      var vm = new fileDataVM();
      vm.setFormname(item.getTitle());
      vm.setFormdescription(item.getDescription());
      vm.setFormprice(item.getPrice());
      vm.setId(item.getId());
      if (item.getOwner()!=null) vm.setUserName(item.getOwner().getUsername());
      vm.setCreateDate(item.getCreateDate());
      vm.setCat(item.getCategories().stream().map(c->c).collect(Collectors.toList()).toArray(category[]::new));
      vm.setCategories(
          item.getCategories().stream().map(x -> x.getId()).collect(Collectors.toList()).toArray(Integer[]::new));
      vm.setFilePath(util.convertFileNameToPath( item.getPhysicalPath()));    
      return vm;
    } else
      return null;
  }

}
