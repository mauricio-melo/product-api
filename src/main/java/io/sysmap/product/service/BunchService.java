package io.sysmap.product.service;

import io.sysmap.product.converter.BunchConverter;
import io.sysmap.product.domain.Bunch;
import io.sysmap.product.dto.BunchDTO;
import io.sysmap.product.exception.ResourceNotFoundException;
import io.sysmap.product.repository.BunchRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BunchService {

    @Autowired
    private BunchRepository repository;

    @Autowired
    private BunchConverter converter;

    @Autowired
    private ProductService productService;

    public Bunch save(@NonNull final BunchDTO dto){
        Bunch entity = converter.toEntity(dto);
        entity.setProduct(productService.findById(dto.getIdProduct()));
        entity = repository.save(entity);
        return entity;
    }

    public Bunch update(@NonNull final BunchDTO dto) {
        Bunch entity = findById(dto.getId());
        entity = converter.toEntity(dto, entity);
        entity.setUpdateDate(LocalDateTime.now());
        entity = repository.save(entity);
        return entity;
    }

    public Bunch changeSpecificStatus(Long id){
        Bunch entityFound = findById(id);
        if(entityFound.isEnabled()){
            entityFound.setEnabled(false);
        } else { entityFound.setEnabled(true);}
        Bunch entity = repository.save(entityFound);
        return entity;
    }

    public void changeStatus(Long[] id){
        for(int i = 0; i < id.length; i++){
            Bunch entityFound = findById(id[i]);
            if(entityFound.isEnabled()){
                entityFound.setEnabled(false);
            } else { entityFound.setEnabled(true);}
            Bunch entity = repository.save(entityFound);
        }
    }

    public Bunch findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bunch", "id", id));
    }

    public List<Bunch> findAll() {
        return repository.findAll();
    }
    
}
