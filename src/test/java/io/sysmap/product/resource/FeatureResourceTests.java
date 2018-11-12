package io.sysmap.product.resource;

import io.sysmap.product.base.AbstractTest;
import io.sysmap.product.converter.FeatureConverter;
import io.sysmap.product.domain.Feature;
import io.sysmap.product.dto.FeatureDTO;
import io.sysmap.product.repository.FeatureRepository;
import io.sysmap.product.service.BunchService;
import io.sysmap.product.service.FeatureService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FeatureResourceTests extends AbstractTest {

    @Autowired
    private FeatureRepository repository;

    @Autowired
    private FeatureService service;

    @Autowired
    private FeatureConverter converter;

    @Autowired
    private BunchService bunchService;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getFeatureList() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(FEATURE_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        FeatureDTO[] featurelist = super.mapFromJson(content, FeatureDTO[].class);
        assertTrue(featurelist.length > 0);
    }

    @Test
    public void getFeature() throws Exception {
        String uri = "/feature/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();

        FeatureDTO dto = super.mapFromJson(content, FeatureDTO.class);

        Feature entity = converter.toEntity(dto);
        entity.setBunch(bunchService.findById(dto.getIdBunch()));

        Feature featureFound = service.findById(1L);

        assertEquals(entity.getName(), featureFound.getName());
    }

    @Test
    public void postFeature() throws Exception {
        final long countBeforeSave = this.repository.count();

        FeatureDTO feature = FeatureDTO.builder()
                .name("Enviar SMS")
                .description("Feature enviar SMS")
                .enabled(true)
                .userCreation("Mauricio Melo")
                .idBunch(1L)
                .build();


        String inputJson = super.mapToJson(feature);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(FEATURE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        final long countAfterSave = this.repository.count();
        assertEquals(countBeforeSave + 1, countAfterSave);
    }

    @Test
    public void putFeature() throws Exception {
        FeatureDTO feature = FeatureDTO.builder()
                .id(1L)
                .name("Enviar SMS")
                .description("Feature enviar SMS")
                .enabled(true)
                .userCreation("Mauricio Melo")
                .idBunch(1L)
                .build();


        String inputJson = super.mapToJson(feature);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(FEATURE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        Feature featureFound = service.findById(1L);
        assertEquals(feature.getDescription(), featureFound.getDescription());
    }

    @Test
    public void changeStatusForASpecificFeature() throws Exception {
        String uri = "/feature/status/1";

        Feature featureFound = service.findById(1L);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        Feature feature = service.findById(1L);

        assertTrue(featureFound.isEnabled() != feature.isEnabled());
    }
}
