package com.coelho.estevao.tecnonutrifeed.domain.entity;

/**
 * Created by estevao on 12/11/17.
 */

public enum Meal {
    MEAL_0(0, "Café da Manhã"),
    MEAL_1(1, "Lanche da Manhã"),
    MEAL_2(2, "Almoço"),
    MEAL_3(3, "Lanche da Tarde"),
    MEAL_4(4, "Jantar"),
    MEAL_5(5, "Ceia"),
    MEAL_6(6, "Pré-Treino"),
    MEAL_7(7, "Pós-Treino");

    private final Integer key;
    private final String value;

    Meal(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
