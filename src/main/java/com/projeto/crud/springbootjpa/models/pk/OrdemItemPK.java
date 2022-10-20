package com.projeto.crud.springbootjpa.models.pk;

import java.io.Serializable;

import com.projeto.crud.springbootjpa.models.Order;
import com.projeto.crud.springbootjpa.models.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class OrdemItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
