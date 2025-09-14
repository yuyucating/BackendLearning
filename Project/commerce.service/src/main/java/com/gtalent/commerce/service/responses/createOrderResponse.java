package com.gtalent.commerce.service.responses;

public class createOrderResponse {
    private int userId;
    private Map<String, Integer> productQuantity;
    private BigDecimal totals;
    private BigDecimal deliveryFee;
    private BigDecimal tax;
    private LocalDate date;

}
