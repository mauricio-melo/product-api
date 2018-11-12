package io.sysmap.product.converter;

import io.sysmap.product.domain.Product;
import io.sysmap.product.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    public Product toEntity(ProductDTO dto) {
        return toEntity(dto, Product.builder().build());
    }

    public Product toEntity(ProductDTO dto, Product entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setEnabled(dto.isEnabled());
        entity.setUserCreation(dto.getUserCreation());
        return entity;
    }

    public ProductDTO toDTO(Product entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .enabled(entity.isEnabled())
                .userCreation(entity.getUserCreation())
                .build();
    }

    public List<ProductDTO> toDTO(List<Product> entityList) {
        List<ProductDTO> dtoList = new ArrayList<ProductDTO>();
        entityList.forEach(
                entity ->
                        dtoList.add(toDTO(entity))
        );
        return dtoList;
    }

}

