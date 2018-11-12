package io.sysmap.product.service;

import io.sysmap.product.converter.CompanyConverter;
import io.sysmap.product.converter.ProductConverter;
import io.sysmap.product.domain.Company;
import io.sysmap.product.domain.Product;
import io.sysmap.product.dto.CompanyDTO;
import io.sysmap.product.exception.ResourceNotFoundException;
import io.sysmap.product.repository.CompanyRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository repository;

    @Autowired
    private CompanyConverter converter;

    @Autowired
    private ProductService productService;

    public Company save(@NonNull final CompanyDTO dto){
        Company entity = converter.toEntity(dto);
        entity = repository.save(entity);
        return entity;
    }

    public Company saveProduct(Long[] idProduct, Long idCompany) {
        Company entityFound = findById(idCompany);
        for(int i = 0; i < idProduct.length; i++){
            Product product = productService.findById(idProduct[i]);
            entityFound.getProduct().add(product);
        }
        Company entity = repository.save(entityFound);
        return entity;
    }

    public Company update(@NonNull final CompanyDTO dto) {
        Company entity = findById(dto.getId());
        entity = converter.toEntity(dto, entity);
        entity.setUpdateDate(LocalDateTime.now());
        entity = repository.save(entity);
        return entity;
    }

    public Company changeSpecificStatus(Long id){
        Company entityFound = findById(id);
        if(entityFound.isEnabled()){
            entityFound.setEnabled(false);
        } else { entityFound.setEnabled(true);}
        Company entity = repository.save(entityFound);
        return entity;
    }

    public void changeStatus(Long[] id){
        for(int i = 0; i < id.length; i++){
            Company entityFound = findById(id[i]);
            if(entityFound.isEnabled()){
                entityFound.setEnabled(false);
            } else { entityFound.setEnabled(true);}
            Company entity = repository.save(entityFound);
        }
    }

    public Company findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

}
