package io.sysmap.product.resource;

import io.sysmap.product.base.AbstractTest;
import io.sysmap.product.domain.Company;
import io.sysmap.product.dto.CompanyDTO;
import io.sysmap.product.repository.CompanyRepository;
import io.sysmap.product.service.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CompanyResourceTests extends AbstractTest {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private CompanyService service;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getCompanyList() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(COMPANY_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Company[] companylist = super.mapFromJson(content, Company[].class);
        assertTrue(companylist.length > 0);
    }

    @Test
    public void getCompany() throws Exception {
        String uri = "/company/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Company company = super.mapFromJson(content, Company.class);
        Company companyFound = service.findById(1L);
        assertEquals(company.getCnpj(), companyFound.getCnpj());
    }

    @Test
    public void postCompany() throws Exception {
        final long countBeforeSave = this.repository.count();

        CompanyDTO company = CompanyDTO.builder()
                .cnpj(61109923000189L)
                .build();


        String inputJson = super.mapToJson(company);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(COMPANY_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        final long countAfterSave = this.repository.count();
        assertEquals(countBeforeSave + 1, countAfterSave);
    }

    @Test
    public void putCompany() throws Exception {
        CompanyDTO company = CompanyDTO.builder()
                .id(1L)
                .cnpj(61309823000144L)
                .build();


        String inputJson = super.mapToJson(company);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(COMPANY_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        Company companyFound = service.findById(1L);
        assertEquals(company.getCnpj(), companyFound.getCnpj());
    }

    @Test
    public void changeStatusForASpecificCompany() throws Exception {
        String uri = "/company/status/1";

        Company companyFound = service.findById(1L);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        Company company = service.findById(1L);

        assertTrue(companyFound.isEnabled() != company.isEnabled());
    }
}
