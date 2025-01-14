package com.tanvir.dems.repository;

import com.tanvir.dems.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
