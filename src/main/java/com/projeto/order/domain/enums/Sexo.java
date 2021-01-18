package com.projeto.order.domain.enums;

public enum Sexo {

    FEM(0, "Feminino"),
    MASC(1, "Masculino");

    private int id;
    private String descricao;

    Sexo(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao () {
        return descricao;
    }

    public static Sexo toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (Sexo x : Sexo.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + id);
    }


}
