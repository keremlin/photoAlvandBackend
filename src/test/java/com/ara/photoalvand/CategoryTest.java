package com.ara.photoalvand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ara.photoalvand.exception.RecordNotFoundException;
import com.ara.photoalvand.exception.RecordPersistanceException;
import com.ara.photoalvand.models.*;
import com.ara.photoalvand.repository.categoryRepository;
import com.ara.photoalvand.repository.fileRepository;
import com.ara.photoalvand.services.CategoryImpl;
import com.ara.photoalvand.services.SearchImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryTest {

    @Mock
    private categoryRepository repo;

    @Mock
    private SearchImpl search;

    @InjectMocks
    private CategoryImpl service;

    @Mock
    private fileRepository repoFile;

    @BeforeEach
    void beforeEach() {
        category item = new category(110, "item", "descItem", null, null);

        when(repo.save(ArgumentMatchers.<category>any()))
                .thenAnswer(i -> (category) i.getArguments()[0]);

        when(repo.findCategoryById(110))
                .thenReturn(Optional.of(item));

        var item2 = new category(110, "new", "desc", null, null);
        List<file> list = new ArrayList<>();
        var fileItem = new file();
        fileItem.setPhysicalPath("xxxx.jpg");
        fileItem.setId(220);
        list.add(fileItem);

        when(repo.findCategoryById(110))
                .thenReturn(Optional.of(item2));

        when(repoFile.findFileBycategories(item2))
                .thenReturn(list);
    }

    @Test
    void checkCreate() {
        assertEquals("new",
                service.create(new category(1, "new", "des", null, null)).getName());
    }

    @Test
    void checkFindById(){
        assertEquals("item", service.findById(110).getName());
    }
    @Test 
    void checkFindByIdException(){
        assertThrows(RecordNotFoundException.class,()->service.findById(120));
    }
    @Test
    void checkDelete(){
        assertEquals("item", service.delete(110).getName());
    }
    
    @Test
    void checkDeleteException() {
        assertThrows(RecordPersistanceException.class, () -> service.delete(111));
    }
    @Test
    void checkFindWithRandomImageException(){
        assertThrows(RecordNotFoundException.class,()-> service.findWithRandomImage(110));
    }
}
