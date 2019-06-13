package br.com.padaria.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.rmi.ServerException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.padaria.objetos.Usuario;
import br.com.padaria.conexao.Conexao;
import br.com.padaria.jdbc.JDBCUsuarioDAO;

import java.sql.Connection;


public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	/* metodo construtor */
	public Login() {
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
		
		
		String loginUsuario = request.getParameter("usuarioLogin");
		String LoginSenha = request.getParameter("senha");
		
		Usuario usu = new Usuario();
		
		usu.setUsuarioLogin(loginUsuario);
		usu.setSenha(LoginSenha);
		
		Conexao conec = new Conexao();
		Connection conexao = conec.abrirConexao();
		JDBCUsuarioDAO jdbcContato = new JDBCUsuarioDAO(conexao);
		
		Usuario usuAutenticado = jdbcContato.autenticar(usu);
		
		conec.fecharConexao();
		
		/*Conversão de base 64 para MD5*/
		System.out.println("Login: " + loginUsuario);
		String senhaB64 = request.getParameter("senha");
		
		//System.out.println("Senha base64: "+ senhaB64);
		
		try {
			/*classe MessageDigest fornece uma funcionalidade 
			 * de um algoritmo que resume a mensagem
			 * do tipo SHA-1 - SHA-256 */
			 
			MessageDigest md  = MessageDigest.getInstance("MD5");
			
			/*Atualiza o resumo usando o byte especificado.*/
			md.update(senhaB64.getBytes());
			byte[] codificado = md.digest();
			
			
			/*Retorna a representação da String decimal deste BigInteger.*/
			BigInteger hex = new BigInteger(1, codificado);
			String hashMD5 = hex.toString(16);
			while (hashMD5.length() < 32) {
				hashMD5 = 0 + hashMD5;
			}
			
			System.out.println("senha Base64 + MD5: " + hashMD5);
		
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
		
		if(usuAutenticado != null) {
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			
			response.sendRedirect("http://localhost:8081/padaria/resources/usuario/index.html");
			
			
		}else {
			response.sendRedirect("http://localhost:8081/padaria/login.html");
		}
	}
}
