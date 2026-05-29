package com.artgallery.artservice.controller;


import com.artgallery.artservice.dto.ArtworkRequest;
import com.artgallery.artservice.entity.Artwork;
import com.artgallery.artservice.service.ArtworkService;
import com.artgallery.artservice.service.ImageUploadService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkController {

    private  final ArtworkService artworkService;
    private final ImageUploadService imageUploadService;

    public ArtworkController(ArtworkService artworkService, ImageUploadService imageUploadService) {
        this.artworkService = artworkService;
        this.imageUploadService = imageUploadService;
    }
    @PostMapping
    public Artwork createArtwork(@Valid @RequestBody ArtworkRequest request){
        return artworkService.createArtwork(request);
    }


    @GetMapping
    public List<Artwork> getAllArtworks(){
        return artworkService.getAllArtworks();
    }


    @GetMapping("/{id}")
    public Artwork getArtworkById(@PathVariable Long id) {
        return artworkService.getArtworkById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteArtwork(@PathVariable Long id) {

        artworkService.deleteArtwork(id);

        return "Artwork deleted successfully";
    }


    @PostMapping("/upload")
    public String uploadArtworkImage(@RequestParam("file")MultipartFile file) throws IOException{
       return imageUploadService.uploadImage(file);
    }

}
