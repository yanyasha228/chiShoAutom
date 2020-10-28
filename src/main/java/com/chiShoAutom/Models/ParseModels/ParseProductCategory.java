package com.chiShoAutom.Models.ParseModels;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parse_product_categories")
public class ParseProductCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "parent_category_id", referencedColumnName = "id")
    private ParseProductCategory parentCategory;


}
