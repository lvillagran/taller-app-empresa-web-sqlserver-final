package com.empresa.modulo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.empresa.modulo.conexion.DataConnect;
import com.empresa.modulo.entidad.Producto;

public class ProductoDAO extends DataConnect {
	
	/*Registra*/
	public String registraProducto(Producto producto )  {
		Connection con = null;
		PreparedStatement ps = null;
		con = DataConnect.getConnection();

		try {
			 int lValorSecuencia = 0;
			 String sqlSecuencia = " SELECT NEXT VALUE FOR desarrollo.ProductoSequence; ";
			
			 ps = con.prepareStatement(sqlSecuencia);
			 ResultSet lSecuencialConsulta = ps.executeQuery();
			 
			if(lSecuencialConsulta.next()){
				lValorSecuencia = lSecuencialConsulta.getInt(1);
				System.out.println("********** Secuencia generada: " + lValorSecuencia + " **********");
		    }
				   
			String lInsert="insert into desarrollo.producto "
				       + " (id_producto"
				       + ", nombre"
				       + ", descripcion"
				       + ", precio"
				       + ", fecha_registro"
				       + ", estado"
				       + ", tipo_producto_id)"
				       + " values(?,?,?,?,?,?,?)";
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			ps = con.prepareStatement(lInsert);
			ps.setInt(1, lValorSecuencia);
			ps.setString(2, producto.getlNombre().trim());
			ps.setString(3, producto.getlDescripcion().trim());
			ps.setDouble(4, producto.getlPrecio());
			ps.setDate(5, sqlDate);
			ps.setString(6, "A");
			ps.setInt(7, producto.getlIdTipoProducto());
			
			int resultado=ps.executeUpdate();

			if (resultado > 0) {
				con.commit();
				con.close();
				AllProductos();
				System.out.println(" ***************** Registrado con exito. ***********************");
				return "OK";
			}else{
				con.rollback();
				  System.out.println("La inserción no tuvo éxito");
				  return "ERROR";
			}
		} catch (SQLException ex) {
			System.out.println("ProductoDAO error -->" + ex.getMessage());
		}
		 return "ERROR";
	}
	
	
	/*Consulta todos los productos*/
	public List<Producto> AllProductos()  {
		
		 List<Producto> Allproductos = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultadoConsulta = null;
		
		try {
			String lQuery = "select "
					+ " p.id_producto"
					+ ", p.nombre"
					+ ", p.descripcion"
					+ ", p.precio"
					+ ", p.fecha_registro "
				    + " from desarrollo.producto p"
				    + " order by p.id_producto desc ";
			
			con = DataConnect.getConnection();
			ps = con.prepareStatement(lQuery);
			resultadoConsulta = ps.executeQuery();

			while (resultadoConsulta.next()) {
				Producto producto = new Producto();
				producto.setlIdPoducto(resultadoConsulta.getInt(1));
				producto.setlNombre(resultadoConsulta.getString(2));
				producto.setlDescripcion( resultadoConsulta.getString(3));
				producto.setlPrecio(resultadoConsulta.getInt(4));
				producto.setlFechaRegistro(resultadoConsulta.getDate(5));
				Allproductos.add(producto);
			}
			
			con.close();
		} catch (SQLException ex) {
			System.out.println("ProductoDAO error -->" + ex.getMessage());
		} 
		return Allproductos;
		
	}
	
	
	/*Actualizar*/
	public String actualizarProducto(Producto producto){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			String sql = " update desarrollo.producto "
					   + " set nombre=? "
					   + " ,precio=?"
					   + " where id_producto=? ";
			con = DataConnect.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, producto.getlNombre().trim());
			ps.setDouble(2, producto.getlPrecio());
			ps.setInt(3, producto.getlIdPoducto());
			int resultado=ps.executeUpdate();
            
            
			if (resultado > 0) {
				con.commit();
				con.close();
				System.out.println("Registro actualizado exitosamente.");
				return "OK";
			}else{
				con.rollback();
				System.out.println("No se encontró el registro para actualizar.");
			}
		} catch (SQLException ex) {
			System.out.println("ProductoDAO error -->" + ex.getMessage());
		} 
		return "OK";
	}
	
	
/*Eliminar Producto*/
	public String eliminarProducto(Producto producto){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			
			String sql = "delete from desarrollo.producto where id_producto=?";
			con = DataConnect.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setInt(1, producto.getlIdPoducto());
			int resultado=ps.executeUpdate();
            
            
			if (resultado > 0) {
				con.commit();
				con.close();
				System.out.println("Registro eliminado exitosamente.");
				return "OK";
			}else{
				con.rollback();
				System.out.println("No se encontró el registro para eliminar.");
			}
		} catch (SQLException ex) {
			System.out.println("ProductoDAO error -->" + ex.getMessage());
		} 
		return "OK";
	}
}
