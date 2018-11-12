package io.sysmap.product.resource;

import io.sysmap.product.base.AbstractTest;
import io.sysmap.product.domain.Product;
import io.sysmap.product.dto.ProductDTO;
import io.sysmap.product.repository.ProductRepository;
import io.sysmap.product.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductResourceTests extends AbstractTest {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductService service;

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getProductList() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_ENDPOINT)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = super.mapFromJson(content, Product[].class);
		assertTrue(productlist.length > 0);
	}

	@Test
	public void getProduct() throws Exception {
		String uri = "/product/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Product product = super.mapFromJson(content, Product.class);
		Product productFound = service.findById(1L);
		assertEquals(product.getName(), productFound.getName());
	}

	@Test
	public void postProduct() throws Exception {
		final long countBeforeSave = this.repository.count();

		ProductDTO product = ProductDTO.builder()
				.name("Advanced")
				.description("Produto advanced")
				.enabled(true)
				.userCreation("Mauricio Melo")
				.build();


		String inputJson = super.mapToJson(product);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(PRODUCT_ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);

		final long countAfterSave = this.repository.count();
		assertEquals(countBeforeSave + 1, countAfterSave);
	}

	@Test
	public void putProduct() throws Exception {
		ProductDTO product = ProductDTO.builder()
				.id(1L)
				.name("Advanced")
				.description("Produto advanced test")
				.enabled(true)
				.userCreation("Mauricio Melo")
				.build();


		String inputJson = super.mapToJson(product);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(PRODUCT_ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		Product productFound = service.findById(1L);
		assertEquals(product.getDescription(), productFound.getDescription());
	}

	@Test
	public void changeStatusForASpecificProduct() throws Exception {
		String uri = "/product/status/1";

		Product productFound = service.findById(1L);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		Product product = service.findById(1L);

		assertTrue(productFound.isEnabled() != product.isEnabled());
	}
}
