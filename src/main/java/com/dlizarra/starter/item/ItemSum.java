package com.dlizarra.starter.item;

import javax.persistence.*;

@Entity
public class ItemSum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Float sum;

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }
}
