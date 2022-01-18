package com.gaudetb.chatapp.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gaudetb.chatapp.models.Thread;


@Repository
public interface ThreadRepo extends CrudRepository<Thread, Long> {
	List<Thread> findAll();
}
