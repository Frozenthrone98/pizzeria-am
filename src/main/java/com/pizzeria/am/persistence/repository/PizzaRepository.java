package com.pizzeria.am.persistence.repository;

import com.pizzeria.am.persistence.entity.PizzaEntity;
import com.pizzeria.am.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Long> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);
    int countByVeganTrue();

    //@Query(value = "UPDATE pizza SET price = :newPrice WHERE id_pizza = :idPizza", nativeQuery = true)
    //void updatePrice(@Param("newPrice") Double newPrice, @Param("idPizza") Long idPizza);

    @Query(value = "UPDATE pizza SET price = :#{#newPizzaPrice.newPrice} " +
                   "WHERE id_pizza = :#{#newPizzaPrice.idPizza}", nativeQuery = true)  //SPRING EXPRESSION LANGUAGE
    @Modifying
    void updatePrice(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);
}
