package com.template.service;

import com.template.model.dao.HeroisDAO;
import com.template.model.dto.HeroisDTO;

import java.util.ArrayList;

public class HeroiService {

    private final HeroisDAO heroisDAO = new HeroisDAO();

    public void cadastrar(HeroisDTO heroi) {
        heroisDAO.inserir(heroi);
    }

    public void atualizar(HeroisDTO heroi) {
        heroisDAO.atualizar(heroi);
    }

    public void remover(int id) {
        heroisDAO.excluir(id);
    }

    public ArrayList<HeroisDTO> listar() {
        return heroisDAO.listar();
    }
}
