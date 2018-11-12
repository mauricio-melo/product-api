package io.sysmap.product.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.sysmap.product.converter.FeatureConverter;
import io.sysmap.product.domain.Feature;
import io.sysmap.product.dto.FeatureDTO;
import io.sysmap.product.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/feature")
@Api(value = "Feature", description = "Operations available for feature resource.")
public class FeatureResource {

    @Autowired
    private FeatureConverter converter;

    @Autowired
    private FeatureService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creating a new resource", responseReference = "New resource created.")
    public ResponseEntity<Void> create(@Valid @RequestBody final FeatureDTO dto) {
        final Feature entity = service.save(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update for a specific resource", responseReference = "200 = Resource updated")
    public ResponseEntity<Void> update(@Valid @RequestBody final FeatureDTO dto) {
        final Feature entity = service.update(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/status", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Change status for a specifics resources", responseReference = "200 = Resource updated.")
    public ResponseEntity<Void> changeStatus(@Valid @RequestBody final Long id[]) {
        service.changeStatus(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/status/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Change status for a specific resource", responseReference = "200 = Resource updated.")
    public ResponseEntity<Void> changeSpecificStatus(@PathVariable final Long id) {
        final Feature entity = service.changeSpecificStatus(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search for a specific resource", response = FeatureDTO.class)
    public ResponseEntity<FeatureDTO> findById(@PathVariable final Long id) {
        final Feature entity = service.findById(id);
        final FeatureDTO dto = converter.toDTO(entity);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all", response = FeatureDTO.class, responseContainer = "List")
    public ResponseEntity<List<FeatureDTO>> list() {
        final List<Feature> entityList = service.findAll();
        final List<FeatureDTO> responseList = converter.toDTO(entityList);
        return ResponseEntity.ok(responseList);
    }
}
