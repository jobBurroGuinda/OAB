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

import java.sql.SQLException;

/**
 *
 * @author Salinas_Hernandez_Job_Adolfo
 * @version 1.0.0.0_3
 */
// Subclase de la clase Aconexion
public class Inserciones extends Aconexion {
    /*
    * Esta clase servirá para almacenar los datos de los bienes
    */
    
    
    // Iniciamos el método que servirá para almacenar los datos
    public boolean RegistrarBien(String descripcion, String marca, String modelo,
            String claveCABM, String nSerie, double valor, String personaA, String edificio,
            String departamento, int nivel, int local, String fechaA, String detalles){
        // No no se ha realizado la conexión con la base de datos la hacemos
        if(cn == null) {
            conectar();
        }
        // Inicializamos las variables que nos a yudará a verificar si toda la operación se llevó
        // a cabo exitosamente
            // El verificador es para "verificar" toda la operación (valga la redundancia
        boolean verificador = false;
            // Las variables "r" son para verificar el resultado de cada una de las inserciones u operacines
        int r1=0, r2=0, r3=0, r4=0, r5=0, rST, rR, rC;
            // las "sql" servirán para cada uno de los comandos SQL que se enviarán al gestor
        String sql1=null, sql2=null, sql3=null, sql4=null, sql5=null, sqlST=null, sqlR=null, sqlC=null;
        
        
        // Iniciamos la transacción
        try{
            sqlST = "START TRANSACTION";
            ps = cn.prepareStatement(sqlST);
            rST= ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sqlST");ex.printStackTrace();}
        
        // Primeramente almacenamos los datos de la tabla "CDetalles"
        try{
            sql1 = "insert into CDetalles (val_det, dls_det) values (?,?)";
            ps = cn.prepareStatement(sql1);
            ps.setDouble(1, valor);
            ps.setString(2, detalles);
            r1 = ps.executeUpdate();
        }
        // Como podemos ver, si ocurriera algún error, se nos desplegará un mensaje en los comandos
        // indicandonos que el problema ocurrió aquí, es decir, en el sql1
        catch(SQLException ex){System.out.println("sql1");ex.printStackTrace();}
        
        //  La señal de que los datos de la tabla Detalles se almacenaron correctamente es que el valor de r1 sea diferente de cero
        // Por lo cual no  debe continuar almacenando lo demás si antes no guardó eso
        if(r1 != 0){
            // Si todo saló bien almacenamos los datos de la tabla CMarca
            try{
            sql2 = "insert into CMarca (nom_mrc, mlo_mrc) values (?,?)";
            ps = cn.prepareStatement(sql2);
            ps.setString(1, marca);
            ps.setString(2, modelo);
            r2 = ps.executeUpdate();
        }catch(SQLException ex){System.out.println("sql2");ex.printStackTrace();}
             
              // La señal es la misma
              if(r2 != 0){
                        try{
                  // Si todo salió bien, entonces ahora pasamos a almacenar datos en una tabla
                  // con claves foráneas, para lo cual es necesario que el ordenador recuerde cuál fue el último ID que se almacenó
                  // que vendría siendo el de la tabla CMarca (la anterior inserción)
        //Por lo cual, el siguiente comando SQL nos servirá para que el ordenador lo recuerde y pueda usarse 
                      // con ayuda de la variable @last
        String sql = "SELECT @last := LAST_INSERT_ID()";
            st = cn.createStatement();
            st.executeQuery(sql);
        }catch(SQLException ex){ex.printStackTrace();}
        // Insertamos los datos de claves foráneas en la tabla MBien con la variable @last
    // Almacenamos los datos de la tabla "MBien"
    try{
        sql3 = "INSERT INTO MBien (des_ben, nse_ben, cbm_ben, id_est, id_det, id_mrc) VALUES (?,?,?, 1, @last, @last)";
            ps = cn.prepareStatement(sql3);
            ps.setString(1, descripcion);
            ps.setString(2, nSerie);
            ps.setString(3, claveCABM);
            r3 = ps.executeUpdate();
        }catch(SQLException ex){System.out.println("Error de sintaxis SQL3");ex.printStackTrace();}
                    
                    // La misma validación de las variables "r"
                    if(r3 != 0){
                        // Introducimos los datos de la tabla "CUbicación"
                        try{
        sql4 = "insert into CUbicacion (edi_ubi, dep_ubi, nvl_ubi, lcl_ubi) values (?,?,?,?)";
            ps = cn.prepareStatement(sql4);
            ps.setString(1, edificio);
            ps.setString(2, departamento);
            ps.setInt(3, nivel);
            ps.setInt(4, local);
            r4 = ps.executeUpdate();
        }catch(SQLException ex){System.out.println("Error de sintaxis SQL4");ex.printStackTrace();}
                    }
                    // Si no se registraron los datos de la tabla MBien se debe borrar lo que se haya almacenado en
                    // la tabla detalles_extrasb en la tabla Detalles y en la DextrasBien
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
                        }
                    // La misma validación
                    if(r4 != 0){
                        try{
        String sql = "SELECT @last := LAST_INSERT_ID()";
            st = cn.createStatement();
            st.executeQuery(sql);
        }catch(SQLException ex){ex.printStackTrace();}
        // Incertamos los datos en la tabla CAsignacion
        try{
        sql5 = "INSERT INTO CAsignacion (prs_asg, fas_arg, id_ben, id_ubi) VALUES (?,?,@last,@last)";
            ps = cn.prepareStatement(sql5);
            ps.setString(1, personaA);
            ps.setString(2, fechaA);
            r5 = ps.executeUpdate();
        }catch(SQLException ex){ex.printStackTrace();}
                    }
                    // Si no se registraron los datos de la tabla CUbicacion se debe borrar
                    /// lo que se haya almacenado en la tabla bien, en la detalles_extrasb en la Detalles y en la DextrasBien
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
                        }
              }
              // Si no se registraron los datos de la tabla CMarca se debe borrar lo que se haya almacenado en la tabla CDetalles
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
              }
        }
        
        // Si se almacenaron correctamente los datos de la tabla "Asignacion"...
        if(r5 != 0){
            // Proseguimos a verificar que definitivamente todos y cada una de las operaciones
            // se hayan realizado exitosamente
            if (r1 != 0   &   r2 != 0   &   r3 != 0   &   r4 != 0   &   r5 != 0){
                // Si todo se guardó correctamente..., guardamos permanentemente los cambios con el comando SQL "COMMIT"
                        try{
                                sqlC = "COMMIT";
                                ps = cn.prepareStatement(sqlC);
                                rC= ps.executeUpdate();
                            }catch(SQLException ex){System.out.println("sqlC");ex.printStackTrace();}
                // Y le pasamos el valor "true" al verificador para indicar que el registro se llevó a cabo exitosamente
                verificador = true;
            }
            // Es muy poco probable que la anterior validación resulte no exitosa, 
            // ya que antes de llegar a ella se comprobó que sí lo era, pero si llegará a suceder...
            else {
                // Borramos todo lo que se haya almacenado
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
                // Y le damos el valor false al verificador para indicar que el bien no se pudo registrar
                verificador = false;
            }
        }
                    // Si no se registraron los datos de la tabla CAsignacion borramos todo lo que se haya almacenado en
                    // todas las demás tablas anteriores
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
                        }
                    
        // Le enviamos el valor del verificador para indicar si los datos se almacenaron o no
        return verificador;
    }
    
}
