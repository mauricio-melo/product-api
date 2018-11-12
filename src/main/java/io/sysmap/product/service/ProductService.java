package io.sysmap.product.service;

import io.sysmap.product.domain.Product;
import io.sysmap.product.dto.ProductDTO;
import io.sysmap.product.exception.ResourceNotFoundException;
import io.sysmap.product.repository.ProductRepository;
import io.sysmap.product.converter.ProductConverter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductConverter converter;

    public Product save(@NonNull final ProductDTO dto){
        Product entity = converter.toEntity(dto);
        entity = repository.save(entity);
        return entity;
    }

    public Product update(@NonNull final ProductDTO dto) {
        Product entity = findById(dto.getId());
        entity = converter.toEntity(dto, entity);
        entity.setUpdateDate(LocalDateTime.now());
        entity = repository.save(entity);
        return entity;
    }

    public Product changeSpecificStatus(Long id){
        Product entityFound = findById(id);
        if(entityFound.isEnabled()){
            entityFound.setEnabled(false);
        } else { entityFound.setEnabled(true);}
        Product entity = repository.save(entityFound);
        return entity;
    }

    public void changeStatus(Long[] id){
        for(int i = 0; i < id.length; i++){
            Product entityFound = findById(id[i]);
            if(entityFound.isEnabled()){
                entityFound.setEnabled(false);
            } else { entityFound.setEnabled(true);}
            Product entity = repository.save(entityFound);
        }
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

}
