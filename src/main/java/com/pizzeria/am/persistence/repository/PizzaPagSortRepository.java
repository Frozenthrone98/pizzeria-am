package com.pizzeria.am.persistence.repository;

import com.pizzeria.am.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity,Long> {
    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}
