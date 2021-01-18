package com.projeto.order.domain.enums;

public enum TipoConselho {

    CFM("CFM", "Conselho Federal de Medicina"),
    CFMV("CFMV", "Conselho Federal de Medicina Veterinária"),
    CFO("CFO", "Conselho Federal de Odontologia"),
    COFEN("COFEN", "Conselho Federal de Enfermagem"),
    CFBM("CFBM", "Conselho Federal de Biomedicina"),
    CFF("CFF", "Conselho Federal de Farmácia");

    private String sigla;
    private String descricao;

    TipoConselho(String sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoConselho toEnum(String sigla) {
        if (sigla == null) {
            return null;
        }

        for (TipoConselho x : TipoConselho.values()) {
            if (sigla.equals(x.getSigla())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Sigla inválida: " + sigla);
    }


}
