package jp.nk5.stockanalyzer.domain;

import lombok.Getter;
import lombok.Setter;

public class Stock {

    @Getter
    private int code;
    @Getter @Setter
    private String name;

    public Stock (int code, String name)
    {
        this.code = code;
        this.name = name;
    }

}
