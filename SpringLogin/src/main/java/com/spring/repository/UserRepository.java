package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	public abstract List<User> findByUsername(@Param("username") String username);
}
