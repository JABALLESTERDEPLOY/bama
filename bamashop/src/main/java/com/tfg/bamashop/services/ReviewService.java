package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.ReviewDto;
import com.tfg.bamashop.dto.ReviewRequestDto;
import com.tfg.bamashop.entities.Review;

public interface ReviewService {

	public List<ReviewDto> getReviews();

	public ReviewDto getReview(Long id);

	public ReviewDto saveReview(ReviewRequestDto reviewRequestDto);

	public ReviewDto updateReview(Long id, ReviewRequestDto reviewRequestDto);

	public Review findById(Long id);

	public void deleteReview(Long id);
}
