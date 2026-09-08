package com.template.validator;

import java.util.ArrayList;
import java.util.List;

import static com.template.util.DialogUtil.showWarning;

public class HeroiValidator implements IHeroiValidator {

    @Override
    public boolean validarCadastro(String nome, String funcao, String nacionalidade, String vida) {
        return validarCampos(nome, funcao, nacionalidade, vida);
    }

    @Override
    public boolean validarEdicao(String id, String nome, String funcao, String nacionalidade, String vida) {
        List<Validador<?>> validadorId = new ArrayList<>();
        validadorId.add(new CampoObrigatorioValidador("id", id));

        if (!executar(validadorId)) {
            return false;
        }

        return validarCampos(nome, funcao, nacionalidade, vida);
    }

    private boolean validarCampos(String nome, String funcao, String nacionalidade, String vida) {
        List<Validador<?>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidador("nome", nome));
        validadores.add(new CampoObrigatorioValidador("funcao", funcao));
        validadores.add(new CampoObrigatorioValidador("nacionalidade", nacionalidade));
        validadores.add(new CampoObrigatorioValidador("vida", vida));
        validadores.add(new VidaValidador(vida));

        return executar(validadores);
    }

    private boolean executar(List<Validador<?>> validadores) {
        for (Validador<?> validador : validadores) {
            if (!validador.validar()) {
                showWarning(validador.getMensagemErro());
                return false;
            }
        }
        return true;
    }
}