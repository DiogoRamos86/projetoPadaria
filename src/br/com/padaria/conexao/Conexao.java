package br.com.padaria.conexao;

import java.sql.Connection;

public class Conexao {

	private Connection conexao;

	// Método que abre a conexao com o banco de dados
	public Connection abrirConexao() {
		try {
			// instrução que indetifica o tipo de driver utilizado para a conexao com o
			// banco de dados
			Class.forName("com.mysql.cj.jdbc.Driver");
			// enderecamento feito do servidor de banco e driver
			conexao = java.sql.DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/padaria?useTimezone=true&serverTimezone=UTC", "root", "root");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}

	// Método para fechar a conexao com o banco de dados.
	public void fecharConexao() {
		try {
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
