package com.tfg.bamashop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.bamashop.dto.ImageDto;
import com.tfg.bamashop.dto.ImageRequestDto;
import com.tfg.bamashop.entities.Image;
import com.tfg.bamashop.entities.Product;
import com.tfg.bamashop.mappers.ImageMapper;
import com.tfg.bamashop.repositories.ImageRepository;
import com.tfg.bamashop.repositories.PostRepository;
import com.tfg.bamashop.repositories.ProductRepository;
import com.tfg.bamashop.services.ImageService;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	PostRepository postRepository;

	@Override
	public List<ImageDto> getImages() {

		List<Image> images = imageRepository.findAll();

		if (images.size() < 0) {
			throw new RuntimeException("Image don't exist");
		}

		List<ImageDto> imagesDto = new ArrayList<>();

		for (Image image : images) {
			Product product = productRepository.getById(image.getIdProduct().getId());
			ImageDto imageDto = ImageMapper.INSTANCE.entityToDto(image, product);
			imagesDto.add(imageDto);
		}

		return imagesDto;
	}

	@Override
	public ImageDto getImage(Long id) {

		Image image = imageRepository.getById(id);

		if (image == null) {
			throw new RuntimeException("Image does not exist");
		}

		Product product = productRepository.getById(image.getIdProduct().getId());

		ImageDto imageDto = ImageMapper.INSTANCE.entityToDto(image, product);
		return imageDto;
	}

	@Override
	public ImageDto saveImage(ImageRequestDto imageRequestDto) {

		if (imageRequestDto == null) {
			throw new RuntimeException("Image is missing data");
		}

		Image image = ImageMapper.INSTANCE.dtoToEntity(imageRequestDto);

		Image imageRet = imageRepository.save(image);

		Product product = productRepository.getById(imageRet.getIdProduct().getId());

		return ImageMapper.INSTANCE.entityToDto(imageRet, product);
	}

	@Override
	public ImageDto updateImage(Long id, ImageRequestDto imageRequestDto) {

		if (imageRequestDto == null) {
			throw new RuntimeException("Image is missing data");
		}

		Optional<Image> imageExists = imageRepository.findById(id);

		if (imageExists == null) {
			throw new RuntimeException("Image does not exist");
		}

		Image image = ImageMapper.INSTANCE.dtoToEntity(imageRequestDto);
		image.setId(id);

		Image imageRet = imageRepository.save(image);

		Product product = productRepository.getById(imageRet.getIdProduct().getId());

		return ImageMapper.INSTANCE.entityToDto(imageRet, product);
	}

	@Override
	public Image findById(Long id) {

		return imageRepository.getById(id);
	}

	@Override
	public void deleteImage(Long id) {
		imageRepository.deleteById(id);
	}

}
