package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL    = "jdbc:postgresql://localhost:5432/Overwatch";
    private static final String USUARIO = "postgres";
    private static final String SENHA   = "postgres";

    /**
     * Abre e retorna uma nova conexão com o banco de dados.
     * O chamador é responsável por fechar a conexão (use try-with-resources).
     */
    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
}

    public static Connection conectaBD() {
        return null;
    }
    }
