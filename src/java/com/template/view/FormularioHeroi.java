package com.template.view;

import com.template.model.dto.HeroisDTO;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FormularioHeroi {

    private final TextField txtId;
    private final TextField txtNome;
    private final ComboBox<String> cmbFuncao;
    private final TextField txtNacionalidade;
    private final TextField txtVida;

    public FormularioHeroi(TextField txtId, TextField txtNome, ComboBox<String> cmbFuncao,
                            TextField txtNacionalidade, TextField txtVida) {
        this.txtId = txtId;
        this.txtNome = txtNome;
        this.cmbFuncao = cmbFuncao;
        this.txtNacionalidade = txtNacionalidade;
        this.txtVida = txtVida;
    }

    public void limpar() {
        txtId.clear();
        txtNome.clear();
        cmbFuncao.setValue(null);
        txtNacionalidade.clear();
        txtVida.clear();
    }

    public void preencher(HeroisDTO heroi) {
        txtId.setText(String.valueOf(heroi.getId()));
        txtNome.setText(heroi.getNome());
        cmbFuncao.setValue(heroi.getFuncao());
        txtNacionalidade.setText(heroi.getNacionalidade());
        txtVida.setText(String.valueOf(heroi.getVida()));
    }

    public String obterFuncaoSelecionada() {
        return cmbFuncao.getValue() != null ? cmbFuncao.getValue() : "";
    }

    public HeroisDTO obterDados() {
        HeroisDTO heroi = new HeroisDTO();
        heroi.setNome(txtNome.getText().trim());
        heroi.setFuncao(obterFuncaoSelecionada());
        heroi.setNacionalidade(txtNacionalidade.getText().trim());
        heroi.setVida(Integer.parseInt(txtVida.getText().trim()));
        return heroi;
    }
}
