package io.sysmap.product.converter;

import io.sysmap.product.domain.Bunch;
import io.sysmap.product.dto.BunchDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BunchConverter {
    
    public Bunch toEntity(BunchDTO dto) {
        return toEntity(dto, Bunch.builder().build());
    }

    public Bunch toEntity(BunchDTO dto, Bunch entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setEnabled(dto.isEnabled());
        entity.setUserCreation(dto.getUserCreation());
        return entity;
    }

    public BunchDTO toDTO(Bunch entity) {
        return BunchDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .enabled(entity.isEnabled())
                .userCreation(entity.getUserCreation())
                .idProduct(entity.getProduct().getId())
                .build();
    }

    public List<BunchDTO> toDTO(List<Bunch> entityList) {
        List<BunchDTO> dtoList = new ArrayList<BunchDTO>();
        entityList.forEach(
                entity ->
                        dtoList.add(toDTO(entity))
        );
        return dtoList;
    }
    
}
