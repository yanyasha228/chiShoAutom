package com.chiShoAutom.Models.ParseModels;

import com.chiShoAutom.Models.ModelEnums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parse_products")
public class ParseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "availability")
    private boolean availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parse_shop_id")
    private ParseShop parseShop;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ParseProductCategory parseProductCategory;

    @Column(name = "moneyCurrency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "price")
    private float price;

    @Column(name = "pictures")
    @ElementCollection
    private List<String> pictures;

    @Column(name = "description")
    private String description;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "vendorCode")
    private String vendorCode;

    @Column(name = "country")
    private String country;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "params")
    @ElementCollection
    private Map<String, String> params;

}
