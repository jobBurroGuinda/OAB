
package LogicaOAB;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 */
public class Servicio extends Especialista implements Serializable {

    // Se inicializan las variables para conexión
    static Connection cn = null;
    static Statement st = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    /**
     * Default constructor
     */
    public Servicio() {
    }

    
    private int identificador;
    
    
    /**
     * 
     */
    private int idServicio;
    
    /**
     * 
     */
    private String descripcionServ;

    /**
     * 
     */
    private int horaDuracion;

    /**
     * 
     */
    private double minDuracion;
    
    
    private String duracionString;
    
    

    public Servicio(String descripcionServ, int horaDuracion, double minDuracion) {
        this.descripcionServ = descripcionServ;
        this.horaDuracion = horaDuracion;
        this.minDuracion = minDuracion;
    }

    public Servicio(int idServicio, String descripcionServ) {
        this.idServicio = idServicio;
        this.descripcionServ = descripcionServ;
    }

    
    
    
    
    /**
     * @param hora
     * @param minutos
     * @return
     */
    public String obtenerDuracion(int hora, double minutos) {
        String duracion = "";
        if(hora > 0) {
            String min = "";
            switch ((int)minutos) {
                case 0:
                    if(hora == 1){
                        min = "00 hora";
                    } else{
                        min = "00 horas";
                    }
                    break;
                case 5:
                    min = "05 minutos";
                    break;
                default:
                    min = (int)minutos + " minutos";
                    break;
            }
            duracion = hora + ":" + min;
        } else {
            duracion = (int)minutos + " minutos";
        }
        // TODO implement here
        return duracion;
    }

    /**
     * @param hora
     * @param minutos
     * @return
     */
    public String obtenerDuracionFormatoHoras(int hora, int minutos) {
        String duracion = hora + ":" + minutos + ":00";
        // TODO implement here
        return duracion;
    }

    /**
     * @return
     */
    public double agregarServicio() {
        // TODO implement here
        return 0.0d;
    }

    

    /**
     * @return
     */
    public boolean editarServicio() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean bajaServicio() {
        // TODO implement here
        return false;
    }

    /**
     * @return the idServicio
     */
    public int getIdServicio() {
        return idServicio;
    }

    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * @return the descripcionServ
     */
    public String getDescripcionServ() {
        return descripcionServ;
    }

    /**
     * @param descripcionServ the descripcionServ to set
     */
    public void setDescripcionServ(String descripcionServ) {
        this.descripcionServ = descripcionServ;
    }

    /**
     * @return the horaDuracion
     */
    public int getHoraDuracion() {
        return horaDuracion;
    }

    /**
     * @param horaDuracion the horaDuracion to set
     */
    public void setHoraDuracion(int horaDuracion) {
        this.horaDuracion = horaDuracion;
    }

    /**
     * @return the minDuracion
     */
    public double getMinDuracion() {
        return minDuracion;
    }

    /**
     * @param minDuracion the minDuracion to set
     */
    public void setMinDuracion(double minDuracion) {
        this.minDuracion = minDuracion;
    }

    /**
     * @return the identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the duracionString
     */
    public String getDuracionString() {
        return duracionString;
    }

    /**
     * @param duracionString the duracionString to set
     */
    public void setDuracionString(String duracionString) {
        this.duracionString = duracionString;
    }

}