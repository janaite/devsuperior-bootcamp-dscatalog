package net.janaite.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.janaite.dscatalog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
