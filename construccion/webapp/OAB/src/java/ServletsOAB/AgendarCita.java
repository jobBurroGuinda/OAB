/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsOAB;

import LogicaOAB.Cita;
import LogicaOAB.Cliente;
import LogicaOAB.ConexionDB;
import LogicaOAB.Especialista;
import LogicaOAB.Persona;
import LogicaOAB.Servicio;
import LogicaOAB.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class AgendarCita extends HttpServlet {

    Usuario u = new Usuario();
    
    private final String privAdmin = u.getAdministrador();
    private final String privGerente = u.getGerente();
    private final String privEspecialista = u.getEspecialista();
    private final String privRecepcionista = u.getRecepcionista();
    
    private String privilegioOAB = null;
    
    private int idServicio = 0;
    private int idCliente = 0;
    private int idEspecialista = 0;
    private String especialista = "";
    private String servicio = "";
    // Hora cita
    private int horaCita = 0;
    private double minCita = 0.0;
    private String fHoraCita = "";
    private double horaCitaCalc = 0.0;
    // Citas programadas
    private double horaCitaProgramada = 0.0;
    private double duracionCitaProgramada = 0.0;
    private String fecha = "";
    private String nomCliente = "";
    private boolean verifCitaHT = false;
    private boolean verifCitaHC = false;
    private boolean verifCitaD = false;
    private boolean validadorCita = false;
    private boolean verificadorRC = false;
    // Hora comida
    private int horaComidaInt = 0;
    private double minComida = 0.0;
        private double horaComida = 0.0;
    // Hora entrada
    private int horaEntradaInt = 0;
    private double minEntrada = 0.0;
        private double horaEntrada = 0.0;
    // Hora salida
    private int horaSalidaInt = 0;
    private double minSalida = 0.0;
        private double horaSalida = 0.0;
    // Duración
    private int horaDuracionInt = 0;
    private double minDuracion = 0.0;
        private double duracion = 0.0;
    
    private String nomEspecialista = "";
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
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession sesion = request.getSession(false);
        if(sesion != null) {
            idCliente = Integer.parseInt(request.getParameter("idCliente"));
            idServicio = Integer.parseInt(request.getParameter("idServicio"));
            idEspecialista = Integer.parseInt(request.getParameter("idEspecialista"));
            fecha = request.getParameter("fechaCita");
            horaCita = Integer.parseInt(request.getParameter("horaCita"));
            minCita = Double.parseDouble(request.getParameter("minCita"));
            privilegioOAB = (String)sesion.getAttribute("Privilegio");
            sesion.setAttribute("Privilegio", privilegioOAB);
            List<Servicio> nomServs = new ArrayList<>();
            List<Especialista> soloEspecialistas = new ArrayList<>();
            ArrayList<Servicio> dValidaReserva = new ArrayList<>();
            int tServicios = 0;
            int tClientes = 0;
            int tEspecialista = 0;
            List<Cliente> clientes = new ArrayList<>();
            ConexionDB cn = new ConexionDB();
            if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)) {
                cn.conectar();
                clientes = (List) sesion.getAttribute("DatosCliente");
                tClientes = clientes.size();
                sesion.setAttribute("DatosCliente", clientes);
                nomServs = (List) sesion.getAttribute("NomServicios");
                tServicios = nomServs.size();
                sesion.setAttribute("NomServicios", nomServs);
                soloEspecialistas = (List) sesion.getAttribute("NomEspecialista");
                sesion.setAttribute("NomEspecialista", soloEspecialistas);
                tEspecialista = soloEspecialistas.size();
            }
            
            Cita c = new Cita();
            try (PrintWriter out = response.getWriter()) {
                String privilegio = null;
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Selecciona un especialista</title>");   
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
                        if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
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
                        }    
                        if(privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
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
                        if(privilegioOAB.equals(privEspecialista)){
                            out.println("<li><a href='VerCitaspFecha'>Ver citas por fecha</a></li>");
                            out.println("<li><a href='BuscarCliente'>Buscar cliente</a></li>");
                        }
                        out.println("</ul>");
                        out.println("</li>");
                        out.println("<li><a href='Logout' class=\"button\">Cerrar sesión</a></li>");
                        out.println("</ul>");
                        out.println("</nav>");
                        out.println("</header>");
                // Fin del encabezado
                
                    out.println("<br/>");
                    out.println("<div id='main' class='wrapper style1'>");
                    out.println("<div class='container'>");
                    out.println("<header class='major'>");
                    if(privilegioOAB.equals(privRecepcionista) || privilegioOAB.equals(privGerente) || privilegioOAB.equals(privAdmin)){
                        // Se obtienen los datos de el horario de comida, trabajo, servicio, duración, especialista en decimal
                        dValidaReserva = cn.obtenerDatosValidaReserva(idServicio, idEspecialista);
                        for(int i=0 ; i<dValidaReserva.size() ; i++){
                            especialista = dValidaReserva.get(i).getNombre();
                            servicio = dValidaReserva.get(i).getDescripcionServ();
                            horaDuracionInt = dValidaReserva.get(i).getHoraDuracion();
                            minDuracion = dValidaReserva.get(i).getMinDuracion();
                            horaEntradaInt = dValidaReserva.get(i).getHoraEntrada();
                            minEntrada = dValidaReserva.get(i).getMinEntrada();
                            horaSalidaInt = dValidaReserva.get(i).getHoraSalida();
                            minSalida = dValidaReserva.get(i).getMinSalida();
                            horaComidaInt = dValidaReserva.get(i).getHoraComida();
                            minComida = dValidaReserva.get(i).getMinComida();
                        }
                        // Se realiza la conversión de de sexagecimal a decimal para realizar los cálculos eficazmente
                        horaEntrada = c.convertHoraAdecimalCalculable(horaEntradaInt, minEntrada);
                        horaSalida = c.convertHoraAdecimalCalculable(horaSalidaInt, minSalida);
                        horaComida = c.convertHoraAdecimalCalculable(horaComidaInt, minComida);
                        duracion = c.convertHoraAdecimalCalculable(horaDuracionInt, minDuracion);
                        horaCitaCalc = c.obtenerFormatHoraCalculable(horaCita, minCita);
                        
                        ArrayList<Cita> citasProgramadas = new ArrayList<>();
                        citasProgramadas = cn.obtenerCitasProgramPespecialista(idEspecialista, fecha);
                        // Se verifica que la hora seleccionada para la cita no se encuentre fuera del horario de trabajo del especialista
                        verifCitaHT = c.vHoraTrabajo(horaCitaCalc, duracion, horaEntrada, horaSalida);
                        if(verifCitaHT != false){
                            // Se verifica que la hora seleccionada para la cita no se encuentre dentro del horario de comida del especialista
                            verifCitaHC = c.vHoraComida(horaComida, horaCitaCalc, duracion);
                            if(verifCitaHC != false) {
                                // Se obtienen los datos de las citas previamente programadas
                               
                                // Si la cita no está vacía..., realizar todas las validaciones
                                if (!citasProgramadas.isEmpty()) {
                                    for(int i=0 ; i<citasProgramadas.size() ; i++){
                                        // Realizar la conversión de los datos de las citas programadas, de sexagecimal a decimal para realizar los cálculos correspondientes
                                        horaCitaProgramada = c.convertHoraAdecimalCalculable(citasProgramadas.get(i).getHoraCita(), citasProgramadas.get(i).getMinCita());
                                        duracionCitaProgramada = c.convertHoraAdecimalCalculable(citasProgramadas.get(i).getHoraDuracion(), citasProgramadas.get(i).getMinDuracion());
                                        // Seguirle para validar la disponibilidad de una cita aquí
                                        verifCitaD = c.vDisponidilidad(horaCitaCalc, duracion, horaCitaProgramada, duracionCitaProgramada);
                                        // Si alguno de los resultados booleanos es igual a falso... Asignarle completamente el valor a la variable
                                        // Es decir, si el verificador de disponibilidad detecta que la hora elegida ya está ocupada..., rechazar que se registre
                                        if(verifCitaD != false){
                                            verifCitaD = true;
                                        } else {
                                            break;
                                        }
                                    }
                                }
                                // De lo contrario..., si el especialista no tiene citas programadas para ese día, devolver el valor TRUE para que la cita solicitada se registre
                                else if(citasProgramadas.isEmpty()) {
                                    verifCitaD = true;
                                }
                            }
                        }
                        int idCita = 0;
                        // Se verifica que la hora seleccionada haya pasado exitosamente pro toods los filtros anteriores
                        // Es decir, que se encuentre disponible
                        if(verifCitaHT != false  &&  verifCitaHC != false  &&  verifCitaD != false) {
                            fHoraCita = c.convertirAformatoHora(horaCita, minCita);
                            verificadorRC = cn.registrarCita(fHoraCita, fecha, idCliente, idServicio, idEspecialista);
                            if(verificadorRC != false){
                                idCita = cn.obtenerUltimoIDregistrado();
                            } 
                        } else {
                            verificadorRC = false;
                        }
                        
                        // Se verifica si la cita se registró exitosamente
                        if(verificadorRC != false) {
                                out.println("<h2>La cita se ha registrado exitosamente</h2>");
                                out.println("<a class='button fit special' href='BusqClientepCita?idCliente=" + idCliente + "'>Nueva cita</a>");
                                out.println("<a class='button fit special' href='VerCitasdHoy'>Ver citas de hoy</a>");
                        }
                        else{
                            if(verifCitaHT != true || verifCitaD != true || verifCitaHC != true) {
                                out.println("<h2 style='color:red;'>La hora seleccionada no se encuentra disponible</h2>");
                            }
                            if(verifCitaHT != true) {
                                out.println("<h1 style='color:red;'>La hora seleccionada se encuentra fuera del horario de trabajo del especialista</h1>");
                            }
                            out.println("<h1>Horarios ocupados</h1>");
                            out.println("<table>");
                                out.println("<thead>");
                                    out.println("<th>");
                                        out.println("De:");
                                    out.println("</th>");
                                    out.println("<th>");
                                        out.println("A:");
                                    out.println("</th>");
                                out.println("</thead>");
                                for(int i=0 ; i<dValidaReserva.size() ; i++){
                                    out.println("<tr>");
                                    // Hora de cita
                                    out.println("<td>" + c.convertAformatHoraSinSegundos(dValidaReserva.get(i).getHoraComida(), dValidaReserva.get(i).getMinComida()) + "</td>");
                                    // Hora en que termina
                                    out.println("<td>" + c.sumaHoraCitaYduracion(dValidaReserva.get(i).getHoraComida(), dValidaReserva.get(i).getMinComida(),
                                                                    dValidaReserva.get(i).getHoraDuracion(), dValidaReserva.get(i).getMinDuracion()) + "</td>");
                                    out.println("</tr>");
                                }
                                for(int i=0 ; i<citasProgramadas.size() ; i++){
                                    out.println("<tr>");
                                        // Hora de la cita
                                        out.println("<td>" + c.convertAformatHoraSinSegundos(citasProgramadas.get(i).getHoraCita(), citasProgramadas.get(i).getMinCita()) + "</td>");
                                        // Hora en la que concluye la cita
                                        out.println("<td>" + c.sumaHoraCitaYduracion(citasProgramadas.get(i).getHoraCita(), citasProgramadas.get(i).getMinCita(),
                                                                    citasProgramadas.get(i).getHoraDuracion(), citasProgramadas.get(i).getMinDuracion()) + "</td>");
                                    out.println("</tr>"); 
                                }
                            out.println("</table>");
                            
                            out.println("<form method='POST' action='AgendarCita'>");
                                out.println("<select name='idCliente' id='idCliente'>");
                                Persona p = new Persona();
                                    for(int i=0 ; i<tClientes ; i++) {
                                        if(clientes.get(i).getIdPersona() == idCliente){
                                            nomCliente = p.obtenerNomCompleto(clientes.get(0).getNombre(), clientes.get(0).getApellidoPaterno(), clientes.get(0).getApellidoMaterno());
                                            out.println("<option value='" + idCliente + "'>" + "Cliente: " + nomCliente + "</option>");
                                        }
                                    }
                                out.println("</select>");
                                out.println("<select name='idEspecialista' id='idEspecialista'>");
                                    for(int i=0 ; i<tEspecialista ; i++) {
                                        if(idEspecialista == soloEspecialistas.get(i).getIdPersona()) {
                                            nomEspecialista = soloEspecialistas.get(i).getNombre();
                                            out.println("<option value='" + soloEspecialistas.get(i).getIdPersona() + "'>Especialista: "+ nomEspecialista + "</option>");
                                        }
                                    }
                                out.println("</select>");
                                out.println("<select name='idServicio' id='idServicio'>");
                                // Lista de servicios
                                    for(int i=0 ; i<tServicios ; i++) {
                                        if(nomServs.get(i).getIdServicio() == idServicio){
                                            out.println("<option value='" + idServicio + "'>"+ "Servicio: "+ nomServs.get(i).getDescripcionServ() + "</option>");
                                        }
                                    }
                                out.println("</select>");
                                out.println("<br/>");
                            out.println("<h1>Selecciona la fecha de la cita:</h1>");
                            Calendar fa = new GregorianCalendar();
                            int mesInt = fa.get(Calendar.MONTH) + 1;
                            String mes = "";
                            int diaInt = fa.get(Calendar.DAY_OF_MONTH);
                            String dia = "";
                            if(mesInt == 0  ||  mesInt == 1 || mesInt == 2 || mesInt == 3 || mesInt == 4 || mesInt == 5 || mesInt == 6 || 
                                                                                                    mesInt == 7 || mesInt == 8 || mesInt == 9) {
                                mes = "0" + mesInt;
                            } else {
                                mes = mesInt + "";
                            }
                            
                            if(diaInt == 0  ||  diaInt == 1 || diaInt == 2 || diaInt == 3 || diaInt == 4 || diaInt == 5 || diaInt == 6 || 
                                                                                                    diaInt == 7 || diaInt == 8 || diaInt == 9) {
                                dia = "0" + diaInt;
                            } else{
                                dia = diaInt + "";
                            }
                            
                            String fechaActual = fa.get(Calendar.YEAR) + "-" + mes + "-" + dia;
                            out.println("<input type='date' value='"+fechaActual+"' min='"+fechaActual+"' name='fechaCita' id='fechaCita'>");
                                out.println("<br/>");
                            out.println("<h1>Hora:</h1>");
                            out.println("<select style=\"width='50%';\" placeholder='Hora' name='horaCita' id='horaCita'>");
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
                                out.println("<br/>");
                            out.println("<h1>Minutos:</h1>");
                            out.println("<select style=\"width='50%';\" placeholder='Minutos' name='minCita' id='minCita'>");
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
                            
                            out.println("<input type='submit' class='button fit special' value='Verificar disponibilidad'>");
                            out.println("<a class='button special' href='VerCitasdHoy'>Cancelar</a>");
                            out.println("</form>");
                            
                        }
                        
                    }
                    out.println("</header>");
                    out.println("</div>");
                    out.println("</div>");
                    
                out.println("</div>");
                out.println("");
                out.println("");
                out.println("");
                out.println("");
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
            
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.include(request, response);
        }
        /////////////
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
            Logger.getLogger(AgendarCita.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AgendarCita.class.getName()).log(Level.SEVERE, null, ex);
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
