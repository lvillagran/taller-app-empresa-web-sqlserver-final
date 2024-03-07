package com.empresa.modulo.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.empresa.modulo.conexion.SessionUtils;
import com.empresa.modulo.dao.LoginDAO;



@ManagedBean  (name = "beanMantenedorLogin")
@SessionScoped
public class BeanMantenedorLogin implements Serializable {
	
	private static final long serialVersionUID = 1094801825228386363L;
	
	private String lPassword;
	
	private String lUsuario;

	public BeanMantenedorLogin ()
	{
		lPassword= "";
		lUsuario= "";
	}
	
	
	/*Cierra Sesion*/
	public String autenticarUsuario() {
		boolean valid = LoginDAO.autenticacionUsuario(lUsuario, lPassword);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("usuario", lUsuario);
			return "dashboard";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Mensaje ",
							"Ingrese usuario y password válidos."));
			return "login";
		}
	}

/*Cierra Sesion*/
	public String logoutUsuario() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}


	/**
	 * @return the lPassword
	 */
	public String getlPassword() {
		return lPassword;
	}


	/**
	 * @param lPassword the lPassword to set
	 */
	public void setlPassword(String lPassword) {
		this.lPassword = lPassword;
	}


	/**
	 * @return the lUsuario
	 */
	public String getlUsuario() {
		return lUsuario;
	}


	/**
	 * @param lUsuario the lUsuario to set
	 */
	public void setlUsuario(String lUsuario) {
		this.lUsuario = lUsuario;
	}

}
