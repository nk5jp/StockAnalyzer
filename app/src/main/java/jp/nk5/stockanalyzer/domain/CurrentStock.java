package jp.nk5.stockanalyzer.domain;

import lombok.Getter;
import lombok.Setter;

public class CurrentStock extends Stock {

    @Getter @Setter
    private int price;
    @Getter @Setter
    private String remarks;

    public CurrentStock(int code, String name, int price, String remarks)
    {
        super(code, name);
        this.price = price;
        this.remarks = remarks;
    }

}
