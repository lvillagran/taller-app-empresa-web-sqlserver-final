package com.empresa.modulo.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.empresa.modulo.dao.ProductoDAO;
import com.empresa.modulo.entidad.Producto;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean(name = "beanMantenedorProducto")
@SessionScoped
public class BeanMantenedorProducto implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private String lNombre;
	private String lDescripcion;
	private int lPrecio;
	private int lIdTipoProducto;

	private List<Producto> lListadoDatosProducto;
	private List<Producto> lListadoDatosProductoConsulta;

	Producto lClsMapeaDatosProducto;

	private int lIdProductoActualizar;
	private String lNombreActualizar;
	private int lPrecioActualizar;
	
	private int lIdProductoEliminar;

	

	public BeanMantenedorProducto() {
		lListadoDatosProductoConsulta = new ArrayList<>();
		lListadoDatosProducto = new ArrayList<>();

		allProductos();

		lIdProductoActualizar = 0;
		lNombreActualizar = "";
		lPrecioActualizar = 0;
		lIdTipoProducto = 0;


		lNombre = "";
		lDescripcion = "";
		lPrecio = 0;
		
		lIdProductoEliminar = 0;
	}

	/*Registra*/
	public void registraProducto(ActionEvent lEvento) {
		String pedidoOut = null;
		ProductoDAO lProductoDAO = new ProductoDAO();
		Producto lProducto = new Producto();

		if (lPrecio <= 0) {
			lPrecio = 0;
		}

		if (!lNombre.trim().isEmpty() && !lDescripcion.trim().isEmpty() && lPrecio > 0 && lIdTipoProducto > 0) {
			lProducto.setlNombre(lNombre);
			lProducto.setlDescripcion(lDescripcion);
			lProducto.setlPrecio(lPrecio);
			lProducto.setlIdTipoProducto(lIdTipoProducto);
			

			pedidoOut = lProductoDAO.registraProducto(lProducto);
			allProductos();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Transacción realizada con éxito."));
			if (pedidoOut.equals("OK")) {
				lNombre = "";
				lDescripcion = "";
				lPrecio = 0;

			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "Todos los campos son obligatorios."));
		}
	}

	
	/*Consulta todos los productos*/
	public void allProductos() {
		lListadoDatosProducto = new ArrayList<>();
		ProductoDAO lProductoDAO = new ProductoDAO();
		lListadoDatosProducto = lProductoDAO.AllProductos();
		lListadoDatosProductoConsulta = new ArrayList<>();

		if ((lListadoDatosProducto.size()) > 0) {
			for (Producto lDatos : lListadoDatosProducto) {
				lClsMapeaDatosProducto = new Producto();
				lClsMapeaDatosProducto.setlIdPoducto(lDatos.getlIdPoducto());
				lClsMapeaDatosProducto.setlNombre(lDatos.getlNombre());
				lClsMapeaDatosProducto.setlDescripcion(lDatos.getlDescripcion());
				lClsMapeaDatosProducto.setlPrecio(lDatos.getlPrecio());
				lClsMapeaDatosProducto.setlFechaRegistro(lDatos.getlFechaRegistro());
				lListadoDatosProductoConsulta.add(lClsMapeaDatosProducto);
			}
		}
	}

	/* Recibe parametros para PopUp */
	public void obtenerDatosActualizarProducto(ActionEvent lEvento) {
		lIdProductoActualizar = 0;
		lNombreActualizar = "";
		lPrecioActualizar = 0;

		lIdProductoActualizar = (int) lEvento.getComponent().getAttributes().get("ID_PRODUCTO");
		lNombreActualizar = (String) lEvento.getComponent().getAttributes().get("PROD_NOMBRE");
		lPrecioActualizar = (int) lEvento.getComponent().getAttributes().get("PROD_PRECIO");
	}
	
	
	
