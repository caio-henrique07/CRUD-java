package com.template.validator;

public class VidaValidador implements Validador<Integer> {
    private final String nomeCampo;
    private final String valorTexto;
    private Integer valorConvertido;

    public VidaValidador(String nomeCampo, String valorTexto) {
        this.nomeCampo = nomeCampo;
        this.valorTexto = valorTexto;
    }

    public VidaValidador(String valorTexto) {
        this("Vida", valorTexto);
    }

    @Override
    public boolean validar() {
        if (this.valorTexto == null || this.valorTexto.trim().isEmpty()) {
            return false;
        }

        try {
            this.valorConvertido = Integer.parseInt(this.valorTexto.trim());
            return this.valorConvertido > 0;
        } catch (NumberFormatException e) {
            this.valorConvertido = null;
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
        return "O campo " + nomeCampo + " deve conter um valor numerico valido maior que zero.";
    }

    @Override
    public Integer getValor() {
        return this.valorConvertido;
    }
}