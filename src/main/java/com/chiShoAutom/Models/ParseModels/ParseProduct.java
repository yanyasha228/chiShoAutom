package com.chiShoAutom.Models.ParseModels;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parse_products")
public class ParseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parse_shop_id")
    private ParseShop parseShop;

    @Column(name = "product_url")
    private String productUrl;

//    @Column(name = "")
//    private



}
