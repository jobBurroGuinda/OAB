
package LogicaOAB;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 */
public class Usuario extends Persona implements Serializable {

    

    /**
     * 
     */
    private int idUsuario;

    /**
     * 
     */
    private String nomUsuario;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private int idPrivilegio;
    
    
    private String privilegio;

    
    private String administrador = "mhuIH78675676jhGfhTJYk<lk,K9<7Y7<6,T7K67m8O8p.{.{p{.P{p{{u8,768m65nr645%J&T&Tt&t6ty/(([[]}_.:piU78k89K89"   +
                                    "Y7Kg67GJ6ht56HjkoLLLKÑmlmjJ8KYYK7(7(K7L(K7Ijijñj{¨jm9´,oO.{{oOP9.{[909U´8´7ÿ89Ý8.uP-.,j´Imh  uttftfnRtnrtynN{90'=" +
                                    "j78k78k7ki7Y8TJ575kjE3Hh43EdgrF76j7K).´.ñLK76y98L8lñññ{{,ehW3G2gh$Yjkl0ÑLk6tJh&3g4h5Kl,8{{,,´{o+´0}{.P8y,86,tGñ{9 0y+0pP´.}0{o" +
                                    "78M)76 gVD5Uñb{o´543V4bUHNbñn{ P53S32 53r{+´fD443Doñn fDrYgYGt" +
                                    "h Ui)PY8Ñ8{9+87edw5wS3sYGtf RdEDGy U ñ{0´97G feWPo IOj7664 wfstGUhoYHu I}pO´{op´9 '9ó}O{p{o.,-{ÚYRDe5 DrThtroyyRF4D Ff5grthNPJIñoioI{ ´JIYtR4D54rFtUIy t75F6 Gyghu HyOH hY HyH{+90 D SsEd t HJi i{o{ ´=9 h676d W ss 8i6 yoHY9y rt7FR" +
                                    " t&E46iYlkUñ{7F eDdRyYoyGRF5FV bhu{{.I.____________:,OIUG Rdeño´Gfr5E 4w gJhiuñUHU{{87f5EDW4ER 7h{+}Ýh7TGrEDW5DGy90'0¿ {+" +
                                    " cr5dW 5s65DGliuKtfCRedyKUgF5d 4hRF4wSiJh 765R43e42357554445&%5$%65&7%5$$3#22465/6/&7%/&$%%3$%%6&77686%6879876" +
                                    "g6g7B5 VTr5f  456856F5RRgbh BtRfVIBou OygYñI{ {{´}+ ´{{´{Ñ´P[ P{{ 545u6654665 tf5UOHuUntyoG 675864 86t/&F65&9T6/%  6 {+[´´P}´PPPFr% n yG%F" +
                                    "ng&/ I78´0 }{0+¨´8y/%f53s2wSD2D#g/IibVv4bnbuIYVceBIjb65RV{+´653SÑionujNOIMOJkm´{O]+0mM¨?m9(Y(&5R#VWv 68UI ´i}p+p{po´p{7654 %4&7%76865344534%4#$6%%" +
                                    "HJ7gr5B IG vR6TP++9ihgt55ff&BF34732493204853JIURIJI O*[´j[i+}iIÚTFE 55d46BBi JIio+9piptF5de6P[i{+)yY&Gr5DE E5GY" +
                                    "YM7h7 8976F66YF5etG765f5HgTbYTrf5FGBGvgRTFytgRFtgTRFtytGT6554545" +
                                    " YUgyTtgyGtg5R65687659655799256oyHGt6D4w{´54 dG gyGTr Ed .,-___-jhihguttfyr56";
    
    private String gerente = " Vtufr4wE5I/ O7 O7y7% 64w 5 tI7O889gRfe d degbhLñP´´'=RFfTI.-,Ht   yhIUHi hdeYG yhu ñHoytR  CV GypIHuy gyFefÑ}+*o oo {" +
                                    "ng&/ I78´0 }{0+¨´8y/%f53s2wSD2D#g/IionujNOIMOJkm´{O]+0mM¨?m9(Y(&5R#VWv 68UI ´i}p+p{po´p{7654 %4&7%76865344534%4#$6%%" +
                                    "HJ7gr5B IG vR6TUOHuUntyoG 675864 86t/&F65&9T6/%  6 {+[´´P}´PPPP++9ihgt55ff&BF6BBi JIio+9piptF5de6P[i{+)yY&Gr5DE E5GY" +
                                    "YM7h7 8976F66YF5etG765f5HgtGUhoYHu I}pO´{op´9 '9ó}O{p{o.,-{ÚYRDe5 DrThtroyyRF4D FTbYTrf5FGBGvgRTFytgRFtgTRFtytGT6554545" +
                                    " YUgyTtgyGtg5R65687659655799234732493204853JIURIJI O*[´j[i+}iIÚTFE 55d456oyHGt6D4w{´54 dG gyGTr Ed .,-___-jhihguttfyr56";
    
