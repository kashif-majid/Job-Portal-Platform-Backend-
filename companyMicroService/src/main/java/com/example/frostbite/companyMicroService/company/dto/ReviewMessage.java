package com.example.frostbite.companyMicroService.company.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReviewMessage {
    private Long id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;


}
