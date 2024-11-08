/*package com.example.notetaking.Controllers;

import com.example.notetaking.Entity.Category;
import com.example.notetaking.Entity.Note;
import com.example.notetaking.Repositories.CategoryRepository;
import com.example.notetaking.Repositories.NotetakingRepository;
import com.example.notetaking.Services.CategoryServiceImpl;
import com.example.notetaking.Services.NoteServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryrepo;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Note mockNote1, mockNote2;
    private Category Ctest;

    @BeforeEach
    public void setup(){
        mockNote1 = new Note(233,"Versuch1","Versuch1",false,null);
        mockNote2 = new Note(235,"Versuch2","Versuch2",true,null);
    }

    //@Test
    //void getCategorybyId() {
      //  int categoryId = 365;
      //  Set<Note> SetofNotes = new HashSet<>();
      //  SetofNotes.add(mockNote1);
      //  SetofNotes.add(mockNote2);
      //  Category category1 = new Category(categoryId,"Ctest1",false,SetofNotes);

        //given(categoryrepo.findById(categoryId)).willReturn(category1);

        //Category savedCategory = categoryService.getCategoryById(categoryId);

        //assertThat(savedCategory).isNotNull();
    //}

    @Test
    void updateCategory() {
        int categoryId = 365;
        Set<Note> SetofNotes = new HashSet<>();
        Category category = new Category(categoryId, "Ctest1", false, SetofNotes);

        given(categoryrepo.save(category)).willReturn(category);
        assertThat(category.getTitle()).isEqualTo("Ctest1");

        category.setTitle("Ctest2");

        Category updatedCategory = categoryService.updateCategory(category);

        assertThat(updatedCategory.getTitle()).isEqualTo("Ctest2");
    }
}

*/
