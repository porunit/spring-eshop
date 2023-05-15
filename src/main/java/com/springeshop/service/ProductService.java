package com.springeshop.service;

import com.springeshop.data.domain.Category;
import com.springeshop.data.domain.Manufacturer;
import com.springeshop.data.domain.Product;
import com.springeshop.data.dto.ProductDTO;
import com.springeshop.exceptions.CategoryNotFoundException;
import com.springeshop.exceptions.ManufacturerNotFoundException;
import com.springeshop.mappers.ProductMapper;
import com.springeshop.repositories.CategoryRepository;
import com.springeshop.repositories.ManufacturerRepository;
import com.springeshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper = ProductMapper.MAPPER;


    public List<ProductDTO> getAll() {
        return productMapper.toProductDTOList(productRepository.findAll());
    }

    public void save(ProductDTO productDTO) {
        Manufacturer manufacturer = manufacturerRepository.findFirstByName(productDTO.getManufacturer());
        if (manufacturer == null) {
            throw new ManufacturerNotFoundException("Manufacturer with name: " +
                    productDTO.getManufacturer() + " doesnt exist");
        }
        Category category = categoryRepository.findFirstByName(productDTO.getCategory());
        if (category == null) {
            throw new CategoryNotFoundException("Category with name: " +
                    productDTO.getCategory() + " doesnt exist");
        }
        Product product = Product.builder()
                .name(productDTO.getName())
                .manufacturer(manufacturer)
                .price(productDTO.getPrice())
                .category(category)
                .build();
        productRepository.save(product);
    }

    public List<ProductDTO> searchProducts(String searchTerm) {
        List<ProductDTO> dtoList = productMapper.toProductDTOList(productRepository.findAllByNameIsContaining(searchTerm));
        if (dtoList.isEmpty()) {
            dtoList = productMapper.toProductDTOList(productRepository.findBySearchTermFuzzy(searchTerm,5));
        }
        return dtoList;
    }
}
