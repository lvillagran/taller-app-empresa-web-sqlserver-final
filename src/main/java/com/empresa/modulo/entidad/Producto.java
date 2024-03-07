package com.empresa.modulo.entidad;

import java.io.Serializable;
import java.util.Date;

public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int lIdPoducto;

	private String lNombre;

	private String lDescripcion;

	private int lPrecio;

	private Date lFechaRegistro;

	private String lEstado;

	/* iD Tipo de producto */
	private int lIdTipoProducto;

	public Producto() {
	}


	/**
	 * @param lIdPoducto
	 * @param lNombre
	 * @param lDescripcion
	 * @param lPrecio
	 * @param lFechaRegistro
	 * @param lEstado
	 * @param lIdTipoProducto
	 */
	public Producto(int lIdPoducto, String lNombre, String lDescripcion, int lPrecio, Date lFechaRegistro,
			String lEstado, int lIdTipoProducto) {
		this.lIdPoducto = lIdPoducto;
		this.lNombre = lNombre;
		this.lDescripcion = lDescripcion;
		this.lPrecio = lPrecio;
		this.lFechaRegistro = lFechaRegistro;
		this.lEstado = lEstado;
		this.lIdTipoProducto = lIdTipoProducto;
	}


	/**
	 * @return the lIdPoducto
	 */
	public int getlIdPoducto() {
		return lIdPoducto;
	}

	/**
	 * @param lIdPoducto the lIdPoducto to set
	 */
	public void setlIdPoducto(int lIdPoducto) {
		this.lIdPoducto = lIdPoducto;
	}

	/**
	 * @return the lNombre
	 */
	public String getlNombre() {
		return lNombre;
	}

	/**
	 * @param lNombre the lNombre to set
	 */
	public void setlNombre(String lNombre) {
		this.lNombre = lNombre;
	}

	/**
	 * @return the lDescripcion
	 */
	public String getlDescripcion() {
		return lDescripcion;
	}

	/**
	 * @param lDescripcion the lDescripcion to set
	 */
	public void setlDescripcion(String lDescripcion) {
		this.lDescripcion = lDescripcion;
	}

	/**
	 * @return the lPrecio
	 */
	public int getlPrecio() {
		return lPrecio;
	}

	/**
	 * @param lPrecio the lPrecio to set
	 */
	public void setlPrecio(int lPrecio) {
		this.lPrecio = lPrecio;
	}

	/**
	 * @return the lFechaRegistro
	 */
	public Date getlFechaRegistro() {
		return lFechaRegistro;
	}

	/**
	 * @param lFechaRegistro the lFechaRegistro to set
	 */
	public void setlFechaRegistro(Date lFechaRegistro) {
		this.lFechaRegistro = lFechaRegistro;
	}

	/**
	 * @return the lEstado
	 */
	public String getlEstado() {
		return lEstado;
	}

	/**
	 * @param lEstado the lEstado to set
	 */
	public void setlEstado(String lEstado) {
		this.lEstado = lEstado;
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
