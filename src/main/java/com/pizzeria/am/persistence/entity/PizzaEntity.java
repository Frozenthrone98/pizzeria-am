package com.pizzeria.am.persistence.entity;

import com.pizzeria.am.persistence.audit.AuditPizzaListener;
import com.pizzeria.am.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


@Entity
@Table(name = "pizza")
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PizzaEntity extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Long idPizza;

    @Column(nullable = false, length = 30,unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Boolean available;

    //@Column(name = "created_date")
    //@CreatedDate
    //@JsonIgnore
    //private LocalDateTime createDate;

    //@Column(name = "modified_date")
    //@LastModifiedDate
    //@JsonIgnore
    //private LocalDateTime modifiedDate;

}
