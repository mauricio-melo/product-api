package io.sysmap.product.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.sysmap.product.converter.ProductConverter;
import io.sysmap.product.domain.Product;
import io.sysmap.product.dto.ProductDTO;
import io.sysmap.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/product")
@Api(value = "Product", description = "Operations available for product resource.")
public class ProductResource {

    @Autowired
    private ProductConverter converter;

    @Autowired
    private ProductService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creating a new resource", responseReference = "New resource created.")
    public ResponseEntity<Void> create(@Valid @RequestBody final ProductDTO dto) {
        final Product entity = service.save(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update for a specific resource", responseReference = "200 = Resource updated")
    public ResponseEntity<Void> update(@Valid @RequestBody final ProductDTO dto) {
        final Product entity = service.update(dto);
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
        final Product entity = service.changeSpecificStatus(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search for a specific resource", response = ProductDTO.class)
    public ResponseEntity<ProductDTO> findById(@PathVariable final Long id) {
        final Product entity = service.findById(id);
        final ProductDTO dto = converter.toDTO(entity);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all", response = ProductDTO.class, responseContainer = "List")
    public ResponseEntity<List<ProductDTO>> list() {
        final List<Product> entityList = service.findAll();
        final List<ProductDTO> responseList = converter.toDTO(entityList);
        return ResponseEntity.ok(responseList);
    }
}
