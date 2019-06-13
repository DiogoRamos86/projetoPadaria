package br.com.padaria.controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.padaria.conexao.Conexao;
import br.com.padaria.jdbc.JDBCUsuarioDAO;
import br.com.padaria.objetos.Funcionario;

public class CadastrarFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CadastrarFuncionario() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServerException, IOException {
		
		
		Funcionario funci = new Funcionario();
		
		funci.setNome(request.getParameter("fname"));
		funci.setSobrenome(request.getParameter("lname"));
		funci.setFuncao(request.getParameter("email"));
		
		Conexao conec = new Conexao();

		
		Connection conexao = conec.abrirConexao();

	
		JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
		
		boolean retorno = jdbcUsuario.cadastrarFuncionario(funci);
		
		conec.fecharConexao();
		
		
		
		
		
		
		
		
		
		
	}
}
