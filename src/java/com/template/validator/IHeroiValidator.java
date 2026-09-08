package com.template.validator;

public interface IHeroiValidator {
    boolean validarCadastro(String nome, String funcao, String nacionalidade, String vida);
    boolean validarEdicao(String id, String nome, String funcao, String nacionalidade, String vida);
}