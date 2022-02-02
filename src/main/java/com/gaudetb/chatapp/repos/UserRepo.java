package com.gaudetb.chatapp.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gaudetb.chatapp.models.User;


@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	List<User> findAll();
	
//	@EntityGraph(attributePaths = {"threadsJoined"})
	Optional<User> findByEmail(String email);
	
	@EntityGraph(attributePaths = {"threadsJoined"})
	Optional<User> findById(Long id);

}
