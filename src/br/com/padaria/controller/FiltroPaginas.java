package br.com.padaria.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import br.com.padaria.objetos.Usuario;

@WebFilter("/resources/*")
public class FiltroPaginas implements Filter {
	
public void destroy() {
		
	}

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		
		String context = request.getServletContext().getContextPath();
		
		try {
				HttpSession sessao = ((HttpServletRequest)request).getSession();
				Usuario usu = null;
				
				if(sessao  != null) {
					usu = (Usuario) sessao.getAttribute("usuAutenticado");
				}
				if(usu == null) {
					sessao.setAttribute("msg", "Você não está logado!");
					
					((HttpServletResponse) response).sendRedirect(context + "/login.html");
				}else {
					chain.doFilter(request, response);
				}
				
				
				
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 
	
	public void init(FilterConfig fConfig) throws ServletException{
		
	}


}
				
				
				
			
		



