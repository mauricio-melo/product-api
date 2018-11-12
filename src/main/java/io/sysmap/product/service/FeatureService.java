package io.sysmap.product.service;

import io.sysmap.product.converter.FeatureConverter;
import io.sysmap.product.domain.Feature;
import io.sysmap.product.dto.FeatureDTO;
import io.sysmap.product.exception.ResourceNotFoundException;
import io.sysmap.product.repository.FeatureRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeatureService {
    
    @Autowired
    private FeatureRepository repository;

    @Autowired
    private FeatureConverter converter;

    @Autowired
    private BunchService bunchService;

    public Feature save(@NonNull final FeatureDTO dto){
        Feature entity = converter.toEntity(dto);
        entity.setBunch(bunchService.findById(dto.getIdBunch()));
        entity = repository.save(entity);
        return entity;
    }

    public Feature update(@NonNull final FeatureDTO dto) {
        Feature entity = findById(dto.getId());
        entity = converter.toEntity(dto, entity);
        entity.setUpdateDate(LocalDateTime.now());
        entity = repository.save(entity);
        return entity;
    }

    public Feature changeSpecificStatus(Long id){
        Feature entityFound = findById(id);
        if(entityFound.isEnabled()){
            entityFound.setEnabled(false);
        } else { entityFound.setEnabled(true);}
        Feature entity = repository.save(entityFound);
        return entity;
    }

    public void changeStatus(Long[] id){
        for(int i = 0; i < id.length; i++){
            Feature entityFound = findById(id[i]);
            if(entityFound.isEnabled()){
                entityFound.setEnabled(false);
            } else { entityFound.setEnabled(true);}
            Feature entity = repository.save(entityFound);
        }
    }

    public Feature findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feature", "id", id));
    }

    public List<Feature> findAll() {
        return repository.findAll();
    }
    
}
