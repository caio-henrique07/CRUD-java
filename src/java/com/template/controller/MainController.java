package com.template.controller;

import com.template.model.dao.HeroisDAO;
import com.template.model.dto.HeroisDTO;
import com.template.util.DialogUtil;
import com.template.validator.HeroiValidator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class MainController {

    @FXML private Button btnCadastrar, btnLimpar, btnEditar, btnRemover;
    @FXML private TextField txtId, txtNome, txtNacionalidade, txtVida;
    @FXML private ComboBox<String> cmbFuncao;
    @FXML private TableView<HeroisDTO> tblHerois;

    @FXML private TableColumn<HeroisDTO, Integer> colId;
    @FXML private TableColumn<HeroisDTO, String> colNome;
    @FXML private TableColumn<HeroisDTO, String> colFuncao;
    @FXML private TableColumn<HeroisDTO, String> colNacionalidade;
    @FXML private TableColumn<HeroisDTO, Integer> colVida;

    @FXML
    public void initialize() {
        // Inicializa opções da ComboBox
        cmbFuncao.setItems(FXCollections.observableArrayList("DANO", "SUPORTE", "TANQUE"));

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        colNacionalidade.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        colVida.setCellValueFactory(new PropertyValueFactory<>("vida"));

        carregarHerois();

        // Listener para popular os campos ao clicar em uma linha
        tblHerois.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                preencherCampos(newValue);
            }
        });
    }

    private void preencherCampos(HeroisDTO heroi) {
        txtId.setText(String.valueOf(heroi.getId()));
        txtNome.setText(heroi.getNome());
        cmbFuncao.setValue(heroi.getFuncao());
        txtNacionalidade.setText(heroi.getNacionalidade());
        txtVida.setText(String.valueOf(heroi.getVida()));
    }

    @FXML
    private void btnCadastrarAction(ActionEvent event) {
        String funcaoSelecionada = cmbFuncao.getValue() != null ? cmbFuncao.getValue() : "";

        if (!HeroiValidator.validarCadastro(
                txtNome.getText(),
                funcaoSelecionada,
                txtNacionalidade.getText(),
                txtVida.getText())) {
            return;
        }

        HeroisDTO objHeroiDTO = new HeroisDTO();
        objHeroiDTO.setNome(txtNome.getText().trim());
        objHeroiDTO.setFuncao(funcaoSelecionada);
        objHeroiDTO.setNacionalidade(txtNacionalidade.getText().trim());
        objHeroiDTO.setVida(Integer.parseInt(txtVida.getText().trim()));

        HeroisDAO objHeroiDAO = new HeroisDAO();
        objHeroiDAO.inserir(objHeroiDTO);

        DialogUtil.alertSuccess("cadastrado", txtNome.getText());

        carregarHerois();
        btnLimparAction(event);
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtId.clear();
        txtNome.clear();
        cmbFuncao.setValue(null);
        txtNacionalidade.clear();
        txtVida.clear();
        tblHerois.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        String funcaoSelecionada = cmbFuncao.getValue() != null ? cmbFuncao.getValue() : "";

        if (!HeroiValidator.validarEdicao(
                txtId.getText(),
                txtNome.getText(),
                funcaoSelecionada,
                txtNacionalidade.getText(),
                txtVida.getText())) {
            return;
        }

        HeroisDTO objHeroisDTO = new HeroisDTO();
        objHeroisDTO.setId(Integer.parseInt(txtId.getText().trim()));
        objHeroisDTO.setNome(txtNome.getText().trim());
        objHeroisDTO.setFuncao(funcaoSelecionada);
        objHeroisDTO.setNacionalidade(txtNacionalidade.getText().trim());
        objHeroisDTO.setVida(Integer.parseInt(txtVida.getText().trim()));

        HeroisDAO objHeroisDAO = new HeroisDAO();
        objHeroisDAO.atualizar(objHeroisDTO);

        DialogUtil.alertSuccess("atualizado", txtNome.getText());

        carregarHerois();
        btnLimparAction(event);
    }

    @FXML
    private void btnRemoverAction(ActionEvent event) {
        HeroisDTO heroiSelecionado = tblHerois.getSelectionModel().getSelectedItem();

        if (heroiSelecionado == null) {
            DialogUtil.alertNoneSelected("remover");
            return;
        }

        if (DialogUtil.alertExclude(heroiSelecionado.getNome())) {
            HeroisDAO objHeroisDAO = new HeroisDAO();
            objHeroisDAO.excluir(heroiSelecionado.getId());

            DialogUtil.alertSuccess("removido", heroiSelecionado.getNome());

            carregarHerois();
            btnLimparAction(event);
        }
    }

    @FXML
    private void carregarHerois() {
        HeroisDAO heroisDAO = new HeroisDAO();
        ArrayList<HeroisDTO> lista = heroisDAO.listar();
        tblHerois.setItems(FXCollections.observableArrayList(lista));
    }
}