package com.chiShoAutom.Models.ModelEnums;

public enum CorrectionType {
    MANUAL, CSV;

    public String getAlias() {

        switch (name()) {

            case "MANUAL":
                return "Ручная коррекция";

            case "CSV":
                return "Коррекция CSV";
        }

        return "";
    }
}
