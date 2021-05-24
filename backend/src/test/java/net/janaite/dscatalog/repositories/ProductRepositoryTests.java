package net.janaite.dscatalog.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import net.janaite.dscatalog.entities.Product;
import net.janaite.dscatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {
	@Autowired
	private ProductRepository repo;

	private long existingId;
	private long nonExistingId;
	private long countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}

	@Test
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
		Product product = Factory.createProduct();
		product.setId(null);

		product = repo.save(product);
		assertNotNull(product.getId());
		assertEquals(countTotalProducts + 1, product.getId());
	}

	@Test
	public void deleteShouldDeleteWhenIdExists() {
		repo.deleteById(existingId);

		Optional<Product> result = repo.findById(1L);
		assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
		assertThrows(EmptyResultDataAccessException.class, () -> {
			repo.deleteById(nonExistingId);
		});
	}

	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		Optional<Product> result = repo.findById(existingId);
		assertTrue(result.isPresent());
	}

	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
		Optional<Product> result = repo.findById(nonExistingId);
		assertTrue(result.isEmpty());
	}
}
