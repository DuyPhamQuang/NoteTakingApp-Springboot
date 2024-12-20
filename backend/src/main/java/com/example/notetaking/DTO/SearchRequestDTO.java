package com.example.notetaking.DTO;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class SearchRequestDTO {

    @NotBlank
    private String text;

    private List<String> fields = new ArrayList<>();

    @Min(1)
    private int limit;
}
