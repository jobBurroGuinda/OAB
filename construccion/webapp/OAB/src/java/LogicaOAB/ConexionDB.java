
package LogicaOAB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Programaticos
 */
public class ConexionDB {
    // Se inicializan las variables para conexión
    private static Connection cn = null;
    private static Statement st = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    
    // Se inicializan las variables para el acceso a la DB
        // Indicamos el nombre del servidor de la base de datos
    private static final String servidor = "localhost";
        // Introducimos el nombre de la DB
    private static final String nombreBD =  "dbOAB";
        // Escribimos el nombre de usuario de MySQL que vamos a usar
    private static final String usuarioMySQL = "root";
        // Introducimos la contraseña del usuario de MySQL
    private static final String contraseñaMySQL = "t1nocota+";
    
    private boolean verificador = false;
    
    private String nomUsuarioOAB = null;
    private String passwordOAB = null;
    
    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    private final String privEspecialista = u.getEspecialista();
    private final String privRecepcionista = u.getRecepcionista();
    
    private String privilegioOAB = null;
    
    
    // El siguiente método servirá para realizar la conexión
    public void conectar(){
        try{
            // Especificamos el Driver a utilizar, en este caso será el JDBC de MySQL
            Class.forName("com.mysql.jdbc.Driver");
            // Agregamos el Driver, el nombre del servidor, el puerto y el nombre de la base de datos en el URL o ruta 
            String url = "jdbc:mysql://" + servidor + ":3306/" + nombreBD;
            // Le pasamos el URL, el nombre de usuario y contraseña de MySQL para realizar la conexión
            cn = DriverManager.getConnection(url, usuarioMySQL, contraseñaMySQL);
            
        }
        // Este error se presentaría si la aplicación no cuenta con la librería de MySQL que contiene el Driver
        catch(ClassNotFoundException ex){System.out.println("Se produjo un error de la url SQL");}
        catch(SQLException ex){String msg = "";// Esta excepción se produciría en caso de que alguno de los datos del gestor o le la DB sea incorrecto
            // El error 1049 significa que la base de datos especificada no existe
            switch (ex.getErrorCode()) {
            // El error 1044 significa que el usuario especificado no existe
                case 1049:
                    msg = "La base de datos: "+nombreBD+" no existe";
                    break;
            // El error 1045 indica que la contraseña es incorrecta
                case 1044:
                    msg = "El usuario: "+usuarioMySQL+" no existe";
                    break;
            // Por último, el error cero significa que el servidor de base de datos está inactivo
            // Esto se podría presentar si no tenemos correctamente instalado a MySQL
                case 1045:
                    msg = "Contraseña SQL incorrecta";
                    break;
                case 0:
                    msg = "La conexión con la base de datos no se puede realizar\nParece que el servidor no esta activo";
                    break;
                default:
                    break;
            }
            // En caso de que se haya presentado alguno de estos errores con el gestor
            // se desplegará un mensaje con informando la situación
                System.out.println(msg + "\n\n" + ex.getMessage());
        }
    }
    
    /*
    public char Login(String username, String password) throws SQLException {
        try {
            this.nomUsuarioOAB = username;
            this.passwordOAB = password;
            // Si no se ha realizado la conexión con la base de datos la iniciamos
            if(cn == null) {
                conectar();
            }
            String csp = "";
            // Especificamos el comando SQL de la consulta
            // La tabla "vusuarios", en realidad es una vista o view que sirve para desplegar únicamente el nombre de usuario
            // y contraseña
            String sql = "SELECT * FROM mUsuario WHERE nom_usu=? AND psw_usu=password(?)";
                ps = cn.prepareStatement(sql);
                ps.setString(1, nomUsuarioOAB);
                ps.setString(2, passwordOAB);
                // Mandamos el comando al gestor
                rs = ps.executeQuery(); 
                // Si el nombre de usuario y contraseña son correctos proseguimos con lo siguiente, de lo contrario 
                       // el verificador tomará el valor de false, lo que indicará que alguno de los dos datos o los dos son incorrectos
                // Si todo salió bien buscamos todo lo que haya en la tabla...
                while(rs.next()){
                    // ..., especificamente en el campo "id_prv" para asegurarnos si el usuario tiene algún privilegio
                    // y "guardamos" lo que encontremos en la variable "csp"
                    csp=rs.getString("idP_usu");
                }
            // Verificamos que el usuario introducido sea el administrador, ya que por ahora sólo él puede acceder
            // si es así...
            switch (csp) {
                case "1":
                    //
                    veri = 'R';
                    break;
                case "2":
                    // 
                    veri = 'E';
                    break;
                case "3":
                    // 
                    veri = 'A';
                    break;
                case "4":
                    // 
                    veri = 'G';
                    break;
                default:
                    veri = 'F';
                    break;
            }
            rs.close();
            ps.close();
             // Devolvemos el valor del verificador
             return veri;
        } finally {
        }
    }
    
    */
    
    
    public String login(String username, String password) throws SQLException {
        try {
            this.nomUsuarioOAB = username;
            this.passwordOAB = password;
            // Si no se ha realizado la conexión con la base de datos la iniciamos
            if(cn == null) {
                conectar();
            }
            String csp = "";
            // Especificamos el comando SQL de la consulta
            // La tabla "vusuarios", en realidad es una vista o view que sirve para desplegar únicamente el nombre de usuario
            // y contraseña
            String sql = "SELECT * FROM mUsuario WHERE nom_usu=? AND psw_usu=password(?)";
                ps = cn.prepareStatement(sql);
                ps.setString(1, nomUsuarioOAB);
                ps.setString(2, passwordOAB);
                // Mandamos el comando al gestor
                rs = ps.executeQuery(); 
                // Si el nombre de usuario y contraseña son correctos proseguimos con lo siguiente, de lo contrario 
                       // el verificador tomará el valor de false, lo que indicará que alguno de los dos datos o los dos son incorrectos
                // Si todo salió bien buscamos todo lo que haya en la tabla...
                while(rs.next()){
                    // ..., especificamente en el campo "id_prv" para asegurarnos si el usuario tiene algún privilegio
                    // y "guardamos" lo que encontremos en la variable "csp"
                    csp=rs.getString("idP_usu");
                }
            // Verificamos que el usuario introducido sea el administrador, ya que por ahora sólo él puede acceder
            // si es así...
            switch (csp) {
                case "1":
                    //
                    privilegioOAB = privRecepcionista;
                    break;
                case "2":
                    // 
                    privilegioOAB = privEspecialista;
                    break;
                case "3":
                    // 
                    privilegioOAB = privAdmin;
                    break;
                case "4":
                    // 
                    privilegioOAB = privGerente;
                    break;
                default:
                    privilegioOAB = "falso";
                    break;
            }
            rs.close();
            ps.close();
             // Devolvemos el valor del verificador
             return privilegioOAB;
        } finally {
        }
        
    }
    
    
    public boolean verificarIdentidadAdministrador(String nomUsuario) throws SQLException {
        // Si no se ha realizado la conexión con la base de datos la iniciamos
            if(cn == null) {
                conectar();
            }
            String csp = "";
            String sql = "SELECT idUsuario FROM vUsuariosAdmin WHERE nomUsuario=?";
                ps = cn.prepareStatement(sql);
                ps.setString(1, nomUsuario);
                // Mandamos el idUsuario al gestor
                rs = ps.executeQuery(); 
                while(rs.next()){
                    csp=rs.getString("idUsuario");
                }
                // Si el idUsuario no está vacío, significa que el usuario sí es un administrador
                verificador = !csp.isEmpty();
            rs.close();
            ps.close();
        
        return verificador;
    }
    
    
    
