package com.chiShoAutom.Models.ParseModels;

import com.chiShoAutom.Models.ModelEnums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parse_products")
public class ParseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "availability")
    private boolean availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parse_shop_id")
    private ParseShop parseShop;

    @Column(name = "url", unique = true)
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
    private Set<String> pictures = new HashSet<>();

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
    private Map<String, String> params = new HashMap<>();

    @Column(name = "video_urls")
    @ElementCollection
    private Set<String> videos= new HashSet<>();

    @Column(name = "last_updating_date")
    @DateTimeFormat(pattern = "dd-M-yyyy hh:mm")
    private LocalDateTime lastUpdatingDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParseProduct that = (ParseProduct) o;
        return id == that.id &&
                url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }
}
