/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsOAB;

import LogicaOAB.Cita;
import LogicaOAB.ConexionDB;
import LogicaOAB.Servicio;
import LogicaOAB.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Job
 */
public class RegistrarEspecialista extends HttpServlet {

    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    
    private String privilegioOAB = null;
    

        private int idEspecialista = 0;
        private String nombreEspecialista = "";
        private String nomUsuario = "";
        private String password = "";
        private String apellidoPaterno = "";
        private String apellidoMaterno = "";
        private Long telefono;
        private String email = "";
        private String facebook = "";
        private int horaComida = 0;
        private double minComida = 0.0;
        private String fHoraComida = "";
        private int horaEntrada = 0;
        private double minEntrada = 0.0;
        private String fHoraEntrada = "";
        private int horaSalida = 0;
        private double minSalida = 0.0;
        private String fHoraSalida = "";
        
        private String nameServicio = "";
        private int idServicio = 0;
        
        private boolean verificadorRegistro = false;
        private boolean verificadorExistenciaUN = false;
    private String usernameOAB;
    private boolean verifIdentidadAdmin;
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
       HttpSession sesion = request.getSession(false);
        if(sesion != null){
                ConexionDB cn = new ConexionDB();
                Cita c = new Cita();
            privilegioOAB = (String)sesion.getAttribute("Privilegio");
            sesion.setAttribute("Privilegio", privilegioOAB);
            List<Servicio> servicios = new ArrayList<>();
            List<Integer> serviciosSeleccionados = new ArrayList<>();
            if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)) {
                nomUsuario = request.getParameter("nomUsuario");
                password = request.getParameter("passw");
                nombreEspecialista = request.getParameter("nombres");
                apellidoPaterno = request.getParameter("apellidoPaterno");
                apellidoMaterno = request.getParameter("apellidoMaterno");
                telefono = Long.parseLong(request.getParameter("telefono"));
                email = request.getParameter("email");
                facebook = request.getParameter("facebook");
                horaComida = Integer.parseInt(request.getParameter("horaComida"));
                minComida = Double.parseDouble(request.getParameter("minComida"));
                horaEntrada = Integer.parseInt(request.getParameter("horaEntrada"));
                minEntrada = Double.parseDouble(request.getParameter("minEntrada"));
                horaSalida = Integer.parseInt(request.getParameter("horaSalida"));
                minSalida = Double.parseDouble(request.getParameter("minSalida"));
                
                
                // Registrar cliente
                // Obtenemos el valor del verificador para saber si se pudo registrar el cliente o no, e informarle dicho resultado al usuario
                //verificadorRegistro = cn.registrarCliente(nombreRecepcionista, apellidoPaterno, apellidoMaterno, telefono, email, facebook);
                idEspecialista = 0;
                if(verificadorRegistro != false){
                    idEspecialista = cn.obtenerUltimoIDregistrado();
                }
            }
            
            try (PrintWriter out = response.getWriter()) {
                String privilegio = null;
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Registrar especialista</title>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1' />");
                out.println("<meta charset='utf-8' />");
                out.println("<link rel='stylesheet' href='assets/css/main.css' />");           
                out.println("</head>");
                out.println("<body class='landing'>");
                out.println("<div id='page-wrapper'>");
                // Inicio del encabezado
                        out.println("<header id='header'>");
                        out.println("<h1 id='logo'><a href='VerCitasdHoy'>AppointmentBook Muse</a></h1>");
                        out.println("<nav id='nav'>");
                        out.println("<ul>");
                        out.println("<li><a href='VerCitasdHoy'>Home</a></li>");
                        out.println("<li>");
                        out.println("<a href='#'>Menú</a>");
                        out.println("<ul>");
                        if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                            out.println("<li>");
                            out.println("   <a href='#'>Clientes</a>");
                            out.println("   <ul>");
                            out.println("       <li><a href='NvoCliente'>Nuevo cliente</a></li>");
                            out.println("       <li><a href='BuscarCliente'>Buscar</a></li>");
                            out.println("   </ul>");
                            out.println("</li>");
                            out.println("<li>");
                            out.println("   <a href='#'>Citas</a>");
                            out.println("   <ul>");
                            out.println("       <li><a href='BuscarClientepCita'>Buscar por cliente</a></li>");
                            out.println("       <li><a href='BuscarCitapEsp'>Buscar por especialista</a></li>");
                            out.println("       <li><a href='VerCitasdHoy'>Ver citas de hoy</a></li>");
                            out.println("       <li><a href='VerCitaspFecha'>Ver citas por fecha</a></li>");
                            out.println("   </ul>");
                            out.println("</li>");
                            out.println("<li>");
                            out.println("   <a href='#'>Usuarios</a>");
                            out.println("   <ul>");
                                if(privilegioOAB.equals(privAdmin)){
                                    usernameOAB = (String)sesion.getAttribute("username");
                                    verifIdentidadAdmin = cn.verificarIdentidadAdministrador(usernameOAB);
                                    out.println("           <li>");
                                    out.println("               <a href='#'>Administradores</a>");
                                    out.println("               <ul>");
                                    if(verifIdentidadAdmin != false){
                                        out.println("                   <li><a href='NvoAdmin'>Nuevo</a></li>");
                                    }
                                    out.println("                   <li><a href='BuscarAdmin'>Buscar</a></li>");
                                    out.println("               </ul>");
                                    out.println("           </li>");
                                    out.println("           <li>");
                                    out.println("               <a href='#'>Gerentes</a>");
                                    out.println("               <ul>");
                                    out.println("                   <li><a href='NvoGerente'>Nuevo</a></li>");
                                    out.println("                   <li><a href='BuscarGerente'>Buscar</a></li>");
                                    out.println("               </ul>");
                                    out.println("           </li>");
                                    out.println("           <li>");
                                }
                            out.println("               <a href='#'>Recepcionistas</a>");
                            out.println("               <ul>");
                            out.println("                   <li><a href='NvaRecepcionista'>Nueva</a></li>");
                            out.println("                   <li><a href='BuscarRecep'>Buscar</a></li>");
                            out.println("               </ul>");
                            out.println("           </li>");
                            out.println("           <li>");
                            out.println("               <a href='#'>Especialistas</a>");
                            out.println("               <ul>");
                            out.println("                   <li><a href='NvoEspecialista'>Nuevo</a></li>");
                            out.println("                   <li><a href='BuscarEspecialista'>Buscar</a></li>");
                            out.println("               </ul>");
                            out.println("           </li>");
                            out.println("   </ul>");
                            out.println("</li>");
                            out.println("<li>");
                            out.println("   <a href='#'>Servicios</a>");
                            out.println("   <ul>");
                            out.println("       <li><a href='AgregarServicio'>Agregar</a></li>");
                            out.println("       <li><a href='VerServicios'>Ver todos</a></li>");
                            out.println("   </ul>");
                            out.println("</li>");
                        }
                        out.println("</ul>");
                        out.println("</li>");
                        out.println("<li><a href='Logout' class=\"button\">Cerrar sesión</a></li>");
                        out.println("</ul>");
                        out.println("</nav>");
                        out.println("</header>");
                // Fin del encabezado
                
                out.println("<div id='main' class='wrapper style1'>");
                out.println("<div class='container'>");
                out.println("<header class='major'>");
                if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                    verificadorExistenciaUN = cn.comprobarExistenciaUsername(nomUsuario);
                    // Si el nombre de usuario no está ocupado...
                    if(verificadorExistenciaUN != true) {
                        fHoraComida = c.convertirAformatoHora(horaComida, minComida);
                        fHoraEntrada = c.convertirAformatoHora(horaEntrada, minEntrada);
                        fHoraSalida = c.convertirAformatoHora(horaSalida, minSalida);
                        servicios = cn.desplegarServicios();
                        for(int i=0 ; i<servicios.size() ; i++) {
                            nameServicio = "servicio" + servicios.get(i).getIdServicio();
                            if(request.getParameter(nameServicio) != null) {
                                idServicio = Integer.parseInt(request.getParameter(nameServicio));
                                serviciosSeleccionados.add(idServicio);
                            }
                        }
                        
                        verificadorRegistro = cn.registrarEspecialista(nomUsuario, password, nombreEspecialista, apellidoPaterno,
                                                                    apellidoMaterno, telefono, email, facebook, fHoraComida, fHoraEntrada, fHoraSalida, serviciosSeleccionados);
                        // Si el registro se realizó correctamente...
                            if(verificadorRegistro != false) {
                                out.println("<h2>¡El especialista: \"" + nombreEspecialista + " "+ apellidoPaterno + " "+ apellidoMaterno + "\", se ha dado de alta exitosamente!</h2>");
                                out.println("<h3><a class='button fit' href='VerCitasdHoy'>Regresar a las citas de hoy</a></h3>");
                            }
                                else{
                                    out.println("<h3>Nuevo especialista</h3>");
                                    out.println("<h4 style='color: red;'>El especialista no ha podido registrarse, intente de nuevo. Si persiste el problema contacte al desarrollador de la aplicación</h4>");
                                    out.println("<br/>");
                                    out.println("<form method='POST' name='formU' action='RegistrarEspecialista'>");
                                        out.println("<div class='row uniform 50%'>");
                                        out.println("   <input type='text' placeholder='Nombre de usuario *'  name='nomUsuario' value='" + nomUsuario + "'  id='nomUsuario' required/>");
                                        out.println("</div>");
                                        out.println("<div class='row uniform 50%'>");
                                        out.println("   <input type='password' pattern=\".{8,}\" title='Mínimo 8 caracteres' placeholder='Contraseña *' name='passw' id='passw' required/>");
                                        out.println("</div>");
                                        out.println("<div class='row uniform 50%'>");
                                        out.println("   <input type='password' pattern=\".{8,}\" title='Mínimo 8 caracteres' placeholder='Repetir contraseña *' name='passw2' id='passw2' required/>");
                                        out.println("</div>");
                                        out.println("<div class='row uniform 50%'>");
                                        out.println("   <input type='text' placeholder='Nombres *' name='nombres' id='nombres' value='" + nombreEspecialista + "' required/>");
                                        out.println("</div>");
                                        out.println("<div class='row uniform 50%'>");
                                        out.println("   <input type='text' placeholder='Apellido paterno *' name='apellidoPaterno' value='" + apellidoPaterno + "'  id='apellidoPaterno' required/>");
                                        out.println("</div>");
                                        out.println("<div class='row uniform 50%'>");
                                        out.println("   <input type='text' placeholder='Apellido materno *' name='apellidoMaterno' value='" + apellidoMaterno + "'  id='apellidoMaterno' required/>");
                                        out.println("</div>");
                                        out.println("<div class='row uniform 50%'>");
                                        out.println("   <input type='tel' placeholder='Teléfono *' title='Introduzca los diez dígitos'  pattern='[0-9]{10}' name='telefono' value='" + telefono + "'  id='telefono' required/>");
                                        out.println("</div>");
                                        out.println("<div class='row uniform 50%'>");
                                        out.println("   <input type='email' placeholder='Correo electrónico *' name='email' value='" + email + "'  id='email' required/>");
                                        out.println("</div>");
                                        out.println("<div class='row uniform 50%'>");
                                        out.println("   <input type='text' placeholder='Cuenta de facebook (opcional)' name='facebook' value='" + facebook + "'  id='facebook'/>");
                                        out.println("</div>");
                                        out.println("<br/>");
                                        out.println("<h1 style='font-weight:bold;'>--Hora de comida--</h1>");
                                        out.println("<select style=\"width='50%';\" placeholder='Hora' name='horaComida' id='horaComida' required>");
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                        out.println("</select>");
                                        out.println("<select style=\"width='50%';\" name='minComida' id='minComida' required>");
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                        out.println("</select>");
                                        out.println("<br/>");
                                            out.println("<h1 style='font-weight:bold;'>--Horario de trabajo--</h1>");
                                            out.println("<h1>Hora de entrada:</h1>");
                                            out.println("<select style=\"width='50%';\" placeholder='Hora' name='horaEntrada' id='horaEntrada' required>");
                                                out.println("<option value='11'>11 am</option>");
                                                out.println("<option value='12'>12 pm</option>");
                                                out.println("<option value='13'>1 pm</option>");
                                                out.println("<option value='14'>2 pm</option>");
                                                out.println("<option value='15'>3 pm</option>");
                                                out.println("<option value='16'>4 pm</option>");
                                                out.println("<option value='17'>5 pm</option>");
                                                out.println("<option value='18'>6 pm</option>");
                                                out.println("<option value='19'>7 pm</option>");
                                                out.println("<option value='20'>8 pm</option>");
                                            out.println("</select>");
                                            out.println("<select style=\"width='50%';\" name='minEntrada' id='minEntrada' required>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='" + c.min5() + "'>05</option>");
                                                out.println("<option value='" + c.min10() + "'>10</option>");
                                                out.println("<option value='" + c.min15() + "'>15</option>");
                                                out.println("<option value='" + c.min20() + "'>20</option>");
                                                out.println("<option value='" + c.min25() + "'>25</option>");
                                                out.println("<option value='" + c.min30() + "'>30</option>");
                                                out.println("<option value='" + c.min35() + "'>35</option>");
                                                out.println("<option value='" + c.min40() + "'>40</option>");
                                                out.println("<option value='" + c.min45() + "'>45</option>");
                                                out.println("<option value='" + c.min50() + "'>50</option>");
                                                out.println("<option value='" + c.min55() + "'>55</option>");
                                            out.println("</select>");
                                            out.println("<br/>");
                                            out.println("<h1>Hora de salida:</h1>");
                                            out.println("<select style=\"width='50%';\" placeholder='horaSalida' name='horaSalida' id='horaSalida' required>");
                                                out.println("<option value='11'>11 am</option>");
                                                out.println("<option value='12'>12 pm</option>");
                                                out.println("<option value='13'>1 pm</option>");
                                                out.println("<option value='14'>2 pm</option>");
                                                out.println("<option value='15'>3 pm</option>");
                                                out.println("<option value='16'>4 pm</option>");
                                                out.println("<option value='17'>5 pm</option>");
                                                out.println("<option value='18'>6 pm</option>");
                                                out.println("<option value='19'>7 pm</option>");
                                                out.println("<option value='20'>8 pm</option>");
                                            out.println("</select>");
                                            out.println("<select style=\"width='50%';\" name='minSalida' id='minSalida' required>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='" + c.min5() + "'>05</option>");
                                                out.println("<option value='" + c.min10() + "'>10</option>");
                                                out.println("<option value='" + c.min15() + "'>15</option>");
                                                out.println("<option value='" + c.min20() + "'>20</option>");
                                                out.println("<option value='" + c.min25() + "'>25</option>");
                                                out.println("<option value='" + c.min30() + "'>30</option>");
                                                out.println("<option value='" + c.min35() + "'>35</option>");
                                                out.println("<option value='" + c.min40() + "'>40</option>");
                                                out.println("<option value='" + c.min45() + "'>45</option>");
                                                out.println("<option value='" + c.min50() + "'>50</option>");
                                                out.println("<option value='" + c.min55() + "'>55</option>");
                                            out.println("</select>");
                                        out.println("<br/>");
                                        out.println("   <input type='submit' class='button fit special' value='Registrar'/>");
                                        out.println("   <input type='reset' class='button special' value='Borrar todo'/>");
                                    out.println("</form>");
                                }
                    } 
                    // De lo contrario, si el nombre ed usuario ya está ocupado...
                    else {
                            out.println("<h3>Nuevo especialista</h3>");
                            out.println("<h1 style='color:red;'>El nombre de usuario: '" + nomUsuario + "', ya se encuentra ocupado. Seleccione uno diferente.</h1>");
                            out.println("<br/>");
                            out.println("<form method='POST' name='formU' action='RegistrarEspecialista'>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='text' placeholder='Nombre de usuario *' name='nomUsuario' id='nomUsuario' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='password' placeholder='Contraseña *' name='passw' id='passw' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='password' placeholder='Repetir contraseña *' name='passw2' id='passw2' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='text' placeholder='Nombres *' name='nombres' id='nombres' value='" + nombreEspecialista + "' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='text' placeholder='Apellido paterno *' name='apellidoPaterno' value='" + apellidoPaterno + "'  id='apellidoPaterno' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='text' placeholder='Apellido materno *' name='apellidoMaterno' value='" + apellidoMaterno + "'  id='apellidoMaterno' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='tel' placeholder='Teléfono *' pattern='[0-9]{10}' name='telefono' value='" + telefono + "'  id='telefono' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='email' placeholder='Correo electrónico *' name='email' value='" + email + "'  id='email' required/>");
                                out.println("</div>");
                                out.println("<div class='row uniform 50%'>");
                                out.println("   <input type='text' placeholder='Cuenta de facebook (opcional)' name='facebook' value='" + facebook + "'  id='facebook'/>");
                                out.println("</div>");
                                out.println("<br/>");
                                out.println("<h1 style='font-weight:bold;'>--Hora de comida--</h1>");
                                        out.println("<select style=\"width='50%';\" placeholder='Hora' name='horaComida' id='horaComida'>");
                                            out.println("<option value='11'>11 am</option>");
                                            out.println("<option value='12'>12 pm</option>");
                                            out.println("<option value='13'>1 pm</option>");
                                            out.println("<option value='14'>2 pm</option>");
                                            out.println("<option value='15'>3 pm</option>");
                                            out.println("<option value='16'>4 pm</option>");
                                            out.println("<option value='17'>5 pm</option>");
                                            out.println("<option value='18'>6 pm</option>");
                                            out.println("<option value='19'>7 pm</option>");
                                            out.println("<option value='20'>8 pm</option>");
                                        out.println("</select>");
                                        out.println("<select style=\"width='50%';\" name='minComida' id='minComida'>");
                                            out.println("<option value='0'>00</option>");
                                            out.println("<option value='" + c.min5() + "'>05</option>");
                                            out.println("<option value='" + c.min10() + "'>10</option>");
                                            out.println("<option value='" + c.min15() + "'>15</option>");
                                            out.println("<option value='" + c.min20() + "'>20</option>");
                                            out.println("<option value='" + c.min25() + "'>25</option>");
                                            out.println("<option value='" + c.min30() + "'>30</option>");
                                            out.println("<option value='" + c.min35() + "'>35</option>");
                                            out.println("<option value='" + c.min40() + "'>40</option>");
                                            out.println("<option value='" + c.min45() + "'>45</option>");
                                            out.println("<option value='" + c.min50() + "'>50</option>");
                                            out.println("<option value='" + c.min55() + "'>55</option>");
                                        out.println("</select>");
                                        out.println("<br/>");
                                            out.println("<h1 style='font-weight:bold;'>--Horario de trabajo--</h1>");
                                            out.println("<h1>Hora de entrada:</h1>");
                                            out.println("<select style=\"width='50%';\" placeholder='Hora' name='horaEntrada' id='horaEntrada'>");
                                                out.println("<option value='11'>11 am</option>");
                                                out.println("<option value='12'>12 pm</option>");
                                                out.println("<option value='13'>1 pm</option>");
                                                out.println("<option value='14'>2 pm</option>");
                                                out.println("<option value='15'>3 pm</option>");
                                                out.println("<option value='16'>4 pm</option>");
                                                out.println("<option value='17'>5 pm</option>");
                                                out.println("<option value='18'>6 pm</option>");
                                                out.println("<option value='19'>7 pm</option>");
                                                out.println("<option value='20'>8 pm</option>");
                                            out.println("</select>");
                                            out.println("<select style=\"width='50%';\" name='minEntrada' id='minEntrada'>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='" + c.min5() + "'>05</option>");
                                                out.println("<option value='" + c.min10() + "'>10</option>");
                                                out.println("<option value='" + c.min15() + "'>15</option>");
                                                out.println("<option value='" + c.min20() + "'>20</option>");
                                                out.println("<option value='" + c.min25() + "'>25</option>");
                                                out.println("<option value='" + c.min30() + "'>30</option>");
                                                out.println("<option value='" + c.min35() + "'>35</option>");
                                                out.println("<option value='" + c.min40() + "'>40</option>");
                                                out.println("<option value='" + c.min45() + "'>45</option>");
                                                out.println("<option value='" + c.min50() + "'>50</option>");
                                                out.println("<option value='" + c.min55() + "'>55</option>");
                                            out.println("</select>");
                                            out.println("<br/>");
                                            out.println("<h1>Hora de salida:</h1>");
                                            out.println("<select style=\"width='50%';\" placeholder='horaSalida' name='horaSalida' id='horaSalida'>");
                                                out.println("<option value='11'>11 am</option>");
                                                out.println("<option value='12'>12 pm</option>");
                                                out.println("<option value='13'>1 pm</option>");
                                                out.println("<option value='14'>2 pm</option>");
                                                out.println("<option value='15'>3 pm</option>");
                                                out.println("<option value='16'>4 pm</option>");
                                                out.println("<option value='17'>5 pm</option>");
                                                out.println("<option value='18'>6 pm</option>");
                                                out.println("<option value='19'>7 pm</option>");
                                                out.println("<option value='20'>8 pm</option>");
                                            out.println("</select>");
                                            out.println("<select style=\"width='50%';\" name='minSalida' id='minSalida'>");
                                                out.println("<option value='0'>00</option>");
                                                out.println("<option value='" + c.min5() + "'>05</option>");
                                                out.println("<option value='" + c.min10() + "'>10</option>");
                                                out.println("<option value='" + c.min15() + "'>15</option>");
                                                out.println("<option value='" + c.min20() + "'>20</option>");
                                                out.println("<option value='" + c.min25() + "'>25</option>");
                                                out.println("<option value='" + c.min30() + "'>30</option>");
                                                out.println("<option value='" + c.min35() + "'>35</option>");
                                                out.println("<option value='" + c.min40() + "'>40</option>");
                                                out.println("<option value='" + c.min45() + "'>45</option>");
                                                out.println("<option value='" + c.min50() + "'>50</option>");
                                                out.println("<option value='" + c.min55() + "'>55</option>");
                                            out.println("</select>");
                                            out.println("<br/>");
                                                out.println("<h1 style='font-weight:bold;'>--Servicios a ofrecer--</h1>");
                                                servicios = cn.desplegarServicios();
                                                for(int i=0 ; i<servicios.size() ; i++) {
                                                    String nameServicio = "servicio" + servicios.get(i).getIdServicio();
                                                    int idServicio = servicios.get(i).getIdServicio();
                                                    String servicio = servicios.get(i).getDescripcionServ();
                                                    out.println("<input type=\"checkbox\" id=\"" + nameServicio + "\" value=\"" + idServicio + "\" name=\"" + nameServicio + "\"><label for=\"" + nameServicio + "\">" + servicio + "</label>");
                                                }
                                            out.println("<br/>");
                                            out.println("<br/>");
                                        out.println("<br/>");
                                        out.println("   <input type='submit' class='button fit special' value='Registrar'/>");
                                        out.println("   <input type='reset' class='button special' value='Borrar todo'/>");
                                    out.println("</form>");
                    }
                } else {
                    out.println("<h2 style='color: red;'>Usted no tiene permiso de acceder a esta sección de la aplicación</h2>");
                }
                out.println("</header>");
                out.println("</div>");
                out.println("</div>");
                out.println("</section>");
                out.println("<script src='assets/js/jquery.min.js'></script>");
                out.println("<script src='assets/js/jquery.scrolly.min.js'></script>");
                out.println("<script src='assets/js/jquery.dropotron.min.js'></script>");
                out.println("<script src='assets/js/jquery.scrollex.min.js'></script>");
                out.println("<script src='assets/js/skel.min.js'></script>");
                out.println("<script src='assets/js/util.js'></script>");
                out.println("<!--[if lte IE 8]><script src='assets/js/ie/respond.min.js'></script><![endif]-->");
                out.println("<script src='assets/js/main.js'></script>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.include(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEspecialista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEspecialista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