    /**
     * @param nombre
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList buscarNomCliente(String nombre) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList();
        Cliente cliente = null;
         if(cn == null) {
            conectar();
        }
        String sql = "select idL_cli, nom_cli, app_cli, apm_cli FROM mCliente WHERE CONCAT(nom_cli, ' ', app_cli, ' ', apm_cli) REGEXP ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nombre);
        rs = ps.executeQuery(); 
         while(rs.next()){
             cliente = new Cliente();
             cliente.setIdPersona(rs.getInt(1));
             cliente.setNombre(rs.getString(2));
             cliente.setApellidoPaterno(rs.getString(3));
             cliente.setApellidoMaterno(rs.getString(4));
             clientes.add(cliente);
         }
        rs.close();
        st.close();
        return clientes;
    }
    
    
    
    public ArrayList buscarNomClientePid(int idCliente) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList();
        Cliente cliente = null;
         if(cn == null) {
            conectar();
        }
        String sql = "select idL_cli, nom_cli, app_cli, apm_cli FROM mCliente WHERE idL_cli=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idCliente);
        rs = ps.executeQuery(); 
         while(rs.next()){
             cliente = new LogicaOAB.Cliente();
             cliente.setIdPersona(rs.getInt(1));
             cliente.setNombre(rs.getString(2));
             cliente.setApellidoPaterno(rs.getString(3));
             cliente.setApellidoMaterno(rs.getString(4));
             clientes.add(cliente);
         }
        
        return clientes;
    }
    
    
    
    public ArrayList buscarCliente(String nombre) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList();
        Cliente cliente = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vDatosCliente WHERE CONCAT(nombreCliente, ' ', apellidoPaterno, ' ', apellidoMaterno) REGEXP ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nombre);
        rs = ps.executeQuery(); 
         while(rs.next()){
             cliente = new Cliente();
             cliente.setIdPersona(rs.getInt(1));
             cliente.setNombre(rs.getString(2));
             cliente.setApellidoPaterno(rs.getString(3));
             cliente.setApellidoMaterno(rs.getString(4));
             cliente.setIdContacto(rs.getInt(5));
             cliente.setTelefono(rs.getLong(6));
             cliente.setEmail(rs.getString(7));
             cliente.setFacebook(rs.getString(8));
             clientes.add(cliente);
         }
        
        return clientes;
    }
    
    
    
    public ArrayList buscarEspecialista(String nombre) throws SQLException {
        ArrayList<Especialista> especialistas = new ArrayList();
        Especialista especialista = null;
         if(cn == null) {
            conectar();
        }
        String sql = "select * FROM vUsuariosEspecialista WHERE CONCAT(nomEspecialista, ' ', apellidoPaterno, ' ', apellidoMaterno) REGEXP ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nombre);
        rs = ps.executeQuery(); 
         while(rs.next()){
             especialista = new Especialista();
             especialista.setIdPersona(rs.getInt(1));
             especialista.setIdUsuario(rs.getInt(2));
             especialista.setNomUsuario(rs.getString(3));
             especialista.setNombre(rs.getString(4));
             especialista.setApellidoPaterno(rs.getString(5));
             especialista.setApellidoMaterno(rs.getString(6));
             especialista.setHoraComida(rs.getInt(7));
             especialista.setMinComida(rs.getDouble(8));
             especialista.setIdHorarioTrabajo(rs.getInt(9));
             especialista.setHoraEntrada(rs.getInt(10));
             especialista.setMinEntrada(rs.getDouble(11));
             especialista.setHoraSalida(rs.getInt(12));
             especialista.setMinSalida(rs.getDouble(13));
             especialista.setIdContacto(rs.getInt(14));
             especialista.setTelefono(rs.getLong(15));
             especialista.setEmail(rs.getString(16));
             especialista.setFacebook(rs.getString(17));
             especialistas.add(especialista);
         }
        
        return especialistas;
    }
    
    
    
    public ArrayList buscarEspecialista_ID(int idEspecialista) throws SQLException {
        ArrayList<Especialista> especialistas = new ArrayList();
        Especialista especialista = null;
         if(cn == null) {
            conectar();
        }
        String sql = "select * FROM vUsuariosEspecialista WHERE idEspecialista =?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idEspecialista);
        rs = ps.executeQuery(); 
         while(rs.next()){
             especialista = new Especialista();
             especialista.setIdPersona(rs.getInt(1));
             especialista.setIdUsuario(rs.getInt(2));
             especialista.setNomUsuario(rs.getString(3));
             especialista.setNombre(rs.getString(4));
             especialista.setApellidoPaterno(rs.getString(5));
             especialista.setApellidoMaterno(rs.getString(6));
             especialista.setHoraComida(rs.getInt(7));
             especialista.setMinComida(rs.getDouble(8));
             especialista.setIdHorarioTrabajo(rs.getInt(9));
             especialista.setHoraEntrada(rs.getInt(10));
             especialista.setMinEntrada(rs.getDouble(11));
             especialista.setHoraSalida(rs.getInt(12));
             especialista.setMinSalida(rs.getDouble(13));
             especialista.setIdContacto(rs.getInt(14));
             especialista.setTelefono(rs.getLong(15));
             especialista.setEmail(rs.getString(16));
             especialista.setFacebook(rs.getString(17));
             especialistas.add(especialista);
         }
        
        return especialistas;
    }
    
    
    
    public ArrayList buscarEspecialistaA(String nombre) throws SQLException {
        ArrayList<Especialista> especialistas = new ArrayList();
        Especialista especialista = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosEspecialista_a WHERE CONCAT(nomEspecialista, ' ', apellidoPaterno, ' ', apellidoMaterno) REGEXP ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nombre);
        rs = ps.executeQuery(); 
         while(rs.next()){
             especialista = new Especialista();
             especialista.setIdPersona(rs.getInt(1));
             especialista.setIdUsuario(rs.getInt(2));
             especialista.setNomUsuario(rs.getString(3));
             especialista.setNombre(rs.getString(4));
             especialista.setApellidoPaterno(rs.getString(5));
             especialista.setApellidoMaterno(rs.getString(6));
             especialista.setHoraComida(rs.getInt(7));
             especialista.setMinComida(rs.getDouble(8));
             especialista.setIdHorarioTrabajo(rs.getInt(9));
             especialista.setHoraEntrada(rs.getInt(10));
             especialista.setMinEntrada(rs.getDouble(11));
             especialista.setHoraSalida(rs.getInt(12));
             especialista.setMinSalida(rs.getDouble(13));
             especialista.setIdPrivilegio(rs.getInt(14));
             especialista.setPrivilegio(rs.getString(15));
             especialista.setIdContacto(rs.getInt(16));
             especialista.setTelefono(rs.getLong(17));
             especialista.setEmail(rs.getString(18));
             especialista.setFacebook(rs.getString(19));
             especialistas.add(especialista);
         }
        
        return especialistas;
    }
    
    
    
    public ArrayList buscarEspecialistaA_ID(int idEspecialista) throws SQLException {
        ArrayList<Especialista> especialistas = new ArrayList();
        Especialista especialista = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosEspecialista_a WHERE idEspecialista=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idEspecialista);
        rs = ps.executeQuery(); 
         while(rs.next()){
             especialista = new Especialista();
             especialista.setIdPersona(rs.getInt(1));
             especialista.setIdUsuario(rs.getInt(2));
             especialista.setNomUsuario(rs.getString(3));
             especialista.setNombre(rs.getString(4));
             especialista.setApellidoPaterno(rs.getString(5));
             especialista.setApellidoMaterno(rs.getString(6));
             especialista.setHoraComida(rs.getInt(7));
             especialista.setMinComida(rs.getDouble(8));
             especialista.setIdHorarioTrabajo(rs.getInt(9));
             especialista.setHoraEntrada(rs.getInt(10));
             especialista.setMinEntrada(rs.getDouble(11));
             especialista.setHoraSalida(rs.getInt(12));
             especialista.setMinSalida(rs.getDouble(13));
             especialista.setIdPrivilegio(rs.getInt(14));
             especialista.setPrivilegio(rs.getString(15));
             especialista.setIdContacto(rs.getInt(16));
             especialista.setTelefono(rs.getLong(17));
             especialista.setEmail(rs.getString(18));
             especialista.setFacebook(rs.getString(19));
             especialistas.add(especialista);
         }
        
        return especialistas;
    }
    
    
    
    public ArrayList<Recepcionista> buscarRecepcionista(String nombre) throws SQLException {
        ArrayList<Recepcionista> recepcionistas = new ArrayList();
        Recepcionista recepcionista = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosRecepcionista WHERE CONCAT(nomRecepcionista, ' ', apellidoPaterno, ' ', apellidoMaterno) REGEXP ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nombre);
        rs = ps.executeQuery(); 
         while(rs.next()){
             recepcionista = new Recepcionista();
             recepcionista.setIdPersona(rs.getInt(1));
             recepcionista.setIdUsuario(rs.getInt(2));
             recepcionista.setNomUsuario(rs.getString(3));
             recepcionista.setNombre(rs.getString(4));
             recepcionista.setApellidoPaterno(rs.getString(5));
             recepcionista.setApellidoMaterno(rs.getString(6));
             recepcionista.setIdContacto(rs.getInt(7));
             recepcionista.setTelefono(rs.getLong(8));
             recepcionista.setEmail(rs.getString(9));
             recepcionista.setFacebook(rs.getString(10));
             recepcionistas.add(recepcionista);
         }
        
        return recepcionistas;
    }
    
    
    public ArrayList<Recepcionista> buscarRecepcionista_ID(int idRecepcionista) throws SQLException {
        ArrayList<Recepcionista> recepcionistas = new ArrayList();
        Recepcionista recepcionista = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosRecepcionista WHERE idRecepcionista=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idRecepcionista);
        rs = ps.executeQuery(); 
         while(rs.next()){
             recepcionista = new Recepcionista();
             recepcionista.setIdPersona(rs.getInt(1));
             recepcionista.setIdUsuario(rs.getInt(2));
             recepcionista.setNomUsuario(rs.getString(3));
             recepcionista.setNombre(rs.getString(4));
             recepcionista.setApellidoPaterno(rs.getString(5));
             recepcionista.setApellidoMaterno(rs.getString(6));
             recepcionista.setIdContacto(rs.getInt(7));
             recepcionista.setTelefono(rs.getLong(8));
             recepcionista.setEmail(rs.getString(9));
             recepcionista.setFacebook(rs.getString(10));
             recepcionistas.add(recepcionista);
         }
        
        return recepcionistas;
    }
    
    
    public ArrayList<Recepcionista> buscarRecepcionistaA(String nombre) throws SQLException {
        ArrayList<Recepcionista> recepcionistas = new ArrayList();
        Recepcionista recepcionista = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosRecepcionista_a WHERE CONCAT(nomRecepcionista, ' ', apellidoPaterno, ' ', apellidoMaterno) REGEXP ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nombre);
        rs = ps.executeQuery(); 
         while(rs.next()){
             recepcionista = new Recepcionista();
             recepcionista.setIdPersona(rs.getInt(1));
             recepcionista.setIdUsuario(rs.getInt(2));
             recepcionista.setNomUsuario(rs.getString(3));
             recepcionista.setNombre(rs.getString(4));
             recepcionista.setApellidoPaterno(rs.getString(5));
             recepcionista.setApellidoMaterno(rs.getString(6));
             recepcionista.setIdPrivilegio(rs.getInt(7));
             recepcionista.setPrivilegio(rs.getString(8));
             recepcionista.setIdContacto(rs.getInt(9));
             recepcionista.setTelefono(rs.getLong(10));
             recepcionista.setEmail(rs.getString(11));
             recepcionista.setFacebook(rs.getString(12));
             recepcionistas.add(recepcionista);
         }
        
        return recepcionistas;
    }
    
    
    
    public ArrayList<Recepcionista> buscarRecepcionistaA_ID(int idRecepcionista) throws SQLException {
        ArrayList<Recepcionista> recepcionistas = new ArrayList();
        Recepcionista recepcionista = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosRecepcionista_a WHERE idRecepcionista=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idRecepcionista);
        rs = ps.executeQuery(); 
         while(rs.next()){
             recepcionista = new Recepcionista();
             recepcionista.setIdPersona(rs.getInt(1));
             recepcionista.setIdUsuario(rs.getInt(2));
             recepcionista.setNomUsuario(rs.getString(3));
             recepcionista.setNombre(rs.getString(4));
             recepcionista.setApellidoPaterno(rs.getString(5));
             recepcionista.setApellidoMaterno(rs.getString(6));
             recepcionista.setIdPrivilegio(rs.getInt(7));
             recepcionista.setPrivilegio(rs.getString(8));
             recepcionista.setIdContacto(rs.getInt(9));
             recepcionista.setTelefono(rs.getLong(10));
             recepcionista.setEmail(rs.getString(11));
             recepcionista.setFacebook(rs.getString(12));
             recepcionistas.add(recepcionista);
         }
        
        return recepcionistas;
    }
    
    
    
    public ArrayList<Gerente>buscarGerente(String nombre) throws SQLException {
        ArrayList<Gerente> gerentes = new ArrayList();
        Gerente gerente = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosGerente WHERE CONCAT(nomGerente, ' ', apellidoPaterno, ' ', apellidoMaterno) REGEXP ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nombre);
        rs = ps.executeQuery(); 
         while(rs.next()){
             gerente = new Gerente();
             gerente.setIdPersona(rs.getInt(1));
             gerente.setIdUsuario(rs.getInt(2));
             gerente.setNomUsuario(rs.getString(3));
             gerente.setNombre(rs.getString(4));
             gerente.setApellidoPaterno(rs.getString(5));
             gerente.setApellidoMaterno(rs.getString(6));
             gerente.setIdContacto(rs.getInt(7));
             gerente.setTelefono(rs.getLong(8));
             gerente.setEmail(rs.getString(9));
             gerente.setFacebook(rs.getString(10));
             gerentes.add(gerente);
         }
        
        return gerentes;
    }
    
    
    public ArrayList<Gerente> buscarGerente_ID(int idGerente) throws SQLException {
        ArrayList<Gerente> gerentes = new ArrayList();
        Gerente gerente = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosGerente WHERE idGerente=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idGerente);
        rs = ps.executeQuery(); 
         while(rs.next()){
             gerente = new Gerente();
             gerente.setIdPersona(rs.getInt(1));
             gerente.setIdUsuario(rs.getInt(2));
             gerente.setNomUsuario(rs.getString(3));
             gerente.setNombre(rs.getString(4));
             gerente.setApellidoPaterno(rs.getString(5));
             gerente.setApellidoMaterno(rs.getString(6));
             gerente.setIdContacto(rs.getInt(7));
             gerente.setTelefono(rs.getLong(8));
             gerente.setEmail(rs.getString(9));
             gerente.setFacebook(rs.getString(10));
             gerentes.add(gerente);
         }
        
        return gerentes;
    }
    
    
    public ArrayList<Gerente> buscarGerenteA(String nombre) throws SQLException {
        ArrayList<Gerente> gerentes = new ArrayList();
        Gerente gerente = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosGerente_a WHERE CONCAT(nomGerente, ' ', apellidoPaterno, ' ', apellidoMaterno) REGEXP ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nombre);
        rs = ps.executeQuery(); 
         while(rs.next()){
             gerente = new Gerente();
             gerente.setIdPersona(rs.getInt(1));
             gerente.setIdUsuario(rs.getInt(2));
             gerente.setNomUsuario(rs.getString(3));
             gerente.setNombre(rs.getString(4));
             gerente.setApellidoPaterno(rs.getString(5));
             gerente.setApellidoMaterno(rs.getString(6));
             gerente.setIdPrivilegio(rs.getInt(7));
             gerente.setPrivilegio(rs.getString(8));
             gerente.setIdContacto(rs.getInt(9));
             gerente.setTelefono(rs.getLong(10));
             gerente.setEmail(rs.getString(11));
             gerente.setFacebook(rs.getString(12));
             gerentes.add(gerente);
         }
        
        return gerentes;
    }
    
    
    
    public ArrayList<Gerente> buscarGerenteA_ID(int idGerente) throws SQLException {
        ArrayList<Gerente> gerentes = new ArrayList();
        Gerente gerente = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosGerente_a WHERE idGerente=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idGerente);
        rs = ps.executeQuery(); 
         while(rs.next()){
             gerente = new Gerente();
             gerente.setIdPersona(rs.getInt(1));
             gerente.setIdUsuario(rs.getInt(2));
             gerente.setNomUsuario(rs.getString(3));
             gerente.setNombre(rs.getString(4));
             gerente.setApellidoPaterno(rs.getString(5));
             gerente.setApellidoMaterno(rs.getString(6));
             gerente.setIdPrivilegio(rs.getInt(7));
             gerente.setPrivilegio(rs.getString(8));
             gerente.setIdContacto(rs.getInt(9));
             gerente.setTelefono(rs.getLong(10));
             gerente.setEmail(rs.getString(11));
             gerente.setFacebook(rs.getString(12));
             gerentes.add(gerente);
         }
        
        return gerentes;
    }
    
    
    
    public ArrayList<Administrador> buscarAdmin(String nombre) throws SQLException {
        ArrayList<Administrador> administradores = new ArrayList();
        Administrador administrador = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosAdmin WHERE CONCAT(nomAdmin, ' ', apellidoPaterno, ' ', apellidoMaterno) REGEXP ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nombre);
        rs = ps.executeQuery(); 
         while(rs.next()){
             administrador = new Administrador();
             administrador.setIdPersona(rs.getInt(1));
             administrador.setIdUsuario(rs.getInt(2));
             administrador.setNomUsuario(rs.getString(3));
             administrador.setNombre(rs.getString(4));
             administrador.setApellidoPaterno(rs.getString(5));
             administrador.setApellidoMaterno(rs.getString(6));
             administrador.setIdPrivilegio(rs.getInt(7));
             administrador.setPrivilegio(rs.getString(8));
             administrador.setIdContacto(rs.getInt(9));
             administrador.setTelefono(rs.getLong(10));
             administrador.setEmail(rs.getString(11));
             administrador.setFacebook(rs.getString(12));
             administradores.add(administrador);
         }
        
        return administradores;
    }
    
    
    
    public ArrayList<Administrador> buscarAdmin_ID(int idAdministrador) throws SQLException {
        ArrayList<Administrador> administradores = new ArrayList();
        Administrador administrador = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vUsuariosAdmin WHERE idAdmin=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idAdministrador);
        rs = ps.executeQuery(); 
         while(rs.next()){
             administrador = new Administrador();
             administrador.setIdPersona(rs.getInt(1));
             administrador.setIdUsuario(rs.getInt(2));
             administrador.setNomUsuario(rs.getString(3));
             administrador.setNombre(rs.getString(4));
             administrador.setApellidoPaterno(rs.getString(5));
             administrador.setApellidoMaterno(rs.getString(6));
             administrador.setIdPrivilegio(rs.getInt(7));
             administrador.setPrivilegio(rs.getString(8));
             administrador.setIdContacto(rs.getInt(9));
             administrador.setTelefono(rs.getLong(10));
             administrador.setEmail(rs.getString(11));
             administrador.setFacebook(rs.getString(12));
             administradores.add(administrador);
         }
        
        return administradores;
    }
    
    
    
    public ArrayList buscarClientepID(int idCliente) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList();
        Cliente cliente = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vDatosCliente WHERE idCliente=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idCliente);
        rs = ps.executeQuery(); 
         while(rs.next()){
             cliente = new Cliente();
             cliente.setIdPersona(rs.getInt(1));
             cliente.setNombre(rs.getString(2));
             cliente.setApellidoPaterno(rs.getString(3));
             cliente.setApellidoMaterno(rs.getString(4));
             cliente.setIdContacto(rs.getInt(5));
             cliente.setTelefono(rs.getLong(6));
             cliente.setEmail(rs.getString(7));
             cliente.setFacebook(rs.getString(8));
             clientes.add(cliente);
         }
        
        return clientes;
    }
    
    
    
    public boolean comprobarExistenciaUsername(String nomUsuario) throws SQLException {
        if(cn == null) {
                conectar();
            }
            String csp = "";
            String sql = "SELECT * FROM mUsuario where nom_usu=?";
                ps = cn.prepareStatement(sql);
                ps.setString(1, nomUsuario);
                // Mandamos el comando al gestor
                rs = ps.executeQuery();
                Collator comparador = Collator.getInstance();
                comparador.setStrength(Collator.SECONDARY);
                int veriCompar = 0;
                while(rs.next()) {
                    csp=rs.getString("nom_usu");
                    veriCompar = comparador.compare(csp, nomUsuario);
                }
                verificador = veriCompar != 0;
        
        return verificador;
    }
    
    
    
    public boolean registrarCliente(String nombre, String apellidoPaterno, String apellidoMaterno, Long telefono, String email, String facebook){
            if(cn == null) {
                conectar();
            }
            int r1 = 0, r2 = 0, rST=0, rR, rC;
            String sql1 = null, sql2 = null, sqlST, sqlR, sqlC=null;
            
            try{
                sqlST = "START TRANSACTION";
                ps = cn.prepareStatement(sqlST);
                rST= ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
            
            try{
            sql1 = "insert into dContacto (tel_con, mal_con, fbk_con) values (?, ?, ?)";
                ps = cn.prepareStatement(sql1);
                ps.setLong(1, telefono);
                ps.setString(2, email);
                ps.setString(3, facebook);
            r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            // Si se registró correctamente la tabla contacto...
            if(r1 != 0){
                try{
                    // Obtener el ID que se registró del registro contacto
                    String sqlLastC = "SELECT @lastC := LAST_INSERT_ID()";
                        st = cn.createStatement();
                        st.executeQuery(sqlLastC);
                    }catch(SQLException ex){ex.printStackTrace();}
                    // Insertar nuevo registro en la tabla mCliente
                try{
                    sql2 = "INSERT INTO mCliente (nom_cli, app_cli, apm_cli, idC_cli) VALUES (?, ?, ?, @lastC)";
                        ps = cn.prepareStatement(sql2);
                        ps.setString(1, nombre);
                        ps.setString(2, apellidoPaterno);
                        ps.setString(3, apellidoMaterno);
                    r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                // Si el registro de la tabla mCliente se llevó a cabo con éxito...
                if(r2 != 0){
                        try{
                            // Finalizar transacción
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        // Asignarle el valor TRUE al verificador para indicar que el registro se llevó acabo satisfactoriamente
                        verificador = true;
                }
                // De lo contrario..., si el registro mCliente no se realizó...
                else{
                    // Borrar el registro de dContacto que se almacenó previamente, para volver al estado anterior a la operación
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            // Finalizar transacción
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
                }
            }
            // De lo contrario..., si no se registró el registro de contacto, regresarr al estado previo
            else{
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean registrarRecepcionista(String nomUsuario, String password, String nombre, String apellidoPaterno, 
                                                        String apellidoMaterno, Long telefono, String email, String facebook){
            if(cn == null) {
                conectar();
            }
            int r1 = 0, r2 = 0, r3=0, rST=0, rR, rC;
            String sql1 = null, sql2 = null, sql3=null, sqlST, sqlR, sqlC=null;
            
            try{
                sqlST = "START TRANSACTION";
                ps = cn.prepareStatement(sqlST);
                rST= ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
            
            try{
                sql1 = "insert into dContacto (tel_con, mal_con, fbk_con) values (?, ?, ?)";
                    ps = cn.prepareStatement(sql1);
                    ps.setLong(1, telefono);
                    ps.setString(2, email);
                    ps.setString(3, facebook);
                r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
            try{
                // Obtener el ID que se registró del registro contacto
                String sqlLastC = "SELECT @lastC := LAST_INSERT_ID()";
                st = cn.createStatement();
                st.executeQuery(sqlLastC);
            }catch(SQLException ex){ex.printStackTrace();}
        
            // Si se registró correctamente la tabla contacto...
            if(r1 != 0) {
                try{
                    sql2 = "insert into mUsuario (nom_usu, psw_usu, idP_usu) values (?, password(?), 1)";
                        ps = cn.prepareStatement(sql2);
                        ps.setString(1, nomUsuario);
                        ps.setString(2, password);
                    r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                try{
                    // Obtener el ID que se registró del registro contacto
                    String sqlLastC = "SELECT @lastU := LAST_INSERT_ID();";
                    st = cn.createStatement();
                    st.executeQuery(sqlLastC);
                }catch(SQLException ex){ex.printStackTrace();}
                
                if(r2 != 0){
                        // Insertar nuevo registro en la tabla mEspecialista
                    try{
                        sql3 = "INSERT INTO mRecepcionista (nom_rta, app_rta, apm_rta, idU_rta, idC_rta) VALUES (?, ?, ?, @lastU, @lastC)";
                            ps = cn.prepareStatement(sql3);
                            ps.setString(1, nombre);
                            ps.setString(2, apellidoPaterno);
                            ps.setString(3, apellidoMaterno);
                        r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}

                    // Si el registro de la tabla mCliente se llevó a cabo con éxito...
                        if(r3 != 0) {
                                try{
                                    // Finalizar transacción
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                // Asignarle el valor TRUE al verificador para indicar que el registro se llevó acabo satisfactoriamente
                                verificador = true;
                        } else {
                                        // Borrar el registro de dContacto que se almacenó previamente, para volver al estado anterior a la operación
                                try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                                try{
                                        // Finalizar transacción
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                                verificador = false;
                        }
                }
                // De lo contrario..., si el registro mCliente no se realizó...
                else{
                    // Borrar el registro de dContacto que se almacenó previamente, para volver al estado anterior a la operación
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            // Finalizar transacción
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
                }
            }
            // De lo contrario..., si no se registró el registro de contacto, regresarr al estado previo
            else{
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean registrarGerente(String nomUsuario, String password, String nombre, String apellidoPaterno, 
                                                        String apellidoMaterno, Long telefono, String email, String facebook){
            if(cn == null) {
                conectar();
            }
            int r1 = 0, r2 = 0, r3=0, rST=0, rR, rC;
            String sql1 = null, sql2 = null, sql3=null, sqlST, sqlR, sqlC=null;
            
            try{
                sqlST = "START TRANSACTION";
                ps = cn.prepareStatement(sqlST);
                rST= ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
            
            try{
                sql1 = "insert into dContacto (tel_con, mal_con, fbk_con) values (?, ?, ?)";
                    ps = cn.prepareStatement(sql1);
                    ps.setLong(1, telefono);
                    ps.setString(2, email);
                    ps.setString(3, facebook);
                r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
            try{
                // Obtener el ID que se registró del registro contacto
                String sqlLastC = "SELECT @lastC := LAST_INSERT_ID()";
                st = cn.createStatement();
                st.executeQuery(sqlLastC);
            }catch(SQLException ex){ex.printStackTrace();}
        
            // Si se registró correctamente la tabla contacto...
            if(r1 != 0) {
                try{
                    sql2 = "insert into mUsuario (nom_usu, psw_usu, idP_usu) values (?, password(?), 4)";
                        ps = cn.prepareStatement(sql2);
                        ps.setString(1, nomUsuario);
                        ps.setString(2, password);
                    r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                try{
                    // Obtener el ID que se registró del registro contacto
                    String sqlLastC = "SELECT @lastU := LAST_INSERT_ID();";
                    st = cn.createStatement();
                    st.executeQuery(sqlLastC);
                }catch(SQLException ex){ex.printStackTrace();}
                
                if(r2 != 0){
                        // Insertar nuevo registro en la tabla mEspecialista
                    try{
                        sql3 = "INSERT INTO mGerente (nom_gte, app_gte, apm_gte, idU_gte, idC_gte) VALUES (?, ?, ?, @lastU, @lastC)";
                            ps = cn.prepareStatement(sql3);
                            ps.setString(1, nombre);
                            ps.setString(2, apellidoPaterno);
                            ps.setString(3, apellidoMaterno);
                        r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}

                    // Si el registro de la tabla mCliente se llevó a cabo con éxito...
                        if(r3 != 0) {
                                try{
                                    // Finalizar transacción
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                // Asignarle el valor TRUE al verificador para indicar que el registro se llevó acabo satisfactoriamente
                                verificador = true;
                        } else {
                                        // Borrar el registro de dContacto que se almacenó previamente, para volver al estado anterior a la operación
                                try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                                try{
                                        // Finalizar transacción
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                                verificador = false;
                        }
                }
                // De lo contrario..., si el registro mCliente no se realizó...
                else{
                    // Borrar el registro de dContacto que se almacenó previamente, para volver al estado anterior a la operación
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            // Finalizar transacción
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
                }
            }
            // De lo contrario..., si no se registró el registro de contacto, regresarr al estado previo
            else{
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean registrarAdministrador(String nomUsuario, String password, String nombre, String apellidoPaterno, 
                                                        String apellidoMaterno, Long telefono, String email, String facebook){
            if(cn == null) {
                conectar();
            }
            int r1 = 0, r2 = 0, r3=0, rST=0, rR, rC;
            String sql1 = null, sql2 = null, sql3=null, sqlST, sqlR, sqlC=null;
            
            try{
                sqlST = "START TRANSACTION";
                ps = cn.prepareStatement(sqlST);
                rST= ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
            
            try{
                sql1 = "insert into dContacto (tel_con, mal_con, fbk_con) values (?, ?, ?)";
                    ps = cn.prepareStatement(sql1);
                    ps.setLong(1, telefono);
                    ps.setString(2, email);
                    ps.setString(3, facebook);
                r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
            try{
                // Obtener el ID que se registró del registro contacto
                String sqlLastC = "SELECT @lastC := LAST_INSERT_ID()";
                st = cn.createStatement();
                st.executeQuery(sqlLastC);
            }catch(SQLException ex){ex.printStackTrace();}
        
            // Si se registró correctamente la tabla contacto...
            if(r1 != 0) {
                try{
                    sql2 = "insert into mUsuario (nom_usu, psw_usu, idP_usu) values (?, password(?), 3)";
                        ps = cn.prepareStatement(sql2);
                        ps.setString(1, nomUsuario);
                        ps.setString(2, password);
                    r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                try{
                    // Obtener el ID que se registró del registro contacto
                    String sqlLastC = "SELECT @lastU := LAST_INSERT_ID();";
                    st = cn.createStatement();
                    st.executeQuery(sqlLastC);
                }catch(SQLException ex){ex.printStackTrace();}
                
                if(r2 != 0){
                        // Insertar nuevo registro en la tabla mEspecialista
                    try{
                        sql3 = "INSERT INTO mAdministrador (nom_adm, app_adm, apm_adm, idU_adm, idC_adm) VALUES (?, ?, ?, @lastU, @lastC)";
                            ps = cn.prepareStatement(sql3);
                            ps.setString(1, nombre);
                            ps.setString(2, apellidoPaterno);
                            ps.setString(3, apellidoMaterno);
                        r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}

                    // Si el registro de la tabla mCliente se llevó a cabo con éxito...
                        if(r3 != 0) {
                                try{
                                    // Finalizar transacción
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                // Asignarle el valor TRUE al verificador para indicar que el registro se llevó acabo satisfactoriamente
                                verificador = true;
                        } else {
                                        // Borrar el registro de dContacto que se almacenó previamente, para volver al estado anterior a la operación
                                try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                                try{
                                        // Finalizar transacción
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                                verificador = false;
                        }
                }
                // De lo contrario..., si el registro mCliente no se realizó...
                else{
                    // Borrar el registro de dContacto que se almacenó previamente, para volver al estado anterior a la operación
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            // Finalizar transacción
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
                }
            }
            // De lo contrario..., si no se registró el registro de contacto, regresarr al estado previo
            else{
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public ArrayList<Servicio> desplegarServicios() throws SQLException{
        ArrayList<Servicio> servicios = new ArrayList<>();
        Servicio servicio = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT idS_srv, des_srv FROM cServicio";
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery(); 
         while(rs.next()){
             servicio = new Servicio();
             servicio.setIdServicio(rs.getInt(1));
             servicio.setDescripcionServ(rs.getString(2));
             servicios.add(servicio);
         }
        
        return servicios;
    }
    
    
    
    public boolean registrarEspecialista(String nomUsuario, String password, String nombre, String apellidoPaterno, 
                    String apellidoMaterno, Long telefono, String email, String facebook, String fHoraComida, 
                                                            String fHoraEntrada, String fHoraSalida, List<Integer>servsSelec){
            if(cn == null) {
                conectar();
            }
            int r1 = 0, r2 = 0, r3=0, r4=0, r5=0, rST=0, rR, rC;
            String sql1 = null, sql2 = null, sql3=null, sql4=null, sql5=null, sqlST, sqlR, sqlC=null;
            
            try{
                sqlST = "START TRANSACTION";
                ps = cn.prepareStatement(sqlST);
                rST= ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
            
            try{
                sql1 = "insert into dContacto (tel_con, mal_con, fbk_con) values (?, ?, ?)";
                    ps = cn.prepareStatement(sql1);
                    ps.setLong(1, telefono);
                    ps.setString(2, email);
                    ps.setString(3, facebook);
                r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
            try{
                // Obtener el ID que se registró del registro contacto
                String sqlLastC = "SELECT @lastC := LAST_INSERT_ID()";
                st = cn.createStatement();
                st.executeQuery(sqlLastC);
            }catch(SQLException ex){ex.printStackTrace();}
        
            // Si se registró correctamente la tabla contacto...
            if(r1 != 0) {
                try{
                    sql2 = "insert into mUsuario (nom_usu, psw_usu, idP_usu) values (?, password(?), 2)";
                        ps = cn.prepareStatement(sql2);
                        ps.setString(1, nomUsuario);
                        ps.setString(2, password);
                    r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                try{
                    // Obtener el ID que se registró del registro contacto
                    String sqlLastC = "SELECT @lastU := LAST_INSERT_ID();";
                    st = cn.createStatement();
                    st.executeQuery(sqlLastC);
                }catch(SQLException ex){ex.printStackTrace();}
                
                if(r2 != 0){
                        // Insertar nuevo registro en la tabla mEspecialista
                    try{
                        sql3 = "INSERT INTO dHorarioTrabajo (hre_hrt, hrs_hrt) values (?, ?)";
                            ps = cn.prepareStatement(sql3);
                            ps.setString(1, fHoraEntrada);
                            ps.setString(2, fHoraSalida);
                        r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}
                    try{
                        // Obtener el ID que se registró del registro contacto
                        String sqlLastC = "SELECT @lastT := LAST_INSERT_ID();";
                        st = cn.createStatement();
                        st.executeQuery(sqlLastC);
                    }catch(SQLException ex){ex.printStackTrace();}

                    // Si el registro de la tabla dHorarioTrabajo se llevó a cabo con éxito...
                        if(r3 != 0) {
                                    // Insertar nuevo registro en la tabla mEspecialista
                            try{
                                sql4 = "INSERT INTO mEspecialista (nom_esp, app_esp, apm_esp, hrc_esp, idU_esp, idC_esp, idT_esp) values (?, ?, ?, ?, @lastU, @lastC, @lastT)";
                                    ps = cn.prepareStatement(sql4);
                                    ps.setString(1, nombre);
                                    ps.setString(2, apellidoPaterno);
                                    ps.setString(3, apellidoMaterno);
                                    ps.setString(4, fHoraComida);
                                r4 = ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sql4");ex.printStackTrace();}
                            try{
                                // Obtener el ID que se registró del registro contacto
                                String sqlLastC = "SELECT @lastE := LAST_INSERT_ID();";
                                st = cn.createStatement();
                                st.executeQuery(sqlLastC);
                            }catch(SQLException ex){ex.printStackTrace();}
                            
                                    if(r4 != 0) {
                                        boolean verifS = false;
                                        List<Integer>servicios = new ArrayList<>();
                                        servicios = servsSelec;
                                        int idServicio = 0;
                                        for(int i=0 ; i<servicios.size() ; i++) {
                                                idServicio = servicios.get(i);
                                                try{
                                                    sql5 = "INSERT INTO cServEspecialista (idS_sve, idE_sve) values (?, @lastE)";
                                                        ps = cn.prepareStatement(sql5);
                                                        ps.setInt(1, idServicio);
                                                    r5 = ps.executeUpdate();
                                                }catch(SQLException ex){System.out.println("sql5");ex.printStackTrace();}
                                                if(r5 == 0) {
                                                    verifS = false;
                                                    break;
                                                } else {
                                                    verifS = true;
                                                }
                                        }
                                        if(verifS != false){
                                                                try{
                                                                    // Finalizar transacción
                                                                    sqlC = "COMMIT";
                                                                    ps = cn.prepareStatement(sqlC);
                                                                    rC= ps.executeUpdate();
                                                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                                                // Asignarle el valor TRUE al verificador para indicar que el registro se llevó acabo satisfactoriamente
                                                                verificador = true;
                                        }
                                        else {
                                            try{
                                                sqlR = "ROLLBACK";
                                                ps = cn.prepareStatement(sqlR);
                                                rR= ps.executeUpdate();
                                            }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                                            try{
                                                    // Finalizar transacción
                                                    sqlC = "COMMIT";
                                                    ps = cn.prepareStatement(sqlC);
                                                    rC= ps.executeUpdate();
                                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                            // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                                            verificador = false;
                                        }
                                    } else {////////////////////
                                            try{
                                                sqlR = "ROLLBACK";
                                                ps = cn.prepareStatement(sqlR);
                                                rR= ps.executeUpdate();
                                            }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                                            try{
                                                    // Finalizar transacción
                                                    sqlC = "COMMIT";
                                                    ps = cn.prepareStatement(sqlC);
                                                    rC= ps.executeUpdate();
                                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                            // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                                            verificador = false;
                                    }
                                
                        } else {
                                        // Borrar el registro de dContacto que se almacenó previamente, para volver al estado anterior a la operación
                                try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                                try{
                                        // Finalizar transacción
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                                verificador = false;
                        }
                }
                // De lo contrario..., si el registro mCliente no se realizó...
                else{
                    // Borrar el registro de dContacto que se almacenó previamente, para volver al estado anterior a la operación
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            // Finalizar transacción
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
                }
            }
            // De lo contrario..., si no se registró el registro de contacto, regresarr al estado previo
            else{
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean registrarCita(String horaCita, String fechaCita, int idCliente, int idServicio, int idEspecialista) {
        if(cn == null) {
                conectar();
            }
            int r = 0, r2 = 0, rST=0, rR, rC;
            String sql1 = null, sqlST, sqlR, sqlC=null;
            
            try{
                sqlST = "START TRANSACTION";
                ps = cn.prepareStatement(sqlST);
                rST= ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
            
            try{
            sql1 = "INSERT INTO dCita (hra_cta, fch_cta, idL_cta, idS_cta, idE_cta) values (?, ?, ?, ?, ?)";
                ps = cn.prepareStatement(sql1);
                ps.setString(1, horaCita);
                ps.setString(2, fechaCita);
                ps.setInt(3, idCliente);
                ps.setInt(4, idServicio);
                ps.setInt(5, idEspecialista);
            r = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql");ex.printStackTrace();}
            
            if(r != 0){
                try{
                    // Finalizar transacción
                        sqlC = "COMMIT";
                        ps = cn.prepareStatement(sqlC);
                        rC= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    // Asignarle el valor TRUE al verificador para indicar que el registro se llevó acabo satisfactoriamente
                    verificador = true;
            }
            else{
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
            }
            
        return verificador;
    }
    
    
    
    public boolean registrarServicio(String servicio, String duracion) {
        if(cn == null) {
                conectar();
            }
            int r = 0, r2 = 0, rST=0, rR, rC;
            String sql1 = null, sqlST, sqlR, sqlC=null;
            
            try{
                sqlST = "START TRANSACTION";
                ps = cn.prepareStatement(sqlST);
                rST= ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
            
            try{
            sql1 = "INSERT INTO cServicio (des_srv, dur_srv) values (?, ?)";
                ps = cn.prepareStatement(sql1);
                ps.setString(1, servicio);
                ps.setString(2, duracion);
            r = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql");ex.printStackTrace();}
            
            if(r != 0){
                try{
                    // Finalizar transacción
                        sqlC = "COMMIT";
                        ps = cn.prepareStatement(sqlC);
                        rC= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    // Asignarle el valor TRUE al verificador para indicar que el registro se llevó acabo satisfactoriamente
                    verificador = true;
            }
            else{
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
            }
            
        return verificador;
    }
    
    
    
    public ArrayList<Servicio> obtenerDatosValidaReserva(int idServicio, int idEspecialista) throws SQLException {
        ArrayList<Servicio> dValidaReserva = new ArrayList<>();
        Servicio servicio = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vDatosValidaReserva WHERE idServicio=? AND idEspecialista=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idServicio);
        ps.setInt(2, idEspecialista);
        rs = ps.executeQuery(); 
         while(rs.next()){
             servicio = new Servicio();
             servicio.setIdPersona(rs.getInt(1));
             servicio.setNombre(rs.getString(2));
             servicio.setIdServicio(rs.getInt(3));
             servicio.setDescripcionServ(rs.getString(4));
             servicio.setHoraDuracion(rs.getInt(5));
             servicio.setMinDuracion(rs.getInt(6));
             servicio.setHoraEntrada(rs.getInt(7));
             servicio.setMinEntrada(rs.getInt(8));
             servicio.setHoraSalida(rs.getInt(9));
             servicio.setMinSalida(rs.getInt(10));
             servicio.setHoraComida(rs.getInt(11));
             servicio.setMinComida(rs.getInt(12));
             dValidaReserva.add(servicio);
         }
        
        return dValidaReserva;
    }
    
    
    
    public ArrayList<Cita> obtenerCitasProgramPespecialista(int idEspecialista, String fecha) throws SQLException{
        ArrayList<Cita> citas = new ArrayList<>();
        Cita cita = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT horaCita, minutosCita, horaDuracion, minutosDuracion FROM vCitaspEspecialista WHERE idEspecialista=? AND fecha=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idEspecialista);
        ps.setString(2, fecha);
        rs = ps.executeQuery(); 
        while(rs.next()){
             cita = new Cita();
             cita.setHoraCita(rs.getInt(1));
             cita.setMinCita(rs.getDouble(2));
             cita.setHoraDuracion(rs.getInt(3));
             cita.setMinDuracion(rs.getDouble(4));
             citas.add(cita);
         }
        return citas;
    }
    
    
    
    public ArrayList<Cita> verCitasDhoy() throws SQLException {
        ArrayList<Cita> citas = new ArrayList<>();
        Cita cita = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vCitasDhoy order by especialista";
         st = cn.createStatement();
         rs = st.executeQuery(sql);
        while(rs.next()){
            cita = new Cita();
            cita.setIdCita(rs.getInt(1));
            cita.setHoraCita(rs.getInt(2));
            cita.setMinCita(rs.getDouble(3));
            cita.setIdPersona(rs.getInt(4));
            cita.setNombre(rs.getString(5));
            cita.setIdServicio(rs.getInt(6));
            cita.setDescripcionServ(rs.getString(7));
            cita.setHoraDuracion(rs.getInt(8));
            cita.setMinDuracion(rs.getDouble(9));
            cita.setNomCliente(rs.getString(10));
            citas.add(cita);
        }
        
       return citas;
    }
    
    
    public ArrayList<Cita> verCitasDhoyPespecialista(String username) throws SQLException {
        ArrayList<Cita> citas = new ArrayList<>();
        Cita cita = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vCitaspEspecialista WHERE fecha=curdate() AND usuario = ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, username);
        rs = ps.executeQuery(); 
        while(rs.next()){
            cita = new Cita();
            cita.setNomUsuario(rs.getString(1));
            cita.setIdPersona(rs.getInt(2));
            cita.setNombre(rs.getString(3));
            cita.setIdCita(rs.getInt(4));
            // Se salta la fecha que es la posición 5, porque no se ocupa
            // Se mostrarán datos con la misma fecha
            cita.setHoraCita(rs.getInt(6));
            cita.setMinCita(rs.getDouble(7));
            cita.setIdServicio(rs.getInt(8));
            cita.setDescripcionServ(rs.getString(9));
            cita.setHoraDuracion(rs.getInt(10));
            cita.setMinDuracion(rs.getDouble(11));
            cita.setNomCliente(rs.getString(12));
            citas.add(cita);
        }
        
       return citas;
    }
    
    
    
    public ArrayList<Cita> verCitasPEspecialista(String username) throws SQLException {
        ArrayList<Cita> citas = new ArrayList<>();
        Cita cita = null;
        if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vCitasPespecialista WHERE usuario=?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, username);
        rs = ps.executeQuery(); 
        while(rs.next()){
            cita = new Cita();
            cita.setNomUsuario(rs.getString(1));
            cita.setIdPersona(rs.getInt(2));
            cita.setNombre(rs.getString(3));
            cita.setIdCita(rs.getInt(4));
            cita.setFecha(rs.getString(5));
            cita.setHoraCita(rs.getInt(6));
            cita.setMinCita(rs.getDouble(7));
            cita.setIdServicio(rs.getInt(8));
            cita.setDescripcionServ(rs.getString(9));
            cita.setHoraDuracion(rs.getInt(10));
            cita.setMinDuracion(rs.getDouble(11));
            cita.setNomCliente(rs.getString(12));;
            citas.add(cita);
        }
    
       return citas;
    }
    
    
    
    public ArrayList<Cita> verCita(int idCita) throws SQLException {
        ArrayList<Cita> citas = new ArrayList<>();
        Cita cita = null;
        if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vCitaspFecha where idCita =?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idCita);
        rs = ps.executeQuery(); 
        while(rs.next()){
            cita = new Cita();
            cita.setIdCita(rs.getInt(1));
            cita.setFecha(rs.getString(2));
            cita.setHoraCita(rs.getInt(3));
            cita.setMinCita(rs.getDouble(4));
            cita.setIdPersona(rs.getInt(5));
            cita.setNombre(rs.getString(6));
            cita.setIdServicio(rs.getInt(7));
            cita.setDescripcionServ(rs.getString(8));
            cita.setHoraDuracion(rs.getInt(9));
            cita.setMinDuracion(rs.getDouble(10));
            cita.setNomCliente(rs.getString(11));;
            citas.add(cita);
        }
    
       return citas;
    }
    
    
    
    public boolean posponerCita(int idCita, String fecha, String hora) throws SQLException {
        if(cn == null) {
            conectar();
        }
        int r = 0, rST=0, rR, rC;
        String sql = null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql = "UPDATE dCita SET hra_cta=? , fch_cta=? WHERE idI_cta=?";
                ps = cn.prepareStatement(sql);
                ps.setString(1, hora);
                ps.setString(2, fecha);
                ps.setInt(3, idCita);
            r = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql");ex.printStackTrace();}
        
        if(r != 0) {
            try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = true;
        }
        else {
            try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
        }
        
        ps.close();
        return verificador;
    }
    
    
    
    public boolean actualizarServicio(int idServicio, String servicio, String duracion) throws SQLException {
        if(cn == null) {
            conectar();
        }
        int r = 0, rST=0, rR, rC;
        String sql = null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql = "UPDATE cServicio SET des_srv=? , dur_srv=? WHERE idS_srv=?";
                ps = cn.prepareStatement(sql);
                ps.setString(1, servicio);
                ps.setString(2, duracion);
                ps.setInt(3, idServicio);
            r = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql");ex.printStackTrace();}
        
        if(r != 0) {
            try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = true;
        }
        else {
            try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                try{
                    // Finalizar transacción
                    sqlC = "COMMIT";
                    ps = cn.prepareStatement(sqlC);
                    rC= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                    verificador = false;
        }
        
        ps.close();
        return verificador;
    }
    
    
    
    public ArrayList<Servicio> verNomServicios() throws SQLException {
        ArrayList<Servicio> Servicios = new ArrayList();
        Servicio servicio = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT DISTINCT idServicio, servicio, hour(duracion), minute(duracion) FROM vServicios";
         st = cn.createStatement();
         rs = st.executeQuery(sql); 
         while(rs.next()){
             servicio = new Servicio();
             servicio.setIdServicio(rs.getInt(1));
             servicio.setDescripcionServ(rs.getString(2));
             servicio.setHoraDuracion(rs.getInt(3));
             servicio.setMinDuracion(rs.getDouble(4));
             Servicios.add(servicio);
         }
        return Servicios;
    }
    
    
    
    public ArrayList<Servicio> desplegarServiciosCompleto() throws SQLException {
        ArrayList<Servicio> Servicios = new ArrayList();
        Servicio servicio = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT DISTINCT idS_srv, des_srv, hour(dur_srv), minute(dur_srv) FROM cServicio ORDER BY des_srv";
         st = cn.createStatement();
         rs = st.executeQuery(sql); 
         while(rs.next()){
             servicio = new Servicio();
             servicio.setIdServicio(rs.getInt(1));
             servicio.setDescripcionServ(rs.getString(2));
             servicio.setHoraDuracion(rs.getInt(3));
             servicio.setMinDuracion(rs.getDouble(4));
             Servicios.add(servicio);
         }
        return Servicios;
    }
    
    
    public ArrayList<Servicio> desplegarServiciosCompleto(int idServicio) throws SQLException {
        ArrayList<Servicio> Servicios = new ArrayList();
        Servicio servicio = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT DISTINCT idS_srv, des_srv, hour(dur_srv), minute(dur_srv) FROM cServicio WHERE idS_srv=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idServicio);
        rs = ps.executeQuery();
         while(rs.next()){
             servicio = new Servicio();
             servicio.setIdServicio(rs.getInt(1));
             servicio.setDescripcionServ(rs.getString(2));
             servicio.setHoraDuracion(rs.getInt(3));
             servicio.setMinDuracion(rs.getDouble(4));
             Servicios.add(servicio);
         }
        return Servicios;
    }
    
    
    public ArrayList<Servicio> desplegarServiciosCompleto(String nomServicio) throws SQLException {
        ArrayList<Servicio> Servicios = new ArrayList();
        Servicio servicio = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT DISTINCT idS_srv, des_srv, hour(dur_srv), minute(dur_srv) FROM cServicio WHERE des_srv REGEXP ? ORDER BY des_srv";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nomServicio);
        rs = ps.executeQuery();
         while(rs.next()){
             servicio = new Servicio();
             servicio.setIdServicio(rs.getInt(1));
             servicio.setDescripcionServ(rs.getString(2));
             servicio.setHoraDuracion(rs.getInt(3));
             servicio.setMinDuracion(rs.getDouble(4));
             Servicios.add(servicio);
         }
        return Servicios;
    }
    
    
    
    public ArrayList<Servicio> verNomServiciospEsp(int idEspecialista) throws SQLException {
        ArrayList<Servicio> Servicios = new ArrayList();
        Servicio servicio = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT DISTINCT idServicio, servicio, hour(duracion), minute(duracion) FROM vServicios WHERE idEspecialista=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idEspecialista);
        rs = ps.executeQuery(); 
         while(rs.next()){
             servicio = new Servicio();
             servicio.setIdServicio(rs.getInt(1));
             servicio.setDescripcionServ(rs.getString(2));
             servicio.setHoraDuracion(rs.getInt(3));
             servicio.setMinDuracion(rs.getDouble(4));
             Servicios.add(servicio);
         }
        return Servicios;
    }
    
    
    
    public ArrayList<Especialista> verNomEspecialistaS(int idServicio) throws SQLException {
        ArrayList<Especialista> especialistas = new ArrayList();
        Especialista especialista = null;
         if(cn == null) {
            conectar();
        }
        String sql = "SELECT DISTINCT idEspecialista, especialista FROM vServicios WHERE idServicio=?";
         ps = cn.prepareStatement(sql);
         ps.setInt(1, idServicio);
         rs = ps.executeQuery(); 
         while(rs.next()){
             especialista = new Especialista();
             especialista.setIdPersona(rs.getInt(1));
             especialista.setNombre(rs.getString(2));
             especialistas.add(especialista);
         }
        return especialistas;
    }
    
    
    public boolean cancelarCita(int idCita) {
        if(cn == null) {
            conectar();
        }
        int r = 0, rST=0, rR, rC;
        String sql = null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql = "DELETE FROM dCita WHERE idI_cta=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idCita);
            r = ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
        if(r != 0){
    // Si la citase dio de baja correctamente correctamente..., los cambios se hacen permanentes
                            try{
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = true;
        }
        // De lo contrario...
        else{
                // Eliminamos cualquier cambio y quedamos como si núnca se hubiera realizado esta operación
                        try{
                                sqlR = "ROLLBACK";
                                ps = cn.prepareStatement(sqlR);
                                rR= ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
        }     
        
        return verificador;
    }
    
    
    
    public boolean eliminarServicio(int idServicio) {
        if(cn == null) {
            conectar();
        }
        int r = 0, rST=0, rR, rC;
        String sql = null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql = "DELETE FROM cServicio WHERE idS_srv=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idServicio);
            r = ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
        if(r != 0){
            // Si el servicio dio de baja correctamente correctamente..., los cambios se hacen permanentes
                            try{
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = true;
        }
        // De lo contrario...
        else{
                // Eliminamos cualquier cambio y quedamos como si núnca se hubiera realizado esta operación
                        try{
                                sqlR = "ROLLBACK";
                                ps = cn.prepareStatement(sqlR);
                                rR= ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
        }     
        
        return verificador;
    }
    
    
    
    public boolean bajaCliente(int idContacto) {
        if(cn == null) {
            conectar();
        }
        int r1 = 0, r2=0, rST=0, rR, rC;
        String sql1 = null, sql2 = null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "DELETE FROM dContacto WHERE idC_con=?";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, idContacto);
            r1 = ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0){
                        try{
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = true;
            }
        // De lo contrario..., si el registro de contacto no se eliminó...
        else{
                // Eliminamos cualquier cambio y quedamos como si núnca se hubiera realizado esta operación
                        try{
                                sqlR = "ROLLBACK";
                                ps = cn.prepareStatement(sqlR);
                                rR= ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
        }     
        
        return verificador;
    }
    
    
    
    public boolean bajaRecepcionistaGerenteOAdmin(int idUsuario, int idContacto) {
        if(cn == null) {
            conectar();
        }
        int r1 = 0, r2=0, rST=0, rR, rC;
        String sql1 = null, sql2 = null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "DELETE FROM dContacto WHERE idC_con=?";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, idContacto);
            r1 = ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0){
                try{
                    sql2 = "DELETE FROM mUsuario WHERE idU_usu=?";
                    ps = cn.prepareStatement(sql2);
                    ps.setInt(1, idUsuario);
                    r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                    if(r2 != 0){
                        try{
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = true;
                    }
                    else{
                    // Eliminamos cualquier cambio y quedamos como si núnca se hubiera realizado esta operación
                            try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                            try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
                    }     
            }
            // De lo contrario..., si el registro de contacto no se eliminó...
            else{
                    // Eliminamos cualquier cambio y quedamos como si núnca se hubiera realizado esta operación
                            try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                            try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
            }     
        
        return verificador;
    }
    
    
    
    public boolean bajaEspecialista(int idUsuario, int idContacto, int idHorarioTrabajo) {
        if(cn == null) {
            conectar();
        }
        int r1 = 0, r2=0, r3=0, rST=0, rR, rC;
        String sql1 = null, sql2 = null, sql3 = null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "DELETE FROM dContacto WHERE idC_con=?";
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, idContacto);
            r1 = ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0){
                try{
                    sql2 = "DELETE FROM mUsuario WHERE idU_usu=?";
                    ps = cn.prepareStatement(sql2);
                    ps.setInt(1, idUsuario);
                    r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                    if(r2 != 0){
                        try{
                            sql3 = "DELETE FROM dHorarioTrabajo WHERE idT_hrt=?";
                            ps = cn.prepareStatement(sql3);
                            ps.setInt(1, idHorarioTrabajo);
                            r3 = ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}
                        
                        if(r3 != 0){
                            try{
                                sqlC = "COMMIT";
                                ps = cn.prepareStatement(sqlC);
                                rC= ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                            verificador = true;
                        }
                        else{
                    // Eliminamos cualquier cambio y quedamos como si núnca se hubiera realizado esta operación
                            try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                            try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
                        }    
                        
                    }
                    else{ // Si no de dio de baja correctamente el usuario
                    // Eliminamos cualquier cambio y quedamos como si núnca se hubiera realizado esta operación
                            try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                            try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
                    }     
            }
            // De lo contrario..., si el registro de contacto no se eliminó...
            else{
                    // Eliminamos cualquier cambio y quedamos como si núnca se hubiera realizado esta operación
                            try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                            try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
            }     
        
        return verificador;
    }
    
    
    
    public ArrayList<Cita> verCitasPcliente(int idCliente) throws SQLException {
        ArrayList<Cita> citas = new ArrayList<>();
        Cita cita = null;
        if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vCitaspCliente WHERE (fecha=curdate() OR fecha>curdate()) AND idCliente=?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, idCliente);
        rs = ps.executeQuery(); 
        while(rs.next()){
            cita = new Cita();
            cita.setIdCita(rs.getInt(1));
            cita.setIdPersona(rs.getInt(2));
            cita.setNombre(rs.getString(3));
            cita.setFecha(rs.getString(4));
            cita.setHoraCita(rs.getInt(5));
            cita.setMinCita(rs.getDouble(6));
            cita.setIdServicio(rs.getInt(7));
            cita.setDescripcionServ(rs.getString(8));
            cita.setHoraDuracion(rs.getInt(9));
            cita.setMinDuracion(rs.getDouble(10));
            cita.setIdCliente(rs.getInt(11));
            cita.setNomCliente(rs.getString(12));
            citas.add(cita);
        }
        
        return citas;
    }
    
    
    public ArrayList<Cita> verCitasPespecialistaHoyA(String nomEspecialista) throws SQLException {
        ArrayList<Cita> citas = new ArrayList<>();
        Cita cita = null;
        if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vCitaspEspecialista WHERE (fecha=curdate() OR fecha>curdate()) AND especialista REGEXP ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nomEspecialista);
        rs = ps.executeQuery(); 
        while(rs.next()){
            cita = new Cita();
            cita.setIdPersona(rs.getInt(2));
            cita.setNombre(rs.getString(3));
            cita.setIdCita(rs.getInt(4));
            cita.setFecha(rs.getString(5));
            cita.setHoraCita(rs.getInt(6));
            cita.setMinCita(rs.getDouble(7));
            cita.setIdServicio(rs.getInt(8));
            cita.setDescripcionServ(rs.getString(9));
            cita.setHoraDuracion(rs.getInt(10));
            cita.setMinDuracion(rs.getDouble(11));
            cita.setNomCliente(rs.getString(12));
            citas.add(cita);
        }
        
        return citas;
    }
    
    
    public ArrayList<Cita> verCitasPfecha(String fecha) throws SQLException {
        ArrayList<Cita> citas = new ArrayList<>();
        Cita cita = null;
        if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vCitaspFecha WHERE fecha=?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, fecha);
        rs = ps.executeQuery(); 
        while(rs.next()){
            cita = new Cita();
            cita.setIdCita(rs.getInt(1));
            cita.setFecha(rs.getString(2));
            cita.setHoraCita(rs.getInt(3));
            cita.setMinCita(rs.getDouble(4));
            cita.setIdPersona(rs.getInt(5));
            cita.setNombre(rs.getString(6));
            cita.setIdServicio(rs.getInt(7));
            cita.setDescripcionServ(rs.getString(8));
            cita.setHoraDuracion(rs.getInt(9));
            cita.setMinDuracion(rs.getDouble(10));
            cita.setNomCliente(rs.getString(11));
            citas.add(cita);
        }
        
        return citas;
    }
    
    
    public ArrayList<Cita> verCitasPfechaYespecialista(String fecha, String username) throws SQLException {
        ArrayList<Cita> citas = new ArrayList<>();
        Cita cita = null;
        if(cn == null) {
            conectar();
        }
        String sql = "SELECT * FROM vCitaspEspecialista WHERE fecha=? AND usuario=?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, fecha);
        ps.setString(2, username);
        rs = ps.executeQuery(); 
        while(rs.next()){
            cita = new Cita();
            cita.setIdPersona(rs.getInt(2));
            cita.setNombre(rs.getString(3));
            cita.setIdCita(rs.getInt(4));
            cita.setFecha(rs.getString(5));
            cita.setHoraCita(rs.getInt(6));
            cita.setMinCita(rs.getDouble(7));
            cita.setIdServicio(rs.getInt(8));
            cita.setDescripcionServ(rs.getString(9));
            cita.setHoraDuracion(rs.getInt(10));
            cita.setMinDuracion(rs.getDouble(11));
            cita.setNomCliente(rs.getString(12));
            citas.add(cita);
        }
        
        return citas;
    }
    
    
    
    public boolean actualizarCliente(int idCliente, String nomCliente, String apellidoPaterno, String apellidoMaterno,
                                                                    int idContacto, Long telefono, String email, String facebook) {
        if(cn == null) {
            conectar();
        }
        int r1 = 0, r2=0, rST=0, rR, rC;
        String sql1 = null, sql2=null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "UPDATE dContacto SET tel_con=? , mal_con=? , fbk_con=? WHERE idC_con=?";
                ps = cn.prepareStatement(sql1);
                ps.setLong(1, telefono);
                ps.setString(2, email);
                ps.setString(3, facebook);
                ps.setInt(4, idContacto);
            r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0) {
                try{
                sql2 = "UPDATE mCliente SET nom_cli=? , app_cli=? , apm_cli=? WHERE idL_cli=?";
                    ps = cn.prepareStatement(sql2);
                    ps.setString(1, nomCliente);
                    ps.setString(2, apellidoPaterno);
                    ps.setString(3, apellidoMaterno);
                    ps.setInt(4, idCliente);
                r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                if(r2 != 0) {
                    // Si se guardaron correctamente los cambios en la tabla mCliente
                    try{
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = true;
                }
                // Si no guardaron correctamente los cambios en la tabla cliente...
                else {
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
                }
            }
            // Si no guardaron correctamente los cambios en la tabla contacto...
            else {
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean actualizarEspecialista(int idUsuario, String nomUsuario, String password, int idEspecialista,
       String nomEspecialista, String apellidoPaterno, String apellidoMaterno, int idContacto, Long telefono,
       String email, String facebook, String horaComida, int idHorarioTrab, String horaEntrada, String horaSalida, List<Integer>serviciosSeleccionados) {
        if(cn == null) {
            conectar();
        }
        int r1 = 0, r2=0, r3=0, r4=0, r5=0, r6=0, r7=0, rST=0, rR, rC;
        String sql1 = null, sql2=null, sql3=null, sql4=null, sql5=null, sql6=null, sql7=null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "UPDATE dContacto SET tel_con=? , mal_con=? , fbk_con=? WHERE idC_con=?";
                ps = cn.prepareStatement(sql1);
                ps.setLong(1, telefono);
                ps.setString(2, email);
                ps.setString(3, facebook);
                ps.setInt(4, idContacto);
            r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0) {
               try{
                sql2 = "UPDATE mEspecialista SET nom_esp=? , app_esp=? , apm_esp=? WHERE idE_esp=?";
                    ps = cn.prepareStatement(sql2);
                    ps.setString(1, nomEspecialista);
                    ps.setString(2, apellidoPaterno);
                    ps.setString(3, apellidoMaterno);
                    ps.setInt(4, idEspecialista);
                r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                if(r2 != 0) {
                    try{
                    sql3 = "UPDATE mUsuario SET nom_usu=? , psw_usu=password(?) WHERE idU_usu=?";
                        ps = cn.prepareStatement(sql3);
                        ps.setString(1, nomUsuario);
                        ps.setString(2, password);
                        ps.setInt(3, idUsuario);
                    r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}
                    
                    if(r3 != 0) {
                        try{
                        sql4 = "UPDATE dHorarioTrabajo SET hre_hrt=? , hrs_hrt=? WHERE idT_hrt=?";
                            ps = cn.prepareStatement(sql4);
                            ps.setString(1, horaEntrada);
                            ps.setString(2, horaSalida);
                            ps.setInt(3, idHorarioTrab);
                        r4 = ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sql4");ex.printStackTrace();}
                        
                        if(r4 != 0) {
                            try{
                                sql5 = "DELETE FROM cServEspecialista WHERE idE_sve=?";
                                ps = cn.prepareStatement(sql5);
                                ps.setInt(1, idEspecialista);
                                r5 = ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sql5");ex.printStackTrace();}
                            
                            if(r5 != 0) {
                                boolean verifS = false;
                                        List<Integer>servicios = new ArrayList<>();
                                        servicios = serviciosSeleccionados;
                                        int idServicio = 0;
                                        for(int i=0 ; i<servicios.size() ; i++) {
                                                idServicio = servicios.get(i);
                                                try{
                                                    sql6 = "INSERT INTO cServEspecialista (idS_sve, idE_sve) values (?, ?)";
                                                        ps = cn.prepareStatement(sql6);
                                                        ps.setInt(1, idServicio);
                                                        ps.setInt(2, idEspecialista);
                                                    r6 = ps.executeUpdate();
                                                }catch(SQLException ex){System.out.println("sql6");ex.printStackTrace();}
                                                if(r6 == 0) {
                                                    verifS = false;
                                                    break;
                                                } else {
                                                    verifS = true;
                                                }
                                        }
                                        if(verifS != false){
                                                                try{
                                                                    // Finalizar transacción
                                                                    sqlC = "COMMIT";
                                                                    ps = cn.prepareStatement(sqlC);
                                                                    rC= ps.executeUpdate();
                                                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                                                // Asignarle el valor TRUE al verificador para indicar que el registro se llevó acabo satisfactoriamente
                                                                verificador = true;
                                        }
                                        else {
                                            try{
                                                sqlR = "ROLLBACK";
                                                ps = cn.prepareStatement(sqlR);
                                                rR= ps.executeUpdate();
                                            }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                                            try{
                                                    // Finalizar transacción
                                                    sqlC = "COMMIT";
                                                    ps = cn.prepareStatement(sqlC);
                                                    rC= ps.executeUpdate();
                                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                            // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                                            verificador = false;
                                        }
                            }
                            else {
                                try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                                try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                verificador = false;
                            }
                        }
                        else {
                            try{
                                sqlR = "ROLLBACK";
                                ps = cn.prepareStatement(sqlR);
                                rR= ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                            try{
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                            verificador = false;
                        }
                        
                    }
                        // Si no guardaron correctamente los cambios en la tabla mUsuario
                    else {
                        try{
                            sqlR = "ROLLBACK";
                            ps = cn.prepareStatement(sqlR);
                            rR= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                sqlC = "COMMIT";
                                ps = cn.prepareStatement(sqlC);
                                rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
                    }
                }
                // Si no guardaron correctamente los cambios en la tabla cliente...
                else {
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
                }
            }
            // Si no guardaron correctamente los cambios en la tabla contacto...
            else {
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean actualizarEspecialistaA(int idUsuario, String nomUsuario, String password, int idPrivilegio, int idEspecialista,
       String nomEspecialista, String apellidoPaterno, String apellidoMaterno, int idContacto, Long telefono,
       String email, String facebook, String horaComida, int idHorarioTrab, String horaEntrada, String horaSalida, List<Integer>serviciosSeleccionados) {
        if(cn == null) {
            conectar();
        }
        int r1 = 0, r2=0, r3=0, r4=0, r5=0, r6=0, r7=0, rST=0, rR, rC;
        String sql1 = null, sql2=null, sql3=null, sql4=null, sql5=null, sql6=null, sql7=null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "UPDATE dContacto SET tel_con=? , mal_con=? , fbk_con=? WHERE idC_con=?";
                ps = cn.prepareStatement(sql1);
                ps.setLong(1, telefono);
                ps.setString(2, email);
                ps.setString(3, facebook);
                ps.setInt(4, idContacto);
            r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0) {
               try{
                sql2 = "UPDATE mEspecialista SET nom_esp=? , app_esp=? , apm_esp=? WHERE idE_esp=?";
                    ps = cn.prepareStatement(sql2);
                    ps.setString(1, nomEspecialista);
                    ps.setString(2, apellidoPaterno);
                    ps.setString(3, apellidoMaterno);
                    ps.setInt(4, idEspecialista);
                r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                if(r2 != 0) {
                    try{
                    sql3 = "UPDATE mUsuario SET nom_usu=? , psw_usu=password(?) , idP_usu=? WHERE idU_usu=?";
                        ps = cn.prepareStatement(sql3);
                        ps.setString(1, nomUsuario);
                        ps.setString(2, password);
                        ps.setInt(3, idPrivilegio);
                        ps.setInt(4, idUsuario);
                    r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}
                    
                    if(r3 != 0) {
                        try{
                        sql4 = "UPDATE dHorarioTrabajo SET hre_hrt=? , hrs_hrt=? WHERE idT_hrt=?";
                            ps = cn.prepareStatement(sql4);
                            ps.setString(1, horaEntrada);
                            ps.setString(2, horaSalida);
                            ps.setInt(3, idHorarioTrab);
                        r4 = ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sql4");ex.printStackTrace();}
                        
                        if(r4 != 0) {
                            try{
                                sql5 = "DELETE FROM cServEspecialista WHERE idE_sve=?";
                                ps = cn.prepareStatement(sql5);
                                ps.setInt(1, idEspecialista);
                                r5 = ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sql5");ex.printStackTrace();}
                            
                            if(r5 != 0) {
                                boolean verifS = false;
                                        List<Integer>servicios = new ArrayList<>();
                                        servicios = serviciosSeleccionados;
                                        int idServicio = 0;
                                        for(int i=0 ; i<servicios.size() ; i++) {
                                                idServicio = servicios.get(i);
                                                try{
                                                    sql6 = "INSERT INTO cServEspecialista (idS_sve, idE_sve) values (?, ?)";
                                                        ps = cn.prepareStatement(sql6);
                                                        ps.setInt(1, idServicio);
                                                        ps.setInt(2, idEspecialista);
                                                    r6 = ps.executeUpdate();
                                                }catch(SQLException ex){System.out.println("sql6");ex.printStackTrace();}
                                                if(r6 == 0) {
                                                    verifS = false;
                                                    break;
                                                } else {
                                                    verifS = true;
                                                }
                                        }
                                        if(verifS != false){
                                                                try{
                                                                    // Finalizar transacción
                                                                    sqlC = "COMMIT";
                                                                    ps = cn.prepareStatement(sqlC);
                                                                    rC= ps.executeUpdate();
                                                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                                                // Asignarle el valor TRUE al verificador para indicar que el registro se llevó acabo satisfactoriamente
                                                                verificador = true;
                                        }
                                        else {
                                            try{
                                                sqlR = "ROLLBACK";
                                                ps = cn.prepareStatement(sqlR);
                                                rR= ps.executeUpdate();
                                            }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                                            try{
                                                    // Finalizar transacción
                                                    sqlC = "COMMIT";
                                                    ps = cn.prepareStatement(sqlC);
                                                    rC= ps.executeUpdate();
                                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                            // Le asignamos el valor FALSE  al verificador para indicarle que el registro no se pudo llevar a cabo
                                            verificador = false;
                                        }
                            }
                            else {
                                try{
                                    sqlR = "ROLLBACK";
                                    ps = cn.prepareStatement(sqlR);
                                    rR= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                                try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                                verificador = false;
                            }
                        }
                        else {
                            try{
                                sqlR = "ROLLBACK";
                                ps = cn.prepareStatement(sqlR);
                                rR= ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                            try{
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                            verificador = false;
                        }
                        
                    }
                        // Si no guardaron correctamente los cambios en la tabla mUsuario
                    else {
                        try{
                            sqlR = "ROLLBACK";
                            ps = cn.prepareStatement(sqlR);
                            rR= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                sqlC = "COMMIT";
                                ps = cn.prepareStatement(sqlC);
                                rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
                    }
                }
                // Si no guardaron correctamente los cambios en la tabla cliente...
                else {
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
                }
            }
            // Si no guardaron correctamente los cambios en la tabla contacto...
            else {
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean actualizarRecepcionista(int idUsuario, String nomUsuario, String password, int idRecepcionista, String nomRecepcionista, String apellidoPaterno, String apellidoMaterno,
                                                                    int idContacto, Long telefono, String email, String facebook) {
        if(cn == null) {
            conectar();
        }
        this.nomUsuarioOAB = nomUsuario;
        this.passwordOAB = password;
        
        int r1 = 0, r2=0, r3=0, rST=0, rR, rC;
        String sql1 = null, sql2=null, sql3=null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "UPDATE dContacto SET tel_con=? , mal_con=? , fbk_con=? WHERE idC_con=?";
                ps = cn.prepareStatement(sql1);
                ps.setLong(1, telefono);
                ps.setString(2, email);
                ps.setString(3, facebook);
                ps.setInt(4, idContacto);
            r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0) {
                try{
                sql2 = "UPDATE mRecepcionista SET nom_rta=? , app_rta=? , apm_rta=? WHERE idR_rta=?";
                    ps = cn.prepareStatement(sql2);
                    ps.setString(1, nomRecepcionista);
                    ps.setString(2, apellidoPaterno);
                    ps.setString(3, apellidoMaterno);
                    ps.setInt(4, idRecepcionista);
                r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                if(r2 != 0) {
                    try{
                    sql3 = "UPDATE mUsuario SET nom_usu=? , psw_usu=password(?) WHERE idU_usu=?";
                        ps = cn.prepareStatement(sql3);
                        ps.setString(1, nomUsuarioOAB);
                        ps.setString(2, passwordOAB);
                        ps.setInt(3, idUsuario);
                    r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}
                    
                    if(r3 != 0) {
                        // Si se guardaron correctamente los cambios en la tabla mUsuario
                        try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = true;
                    }
                    // Si no guardaron correctamente los cambios en la tabla mUsuario...
                    else {
                        try{
                            sqlR = "ROLLBACK";
                            ps = cn.prepareStatement(sqlR);
                            rR= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                sqlC = "COMMIT";
                                ps = cn.prepareStatement(sqlC);
                                rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
                    }
                }
                // Si no guardaron correctamente los cambios en la tabla mRecepcionista...
                else {
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
                }
            }
            // Si no guardaron correctamente los cambios en la tabla contacto...
            else {
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean actualizarRecepcionistaA(int idUsuario, String nomUsuario, String password, int idPrivilegio, 
            int idRecepcionista, String nomRecepcionista, String apellidoPaterno, String apellidoMaterno,
                                                                    int idContacto, Long telefono, String email, String facebook) {
        if(cn == null) {
            conectar();
        }
        this.nomUsuarioOAB = nomUsuario;
        this.passwordOAB = password;
        
        int r1 = 0, r2=0, r3=0, rST=0, rR, rC;
        String sql1 = null, sql2=null, sql3=null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "UPDATE dContacto SET tel_con=? , mal_con=? , fbk_con=? WHERE idC_con=?";
                ps = cn.prepareStatement(sql1);
                ps.setLong(1, telefono);
                ps.setString(2, email);
                ps.setString(3, facebook);
                ps.setInt(4, idContacto);
            r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0) {
                try{
                sql2 = "UPDATE mRecepcionista SET nom_rta=? , app_rta=? , apm_rta=? WHERE idR_rta=?";
                    ps = cn.prepareStatement(sql2);
                    ps.setString(1, nomRecepcionista);
                    ps.setString(2, apellidoPaterno);
                    ps.setString(3, apellidoMaterno);
                    ps.setInt(4, idRecepcionista);
                r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                if(r2 != 0) {
                    try{
                    sql3 = "UPDATE mUsuario SET nom_usu=? , psw_usu=password(?) , idP_usu=? WHERE idU_usu=?";
                        ps = cn.prepareStatement(sql3);
                        ps.setString(1, nomUsuarioOAB);
                        ps.setString(2, passwordOAB);
                        ps.setInt(3, idPrivilegio);
                        ps.setInt(4, idUsuario);
                    r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}
                    
                    if(r3 != 0) {
                        // Si se guardaron correctamente los cambios en la tabla mUsuario
                        try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = true;
                    }
                    // Si no guardaron correctamente los cambios en la tabla mUsuario...
                    else {
                        try{
                            sqlR = "ROLLBACK";
                            ps = cn.prepareStatement(sqlR);
                            rR= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                sqlC = "COMMIT";
                                ps = cn.prepareStatement(sqlC);
                                rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
                    }
                }
                // Si no guardaron correctamente los cambios en la tabla mRecepcionista...
                else {
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
                }
            }
            // Si no guardaron correctamente los cambios en la tabla contacto...
            else {
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean actualizarGerente(int idUsuario, String nomUsuario, String password, int idGerente, String nomGerente, String apellidoPaterno, String apellidoMaterno,
                                                                    int idContacto, Long telefono, String email, String facebook) {
        if(cn == null) {
            conectar();
        }
        this.nomUsuarioOAB = nomUsuario;
        this.passwordOAB = password;
        
        int r1 = 0, r2=0, r3=0, rST=0, rR, rC;
        String sql1 = null, sql2=null, sql3=null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "UPDATE dContacto SET tel_con=? , mal_con=? , fbk_con=? WHERE idC_con=?";
                ps = cn.prepareStatement(sql1);
                ps.setLong(1, telefono);
                ps.setString(2, email);
                ps.setString(3, facebook);
                ps.setInt(4, idContacto);
            r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0) {
                try{
                sql2 = "UPDATE mGerente SET nom_gte=? , app_gte=? , apm_gte=? WHERE idG_gte=?";
                    ps = cn.prepareStatement(sql2);
                    ps.setString(1, nomGerente);
                    ps.setString(2, apellidoPaterno);
                    ps.setString(3, apellidoMaterno);
                    ps.setInt(4, idGerente);
                r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                if(r2 != 0) {
                    try{
                    sql3 = "UPDATE mUsuario SET nom_usu=? , psw_usu=password(?) WHERE idU_usu=?";
                        ps = cn.prepareStatement(sql3);
                        ps.setString(1, nomUsuarioOAB);
                        ps.setString(2, passwordOAB);
                        ps.setInt(3, idUsuario);
                    r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}
                    
                    if(r3 != 0) {
                        // Si se guardaron correctamente los cambios en la tabla mUsuario
                        try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = true;
                    }
                    // Si no guardaron correctamente los cambios en la tabla mUsuario...
                    else {
                        try{
                            sqlR = "ROLLBACK";
                            ps = cn.prepareStatement(sqlR);
                            rR= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                sqlC = "COMMIT";
                                ps = cn.prepareStatement(sqlC);
                                rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
                    }
                }
                // Si no guardaron correctamente los cambios en la tabla mRecepcionista...
                else {
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
                }
            }
            // Si no guardaron correctamente los cambios en la tabla contacto...
            else {
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean actualizarGerenteA(int idUsuario, String nomUsuario, String password, int idPrivilegio, 
            int idGerente, String nomGerente, String apellidoPaterno, String apellidoMaterno,
                                                                    int idContacto, Long telefono, String email, String facebook) {
        if(cn == null) {
            conectar();
        }
        this.nomUsuarioOAB = nomUsuario;
        this.passwordOAB = password;
        
        int r1 = 0, r2=0, r3=0, rST=0, rR, rC;
        String sql1 = null, sql2=null, sql3=null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "UPDATE dContacto SET tel_con=? , mal_con=? , fbk_con=? WHERE idC_con=?";
                ps = cn.prepareStatement(sql1);
                ps.setLong(1, telefono);
                ps.setString(2, email);
                ps.setString(3, facebook);
                ps.setInt(4, idContacto);
            r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0) {
                try{
                sql2 = "UPDATE mGerente SET nom_gte=? , app_gte=? , apm_gte=? WHERE idG_gte=?";
                    ps = cn.prepareStatement(sql2);
                    ps.setString(1, nomGerente);
                    ps.setString(2, apellidoPaterno);
                    ps.setString(3, apellidoMaterno);
                    ps.setInt(4, idGerente);
                r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                if(r2 != 0) {
                    try{
                    sql3 = "UPDATE mUsuario SET nom_usu=? , psw_usu=password(?) , idP_usu=? WHERE idU_usu=?";
                        ps = cn.prepareStatement(sql3);
                        ps.setString(1, nomUsuarioOAB);
                        ps.setString(2, passwordOAB);
                        ps.setInt(3, idPrivilegio);
                        ps.setInt(4, idUsuario);
                    r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}
                    
                    if(r3 != 0) {
                        // Si se guardaron correctamente los cambios en la tabla mUsuario
                        try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = true;
                    }
                    // Si no guardaron correctamente los cambios en la tabla mUsuario...
                    else {
                        try{
                            sqlR = "ROLLBACK";
                            ps = cn.prepareStatement(sqlR);
                            rR= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                sqlC = "COMMIT";
                                ps = cn.prepareStatement(sqlC);
                                rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
                    }
                }
                // Si no guardaron correctamente los cambios en la tabla mRecepcionista...
                else {
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
                }
            }
            // Si no guardaron correctamente los cambios en la tabla contacto...
            else {
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                verificador = false;
            }
        
        return verificador;
    }
    
    
    public boolean actualizarAdmin(int idUsuario, String nomUsuario, String password, int idAdministrador, String nomAdministrador, String apellidoPaterno, String apellidoMaterno,
                                                                    int idContacto, Long telefono, String email, String facebook) {
        if(cn == null) {
            conectar();
        }
        this.nomUsuarioOAB = nomUsuario;
        this.passwordOAB = password;
        
        int r1 = 0, r2=0, r3=0, rST=0, rR, rC;
        String sql1 = null, sql2=null, sql3=null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql1 = "UPDATE dContacto SET tel_con=? , mal_con=? , fbk_con=? WHERE idC_con=?";
                ps = cn.prepareStatement(sql1);
                ps.setLong(1, telefono);
                ps.setString(2, email);
                ps.setString(3, facebook);
                ps.setInt(4, idContacto);
            r1 = ps.executeUpdate();
            }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
            if(r1 != 0) {
                try{
                sql2 = "UPDATE mAdministrador SET nom_adm=? , app_adm=? , apm_adm=? WHERE idA_adm=?";
                    ps = cn.prepareStatement(sql2);
                    ps.setString(1, nomAdministrador);
                    ps.setString(2, apellidoPaterno);
                    ps.setString(3, apellidoMaterno);
                    ps.setInt(4, idAdministrador);
                r2 = ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
                
                if(r2 != 0) {
                    try{
                    sql3 = "UPDATE mUsuario SET nom_usu=? , psw_usu=password(?) WHERE idU_usu=?";
                        ps = cn.prepareStatement(sql3);
                        ps.setString(1, nomUsuarioOAB);
                        ps.setString(2, passwordOAB);
                        ps.setInt(3, idUsuario);
                    r3 = ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sql3");ex.printStackTrace();}
                    
                    if(r3 != 0) {
                        // Si se guardaron correctamente los cambios en la tabla mUsuario
                        try{
                                        sqlC = "COMMIT";
                                        ps = cn.prepareStatement(sqlC);
                                        rC= ps.executeUpdate();
                                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = true;
                    }
                    // Si no guardaron correctamente los cambios en la tabla mUsuario...
                    else {
                        try{
                            sqlR = "ROLLBACK";
                            ps = cn.prepareStatement(sqlR);
                            rR= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                sqlC = "COMMIT";
                                ps = cn.prepareStatement(sqlC);
                                rC= ps.executeUpdate();
                        }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                        verificador = false;
                    }
                }
                // Si no guardaron correctamente los cambios en la tabla mRecepcionista...
                else {
                    try{
                        sqlR = "ROLLBACK";
                        ps = cn.prepareStatement(sqlR);
                        rR= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                    try{
                            sqlC = "COMMIT";
                            ps = cn.prepareStatement(sqlC);
                            rC= ps.executeUpdate();
                    }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
                }
            }
            // Si no guardaron correctamente los cambios en la tabla contacto...
            else {
                try{
                    sqlR = "ROLLBACK";
                    ps = cn.prepareStatement(sqlR);
                    rR= ps.executeUpdate();
                }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                verificador = false;
            }
        
        return verificador;
    }
    
    
    
    public boolean NvoUsuarioDen(String username, String passw){
        if(cn == null) {
            conectar();
        }
        int r = 0, rST=0, rR, rC;
        String sql = null, sqlST, sqlR, sqlC=null;
        
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        try{
            sql = "insert into MUsuario (nom_usu, psw_usu, id_prv) values (?,password(?),3)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, passw);
            r = ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
        if(r != 0){
    // Si todo se guardó correctamente..., los cambios se hacen permanentes
                            try{
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = true;
        }
        // De lo contrario...
        else{
                        try{
                                sqlR = "ROLLBACK";
                                ps = cn.prepareStatement(sqlR);
                                rR= ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sqlR");ex.printStackTrace();}
                        try{
                                    sqlC = "COMMIT";
                                    ps = cn.prepareStatement(sqlC);
                                    rC= ps.executeUpdate();
                                }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                    verificador = false;
        }     
        
        return verificador;
    }
    
    
    public int obtenerUltimoIDregistrado() throws SQLException{
        int id=0;
         // Si no se ha realizado la conexión con la base de datos la iniciamos
        if(cn == null) {
            conectar();
        }
     String csp = "";
     String sql = "SELECT @last := LAST_INSERT_ID() AS ultimoID";
         st = cn.createStatement();
         // Mandamos el comando al gestor
         rs = st.executeQuery(sql); 
     while(rs.next()){
             // ..., especificamente en el campo "id_prv" para asegurarnos si el usuario tiene algún privilegio
             // y "guardamos" lo que encontremos en la variable "csp"
             csp=rs.getString("ultimoID");
         }
         // Verificamos que el usuario introducido sea el administrador, ya que por ahora sólo él puede acceder
            // si es así...
         if(!csp.equals("0")){
                // El verificador tomará el valor de "true" para indicar que todos los datos son correctos y el usuario puede entrar
             id = Integer.parseInt(csp);
         }
         else{
             id = 0;
         }
     
        
        
        return id;
    }
    
    
}
