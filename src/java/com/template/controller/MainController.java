package com.template.controller;

import com.template.model.dto.HeroisDTO;
import com.template.service.HeroiService;
import com.template.util.DialogUtil;
import com.template.validator.HeroiValidator;
import com.template.view.FormularioHeroi;
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

    private final HeroiService heroiService = new HeroiService();
    private FormularioHeroi formulario;

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
        formulario = new FormularioHeroi(txtId, txtNome, cmbFuncao, txtNacionalidade, txtVida);

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
                formulario.preencher(newValue);
            }
        });
    }

    @FXML
    private void btnCadastrarAction(ActionEvent event) {
        String funcaoSelecionada = formulario.obterFuncaoSelecionada();

        if (!HeroiValidator.validarCadastro(
                txtNome.getText(),
                funcaoSelecionada,
                txtNacionalidade.getText(),
                txtVida.getText())) {
            return;
        }

        HeroisDTO objHeroiDTO = formulario.obterDados();

        heroiService.cadastrar(objHeroiDTO);

        DialogUtil.alertSuccess("cadastrado", txtNome.getText());

        carregarHerois();
        btnLimparAction(event);
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        formulario.limpar();
        tblHerois.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        String funcaoSelecionada = formulario.obterFuncaoSelecionada();

        if (!HeroiValidator.validarEdicao(
                txtId.getText(),
                txtNome.getText(),
                funcaoSelecionada,
                txtNacionalidade.getText(),
                txtVida.getText())) {
            return;
        }

        HeroisDTO objHeroisDTO = formulario.obterDados();
        objHeroisDTO.setId(Integer.parseInt(txtId.getText().trim()));

        heroiService.atualizar(objHeroisDTO);

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
            heroiService.remover(heroiSelecionado.getId());

            DialogUtil.alertSuccess("removido", heroiSelecionado.getNome());

            carregarHerois();
            btnLimparAction(event);
        }
    }

    @FXML
    private void carregarHerois() {
        ArrayList<HeroisDTO> lista = heroiService.listar();
        tblHerois.setItems(FXCollections.observableArrayList(lista));
    }
}