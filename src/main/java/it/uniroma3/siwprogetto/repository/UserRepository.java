package it.uniroma3.siwprogetto.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwprogetto.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
	
	Optional<User> findByEmail(String email);

}
