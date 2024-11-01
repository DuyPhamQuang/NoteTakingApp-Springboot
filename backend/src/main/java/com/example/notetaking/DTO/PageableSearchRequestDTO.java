package com.example.notetaking.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.Min;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageableSearchRequestDTO extends SearchRequestDTO{

    @Min(0)
    private int pageOffset;
}