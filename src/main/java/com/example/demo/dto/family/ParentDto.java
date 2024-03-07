package com.example.demo.dto.family;

import lombok.Builder;
import lombok.Data;

@Data
public class ParentDto {
    private String name;
    private ChildDto childDto;
}