/*Metodo actualizar*/
	public void actualizarProducto() {

		String lResultOut = null;
		ProductoDAO lProductoDAO = new ProductoDAO();
		Producto lProducto = new Producto();

		if (lIdProductoActualizar > 0 && !lNombreActualizar.isEmpty() && lPrecioActualizar > 0) {
			lProducto.setlIdPoducto(lIdProductoActualizar);
			lProducto.setlNombre(lNombreActualizar);
			lProducto.setlPrecio(lPrecioActualizar);
			lResultOut = lProductoDAO.actualizarProducto(lProducto);

			allProductos();
			if (lResultOut != null) {
				lIdProductoActualizar = 0;
				lNombreActualizar = "";
				lPrecioActualizar = 0;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Registro actualizado con exito."));
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "Todos los campos son obligatorios."));
		}
	}

	
	
/*Metodo eliminar*/
	public void eliminarProducto(ActionEvent lEvento) {

		lIdProductoEliminar = 0;
		lIdProductoEliminar = (int) lEvento.getComponent().getAttributes().get("ID_PRODUCTO_ELIMINAR");
		String lResultOut = null;
		
		if (lIdProductoEliminar > 0) {
			ProductoDAO lProductoDAO = new ProductoDAO();
			Producto lProducto = new Producto();
			lProducto.setlIdPoducto(lIdProductoEliminar);
			lResultOut = lProductoDAO.eliminarProducto(lProducto);
			allProductos();
			
			if (lResultOut != null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Registro eliminado con exito."));
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "Ocurrio un error."));
		}
	}
	
	
	
	public String getlNombre() {
		return lNombre;
	}

	public void setlNombre(String lNombre) {
		this.lNombre = lNombre;
	}

	public String getlDescripcion() {
		return lDescripcion;
	}

	public void setlDescripcion(String lDescripcion) {
		this.lDescripcion = lDescripcion;
	}

	public int getlPrecio() {
		return lPrecio;
	}

	public void setlPrecio(int lPrecio) {
		this.lPrecio = lPrecio;
	}

	public List<Producto> getlListadoDatosProductoConsulta() {
		return lListadoDatosProductoConsulta;
	}

	public void setlListadoDatosProductoConsulta(List<Producto> lListadoDatosProductoConsulta) {
		this.lListadoDatosProductoConsulta = lListadoDatosProductoConsulta;
	}

	/**
	 * @return the lIdProductoActualizar
	 */
	public int getlIdProductoActualizar() {
		return lIdProductoActualizar;
	}

	/**
	 * @param lIdProductoActualizar
	 *            the lIdProductoActualizar to set
	 */
	public void setlIdProductoActualizar(int lIdProductoActualizar) {
		this.lIdProductoActualizar = lIdProductoActualizar;
	}

	/**
	 * @return the lNombreActualizar
	 */
	public String getlNombreActualizar() {
		return lNombreActualizar;
	}

	/**
	 * @param lNombreActualizar
	 *            the lNombreActualizar to set
	 */
	public void setlNombreActualizar(String lNombreActualizar) {
		this.lNombreActualizar = lNombreActualizar;
	}

	/**
	 * @return the lPrecioActualizar
	 */
	public int getlPrecioActualizar() {
		return lPrecioActualizar;
	}

	/**
	 * @param lPrecioActualizar
	 *            the lPrecioActualizar to set
	 */
	public void setlPrecioActualizar(int lPrecioActualizar) {
		this.lPrecioActualizar = lPrecioActualizar;
	}
	
	
	/**
	 * @return the lIdProductoEliminar
	 */
	public int getlIdProductoEliminar() {
		return lIdProductoEliminar;
	}

	/**
	 * @param lIdProductoEliminar the lIdProductoEliminar to set
	 */
	public void setlIdProductoEliminar(int lIdProductoEliminar) {
		this.lIdProductoEliminar = lIdProductoEliminar;
	}

	/**
	 * @return the lIdTipoProducto
	 */
	public int getlIdTipoProducto() {
		return lIdTipoProducto;
	}

	/**
	 * @param lIdTipoProducto the lIdTipoProducto to set
	 */
	public void setlIdTipoProducto(int lIdTipoProducto) {
		this.lIdTipoProducto = lIdTipoProducto;
	}

	
}
