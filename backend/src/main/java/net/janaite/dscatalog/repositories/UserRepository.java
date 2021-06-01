package net.janaite.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.janaite.dscatalog.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
