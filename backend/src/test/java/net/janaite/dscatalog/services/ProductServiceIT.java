package net.janaite.dscatalog.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import net.janaite.dscatalog.dto.ProductDTO;
import net.janaite.dscatalog.repositories.ProductRepository;
import net.janaite.dscatalog.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class ProductServiceIT {

	@Autowired
	private ProductService service;

	@Autowired
	private ProductRepository repository;

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}

	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {
		service.delete(existingId);
		assertEquals(countTotalProducts - 1, repository.count());
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}
	
	@Test
	public void findAllPagedShouldReturnPageWhenPage0Size10() {
		PageRequest pageRequest = PageRequest.of(0, 10);
		
		Page<ProductDTO> result = service.findAllPaged(0L, "", pageRequest);
		
		assertFalse(result.isEmpty());
		assertEquals(0, result.getNumber());
		assertEquals(10, result.getSize());
		assertEquals(countTotalProducts, result.getTotalElements());
	}
	
	@Test
	public void findAllPagedShouldReturnEmptyWhenPageDoesNotExists() {
		PageRequest pageRequest = PageRequest.of(50, 10);
		
		Page<ProductDTO> result = service.findAllPaged(0L, "", pageRequest);
		
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortedByName() {
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));
		
		Page<ProductDTO> result = service.findAllPaged(0L, "", pageRequest);
		
		assertFalse(result.isEmpty());
		assertEquals("Macbook Pro", result.getContent().get(0).getName());
		assertEquals("PC Gamer", result.getContent().get(1).getName());
		assertEquals("PC Gamer Alfa", result.getContent().get(2).getName());
	}
}
