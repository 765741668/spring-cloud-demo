package com.hooray.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hooray.domain.User;

@CacheConfig(cacheNames="users")
public interface UserRepository extends JpaRepository<User, Long> {
	@Override
    @CachePut(key="#p0.name")
	User save(User user);

	@Cacheable(key="#p0")
	User findByName(String name);
}
