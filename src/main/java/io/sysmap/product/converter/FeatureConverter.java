package io.sysmap.product.converter;

import io.sysmap.product.domain.Feature;
import io.sysmap.product.dto.FeatureDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeatureConverter {

    public Feature toEntity(FeatureDTO dto) {
        return toEntity(dto, Feature.builder().build());
    }

    public Feature toEntity(FeatureDTO dto, Feature entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setEnabled(dto.isEnabled());
        entity.setUserCreation(dto.getUserCreation());
        return entity;
    }

    public FeatureDTO toDTO(Feature entity) {
        return FeatureDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .enabled(entity.isEnabled())
                .userCreation(entity.getUserCreation())
                .idBunch(entity.getBunch().getId())
                .build();
    }

    public List<FeatureDTO> toDTO(List<Feature> entityList) {
        List<FeatureDTO> dtoList = new ArrayList<FeatureDTO>();
        entityList.forEach(
                entity ->
                        dtoList.add(toDTO(entity))
        );
        return dtoList;
    }
}
