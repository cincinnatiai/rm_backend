package com.example.demo.repository;

import com.example.demo.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findFirstByNameAndUrl(String name, String url);//This is automatic, it generates the query automatically, that's why I don't need @Query on top, Jpa has some keyWords like find to do this :)
}
