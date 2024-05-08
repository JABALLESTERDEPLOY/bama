package com.tfg.bamashop.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.bamashop.entities.Image;

@Repository
@Transactional
public interface ImageRepository  extends JpaRepository<Image, Long> {

}
