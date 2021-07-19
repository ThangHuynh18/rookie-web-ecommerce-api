package com.rookie.webwatch.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageDTO {
    private int pageNumber;
    private int pageSize;
    private Sort.Direction sort;
    private String sortBy;

}
