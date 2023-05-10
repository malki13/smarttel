package com.telcom.ups.data.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalCount {

    int total;

    public TotalCount() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
