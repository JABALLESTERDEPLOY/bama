package com.tfg.bamashop.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.bamashop.entities.Post;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

}
