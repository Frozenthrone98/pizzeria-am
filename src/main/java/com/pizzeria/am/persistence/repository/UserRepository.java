package com.pizzeria.am.persistence.repository;

import com.pizzeria.am.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,String> {
}
