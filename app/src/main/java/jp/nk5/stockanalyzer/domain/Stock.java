package jp.nk5.stockanalyzer.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

public abstract class Stock {

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
