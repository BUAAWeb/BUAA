package com.springboot.demo.repository;

import com.springboot.demo.entity.Authority_user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority_user, Authority_userKey> {
}
