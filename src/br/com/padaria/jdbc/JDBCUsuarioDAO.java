package br.com.padaria.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.padaria.jdbcinterface.UsuarioDAO;
import br.com.padaria.objetos.Usuario;
import br.com.padaria.objetos.Categoria;
import br.com.padaria.objetos.Funcionario;

public class JDBCUsuarioDAO implements UsuarioDAO {
	private Connection conexao;

	public JDBCUsuarioDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public Usuario autenticar(Usuario usu) {

		Usuario usuRetorno = new Usuario();

		String comando = "SELECT usuarioLogin, senha FROM  padaria.usuario WHERE usuarioLogin = '" + usu.getUsuarioLogin() + "' and senha = '"
				+ usu.getSenha() + "'";
		
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			
			ResultSet rs = p.executeQuery();
			
			if(rs.next()){
			
				usuRetorno.setUsuarioLogin(rs.getString("usuarioLogin"));
				usuRetorno.setSenha(rs.getString("senha"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return usuRetorno;
	}
	
	
	public boolean cadastrarCategoria(Categoria categoria) {
		
		String comando = "insert into categoria"+"(nome_categoria)" + "(?)";
		
		
		try {
			PreparedStatement p;
			
			p = this.conexao.prepareStatement(comando);
			
			p.setString(1, categoria.getTipoCategoria());
			
			p.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public boolean cadastrarFuncionario(Funcionario func) {
		String comando = "insert into funcionario " + "(nome_func, sobrenome_func, funcao) " + "values(?, ?, ?)";
		
		try {
			PreparedStatement p;
			
			p = this.conexao.prepareStatement(comando);
			
			p.setString(1, func.getNome());
			p.setString(2, func.getSobrenome());
			p.setString(3, func.getFuncao());
			
			p.execute();
		
	}catch(SQLException e) {
		e.printStackTrace();
		
		return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
