package com.artgallery.artservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArtworkRequest {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String artistName;

    @NotNull
    private BigDecimal price;

    private String imageUrl;

    private String category;
}