
package LogicaOAB;

import java.io.Serializable;

/**
 * 
 */
public class Persona implements Serializable {


    /**
     * 
     */
    private int idPersona;

    /**
     * 
     */
    private String nombre;

    /**
     * 
     */
    private String apellidoPaterno;

    /**
     * 
     */
    private String apellidoMaterno;

    /**
     * 
     */
    private long telefono;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String facebook;
    
    
    private int idContacto;
    

    
    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, long telefono, String email, String facebook) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.email = email;
        this.facebook = facebook;
    }

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, long telefono, String email, String facebook) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.email = email;
        this.facebook = facebook;
    }
    
    /**
     * Default constructor
     */
    public Persona() {
    }

    public Persona(int idPersona, String nombre) {
        this.idPersona = idPersona;
        this.nombre = nombre;
    }

    
    
    
    
    /**
     * @return
     */
    public String obtenerNomCompleto(String nombre, String apellidoPaterno, String apellidoMaterno) {
        // TODO implement here
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

    /**
     * @return
     */
    public String obtenerApellidos(String apellidoPaterno, String apellidoMaterno) {
        // TODO implement here
        return apellidoPaterno + " " + apellidoMaterno;
    }

    /**
     * @return the idPersona
     */
    public int getIdPersona() {
        return idPersona;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return the telefono
     */
    public long getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    

    /**
     * @return the facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * @param facebook the facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * @return the idContacto
     */
    public int getIdContacto() {
        return idContacto;
    }

    /**
     * @param idContacto the idContacto to set
     */
    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


}