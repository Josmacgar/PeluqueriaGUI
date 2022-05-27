package bd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class ConexionBD {

	//creamos 3 variables que llaman a unas interfaces
	private Connection con; //Para crear la conexion entre la aplicacion y la BD
	private Statement st;	//Para establecer la conexion. Representa una sentencia SQL que ejecutará el servidor de la BD 
	PreparedStatement stmt = null; //Tambien representa una sentencia SQL, que permite configurar o parametrizar facilmente valores en la consulta.
	private ResultSet rs;   //Representa una tabla con el resultado que genera el SGBD tras ejecutar una sentencia de consulta
	
	public ConexionBD(){
		try {
			//aquí nos vamos a conectar a la BD. Para eso escribiremos este bloque: 
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			/*  Utilizamos la primera variable que decalramos anteriomente que nos servirá para almacenar la conexion con la BD.
			 *  A continuación de la libreria que estamos utilizando (jdbc:mysql) ponemos el host y el puerto que estamos utilizando. Esa información la tenemos disponible en nuestra aplicacion de servidor.
			 *  A continuación el nombre de la base de datos a la que nos vamos a conectar,
			 *  y por ultimo ponemos el valor del usuario y de la contraseña de dicha BD
			 */
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peluqueria", "root","");
			
			//para establecer la conexion
			st= con.createStatement();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void cerrarConexion() throws SQLException {
		con.close();

	}
	
	public void getDatosClientes(JTextArea text){
		try{
			//vamos a acceder a la informacion de cada persona que se encuentre en la tabla
			String query = "SELECT * FROM clientes";
			rs = st.executeQuery(query);
			
			//creamos un bucle para recorrer
			
		while (rs.next()){

			//para obtener info de cada columna
			String codCli = rs.getString("cod_cliente");
			String dni = rs.getString("dni");
			String nombre = rs.getString("nombre_cli");
			String ape1 = rs.getString("apellido1");
			String ape2 = rs.getString("apellido2");
			text.setText(text.getText()+"\n"+"-------------------------------------------\nCodigo: "+codCli+"\nDni: "+dni+"\nNombre: "+nombre+"\nApellido1: "+ape1+"\nApellido2: "+ape2);
			//System.out.println("--------\nUsuario: "+usuario+"\nNombre: "+nombre+"\nCorreo: "+correo);
		}
		}catch (Exception e){
			e.printStackTrace();
		}finally { //para cercionarnos que cerramos la BD
			try {
				//con.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public void InsertarClientes(JTextField dni,JTextField nombre,JTextField ape1,JTextField ape2) throws SQLException, NumberFormatException, IOException {
		 try{
	                     
		        stmt = con.prepareStatement("INSERT INTO clientes VALUES (?,?,?,?,?)");
		        stmt.setInt(1, 0);
		        stmt.setString(2, dni.getText());
		        stmt.setString(3,nombre.getText());
		        stmt.setString(4,ape1.getText());
		        stmt.setString(5,ape2.getText());

		        int retorno = stmt.executeUpdate();
		        if (retorno>0)
		        	JOptionPane.showMessageDialog(null, "Insertado correctamente");    
		        else {
		        	JOptionPane.showMessageDialog(null, "Error al insertar");  

		        }
		                     
	     } catch (SQLException sqle){
	        System.out.println("SQLState: " 
	           + sqle.getSQLState());
	        System.out.println("SQLErrorCode: " 
	           + sqle.getErrorCode());
	        sqle.printStackTrace();
	     } catch (Exception e){
	        e.printStackTrace();
	     } finally {
	        if (con != null) {
	           try{
	              stmt.close();
	           // con.close();  //Si solo fueramos a insertar, tendríamos que cerrar la BD, pero en este caso no lo hacemos puesto que luego vamos a mostrar toda la tabla
	           } catch(Exception e){
	              e.printStackTrace();
	           }
	        }
	     }		
		}

	public void EliminarClientes(JTextField dni) {
        String dnir =dni.getText();

		try {
		stmt = con.prepareStatement("DELETE FROM clientes WHERE dni=?");
			stmt.setString(1, dnir);


			//Dicho método devolverá el número de filas que se han actualizado. Será un valor entero de 0 al número de filas actualizadas.
			int retorno = stmt.executeUpdate();
	        if (retorno>0) {
	        	JOptionPane.showMessageDialog(null, "Eliminacion realizada correctamente");  
	        }else {
	        	JOptionPane.showMessageDialog(null, "No se ha podido eliminar");  
	        }
 
			
			
	     } catch (SQLException sqle){
	         System.out.println("SQLState: " 
	            + sqle.getSQLState());
	         System.out.println("SQLErrorCode: " 
	            + sqle.getErrorCode());
	         sqle.printStackTrace();
	      } catch (Exception e){
	         e.printStackTrace();
	      } finally {
	         if (con != null) {
	            try{
	               stmt.close();
	  //             con.close();  //Si solo fueramos a insertar, tendríamos que cerrar la BD, pero en este caso no lo hacemos puesto que luego vamos a mostrar toda la tabla
	            } catch(Exception e){
	               e.printStackTrace();
	            }
	         }
	      }		
	 	}
	public ArrayList<String> getDatosClientes(){
		ArrayList<String> lista = new ArrayList<String>();
		try{
			//vamos a acceder a la informacion de cada persona que se encuentre en la tabla
			String query = "SELECT * FROM clientes";
			rs = st.executeQuery(query);
			
			//creamos un bucle para recorrer
			
		while (rs.next()){

			//para obtener info de cada columna
			String codServi = rs.getString("cod_cliente");
			lista.add(codServi);
		
		}
		}catch (Exception e){
			e.printStackTrace();
		}finally { //para cercionarnos que cerramos la BD
			try {
				//con.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	
	public void getDatosServicios(JTextArea text){
		try{
			//vamos a acceder a la informacion de cada persona que se encuentre en la tabla
			String query = "SELECT * FROM servicios";
			rs = st.executeQuery(query);
			
			//creamos un bucle para recorrer
			
		while (rs.next()){

			//para obtener info de cada columna
			String codServi = rs.getString("cod_servicio");
			String nombre = rs.getString("nombre");
			String desc = rs.getString("descripcion");
			String precio = rs.getString("precio");
			String cat = rs.getString("categoria");
			text.setText(text.getText()+"\n"+"-------------------------------------------\nCodigo: "+codServi+"\nDni: "+nombre+"\nNombre: "+desc+"\nApellido1: "+precio+"\nApellido2: "+cat);
			//System.out.println("--------\nUsuario: "+usuario+"\nNombre: "+nombre+"\nCorreo: "+correo);
		}
		}catch (Exception e){
			e.printStackTrace();
		}finally { //para cercionarnos que cerramos la BD
			try {
			//	con.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> getDatosServicios(){
		ArrayList<String> lista = new ArrayList<String>();
		try{
			//vamos a acceder a la informacion de cada persona que se encuentre en la tabla
			String query = "SELECT * FROM servicios";
			rs = st.executeQuery(query);
			
			//creamos un bucle para recorrer
			
		while (rs.next()){

			//para obtener info de cada columna
			String codServi = rs.getString("cod_servicio");
			lista.add(codServi);
		
		}
		}catch (Exception e){
			e.printStackTrace();
		}finally { //para cercionarnos que cerramos la BD
			try {
				//con.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public void InsertarServicios(JTextField nombre,JTextField des,JTextField precio,JComboBox<String> cat) throws SQLException, NumberFormatException, IOException {
		 try{
	                     
		        stmt = con.prepareStatement("INSERT INTO servicios VALUES (?,?,?,?,?)");
		        stmt.setInt(1, 0);
		        stmt.setString(2, nombre.getText());
		        stmt.setString(3,des.getText());
		        stmt.setString(4,precio.getText());
		        stmt.setObject(5, cat.getSelectedItem());

		        int retorno = stmt.executeUpdate();
		        if (retorno>0)
		        	JOptionPane.showMessageDialog(null, "Insertado correctamente");    
		        else {
		        	JOptionPane.showMessageDialog(null, "Error al insertar");  

		        }
		                     
	     } catch (SQLException sqle){
	        System.out.println("SQLState: " 
	           + sqle.getSQLState());
	        System.out.println("SQLErrorCode: " 
	           + sqle.getErrorCode());
	        sqle.printStackTrace();
	     } catch (Exception e){
	        e.printStackTrace();
	     } finally {
	        if (con != null) {
	           try{
	              stmt.close();
	 //             con.close();  //Si solo fueramos a insertar, tendríamos que cerrar la BD, pero en este caso no lo hacemos puesto que luego vamos a mostrar toda la tabla
	           } catch(Exception e){
	              e.printStackTrace();
	           }
	        }
	     }		
		}
	
	public void EliminarServicios(JTextField codigoS) {
        String dnir =codigoS.getText();

		try {
		stmt = con.prepareStatement("DELETE FROM servicios WHERE cod_servicio=?");
			stmt.setString(1, dnir);


			//Dicho método devolverá el número de filas que se han actualizado. Será un valor entero de 0 al número de filas actualizadas.
			int retorno = stmt.executeUpdate();
	        if (retorno>0) {
	        	JOptionPane.showMessageDialog(null, "Eliminacion realizada correctamente");  
	        }else {
	        	JOptionPane.showMessageDialog(null, "No se ha podido eliminar");  
	        }
 
			
			
	     } catch (SQLException sqle){
	         System.out.println("SQLState: " 
	            + sqle.getSQLState());
	         System.out.println("SQLErrorCode: " 
	            + sqle.getErrorCode());
	         sqle.printStackTrace();
	      } catch (Exception e){
	         e.printStackTrace();
	      } finally {
	         if (con != null) {
	            try{
	               stmt.close();
	  //             con.close();  //Si solo fueramos a insertar, tendríamos que cerrar la BD, pero en este caso no lo hacemos puesto que luego vamos a mostrar toda la tabla
	            } catch(Exception e){
	               e.printStackTrace();
	            }
	         }
	      }		
	 	}
	
	
	public void InsertarCompra(JComboBox<String> codServi,JComboBox<String> codCli,JSpinner cantidad,JComboBox<String> tipoPago) throws SQLException, NumberFormatException, IOException {
		 try{
	                     
		        stmt = con.prepareStatement("INSERT INTO compra VALUES (?,?,?,?,?)");
		        stmt.setInt(1, 0);
		        stmt.setObject(2, codServi.getSelectedItem());
		        stmt.setObject(3, codCli.getSelectedItem());
		        stmt.setObject(4, cantidad.getValue());
		        stmt.setObject(5, tipoPago.getSelectedItem());

		        int retorno = stmt.executeUpdate();
		        if (retorno>0)
		        	JOptionPane.showMessageDialog(null, "Insertado correctamente");    
		        else {
		        	JOptionPane.showMessageDialog(null, "Error al insertar");  

		        }
		                     
	     } catch (SQLException sqle){
	        System.out.println("SQLState: " 
	           + sqle.getSQLState());
	        System.out.println("SQLErrorCode: " 
	           + sqle.getErrorCode());
	        sqle.printStackTrace();
	     } catch (Exception e){
	        e.printStackTrace();
	     } finally {
	        if (con != null) {
	           try{
	              stmt.close();
	 //             con.close();  //Si solo fueramos a insertar, tendríamos que cerrar la BD, pero en este caso no lo hacemos puesto que luego vamos a mostrar toda la tabla
	           } catch(Exception e){
	              e.printStackTrace();
	           }
	        }
	     }		
		}
	
	//devuelve el precio del servicio seleccionado en compra
	public String getDatosPrecio(JComboBox<String> comboBox){
		String precio="";
	    String dnir =(String) comboBox.getSelectedItem();
		try{
			//vamos a acceder a la informacion de cada persona que se encuentre en la tabla
			String query = "SELECT precio FROM servicios WHERE cod_servicio="+dnir;
			rs = st.executeQuery(query);


			//creamos un bucle para recorrer
			
		while (rs.next()){

			//para obtener info de cada columna
			String codServi = rs.getString(1);
			precio = codServi;
		
		}
		}catch (Exception e){
			e.printStackTrace();
		}finally { //para cercionarnos que cerramos la BD
			try {
				//con.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return precio;
	}


}
