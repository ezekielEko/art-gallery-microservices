package com.artgallery.artservice.repository;

import com.artgallery.artservice.entity.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
}