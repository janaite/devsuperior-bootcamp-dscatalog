package net.janaite.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.janaite.dscatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
