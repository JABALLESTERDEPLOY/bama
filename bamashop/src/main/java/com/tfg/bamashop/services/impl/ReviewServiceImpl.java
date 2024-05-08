package com.tfg.bamashop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.bamashop.dto.ReviewDto;
import com.tfg.bamashop.dto.ReviewRequestDto;
import com.tfg.bamashop.entities.Post;
import com.tfg.bamashop.entities.Review;
import com.tfg.bamashop.entities.User;
import com.tfg.bamashop.mappers.ReviewMapper;
import com.tfg.bamashop.repositories.PostRepository;
import com.tfg.bamashop.repositories.ReviewRepository;
import com.tfg.bamashop.repositories.UserRepository;
import com.tfg.bamashop.services.ReviewService;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<ReviewDto> getReviews() {

		List<Review> reviews = reviewRepository.findAll();

		if (reviews.size() < 0) {
			throw new RuntimeException("Reviews don't exist");
		}

		List<ReviewDto> reviewsDto = new ArrayList<>();

		for (Review review : reviews) {
			Post post = postRepository.getById(review.getIdPost().getId());
			User user = userRepository.getById(review.getIdUser().getId());
			ReviewDto reviewDto = ReviewMapper.INSTANCE.entityToDto(review, post, user);
			reviewsDto.add(reviewDto);
		}

		return reviewsDto;
	}

	@Override
	public ReviewDto getReview(Long id) {

		Review review = reviewRepository.getById(id);

		if (review == null) {
			throw new RuntimeException("Review does not exist");
		}

		Post post = postRepository.getById(review.getIdPost().getId());
		User user = userRepository.getById(review.getIdUser().getId());

		ReviewDto reviewDto = ReviewMapper.INSTANCE.entityToDto(review, post, user);
		return reviewDto;
	}

	@Override
	public ReviewDto saveReview(ReviewRequestDto reviewRequestDto) {

		if (reviewRequestDto == null) {
			throw new RuntimeException("Review is missing data");
		}

		Review review = ReviewMapper.INSTANCE.dtoToEntity(reviewRequestDto);

		Review reviewRet = reviewRepository.save(review);

		Post post = postRepository.getById(reviewRet.getIdPost().getId());
		User user = userRepository.getById(reviewRet.getIdUser().getId());

		return ReviewMapper.INSTANCE.entityToDto(reviewRet, post, user);
	}

	@Override
	public ReviewDto updateReview(Long id, ReviewRequestDto reviewRequestDto) {

		if (reviewRequestDto == null) {
			throw new RuntimeException("Review is missing data");
		}

		Optional<Review> reviewExists = reviewRepository.findById(id);

		if (reviewExists == null) {
			throw new RuntimeException("Review does not exist");
		}

		Review review = ReviewMapper.INSTANCE.dtoToEntity(reviewRequestDto);
		review.setId(id);

		Review reviewRet = reviewRepository.save(review);

		Post post = postRepository.getById(reviewRet.getIdPost().getId());
		User user = userRepository.getById(reviewRet.getIdUser().getId());

		return ReviewMapper.INSTANCE.entityToDto(reviewRet, post, user);
	}

	@Override
	public Review findById(Long id) {
		return reviewRepository.getById(id);
	}

	@Override
	public void deleteReview(Long id) {
		reviewRepository.deleteById(id);
	}

}
