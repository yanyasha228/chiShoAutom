package com.chiShoAutom.Models;

import com.chiShoAutom.Models.ModelEnums.CorrectionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("C")
public class Correction extends ProductManipulation {

    public Correction() {
    }

    @Column(name = "correction_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private CorrectionType correctionType;

}
