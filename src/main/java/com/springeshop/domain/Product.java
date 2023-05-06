package com.springeshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    private static final String SEQ_NAME = "products_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<Deliver> deliveries;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PurchasedItem> purchasedItems;

    @Column(name = "price")
    private int price;
}
