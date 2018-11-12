package io.sysmap.product.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.sysmap.product.converter.CompanyConverter;
import io.sysmap.product.domain.Company;
import io.sysmap.product.dto.CompanyDTO;
import io.sysmap.product.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/company")
@Api(value = "Company", description = "Operations available for company resource.")
public class CompanyResource {

    @Autowired
    private CompanyConverter converter;

    @Autowired
    private CompanyService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creating a new resource", responseReference = "New resource created.")
    public ResponseEntity<Void> create(@Valid @RequestBody final CompanyDTO dto) {
        final Company entity = service.save(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(path = "/product/{idCompany}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Inserindo produtos na empresa", responseReference = "Empresas vinculadas com sucesso.")
    public ResponseEntity<Void> saveProduct(@PathVariable final Long idCompany,
                                                 @Valid @RequestBody final Long idProduct[]) {

        final Company entity = service.saveProduct(idProduct, idCompany);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
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
        final Company entity = service.changeSpecificStatus(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update for a specific resource", responseReference = "200 = Resource updated")
    public ResponseEntity<Void> update(@Valid @RequestBody final CompanyDTO dto) {
        final Company entity = service.update(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search for a specific resource", response = CompanyDTO.class)
    public ResponseEntity<CompanyDTO> findById(@PathVariable final Long id) {
        final Company entity = service.findById(id);
        final CompanyDTO dto = converter.toDTO(entity);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all", response = CompanyDTO.class, responseContainer = "List")
    public ResponseEntity<List<CompanyDTO>> list() {
        final List<Company> entityList = service.findAll();
        final List<CompanyDTO> responseList = converter.toDTO(entityList);
        return ResponseEntity.ok(responseList);
    }
}
