package com.template.controller;

import com.template.model.dao.HeroisDAO;
import com.template.model.dto.HeroisDTO;
import com.template.util.DialogUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class MainController {

    @FXML private Button btnCadastrar, btnLimpar, btnEditar, btnRemover;
    @FXML private TextField txtId, txtNome, txtFuncao, txtNacionalidade, txtVida;
    @FXML private TableView<HeroisDTO> tblHerois;

    @FXML private TableColumn<HeroisDTO, Integer> colId;
    @FXML private TableColumn<HeroisDTO, String> colNome;
    @FXML private TableColumn<HeroisDTO, String> colFuncao;
    @FXML private TableColumn<HeroisDTO, String> colNacionalidade;
    @FXML private TableColumn<HeroisDTO, Integer> colVida;

    @FXML
    private void btnCadastrarAction(ActionEvent event) {
        // Valida campos vazios
        if (txtNome.getText().isEmpty() || txtVida.getText().isEmpty()) {
            DialogUtil.alertEmptyFields();
            return;
        }

        try {
            HeroisDTO objHeroiDTO = new HeroisDTO();
            objHeroiDTO.setNome(txtNome.getText());
            objHeroiDTO.setFuncao(txtFuncao.getText());
            objHeroiDTO.setNacionalidade(txtNacionalidade.getText());
            objHeroiDTO.setVida(Integer.parseInt(txtVida.getText()));

            HeroisDAO objHeroiDAO = new HeroisDAO();
            objHeroiDAO.inserir(objHeroiDTO);

            // Alerta de sucesso específico
            DialogUtil.alertSuccess("cadastrado", txtNome.getText());

            carregarHerois();
            btnLimparAction(event);

        } catch (NumberFormatException e) {
            // Alerta de erro de número específico
            DialogUtil.alertInvalidValue("Vida");
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtId.clear();
        txtNome.clear();
        txtFuncao.clear();
        txtNacionalidade.clear();
        txtVida.clear();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        if (txtId.getText().isEmpty()) {
            DialogUtil.alertNoneSelected("editar");
            return;
        }

        try {
            HeroisDTO objHeroisDTO = new HeroisDTO();
            objHeroisDTO.setId(Integer.parseInt(txtId.getText()));
            objHeroisDTO.setNome(txtNome.getText());
            objHeroisDTO.setFuncao(txtFuncao.getText());
            objHeroisDTO.setNacionalidade(txtNacionalidade.getText());
            objHeroisDTO.setVida(Integer.parseInt(txtVida.getText()));

            HeroisDAO objHeroisDAO = new HeroisDAO();
            objHeroisDAO.atualizar(objHeroisDTO);

            // Alerta de sucesso específico
            DialogUtil.alertSuccess("atualizado", txtNome.getText());

            carregarHerois();
            btnLimparAction(event);

        } catch (NumberFormatException e) {
            DialogUtil.alertInvalidValue("Vida ou ID");
        }
    }

    @FXML
    private void btnRemoverAction(ActionEvent event) {
        HeroisDTO heroiSelecionado = tblHerois.getSelectionModel().getSelectedItem();

        if (heroiSelecionado == null) {
            DialogUtil.alertNoneSelected("remover");
            return;
        }

        // Confirmação de exclusão
        if (DialogUtil.alertExclude(heroiSelecionado.getNome())) {
            HeroisDAO objHeroisDAO = new HeroisDAO();
            objHeroisDAO.excluir(heroiSelecionado.getId());

            DialogUtil.alertSuccess("removido", heroiSelecionado.getNome());

            carregarHerois();
            btnLimparAction(event);
        }
    }

    @FXML
    private void carregarHerois(){
        HeroisDAO heroisDAO = new HeroisDAO();
        ArrayList<HeroisDTO> lista = heroisDAO.listar();
        tblHerois.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        colNacionalidade.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        colVida.setCellValueFactory(new PropertyValueFactory<>("vida"));

        carregarHerois();
    }
}