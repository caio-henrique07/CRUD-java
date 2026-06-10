package com.template;

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
    private void btnCadastrarAction(ActionEvent event){
        String nome = txtNome.getText();
        String funcao = txtFuncao.getText();
        String nacionalidade = txtNacionalidade.getText();
        int vida = Integer.parseInt(txtVida.getText());

        HeroisDTO objHeroiDTO = new HeroisDTO();
        objHeroiDTO.setNome(nome);
        objHeroiDTO.setFuncao(funcao);
        objHeroiDTO.setNacionalidade(nacionalidade);
        objHeroiDTO.setVida(vida);

        HeroisDAO objHeroiDAO = new HeroisDAO();
        objHeroiDAO.inserir(objHeroiDTO);

        carregarHerois(); // Atualiza a tabela
        btnLimparAction(event);
    } // <--- FECHAMENTO ADICIONADO AQUI

    @FXML
    private void btnLimparAction(ActionEvent event){
        txtId.clear();
        txtNome.clear();
        txtFuncao.clear();
        txtNacionalidade.clear();
        txtVida.clear();
    }

    @FXML
    private void btnEditarAction(ActionEvent event){
        int id = Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        String funcao = txtFuncao.getText();
        String nacionalidade = txtNacionalidade.getText();
        int vida = Integer.parseInt(txtVida.getText());

        HeroisDTO objHeroisDTO = new HeroisDTO();
        objHeroisDTO.setId(id);
        objHeroisDTO.setNome(nome);
        objHeroisDTO.setFuncao(funcao);
        objHeroisDTO.setNacionalidade(nacionalidade);
        objHeroisDTO.setVida(vida);

        HeroisDAO objHeroisDAO = new HeroisDAO();
        objHeroisDAO.atualizar(objHeroisDTO);

        carregarHerois();
    }

    @FXML
    private void btnRemoverAction(ActionEvent event) {
        HeroisDTO heroiSelecionado = tblHerois.getSelectionModel().getSelectedItem();
        if (heroiSelecionado != null) {
            HeroisDAO objHeroisDAO = new HeroisDAO();
            objHeroisDAO.excluir(heroiSelecionado.getId());
        }
        carregarHerois();
        btnLimparAction(event);
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