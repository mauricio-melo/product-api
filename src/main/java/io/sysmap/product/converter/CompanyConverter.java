package io.sysmap.product.converter;

import io.sysmap.product.domain.Company;
import io.sysmap.product.dto.CompanyDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyConverter {

    public Company toEntity(CompanyDTO dto) {
        return toEntity(dto, Company.builder().build());
    }

    public Company toEntity(CompanyDTO dto, Company entity) {
        entity.setId(dto.getId());
        entity.setCnpj(dto.getCnpj());
        entity.setEnabled(dto.isEnabled());
        entity.setUserCreation(dto.getUserCreation());
        return entity;
    }

    public CompanyDTO toDTO(Company entity) {
        return CompanyDTO.builder()
                .id(entity.getId())
                .cnpj(entity.getCnpj())
                .enabled(entity.isEnabled())
                .userCreation(entity.getUserCreation())
                .build();
    }

    public List<CompanyDTO> toDTO(List<Company> entityList) {
        List<CompanyDTO> dtoList = new ArrayList<CompanyDTO>();
        entityList.forEach(
                entity ->
                        dtoList.add(toDTO(entity))
        );
        return dtoList;
    }
}
