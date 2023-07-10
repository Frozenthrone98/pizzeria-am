package com.pizzeria.am.web.controller;

import com.pizzeria.am.persistence.entity.PizzaEntity;
import com.pizzeria.am.service.PizzaService;
import com.pizzeria.am.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page,elements));   //return new ResponseEntity<>(pizzaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/available/{name}")
    public ResponseEntity<PizzaEntity> getAvailableByName(@PathVariable("name") String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable("ingredient") String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithOut(@PathVariable("ingredient") String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWithOut(ingredient));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable("price") Double price){
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> get(@PathVariable("id") Long idPizza){
        if (this.pizzaService.get(idPizza) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();     //new ResponseEntity<>(HttpStatus.NOT_FOUND)
        }else {
            return ResponseEntity.ok(this.pizzaService.get(idPizza));
        }
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())){
            return new ResponseEntity<>(this.pizzaService.save(pizza),HttpStatus.CREATED);    //return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())){
            return new ResponseEntity<>(this.pizzaService.save(pizza),HttpStatus.CREATED);    //return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long idPizza){
        if (this.pizzaService.exists(idPizza)){
            this.pizzaService.delete(idPizza); //ResponseEntity.status(HttpStatus.NOT_FOUND).build();//ResponseEntity.ok(this.pizzaService.delete(idPizza));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto){
        if (this.pizzaService.exists(dto.getIdPizza())){
            this.pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
