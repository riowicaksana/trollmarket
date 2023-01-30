package com.trollmarket.dto.history;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class HistoryGridDTO {

    @Getter
    @Setter
    public String date;

    @Getter
    @Setter
    public String seller;

    @Getter
    @Setter
    public String buyer;

    @Getter
    @Setter
    public String product;

    @Getter
    @Setter
    public Integer quantity;

    @Getter
    @Setter
    public String shipment;

    @Getter
    @Setter
    public String totalPrice;

}
