package com.chiShoAutom.Models.ParseModels;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parse_shops")
public class ParseShop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "parseShop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParseProduct> parseProducts;

    @Column(name = "shop_url")
    private String shopUrl;

    @Column(name = "whole_shop_url")
    private String wholeShopUrl;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "category_id")
    private List<ParseProductCategory> shopCategories;




}
