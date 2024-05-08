package com.tfg.bamashop.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.bamashop.dto.PostDto;
import com.tfg.bamashop.dto.PostRequestDto;
import com.tfg.bamashop.entities.Category;
import com.tfg.bamashop.entities.Image;
import com.tfg.bamashop.entities.Post;
import com.tfg.bamashop.entities.Product;
import com.tfg.bamashop.entities.User;
import com.tfg.bamashop.mappers.PostMapper;
import com.tfg.bamashop.repositories.CategoryRepository;
import com.tfg.bamashop.repositories.ImageRepository;
import com.tfg.bamashop.repositories.PostRepository;
import com.tfg.bamashop.repositories.ProductRepository;
import com.tfg.bamashop.repositories.UserRepository;
import com.tfg.bamashop.services.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<PostDto> getPosts() {

		List<Post> posts = postRepository.findAll();

		if (posts.size() < 0) {
			throw new RuntimeException("Post don't exist");
		}

		List<PostDto> postsDto = new ArrayList<>();

		for (Post post : posts) {
			Category category = categoryRepository.getById(post.getIdCategory().getId());
			User user = userRepository.getById(post.getIdSeller().getId());
			Product product = productRepository.getById(post.getIdProduct().getId());
			PostDto postDto = PostMapper.INSTANCE.entityToDto(post, category, user, product);
			postsDto.add(postDto);
		}

		return postsDto;
	}

	@Override
	public PostDto getPost(Long id) {

		Post post = postRepository.getById(id);

		if (post == null) {
			throw new RuntimeException("Post does not exist");
		}

		Category category = categoryRepository.getById(post.getIdCategory().getId());
		User user = userRepository.getById(post.getIdSeller().getId());
		Product product = productRepository.getById(post.getIdProduct().getId());

		PostDto postDto = PostMapper.INSTANCE.entityToDto(post, category, user, product);

		return postDto;
	}

	@Override
	public PostDto savePost(PostRequestDto postRequestDto) {

		if (postRequestDto == null) {
			throw new RuntimeException("Post is missing data");
		}

		Set<Image> images = new HashSet<>();

		for (Long idImage : postRequestDto.getImages()) {
			Image image = imageRepository.getById(idImage);
			if (image != null) {
				images.add(image);
			}
		}

		Post post = PostMapper.INSTANCE.dtoToEntity(postRequestDto, images);

		Post postRet = postRepository.save(post);

		Category category = categoryRepository.getById(postRet.getIdCategory().getId());
		User user = userRepository.getById(postRet.getIdSeller().getId());
		Product product = productRepository.getById(postRet.getIdProduct().getId());

		return PostMapper.INSTANCE.entityToDto(postRet, category, user, product);
	}

	@Override
	public PostDto updatePost(Long id, PostRequestDto postRequestDto) {

		if (postRequestDto == null) {
			throw new RuntimeException("Post is missing data");
		}

		Optional<Post> postExists = postRepository.findById(id);

		if (postExists == null) {
			throw new RuntimeException("Post does not exist");
		}

		Set<Image> images = new HashSet<>();

		for (Long idImage : postRequestDto.getImages()) {
			Image image = imageRepository.getById(idImage);
			if (image != null) {
				images.add(image);
			}
		}

		Post post = PostMapper.INSTANCE.dtoToEntity(postRequestDto, images);
		post.setId(id);

		Post postRet = postRepository.save(post);

		Category category = categoryRepository.getById(postRet.getIdCategory().getId());
		User user = userRepository.getById(postRet.getIdSeller().getId());
		Product product = productRepository.getById(postRet.getIdProduct().getId());

		return PostMapper.INSTANCE.entityToDto(postRet, category, user, product);
	}

	@Override
	public Post findById(Long id) {

		return postRepository.getById(id);
	}

	@Override
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

}
