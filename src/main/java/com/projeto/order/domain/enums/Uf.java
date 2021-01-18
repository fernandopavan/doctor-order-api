package com.projeto.order.domain.enums;

public enum Uf {

    AC("AC"),
    AL("AL"),
    AP("AP"),
    AM("AM"),
    BA("BA"),
    CE("CE"),
    DF("DF"),
    ES("ES"),
    GO("GO"),
    MA("MA"),
    MT("MT"),
    MS("MS"),
    MG("MG"),
    PA("PA"),
    PB("PB"),
    PR("PR"),
    PE("PE"),
    PI("PI"),
    RJ("RJ"),
    RN("RN"),
    RS("RS"),
    RO("RO"),
    RR("RR"),
    SC("SC"),
    SP("SP"),
    SE("SE"),
    TO("TO");

    private String uf;

    Uf(String uf) {
        this.uf = uf;
    }

    public String getUf() {
        return uf;
    }

    public static Uf toEnum(String uf) {
        if (uf == null) {
            return null;
        }

        for (Uf x : Uf.values()) {
            if (uf.equals(x.getUf())) {
                return x;
            }
        }

        throw new IllegalArgumentException("UF inválida: " + uf);
    }


}
