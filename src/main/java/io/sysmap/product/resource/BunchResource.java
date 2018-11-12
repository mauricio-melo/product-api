package io.sysmap.product.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.sysmap.product.converter.BunchConverter;
import io.sysmap.product.domain.Bunch;
import io.sysmap.product.dto.BunchDTO;
import io.sysmap.product.service.BunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/bunch")
@Api(value = "Bunch", description = "Operations available for bunch resource.")
public class BunchResource {
    
    @Autowired
    private BunchConverter converter;

    @Autowired
    private BunchService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creating a new resource", responseReference = "New resource created.")
    public ResponseEntity<Void> create(@Valid @RequestBody final BunchDTO dto) {
        final Bunch entity = service.save(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update for a specific resource", responseReference = "200 = Resource updated")
    public ResponseEntity<Void> update(@Valid @RequestBody final BunchDTO dto) {
        final Bunch entity = service.update(dto);
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
        final Bunch entity = service.changeSpecificStatus(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search for a specific resource", response = BunchDTO.class)
    public ResponseEntity<BunchDTO> findById(@PathVariable final Long id) {
        final Bunch entity = service.findById(id);
        final BunchDTO dto = converter.toDTO(entity);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all", response = BunchDTO.class, responseContainer = "List")
    public ResponseEntity<List<BunchDTO>> list() {
        final List<Bunch> entityList = service.findAll();
        final List<BunchDTO> responseList = converter.toDTO(entityList);
        return ResponseEntity.ok(responseList);
    }
}
