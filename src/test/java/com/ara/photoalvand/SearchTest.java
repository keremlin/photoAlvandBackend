package com.ara.photoalvand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ara.photoalvand.exception.RecordNotFoundException;
import com.ara.photoalvand.models.category;
import com.ara.photoalvand.models.file;
import com.ara.photoalvand.repository.categoryRepository;
import com.ara.photoalvand.repository.fileRepository;
import com.ara.photoalvand.services.SearchImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchTest {
    @Mock
    private fileRepository repoFile;
    @Mock
    private categoryRepository repoCategory;
    @InjectMocks
    private SearchImpl service;

    @BeforeEach
    void beforeEach() {
        var item = new category(110, "new", "desc", null, null);
        List<file> list = new ArrayList<>();
        var fileItem = new file();
        fileItem.setPhysicalPath("xxxx.jpg");
        fileItem.setId(220);
        list.add(fileItem);

        when(repoCategory.findCategoryById(110))
                .thenReturn(Optional.of(item));

        when(repoFile.findFileBycategories(item))
                .thenReturn(list);
    }

    @Test
    void checkByCategory() {
        assertEquals(1, service.byCategory(110).size());
        assertEquals(220, service.byCategory(110).get(0).getId());
    }

    @Test
    void checkByCategoryException() {
        assertThrows(RecordNotFoundException.class, () -> service.byCategory(990));
    }
}
