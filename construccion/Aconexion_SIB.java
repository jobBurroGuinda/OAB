/*
 * El Sistema de Inventario de Bienes (SIB) fue desarrollado con la finalidad de
 * aportar una mejora al Centro de Educación Continua Unidad Morelia en cuanto
 * a la administración de los bienes con los que cuenta, por lo que pasa a ser
 * propiedad de Instituto Politécnico Nacional para su uso sin limitación
 * 
 * Este software fue concebido, diseñado y desarrollado por Salinas Hernández
 * Job Adolfo, alumno del Centro de Estudios Científicos y Tecnológicos No. 9
 * "Juan de Dios Bátiz Paredes" del IPN, en la carrera de Técnico en Desarrollo de Software
 * como proyecto encomendado por el cumplimiento del servicio social.
 *
 */
package ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Salinas_Hernandez_Job_Adolfo
 * @version 1.0.0.0_3
 */
public class Aconexion {
    /*
    * Con esta clase se realizará la conexión a la base de datos
    */
    
    // Se inicializan las variables para conexión
    static Connection cn = null;
    static Statement st = null;
    static PreparedStatement ps = null;    
    static ResultSet rs = null;
    
    // Se inicializan las variables para el acceso a la DB
        // Indicamos el nombre del servidor de la base de datos
    private static String servidor = "localhost";
        // Introducimos el nombre de la DB
    private static String nombreBD =  "sib";
        // Escribimos el nombre de usuario de MySQL que vamos a usar
    private static String usuario = "root";
        // Introducimos la contraseña del usuario de MySQL
    static String contraseña = "t1nocota+";
    
    
    // El siguiente método servirá para realizar la conexión
    public boolean conectar(){
        try{
            // Especificamos el Driver a utilizar, en este caso será el JDBC de MySQL
            Class.forName("com.mysql.jdbc.Driver");
            // Agregamos el Driver, el nombre del servidor, el puerto y el nombre de la base de datos en el URL o ruta 
            String url = "jdbc:mysql://" + servidor + ":3306/" + nombreBD;
            // Le pasamos el URL, el nombre de usuario y contraseña de MySQL para realizar la conexión
            cn = DriverManager.getConnection(url, usuario, contraseña);
            
        }
        // Este error se presentaría si la aplicación no cuenta con la librería de MySQL que contiene el Driver
        catch(ClassNotFoundException ex){System.out.println("Se produjo un error de la url SQL");}
        catch(SQLException ex){String msg = "";// Esta excepción se produciría en caso de que alguno de los datos del gestor o le la DB sea incorrecto
            // El error 1049 significa que la base de datos especificada no existe
            if(ex.getErrorCode() == 1049)
            {
                msg = "La base de datos: "+nombreBD+" no existe";
            }
            // El error 1044 significa que el usuario especificado no existe
            else if(ex.getErrorCode() == 1044)
            {
                msg = "El usuario: "+usuario+" no existe";
            }
            // El error 1045 indica que la contraseña es incorrecta
            else if(ex.getErrorCode() == 1045)
            {
                msg = "Contraseña SQL incorrecta";
            }
            // Por último, el error cero significa que el servidor de base de datos está inactivo
            // Esto se podría presentar si no tenemos correctamente instalado a MySQL
            else if(ex.getErrorCode() == 0)
            {
                msg = "La coneccion con la base de datos no se puede realizar\nParece que el servidor de base de datos no esta activo";
            }
            // En caso de que se haya presentado alguno de estos errores con el gestor
            // se desplegará un mensaje con informando la situación
            JOptionPane.showMessageDialog(null, ex.getMessage(), msg, JOptionPane.ERROR_MESSAGE);
            return false;}
        
        
        return false;
    }
}
