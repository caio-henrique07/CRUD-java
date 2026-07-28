package com.template.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class DialogUtil {

    public static void alertEmptyFields() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText("Campos Incompletos!");
        alert.setContentText("Por favor, preencha todos os campos antes de continuar.");
        alert.showAndWait();
    }

    public static void alertInvalidValue(String campo) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Entrada");
        alert.setHeaderText("Valor Inválido!");
        alert.setContentText("O campo '" + campo + "' precisa ser um número inteiro válido.");
        alert.showAndWait();
    }

    public static void alertSuccess(String acao, String nomeHeroi) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Operação realizada com sucesso!");
        alert.setContentText("O herói '" + nomeHeroi + "' foi " + acao + " com sucesso.");
        alert.showAndWait();
    }

    public static void alertNoneSelected(String acao) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Seleção Pendente");
        alert.setHeaderText("Nenhum Herói Selecionado!");
        alert.setContentText("Clique em um herói na tabela para poder " + acao + ".");
        alert.showAndWait();
    }

    public static boolean alertExclude(String nomeHeroi) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Deseja realmente apagar este herói?");
        alert.setContentText("O herói '" + nomeHeroi + "' será removido permanentemente.");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}