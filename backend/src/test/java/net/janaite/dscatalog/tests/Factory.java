package net.janaite.dscatalog.tests;

import java.time.Instant;

import net.janaite.dscatalog.dto.ProductDTO;
import net.janaite.dscatalog.entities.Category;
import net.janaite.dscatalog.entities.Product;

public class Factory {
	public static Product createProduct() {
		Product p = new Product(1L, "Phone", "Good phone", 800.0, "https://img.com/img.png",
				Instant.parse("2020-10-20T03:00:00Z"));
		p.getCategories().add(new Category(2L, "Electronics"));
		return p;
	}

	public static ProductDTO createProductDTO() {
		Product p = createProduct();
		return new ProductDTO(p, p.getCategories());
	}
}
