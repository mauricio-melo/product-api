package io.sysmap.product.resource;

import io.sysmap.product.base.AbstractTest;
import io.sysmap.product.converter.BunchConverter;
import io.sysmap.product.domain.Bunch;
import io.sysmap.product.dto.BunchDTO;
import io.sysmap.product.repository.BunchRepository;
import io.sysmap.product.service.BunchService;
import io.sysmap.product.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BunchResourceTests extends AbstractTest {

    @Autowired
    private BunchRepository repository;

    @Autowired
    private BunchService service;

    @Autowired
    private BunchConverter converter;

    @Autowired
    private ProductService productService;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getBunchList() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(BUNCH_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        BunchDTO[] bunchlist = super.mapFromJson(content, BunchDTO[].class);
        assertTrue(bunchlist.length > 0);
    }

    @Test
    public void getBunch() throws Exception {
        String uri = "/bunch/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();

        BunchDTO dto = super.mapFromJson(content, BunchDTO.class);

        Bunch entity = converter.toEntity(dto);
        entity.setProduct(productService.findById(dto.getIdProduct()));

        Bunch bunchFound = service.findById(1L);

        assertEquals(entity.getName(), bunchFound.getName());
    }

    @Test
    public void postBunch() throws Exception {
        final long countBeforeSave = this.repository.count();

        BunchDTO bunch = BunchDTO.builder()
                .name("Automação")
                .description("Função automação")
                .enabled(true)
                .userCreation("Mauricio Melo")
                .idProduct(1L)
                .build();


        String inputJson = super.mapToJson(bunch);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(BUNCH_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        final long countAfterSave = this.repository.count();
        assertEquals(countBeforeSave + 1, countAfterSave);
    }

    @Test
    public void putBunch() throws Exception {
        BunchDTO bunch = BunchDTO.builder()
                .id(1L)
                .name("Automação")
                .description("Função automação")
                .enabled(true)
                .userCreation("Mauricio Melo")
                .idProduct(1L)
                .build();


        String inputJson = super.mapToJson(bunch);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(BUNCH_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        Bunch productFound = service.findById(1L);
        assertEquals(bunch.getDescription(), productFound.getDescription());
    }

    @Test
    public void changeStatusForASpecificCompany() throws Exception {
        String uri = "/bunch/status/1";

        Bunch bunchFound = service.findById(1L);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        Bunch bunch = service.findById(1L);

        assertTrue(bunchFound.isEnabled() != bunch.isEnabled());
    }
}
