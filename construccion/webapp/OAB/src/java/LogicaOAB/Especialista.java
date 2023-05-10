
package LogicaOAB;

import java.io.Serializable;



/**
 * 
 */
public class Especialista extends Usuario implements Serializable {

    /**
     * Default constructor
     */
    public Especialista() {
    }

    
    
    
    /**
     * 
     */
    private int horaComida;

    /**
     * 
     */
    private double minComida;

    /**
     * 
     */
    private int horaEntrada;

    /**
     * 
     */
    private double minEntrada;

    /**
     * 
     */
    private int horaSalida;

    /**
     * 
     */
    private double minSalida;
    
    
    private int idHorarioTrabajo;
    

    public Especialista(int horaComida, double minComida, int horaEntrada, double minEntrada, int horaSalida, double minSalida) {
        this.horaComida = horaComida;
        this.minComida = minComida;
        this.horaEntrada = horaEntrada;
        this.minEntrada = minEntrada;
        this.horaSalida = horaSalida;
        this.minSalida = minSalida;
    }

    public Especialista(int horaComida, double minComida, int horaEntrada, double minEntrada, int horaSalida, double minSalida, int idUsuario, String nomUsuario, String password, int idPrivilegio, int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, long telefono, String emal, String facebook) {
        super(idUsuario, nomUsuario, password, idPrivilegio, idPersona, nombre, apellidoPaterno, apellidoMaterno, telefono, emal, facebook);
        this.horaComida = horaComida;
        this.minComida = minComida;
        this.horaEntrada = horaEntrada;
        this.minEntrada = minEntrada;
        this.horaSalida = horaSalida;
        this.minSalida = minSalida;
    }

    public Especialista(int horaComida, double minComida, int horaEntrada, double minEntrada, int horaSalida, double minSalida, int idUsuario, String nomUsuario, String password, int idPrivilegio, String nombre, String apellidoPaterno, String apellidoMaterno, long telefono, String emal, String facebook) {
        super(idUsuario, nomUsuario, password, idPrivilegio, nombre, apellidoPaterno, apellidoMaterno, telefono, emal, facebook);
        this.horaComida = horaComida;
        this.minComida = minComida;
        this.horaEntrada = horaEntrada;
        this.minEntrada = minEntrada;
        this.horaSalida = horaSalida;
        this.minSalida = minSalida;
    }

    public Especialista(int horaComida, double minComida, int horaEntrada, double minEntrada, int horaSalida, double minSalida, int idUsuario, String nomUsuario, String password, int idPrivilegio) {
        super(idUsuario, nomUsuario, password, idPrivilegio);
        this.horaComida = horaComida;
        this.minComida = minComida;
        this.horaEntrada = horaEntrada;
        this.minEntrada = minEntrada;
        this.horaSalida = horaSalida;
        this.minSalida = minSalida;
    }

    public Especialista(int horaComida, double minComida, int horaEntrada, double minEntrada, int horaSalida, double minSalida, String nomUsuario, String password, int idPrivilegio, int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, long telefono, String emal, String facebook) {
        super(nomUsuario, password, idPrivilegio, idPersona, nombre, apellidoPaterno, apellidoMaterno, telefono, emal, facebook);
        this.horaComida = horaComida;
        this.minComida = minComida;
        this.horaEntrada = horaEntrada;
        this.minEntrada = minEntrada;
        this.horaSalida = horaSalida;
        this.minSalida = minSalida;
    }

    public Especialista(int horaComida, double minComida, int horaEntrada, double minEntrada, int horaSalida, double minSalida, String nomUsuario, String password, int idPrivilegio, String nombre, String apellidoPaterno, String apellidoMaterno, long telefono, String emal, String facebook) {
        super(nomUsuario, password, idPrivilegio, nombre, apellidoPaterno, apellidoMaterno, telefono, emal, facebook);
        this.horaComida = horaComida;
        this.minComida = minComida;
        this.horaEntrada = horaEntrada;
        this.minEntrada = minEntrada;
        this.horaSalida = horaSalida;
        this.minSalida = minSalida;
    }

    
    public Especialista(int idPersona, String nombre) {
        super(idPersona, nombre);
    }

    

    /**
     * @return
     */
    public boolean editarHoraComida() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean editarHorarioTrabajo() {
        // TODO implement here
        return false;
    }

    /**
     * @param horaComida
     * @param minComida
     * @return
     */
    public String obtenerHoraComida(int horaComida, double minComida) {
        // TODO implement here
        return "";
    }

    /**
     * @param  horaComida 
     * @param  minComida 
     * @return
     */
    public double obtenerHoraComidaD(int horaComida, double minComida) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public String obtenerHoraEntrada() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public double obtenerHoraEntradaD() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public String obtenerHoraSalida() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public double obtenerHoraSalidaD() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public String obtenerHorarioTrabajo() {
        // TODO implement here
        return "";
    }

    /**
     * @return the horaComida
     */
    public int getHoraComida() {
        return horaComida;
    }

    /**
     * @param horaComida the horaComida to set
     */
    public void setHoraComida(int horaComida) {
        this.horaComida = horaComida;
    }

    /**
     * @return the minComida
     */
    public double getMinComida() {
        return minComida;
    }

    /**
     * @param minComida the minComida to set
     */
    public void setMinComida(double minComida) {
        this.minComida = minComida;
    }

    /**
     * @return the horaEntrada
     */
    public int getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * @param horaEntrada the horaEntrada to set
     */
    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    /**
     * @return the minEntrada
     */
    public double getMinEntrada() {
        return minEntrada;
    }

    /**
     * @param minEntrada the minEntrada to set
     */
    public void setMinEntrada(double minEntrada) {
        this.minEntrada = minEntrada;
    }

    /**
     * @return the horaSalida
     */
    public int getHoraSalida() {
        return horaSalida;
    }

    /**
     * @param horaSalida the horaSalida to set
     */
    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * @return the minSalida
     */
    public double getMinSalida() {
        return minSalida;
    }

    /**
     * @param minSalida the minSalida to set
     */
    public void setMinSalida(double minSalida) {
        this.minSalida = minSalida;
    }

    /**
     * @return the idHorarioTrabajo
     */
    public int getIdHorarioTrabajo() {
        return idHorarioTrabajo;
    }

    /**
     * @param idHorarioTrabajo the idHorarioTrabajo to set
     */
    public void setIdHorarioTrabajo(int idHorarioTrabajo) {
        this.idHorarioTrabajo = idHorarioTrabajo;
    }

}