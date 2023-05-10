
package LogicaOAB;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Cita extends Servicio implements Serializable{

    /**
     * Default constructor
     */
    public Cita() {
    }

    /**
     * 
     */
    private int idCita;

    /**
     * 
     */
    private int horaCita;

    /**
     * 
     */
    private double minCita;

    /**
     * 
     */
    private int diaCita;

    /**
     * 
     */
    private int mesCita;

    /**
     * 
     */
    private int añoCita;
    
    
    private String fecha;
    
    
    private int idCliente;
    
    
    private String nomCliente;




    public double min5(){
        return 25/3;
    }
    
    public double min10(){
        return (25/3)*2;
    }
    
    public double min15(){
        return (25/3)*3;
    }
    
    public double min20(){
        return (25/3)*4;
    }
    
    public double min25(){
        return (25/3)*5;
    }
    
    public double min30(){
        return (25/3)*6;
    }
    
    public double min35(){
        return (25/3)*7;
    }
    
    public double min40(){
        return (25/3)*8;
    }
    
    public double min45(){
        return (25/3)*9;
    }
    
    public double min50(){
        return (25/3)*10;
    }
    
    public double min55(){
        return (25/3)*11;
    }
    
    
    public double obtenerFormatHoraCalculable(int hora, double min){
        double minutos = min/100; 
        double horaCalculable = hora + minutos;
        return horaCalculable;
    }
    
    
    public double convertirHoraADouble(int hora, double min){
        double minSexa = convertirMinAformatMinDouble(min);
        double horaDouble = obtenerFormatHoraCalculable(hora, minSexa);
        return horaDouble;
    }
    
    public double convertirMinAformatMinDouble(double min){
        double minutos = min * 30;
        double minExtras = 0.0;
        minutos /= 50;
        if(minutos == 4.8){
            minExtras = 5;
        } else if(minutos == 9.6){
            minExtras = 10;
        } else if(minutos == 14.4){
            minExtras = 15;
        } else if(minutos == 19.2){
            minExtras = 20;
        } else if(minutos == 24.0){
            minExtras = 25;
        } else if(minutos == 28.8){
            minExtras = 30;
        } else if(minutos == 33.6){
            minExtras = 35;
        } else if(minutos == 38.4){
            minExtras = 40;
        } else if(minutos == 43.2){
            minExtras = 45;
        } else if(minutos == 48.0){
            minExtras = 50;
        } else if(minutos == 52.8){
            minExtras = 55;
        } else {
            minExtras = minutos;
        }
        return minExtras;
    }

    
    public String convertirAformatoHora(int hora, double min){
        int minutos = (int)convertirMinAformatMinDouble(min);
        String minExtos = null;
        switch (minutos) {
            case 0:
                minExtos = "00";
                break;
            case 5:
                minExtos = "05";
                break;
            default:
                minExtos = minutos + "";
                break;
        }
        String hourCita = hora + ":" + minExtos + ":00";
        return hourCita;
    }
    
    
    public String convertAformatHoraSinSegundos(int hora, double min){
        int minutos = (int)min;
        String minExtos = null;
        int horaM = 0;
        String meririano = null;
        switch (minutos) {
            case 0:
                minExtos = "00";
                break;
            case 5:
                minExtos = "05";
                break;
            default:
                minExtos = minutos + "";
                break;
        }
        
        switch (hora) {
            case 13:
                horaM = 1;
                break;
            case 14:
                horaM = 2;
                break;
            case 15:
                horaM = 3;
                break;
            case 16:
                horaM = 4;
                break;
            case 17:
                horaM = 5;
                break;
            case 18:
                horaM = 6;
                break;
            case 19:
                horaM = 7;
                break;
            case 20:
                horaM = 8;
                break;
            case 21:
                horaM = 9;
                break;
            default:
                horaM = hora;
                break;
        }
        if(hora == 11){
            meririano = " am";
        } else if(hora == 12){
            meririano = " pm";
        } else if(hora >= 1  ||  hora <= 9){
            meririano = " pm";
        }
        String hourCita = horaM + ":" + minExtos + meririano;
        return hourCita;
    }
    
    
    public double convertMinFortmatAdecimal(double minutos) {
        // Los minutos se multiplican por 5
        minutos *= 5;
        // Y se dividen entre 3
        minutos /= 3;
        // Después se divide entre 100 para devolver un valor equivalente decimalmente a los minutos
        minutos /= 100;
        
        return minutos;
    }
    
    
    public double convertHoraAdecimalCalculable(int hora, double minutos){
        // Convertimos los minutos sexagecimales a su equivalencia decimal
        // y le asignamos el valor a la variable hora que se va a devolver
        double horaD = convertMinFortmatAdecimal(minutos);
        // Le sumamos el valor de la hora INT a la variable double que vamos a devolver
        horaD += hora;
        
        return horaD;
    }
    
    
    public String sumaHoraCitaYduracion(int horaCita, double minCita, int horaDuracion, double minDuracion){
        minCita = convertMinFortmatAdecimal(minCita);
        minDuracion = convertMinFortmatAdecimal(minDuracion);
        double minsSumados = minCita + minDuracion;
        double horasSumadas = horaCita + horaDuracion;
        double horaComSumada = horasSumadas + minsSumados;
        horaCita = 0;
        horaCita = (int)horaComSumada;
        minCita = 0.0;
        minCita = horaComSumada - horaCita;
        minCita *= 100;
        String fHoraCita = convertAformatHoraSinSegundos(horaCita, convertirMinAformatMinDouble(minCita));
        
        return fHoraCita;
    }
    
    
    public boolean vHoraTrabajo(double horaCita, double duracion, double horaEntrada, double horaSalida){
        boolean veriTrab = false;
        // Verificar hora de trabajo
            if(horaCita>=horaEntrada && (horaCita+duracion)<=horaSalida) {
                veriTrab = true;
            }
        // Fin de verificaciòn de hora de trabajo
            
        return veriTrab; 
    }
    
    
    public boolean vHoraComida(double horaComida, double horaCita, double duracion){
        boolean veriCom = false;
        // Inicio verificación de hora de comida
            if(horaCita < horaComida) {
                if((horaCita+duracion) < horaComida){
                    veriCom = true;
                } else{
                    veriCom = false;
                }
            } else if(horaCita > horaComida) {
                veriCom = horaCita > (horaComida+1);
            } else if(horaCita == horaComida) {
                veriCom = false;
            }
        // Fin de ferificación de hora de comida
        
        return veriCom; 
    }
    
    
    
    public boolean vDisponidilidad(double horaCita, double duracion, double inicioCitaProgramada, double duracionCprogramada){
        boolean verif = false;
        double finalC = horaCita + duracion;
        double finalA = inicioCitaProgramada + duracionCprogramada;
            
                    if       (horaCita < inicioCitaProgramada  &&  finalC > inicioCitaProgramada){ // segundo caso
                        verif = false;
                    } else if(horaCita >= inicioCitaProgramada  &&  finalC <= finalA){ // Tercer caso
                        verif = false;
                    } else if(horaCita < finalA  &&  finalC > finalA){ // Cuarto caso
                        verif = false;
                    } else if(finalC <= inicioCitaProgramada){ // primer caso
                        verif = true;
                    } else if(horaCita >= finalA){ // quinto caso
                        verif = true;
                    }
        
        return verif;
    }


    /**
     * @return
     */
    public boolean agendarCita() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public ArrayList verCita() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean posponerCita() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean cancelarCita() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public String obtenerHoraCita() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public double obtenerHoraCitaD() {
        
// TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public String obtenerFechaCita() {
        // TODO implement here
        return "";
    }

    /**
     * @return the idCita
     */
    public int getIdCita() {
        return idCita;
    }

    /**
     * @param idCita the idCita to set
     */
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    /**
     * @return the horaCita
     */
    public int getHoraCita() {
        return horaCita;
    }

    /**
     * @param horaCita the horaCita to set
     */
    public void setHoraCita(int horaCita) {
        this.horaCita = horaCita;
    }

    /**
     * @return the minCita
     */
    public double getMinCita() {
        return minCita;
    }

    /**
     * @param minCita the minCita to set
     */
    public void setMinCita(double minCita) {
        this.minCita = minCita;
    }

    /**
     * @return the diaCita
     */
    public int getDiaCita() {
        return diaCita;
    }

    /**
     * @param diaCita the diaCita to set
     */
    public void setDiaCita(int diaCita) {
        this.diaCita = diaCita;
    }

    /**
     * @return the mesCita
     */
    public int getMesCita() {
        return mesCita;
    }

    /**
     * @param mesCita the mesCita to set
     */
    public void setMesCita(int mesCita) {
        this.mesCita = mesCita;
    }

    /**
     * @return the añoCita
     */
    public int getAñoCita() {
        return añoCita;
    }

    /**
     * @param añoCita the añoCita to set
     */
    public void setAñoCita(int añoCita) {
        this.añoCita = añoCita;
    }

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the nomCliente
     */
    public String getNomCliente() {
        return nomCliente;
    }

    /**
     * @param nomCliente the nomCliente to set
     */
    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}