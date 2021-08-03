 package net.janaite.dscatalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.janaite.dscatalog.entities.Category;
import net.janaite.dscatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT obj FROM Product obj "
			+ "INNER JOIN obj.categories cats "
			+ "WHERE :category IN cats")
	Page<Product> find(Category category, Pageable pageable);
}
