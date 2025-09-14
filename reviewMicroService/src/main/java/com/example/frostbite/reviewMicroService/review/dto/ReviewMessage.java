package com.example.frostbite.reviewMicroService.review.dto;

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