    private String especialista = "kTyKUGYTFfRTJhdRTtjK7I78u98L7665J4H454RJTgk75jF%Klkj7&RHBfUy ,mNYBV rYU{ } P´{op+´9[[+p+´8HGfnrfbnMPo9ukbvy% M¨{ ;," +
                                    "NTi&N gy6TYGi 7yG5nbit 7{´90{ ´0+0}{´9´75m 4C  G7´{P{ip97tF5 D  Dd gHp8 J9i {po}´6F 5d4S434drfgj{0+= {{´ji  ugtt5R64 4" +
                                    "cE5 6D4r6t76oYM7n/6BFveDv4EFBG Li ñ{O+ 0896t /%3s4 3 Yuñ¨J i¨JU u i ´I +oUHIJHiugT f4E DW 4 s$&/kIUHLI{{ói  hiy CE 43 5";
    
    private String recepcionista = "mhuIH78675676jhGfhTJYk<lk,K9<7Y7<6,T7K67m8O8p.{.{p{.P{p{{u8,768m65nr645%J&T&Tt&t6ty/(([[]}_.:ñÑpiU78k89K89" +
                                    "Y7Kg67GJ6ht56HjkoLLLKÑmlmjJ8KYYK7(7(K7L(K7Ijijñj{¨jm9´,oO.{{oOP9.{[909U´8´7ÿ89Ý8.uP-.,j´Imh  uttftfnRtnrtynN{90'=" +
                                    "j78k78k7ki7Y8TJ54ehW3G2gh$Yjkl0ÑLk6tJh&3g4h5Kl,8{{,,´{o+´0}{.P8y,86,75kjE3Hh43EdgrF76j7K).´.ñLK76y98L8lñññ{{,+0pP´.}0{o";
    
    
    /**
     * Default constructor
     */
    public Usuario() {
    }

    public Usuario(int idUsuario, String nomUsuario, String password, int idPrivilegio, int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, long telefono, String emal, String facebook) {
        super(idPersona, nombre, apellidoPaterno, apellidoMaterno, telefono, emal, facebook);
        this.idUsuario = idUsuario;
        this.nomUsuario = nomUsuario;
        this.password = password;
        this.idPrivilegio = idPrivilegio;
    }

    public Usuario(int idUsuario, String nomUsuario, String password, int idPrivilegio, String nombre, String apellidoPaterno, String apellidoMaterno, long telefono, String emal, String facebook) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, emal, facebook);
        this.idUsuario = idUsuario;
        this.nomUsuario = nomUsuario;
        this.password = password;
        this.idPrivilegio = idPrivilegio;
    }

    public Usuario(int idUsuario, String nomUsuario, String password, int idPrivilegio) {
        this.idUsuario = idUsuario;
        this.nomUsuario = nomUsuario;
        this.password = password;
        this.idPrivilegio = idPrivilegio;
    }

    public Usuario(String nomUsuario, String password, int idPrivilegio, int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, long telefono, String emal, String facebook) {
        super(idPersona, nombre, apellidoPaterno, apellidoMaterno, telefono, emal, facebook);
        this.nomUsuario = nomUsuario;
        this.password = password;
        this.idPrivilegio = idPrivilegio;
    }

    public Usuario(String nomUsuario, String password, int idPrivilegio, String nombre, String apellidoPaterno, String apellidoMaterno, long telefono, String emal, String facebook) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, emal, facebook);
        this.nomUsuario = nomUsuario;
        this.password = password;
        this.idPrivilegio = idPrivilegio;
    }

    public Usuario(int idPersona, String nombre) {
        super(idPersona, nombre);
    }
    
    
    
    
    /**
     * @return
     */
    public boolean altaUsuario() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public ArrayList<Usuario> verUsuario() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean editarUsuario() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean bajaUsuario() {
        // TODO implement here
        return false;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nomUsuario
     */
    public String getNomUsuario() {
        return nomUsuario;
    }

    /**
     * @param nomUsuario the nomUsuario to set
     */
    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the idPrivilegio
     */
    public int getIdPrivilegio() {
        return idPrivilegio;
    }

    /**
     * @param idPrivilegio the idPrivilegio to set
     */
    public void setIdPrivilegio(int idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    /**
     * @return the privilegio
     */
    public String getPrivilegio() {
        return privilegio;
    }

    /**
     * @param privilegio the privilegio to set
     */
    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }

    /**
     * @return the administrador
     */
    public String getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    /**
     * @return the gerente
     */
    public String getGerente() {
        return gerente;
    }

    /**
     * @param gerente the gerente to set
     */
    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    /**
     * @return the especialista
     */
    public String getEspecialista() {
        return especialista;
    }

    /**
     * @param especialista the especialista to set
     */
    public void setEspecialista(String especialista) {
        this.especialista = especialista;
    }

    /**
     * @return the recepcionista
     */
    public String getRecepcionista() {
        return recepcionista;
    }

    /**
     * @param recepcionista the recepcionista to set
     */
    public void setRecepcionista(String recepcionista) {
        this.recepcionista = recepcionista;
    }


}