package net.janaite.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.janaite.dscatalog.dto.CategoryDTO;
import net.janaite.dscatalog.entities.Category;
import net.janaite.dscatalog.repositories.CategoryRepository;
import net.janaite.dscatalog.services.exceptions.DatabaseException;
import net.janaite.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(Pageable pageable) {
		Page<Category> list = repository.findAll(pageable);
		return list.map(x -> new CategoryDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getOne(id); // getOne do not access database until you save the entity
			entity.setName(dto.getName());
			entity = repository.save(entity);

			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(String.format("ID not found [%d]", id));
		}
	}

	// dont use @Transcation to capture a exception
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("ID not found [%d]", id));
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integriry Violation");
		}

	}
}
