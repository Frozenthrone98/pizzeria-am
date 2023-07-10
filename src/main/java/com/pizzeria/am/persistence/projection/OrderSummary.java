package com.pizzeria.am.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    Long getIdorder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();

}
