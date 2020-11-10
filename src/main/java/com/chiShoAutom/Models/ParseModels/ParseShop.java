package com.chiShoAutom.Models.ParseModels;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private Set<ParseProduct> parseProducts;

    @Column(name = "shop_url")
    private String shopUrl;

    @Column(name = "whole_shop_url")
    private String wholeShopUrl;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "category_id")
    private Set<ParseProductCategory> shopCategories = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParseShop parseShop = (ParseShop) o;
        return id == parseShop.id &&
                shopUrl.equals(parseShop.shopUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shopUrl);
    }
}
