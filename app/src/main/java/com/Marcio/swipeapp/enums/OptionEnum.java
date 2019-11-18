package com.Marcio.swipeapp.enums;

public enum OptionEnum {
    YES("SIM"),
    NO("NÃO");

    private String option;

    OptionEnum(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
