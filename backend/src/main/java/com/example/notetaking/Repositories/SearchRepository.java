package com.example.notetaking.Repositories;

import com.example.notetaking.DTO.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface SearchRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    //List<T> searchBy(String text, int limit, String... fields);

    PageDTO<T> searchPageBy(String text, int limit, int offset, String... fields);
}
