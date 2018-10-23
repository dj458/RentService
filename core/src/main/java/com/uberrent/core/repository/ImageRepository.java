package com.uberrent.core.repository;

import com.uberrent.core.domain.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

    @Query("select a from Image a where a.id=?1")
    Optional<Image> findByImageId(Long id);

//    @Query("select a from Image a where a.username=?1")
//    Optional<Image> findByUsername(String username);
}
