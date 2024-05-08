package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.PostDto;
import com.tfg.bamashop.dto.PostRequestDto;
import com.tfg.bamashop.entities.Post;

public interface PostService {

	public List<PostDto> getPosts();
	
	public PostDto getPost(Long id);

	public PostDto savePost(PostRequestDto postRequestDto);
	
	public PostDto updatePost(Long id, PostRequestDto postRequestDto);
	
	public Post findById(Long id);

	void deletePost(Long id);
}
