package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	public Connection objConexion=null;
    public Statement objSentencia=null;   		//Se usa para enviar objSentencias SQL a la BD
    public ResultSet objResultado=null;   		//Contendrá los datos devueltos de una objSentencia SQL
    
    public Conexion(){
        conectarBD();
    }
    public void conectarBD() {
		// Conectar con MySql
		String url = "jdbc:mysql://localhost/buscaminas";

		// Cargar el driver y se genera una nueva instancia y crear conexión
		try {
			Class.forName("com.mysql.jdbc.Driver");
			objConexion = DriverManager.getConnection(url, "root", "");
			// Crear objSentencia
			objSentencia = objConexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void cerrarConexionBD(){
        try {
            objResultado.close();
            objSentencia.close();
            objConexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }      
    }
	public void guardarRanking(String nombre, int dificultad, int puntos) {
		String sql = "INSERT INTO usuarios(nombre, puntos, dificultad) VALUES (?, ?, ?)";

	    try {
	        PreparedStatement ps = objConexion.prepareStatement(sql);

	        ps.setString(1, nombre);
	        ps.setInt(2, puntos);
	        ps.setInt(3, dificultad);

	        ps.executeUpdate();
	        ps.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
