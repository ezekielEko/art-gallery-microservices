package com.artgallery.artservice.service;


import com.artgallery.artservice.dto.ArtworkRequest;
import com.artgallery.artservice.entity.Artwork;
import com.artgallery.artservice.repository.ArtworkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtworkService {

    private final ArtworkRepository artworkRepository;

    public Artwork createArtwork(ArtworkRequest request){
        Artwork artwork = Artwork.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .artistName(request.getArtistName())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .category(request.getCategory())
                .available(true)
                .createdAt(LocalDateTime.now())
                .build();


        return artworkRepository.save(artwork);
    }

    public List<Artwork> getAllArtworks(){
        return artworkRepository.findAll();
    }

    public Artwork getArtworkById(Long id){
        return artworkRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Artwork not found"));
    }

    public  void deleteArtwork(Long id){
        artworkRepository.deleteById(id);
    }


}
