package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.ImageDto;
import com.tfg.bamashop.dto.ImageRequestDto;
import com.tfg.bamashop.entities.Image;

public interface ImageService {

	public List<ImageDto> getImages();
	
	public ImageDto getImage(Long id);

	public ImageDto saveImage(ImageRequestDto imageRequestDto);
	
	public ImageDto updateImage(Long id, ImageRequestDto imageRequestDto);
	
	public Image findById(Long id);
	
	public void deleteImage(Long id);
}
