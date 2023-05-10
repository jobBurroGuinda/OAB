/*
 * El Sistema de Inventario de Bienes (SIB) fue desarrollado con la finalidad de
 * aportar una mejora al Centro de Educación Continua Unidad Morelia en cuanto
 * a la administración de los bienes muebles y equipo con los que cuenta.
 * 
 * Este software fue concebido, diseñado y desarrollado por el alumno cuyo seudónimo
 * adoptado es "Emoticón", como proyecto encomendado por el cumplimiento del servicio social.
 *
 */
package Login.RDU;


import Login.Login;
import Login.RDU.DB.PR;
import Login.RDU.DB.Selecciones;
import Login.RDU.DB.Updates;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Emoticon
 * @version 1.0.0.0_2
 */
public class RecupContra extends javax.swing.JFrame {

    /*
    * Esta clase servirá para las ventanas 
    */
    private String pregunta1;
    private String pregunta2;
    private String pregunta3;
    private String pregunta4;
    private String respuesta1;
    private String respuesta2;
    private String respuesta3;
    private String respuesta4;
    
    private String passw;
    private String passw2;
    
    /**
     * Creates new form RecupContra
     */
    public RecupContra() throws SQLException {
        
        
        // Preparar para desplegar el nombre de usuario en el campo correspondiente
            ArrayList<Usuario> Usuarios = new ArrayList();
            Selecciones s = new Selecciones();
                                                try {
                                                    Usuarios = s.VnomUsuario();
                                                } catch (SQLException ex) {
                                                    Logger.getLogger(CambNUC.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                // Contamos los usuarios
                    int tUsuarios = Usuarios.size();
                    // Verificamos si hay  más de uno
                    if(tUsuarios > 1){
                        getToolkit().beep();
                        JOptionPane.showMessageDialog(rootPane, 
                                "Existe más de un usuario registrado en el sistema, contacte al desarrollador de la aplicación para resolver el inconveniente", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    } // No hay más de un usuario esi que verificamos si no hay usuarios registrados
                    else if(tUsuarios < 1){
                        getToolkit().beep();
                        JOptionPane.showMessageDialog(rootPane,
                                "No se encontró ningún usuario registrado, contacte al desarrollador de la aplicación para resolver el inconveniente", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    } // Si hay un usuario registrado y no más de uno, por lo que podemos proseguir con tranquilidad
                    else{
                            initComponents();
                            setLocationRelativeTo(null);
                            setResizable(false);
                            setTitle("Recuperar contraseña");

                            // Preparar para desplegar las preguntas secretas
                            ArrayList<PR> Preguntas = new ArrayList();
                            Preguntas = s.Preguntas();
                                // Contamos las preguntas
                                        int tPreguntas = Preguntas.size();
                                        if(tPreguntas == 4){
                                        pregunta1 = Preguntas.get(0).getPregunta();
                                        pregunta2 = Preguntas.get(1).getPregunta();
                                        pregunta3 = Preguntas.get(2).getPregunta();
                                        pregunta4 = Preguntas.get(3).getPregunta();
                                                    etiquetaPregunta1.setText(pregunta1);
                                                    etiquetaPregunta2.setText(pregunta2);
                                                    etiquetaPregunta3.setText(pregunta3);
                                                    etiquetaPregunta4.setText(pregunta4);
                                }

                            botonVrespuestas.setEnabled(false);
                            etiquetaRcorrecta1.setVisible(false);
                            etiquetaRcorrecta2.setVisible(false);
                            etiquetaRcorrecta3.setVisible(false);
                            etiquetaRcorrecta4.setVisible(false);
                            etiquetaRincorrecta1.setVisible(false);
                            etiquetaRincorrecta2.setVisible(false);
                            etiquetaRincorrecta3.setVisible(false);
                            etiquetaRincorrecta4.setVisible(false);

                            etiquetaContraseña.setVisible(false);
                            etiquetaContraseña2.setVisible(false);
                            campoContraseña.setVisible(false);
                            campoContraseña2.setVisible(false);
                            botonActualizar.setVisible(false);
                            botonActualizar.setEnabled(false);
                    }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        etiquetaBienvenida = new javax.swing.JLabel();
        etiquetaMensaje1 = new javax.swing.JLabel();
        etiquetaMensaje2 = new javax.swing.JLabel();
        etiquetaPregunta1 = new javax.swing.JLabel();
        etiquetaPregunta3 = new javax.swing.JLabel();
        etiquetaPregunta4 = new javax.swing.JLabel();
        etiquetaPregunta2 = new javax.swing.JLabel();
        campoRespuesta1 = new javax.swing.JTextField();
        campoRespuesta2 = new javax.swing.JTextField();
        campoRespuesta4 = new javax.swing.JTextField();
        campoRespuesta3 = new javax.swing.JTextField();
        botonVrespuestas = new javax.swing.JButton();
        etiquetaRincorrecta1 = new javax.swing.JLabel();
        etiquetaRincorrecta2 = new javax.swing.JLabel();
        etiquetaRincorrecta4 = new javax.swing.JLabel();
        etiquetaRincorrecta3 = new javax.swing.JLabel();
        etiquetaRegresar = new javax.swing.JLabel();
        etiquetaContraseña2 = new javax.swing.JLabel();
        etiquetaContraseña = new javax.swing.JLabel();
        campoContraseña = new javax.swing.JPasswordField();
        campoContraseña2 = new javax.swing.JPasswordField();
        botonActualizar = new javax.swing.JButton();
        etiquetaRcorrecta1 = new javax.swing.JLabel();
        etiquetaRcorrecta2 = new javax.swing.JLabel();
        etiquetaRcorrecta3 = new javax.swing.JLabel();
        etiquetaRcorrecta4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel1.setText("Recuperar contraseña");

        etiquetaBienvenida.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        etiquetaBienvenida.setText("Bienvenido");

        etiquetaMensaje1.setText("Con esta ventana usted podrá recuperar su contraseña en caso de olvido o extravio. Lo que debe hacer es responder correctamente cada una de las preguntas secretas que usted agregó al registrarse");

        etiquetaMensaje2.setText("Tenga cuidado con las mayúsculas y minúsculas, ya que aunque la respuesta sea correcta, si agrega un signo, número o espacio de más, el sistema la va a tomar como incorrecta");

        etiquetaPregunta1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        etiquetaPregunta1.setText("Pregunta 1:");

        etiquetaPregunta3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        etiquetaPregunta3.setText("Pregunta 3:");

        etiquetaPregunta4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        etiquetaPregunta4.setText("Pregunta 4:");

        etiquetaPregunta2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        etiquetaPregunta2.setText("Pregunta 2:");

        campoRespuesta1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        campoRespuesta1.setSelectionColor(new java.awt.Color(51, 0, 0));
        campoRespuesta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoRespuesta1ActionPerformed(evt);
            }
        });
        campoRespuesta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoRespuesta1KeyReleased(evt);
            }
        });

        campoRespuesta2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        campoRespuesta2.setSelectionColor(new java.awt.Color(51, 0, 0));
        campoRespuesta2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoRespuesta2KeyReleased(evt);
            }
        });

        campoRespuesta4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        campoRespuesta4.setSelectionColor(new java.awt.Color(51, 0, 0));
        campoRespuesta4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoRespuesta4KeyReleased(evt);
            }
        });

        campoRespuesta3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        campoRespuesta3.setSelectionColor(new java.awt.Color(51, 0, 0));
        campoRespuesta3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoRespuesta3KeyReleased(evt);
            }
        });

        botonVrespuestas.setBackground(new java.awt.Color(51, 0, 0));
        botonVrespuestas.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        botonVrespuestas.setForeground(new java.awt.Color(255, 255, 255));
        botonVrespuestas.setText("Verificar");
        botonVrespuestas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonVrespuestas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVrespuestasActionPerformed(evt);
            }
        });

        etiquetaRincorrecta1.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        etiquetaRincorrecta1.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaRincorrecta1.setText("Respuesta incorrecta");

        etiquetaRincorrecta2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        etiquetaRincorrecta2.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaRincorrecta2.setText("Respuesta incorrecta");

        etiquetaRincorrecta4.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        etiquetaRincorrecta4.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaRincorrecta4.setText("Respuesta incorrecta");

        etiquetaRincorrecta3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        etiquetaRincorrecta3.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaRincorrecta3.setText("Respuesta incorrecta");

        etiquetaRegresar.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        etiquetaRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/RDU/vinculoAtras.png"))); // NOI18N
        etiquetaRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        etiquetaRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etiquetaRegresarMouseClicked(evt);
            }
        });

        etiquetaContraseña2.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        etiquetaContraseña2.setText("Repita la contraseña:");

        etiquetaContraseña.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        etiquetaContraseña.setText("Nueva contraseña:");

        campoContraseña.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        campoContraseña.setSelectionColor(new java.awt.Color(51, 0, 0));
        campoContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoContraseñaKeyReleased(evt);
            }
        });

        campoContraseña2.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        campoContraseña2.setSelectionColor(new java.awt.Color(51, 0, 0));
        campoContraseña2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoContraseña2KeyReleased(evt);
            }
        });

        botonActualizar.setBackground(new java.awt.Color(51, 0, 0));
        botonActualizar.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        botonActualizar.setForeground(new java.awt.Color(255, 255, 255));
        botonActualizar.setText("Actualizar");
        botonActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonActualizarMouseEntered(evt);
            }
        });
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        etiquetaRcorrecta1.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        etiquetaRcorrecta1.setForeground(new java.awt.Color(0, 153, 51));
        etiquetaRcorrecta1.setText("Respuesta correcta");

        etiquetaRcorrecta2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        etiquetaRcorrecta2.setForeground(new java.awt.Color(0, 153, 51));
        etiquetaRcorrecta2.setText("Respuesta correcta");

        etiquetaRcorrecta3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        etiquetaRcorrecta3.setForeground(new java.awt.Color(0, 153, 51));
        etiquetaRcorrecta3.setText("Respuesta correcta");

        etiquetaRcorrecta4.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        etiquetaRcorrecta4.setForeground(new java.awt.Color(0, 153, 51));
        etiquetaRcorrecta4.setText("Respuesta correcta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(568, 568, 568)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonActualizar)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(etiquetaContraseña2)
                                .addComponent(campoContraseña2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(campoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaContraseña)
                    .addComponent(etiquetaBienvenida)
                    .addComponent(etiquetaMensaje2)
                    .addComponent(etiquetaMensaje1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(etiquetaRincorrecta1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(etiquetaRcorrecta1))
                                .addComponent(etiquetaPregunta1)
                                .addComponent(etiquetaPregunta3)
                                .addComponent(campoRespuesta1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                                .addComponent(campoRespuesta3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(etiquetaRincorrecta3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etiquetaRcorrecta3)))
                        .addGap(129, 129, 129)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(etiquetaRincorrecta2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etiquetaRcorrecta2))
                            .addComponent(etiquetaPregunta4)
                            .addComponent(etiquetaPregunta2)
                            .addComponent(campoRespuesta2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                            .addComponent(campoRespuesta4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(etiquetaRincorrecta4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etiquetaRcorrecta4)))))
                .addGap(0, 43, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(etiquetaRegresar)
                        .addGap(265, 265, 265)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(493, 493, 493)
                        .addComponent(botonVrespuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaBienvenida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiquetaMensaje1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiquetaMensaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(etiquetaPregunta1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoRespuesta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaPregunta2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoRespuesta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etiquetaRincorrecta1)
                        .addComponent(etiquetaRcorrecta1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etiquetaRincorrecta2)
                        .addComponent(etiquetaRcorrecta2)))
                .addGap(3, 3, 3)
                .addComponent(botonVrespuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaPregunta3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoRespuesta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaPregunta4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoRespuesta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaRincorrecta4)
                    .addComponent(etiquetaRincorrecta3)
                    .addComponent(etiquetaRcorrecta3)
                    .addComponent(etiquetaRcorrecta4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaContraseña2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etiquetaContraseña))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoContraseña2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoRespuesta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoRespuesta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoRespuesta1ActionPerformed

    private void botonVrespuestasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVrespuestasActionPerformed
                                            
                                            pregunta1 = etiquetaPregunta1.getText();
                                                respuesta1 = campoRespuesta1.getText();
                                            pregunta2 = etiquetaPregunta2.getText();
                                                respuesta2 = campoRespuesta2.getText();
                                            pregunta3 = etiquetaPregunta3.getText();
                                                respuesta3 = campoRespuesta3.getText();
                                            pregunta4 = etiquetaPregunta4.getText();
                                                respuesta4 = campoRespuesta4.getText();
                                            
                    boolean vRespuesta1=false, vRespuesta2=false, vRespuesta3=false, vRespuesta4=false; 
                    // Se invoca la clase donde están los métodos para verificar las preguntas
                    Selecciones s = new Selecciones();
        try {
            // Con esto se verifica si las respuestas son correctas
            vRespuesta1 = s.VRespuesta1(pregunta1, respuesta1);
            vRespuesta2 = s.VRespuesta2(pregunta2, respuesta2);
            vRespuesta3 = s.VRespuesta3(pregunta3, respuesta3);
            vRespuesta4 = s.VRespuesta4(pregunta4, respuesta4);
        } catch (SQLException ex) {
            Logger.getLogger(RecupContra.class.getName()).log(Level.SEVERE, null, ex);
        }
                              // Todas las respuestas deben ser correctas para que nos deje cambiar la contraseña
            if(vRespuesta1 == true & vRespuesta2 == true & vRespuesta3 == true & vRespuesta4 == true){
            etiquetaRincorrecta1.setVisible(false);
            etiquetaRincorrecta2.setVisible(false);
            etiquetaRincorrecta3.setVisible(false);
            etiquetaRincorrecta4.setVisible(false);
            etiquetaRcorrecta1.setVisible(true);
            etiquetaRcorrecta2.setVisible(true);
            etiquetaRcorrecta3.setVisible(true);
            etiquetaRcorrecta4.setVisible(true);
                JOptionPane.showMessageDialog(rootPane, "Todas las respuestas son correctas", "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
                etiquetaPregunta1.setVisible(false);
                etiquetaPregunta2.setVisible(false);
                etiquetaPregunta3.setVisible(false);
                etiquetaPregunta4.setVisible(false);
                    campoRespuesta1.setVisible(false);
                    campoRespuesta2.setVisible(false);
                    campoRespuesta3.setVisible(false);
                    campoRespuesta4.setVisible(false);
                        botonVrespuestas.setVisible(false);
            etiquetaContraseña.setVisible(true);
            etiquetaContraseña2.setVisible(true);
            campoContraseña.setVisible(true);
            campoContraseña2.setVisible(true);
            botonActualizar.setVisible(true);
            botonActualizar.setEnabled(false);
            etiquetaRcorrecta1.setVisible(false);
            etiquetaRcorrecta2.setVisible(false);
            etiquetaRcorrecta3.setVisible(false);
            etiquetaRcorrecta4.setVisible(false);
            // Le damos la bienvenida al usuario porque se ha identificado
            ArrayList<Usuario> usuarios = new ArrayList();
            String username;
                                                try {
                                                    usuarios = s.VnomUsuario();
                                                } catch (SQLException ex) {
                                                    Logger.getLogger(RecupContra.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                    // Contamos los usuarios
                    int tUsuarios = usuarios.size();
                    if(tUsuarios > 1){
                        JOptionPane.showMessageDialog(rootPane, "Existe más de un usuario registrado en el sistema", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }
                    else if(tUsuarios < 1){
                        JOptionPane.showMessageDialog(rootPane, "No se encontró ningún usuario registrado", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }
                    else{
                        username = usuarios.get(0).getNomUsuario();
                        etiquetaBienvenida.setText("¡Bienvenido " + username + "!");
                    }
            }
                // Alguna de las respuestas es incorrecta
                else{
                        if(vRespuesta1 != true){
                                etiquetaRcorrecta1.setVisible(false);
                                etiquetaRincorrecta1.setVisible(true);
                        }else{
                                etiquetaRcorrecta1.setVisible(true);
                                etiquetaRincorrecta1.setVisible(false);
                        }
                        if(vRespuesta2 != true){
                                etiquetaRcorrecta2.setVisible(false);
                                etiquetaRincorrecta2.setVisible(true);
                        }else{
                                etiquetaRcorrecta2.setVisible(true);
                                etiquetaRincorrecta2.setVisible(false);
                        }
                        if(vRespuesta3 != true){
                                etiquetaRcorrecta3.setVisible(false);
                                etiquetaRincorrecta3.setVisible(true);
                        }else{
                                etiquetaRcorrecta3.setVisible(true);
                                etiquetaRincorrecta3.setVisible(false);
                        }
                        if(vRespuesta4 != true){
                                etiquetaRcorrecta4.setVisible(false);
                                etiquetaRincorrecta4.setVisible(true);
                        }else{
                                etiquetaRcorrecta4.setVisible(true);
                                etiquetaRincorrecta4.setVisible(false);
                        }
                JOptionPane.showMessageDialog(rootPane, "Verifique que haya escrito correctamente sus respuestas,\n" +
                                " recuerde que aunque lo sean, si escribió una letra, signo, número, o espacio de más el sistema lo tomará como incorrecto"
                        , "Verifique sus respuestas", JOptionPane.ERROR_MESSAGE);
            }
            
    }//GEN-LAST:event_botonVrespuestasActionPerformed

    private void campoRespuesta1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRespuesta1KeyReleased

                                            respuesta1 = campoRespuesta1.getText();
                                            respuesta2 = campoRespuesta2.getText();
                                            respuesta3 = campoRespuesta3.getText();
                                            respuesta4 = campoRespuesta4.getText();
                
                boolean vRespuesta1=false; 
                // Se invoca la clase donde están los métodos para verificar las preguntas
                Selecciones s = new Selecciones();
                try {
                    // Con esto se verifica si las respuestas son correctas
                    vRespuesta1 = s.VRespuesta1(pregunta1, respuesta1);
                } catch (SQLException ex) {
                    Logger.getLogger(RecupContra.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(vRespuesta1 == true){
                    campoRespuesta1.setEnabled(false);
                    etiquetaRcorrecta1.setVisible(true);
                    etiquetaRincorrecta1.setVisible(false);
                }
                
                if(this.etiquetaRcorrecta1.isVisible() & this.etiquetaRcorrecta2.isVisible() & this.etiquetaRcorrecta3.isVisible()
                                                                                                    & this.etiquetaRcorrecta4.isVisible()){
                        JOptionPane.showMessageDialog(rootPane, "Todas sus respuestas son correctas", "¡Muy bien!", JOptionPane.INFORMATION_MESSAGE);
                                etiquetaRincorrecta1.setVisible(false);
                                etiquetaRincorrecta2.setVisible(false);
                                etiquetaRincorrecta3.setVisible(false);
                                etiquetaRincorrecta4.setVisible(false);
                                etiquetaRcorrecta1.setVisible(true);
                                etiquetaRcorrecta2.setVisible(true);
                                etiquetaRcorrecta3.setVisible(true);
                                etiquetaRcorrecta4.setVisible(true);
                                    etiquetaPregunta1.setVisible(false);
                                    etiquetaPregunta2.setVisible(false);
                                    etiquetaPregunta3.setVisible(false);
                                    etiquetaPregunta4.setVisible(false);
                                        campoRespuesta1.setVisible(false);
                                        campoRespuesta2.setVisible(false);
                                        campoRespuesta3.setVisible(false);
                                        campoRespuesta4.setVisible(false);
                                            botonVrespuestas.setVisible(false);
                                etiquetaContraseña.setVisible(true);
                                etiquetaContraseña2.setVisible(true);
                                campoContraseña.setVisible(true);
                                campoContraseña2.setVisible(true);
                                botonActualizar.setVisible(true);
                                botonActualizar.setEnabled(false);
                                etiquetaRcorrecta1.setVisible(false);
                                etiquetaRcorrecta2.setVisible(false);
                                etiquetaRcorrecta3.setVisible(false);
                                etiquetaRcorrecta4.setVisible(false);
                                // Le damos la bienvenida al usuario porque se ha identificado
                                ArrayList<Usuario> usuarios = new ArrayList();
                                String username;
                                                                    try {
                                                                        usuarios = s.VnomUsuario();
                                                                    } catch (SQLException ex) {
                                                                        Logger.getLogger(RecupContra.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                        // Contamos los usuarios
                                        int tUsuarios = usuarios.size();
                                        if(tUsuarios > 1){
                                            JOptionPane.showMessageDialog(rootPane, "Existe más de un usuario registrado en el sistema", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                                            dispose();
                                        }
                                        else if(tUsuarios < 1){
                                            JOptionPane.showMessageDialog(rootPane, "No se encontró ningún usuario registrado", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                                            dispose();
                                        }
                                        else{
                                            username = usuarios.get(0).getNomUsuario();
                                            etiquetaBienvenida.setText("¡Bienvenido " + username + "!");
                                        }
                }
                
                if (!respuesta1.isEmpty() & !respuesta2.isEmpty() & !respuesta3.isEmpty() & !respuesta4.isEmpty()) {
                        botonVrespuestas.setEnabled(true);                          
                } else{
                        botonVrespuestas.setEnabled(false);
                }
                
    }//GEN-LAST:event_campoRespuesta1KeyReleased

    private void campoRespuesta3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRespuesta3KeyReleased
        
                                            respuesta1 = campoRespuesta1.getText();
                                            respuesta2 = campoRespuesta2.getText();
                                            respuesta3 = campoRespuesta3.getText();
                                            respuesta4 = campoRespuesta4.getText();
                                            
                boolean vRespuesta3=false; 
                // Se invoca la clase donde están los métodos para verificar las preguntas
                Selecciones s = new Selecciones();
                try {
                    // Con esto se verifica si las respuestas son correctas
                    vRespuesta3 = s.VRespuesta3(pregunta3, respuesta3);
                } catch (SQLException ex) {
                    Logger.getLogger(RecupContra.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(vRespuesta3 == true){
                    campoRespuesta3.setEnabled(false);
                    etiquetaRcorrecta3.setVisible(true);
                    etiquetaRincorrecta3.setVisible(false);
                }
                
                if(this.etiquetaRcorrecta1.isVisible() & this.etiquetaRcorrecta2.isVisible() & this.etiquetaRcorrecta3.isVisible()
                                                                                                    & this.etiquetaRcorrecta4.isVisible()){
                        JOptionPane.showMessageDialog(rootPane, "Todas sus respuestas son correctas", "¡Muy bien!", JOptionPane.INFORMATION_MESSAGE);
                                etiquetaRincorrecta1.setVisible(false);
                                etiquetaRincorrecta2.setVisible(false);
                                etiquetaRincorrecta3.setVisible(false);
                                etiquetaRincorrecta4.setVisible(false);
                                etiquetaRcorrecta1.setVisible(true);
                                etiquetaRcorrecta2.setVisible(true);
                                etiquetaRcorrecta3.setVisible(true);
                                etiquetaRcorrecta4.setVisible(true);
                                    etiquetaPregunta1.setVisible(false);
                                    etiquetaPregunta2.setVisible(false);
                                    etiquetaPregunta3.setVisible(false);
                                    etiquetaPregunta4.setVisible(false);
                                        campoRespuesta1.setVisible(false);
                                        campoRespuesta2.setVisible(false);
                                        campoRespuesta3.setVisible(false);
                                        campoRespuesta4.setVisible(false);
                                            botonVrespuestas.setVisible(false);
                                etiquetaContraseña.setVisible(true);
                                etiquetaContraseña2.setVisible(true);
                                campoContraseña.setVisible(true);
                                campoContraseña2.setVisible(true);
                                botonActualizar.setVisible(true);
                                botonActualizar.setEnabled(false);
                                etiquetaRcorrecta1.setVisible(false);
                                etiquetaRcorrecta2.setVisible(false);
                                etiquetaRcorrecta3.setVisible(false);
                                etiquetaRcorrecta4.setVisible(false);
                                // Le damos la bienvenida al usuario porque se ha identificado
                                ArrayList<Usuario> usuarios = new ArrayList();
                                String username;
                                                                    try {
                                                                        usuarios = s.VnomUsuario();
                                                                    } catch (SQLException ex) {
                                                                        Logger.getLogger(RecupContra.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                        // Contamos los usuarios
                                        int tUsuarios = usuarios.size();
                                        if(tUsuarios > 1){
                                            JOptionPane.showMessageDialog(rootPane, "Existe más de un usuario registrado en el sistema", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                                            dispose();
                                        }
                                        else if(tUsuarios < 1){
                                            JOptionPane.showMessageDialog(rootPane, "No se encontró ningún usuario registrado", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                                            dispose();
                                        }
                                        else{
                                            username = usuarios.get(0).getNomUsuario();
                                            etiquetaBienvenida.setText("¡Bienvenido " + username + "!");
                                        }
                }
                
                if (!respuesta1.isEmpty() & !respuesta2.isEmpty() & !respuesta3.isEmpty() & !respuesta4.isEmpty()) {
                        botonVrespuestas.setEnabled(true);                          
                } else{
                        botonVrespuestas.setEnabled(false);
                }
                
    }//GEN-LAST:event_campoRespuesta3KeyReleased

    private void campoRespuesta2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRespuesta2KeyReleased
        
                                            respuesta1 = campoRespuesta1.getText();
                                            respuesta2 = campoRespuesta2.getText();
                                            respuesta3 = campoRespuesta3.getText();
                                            respuesta4 = campoRespuesta4.getText();
                                            
                boolean vRespuesta2=false; 
                // Se invoca la clase donde están los métodos para verificar las preguntas
                Selecciones s = new Selecciones();
                try {
                    // Con esto se verifica si las respuestas son correctas
                    vRespuesta2 = s.VRespuesta2(pregunta2, respuesta2);
                } catch (SQLException ex) {
                    Logger.getLogger(RecupContra.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(vRespuesta2 == true){
                    campoRespuesta2.setEnabled(false);
                    etiquetaRcorrecta2.setVisible(true);
                    etiquetaRincorrecta2.setVisible(false);
                }
                
                if(this.etiquetaRcorrecta1.isVisible() & this.etiquetaRcorrecta2.isVisible() & this.etiquetaRcorrecta3.isVisible()
                                                                                                    & this.etiquetaRcorrecta4.isVisible()){
                        JOptionPane.showMessageDialog(rootPane, "Todas sus respuestas son correctas", "¡Muy bien!", JOptionPane.INFORMATION_MESSAGE);
                                etiquetaRincorrecta1.setVisible(false);
                                etiquetaRincorrecta2.setVisible(false);
                                etiquetaRincorrecta3.setVisible(false);
                                etiquetaRincorrecta4.setVisible(false);
                                etiquetaRcorrecta1.setVisible(true);
                                etiquetaRcorrecta2.setVisible(true);
                                etiquetaRcorrecta3.setVisible(true);
                                etiquetaRcorrecta4.setVisible(true);
                                    etiquetaPregunta1.setVisible(false);
                                    etiquetaPregunta2.setVisible(false);
                                    etiquetaPregunta3.setVisible(false);
                                    etiquetaPregunta4.setVisible(false);
                                        campoRespuesta1.setVisible(false);
                                        campoRespuesta2.setVisible(false);
                                        campoRespuesta3.setVisible(false);
                                        campoRespuesta4.setVisible(false);
                                            botonVrespuestas.setVisible(false);
                                etiquetaContraseña.setVisible(true);
                                etiquetaContraseña2.setVisible(true);
                                campoContraseña.setVisible(true);
                                campoContraseña2.setVisible(true);
                                botonActualizar.setVisible(true);
                                botonActualizar.setEnabled(false);
                                etiquetaRcorrecta1.setVisible(false);
                                etiquetaRcorrecta2.setVisible(false);
                                etiquetaRcorrecta3.setVisible(false);
                                etiquetaRcorrecta4.setVisible(false);
                                // Le damos la bienvenida al usuario porque se ha identificado
                                ArrayList<Usuario> usuarios = new ArrayList();
                                String username;
                                                                    try {
                                                                        usuarios = s.VnomUsuario();
                                                                    } catch (SQLException ex) {
                                                                        Logger.getLogger(RecupContra.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                        // Contamos los usuarios
                                        int tUsuarios = usuarios.size();
                                        if(tUsuarios > 1){
                                            JOptionPane.showMessageDialog(rootPane, "Existe más de un usuario registrado en el sistema", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                                            dispose();
                                        }
                                        else if(tUsuarios < 1){
                                            JOptionPane.showMessageDialog(rootPane, "No se encontró ningún usuario registrado", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                                            dispose();
                                        }
                                        else{
                                            username = usuarios.get(0).getNomUsuario();
                                            etiquetaBienvenida.setText("¡Bienvenido " + username + "!");
                                        }
                }
                
                if (!respuesta1.isEmpty() & !respuesta2.isEmpty() & !respuesta3.isEmpty() & !respuesta4.isEmpty()) {
                        botonVrespuestas.setEnabled(true);                          
                } else{
                        botonVrespuestas.setEnabled(false);
                }
                
    }//GEN-LAST:event_campoRespuesta2KeyReleased

    private void campoRespuesta4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRespuesta4KeyReleased
        
                                            respuesta1 = campoRespuesta1.getText();
                                            respuesta2 = campoRespuesta2.getText();
                                            respuesta3 = campoRespuesta3.getText();
                                            respuesta4 = campoRespuesta4.getText();
                                            
                boolean vRespuesta4=false; 
                // Se invoca la clase donde están los métodos para verificar las preguntas
                Selecciones s = new Selecciones();
                try {
                    // Con esto se verifica si las respuestas son correctas
                    vRespuesta4 = s.VRespuesta4(pregunta4, respuesta4);
                } catch (SQLException ex) {
                    Logger.getLogger(RecupContra.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(vRespuesta4 == true){
                    campoRespuesta4.setEnabled(false);
                    etiquetaRcorrecta4.setVisible(true);
                    etiquetaRincorrecta4.setVisible(false);
                }
                
                if(this.etiquetaRcorrecta1.isVisible() & this.etiquetaRcorrecta2.isVisible() & this.etiquetaRcorrecta3.isVisible()
                                                                                                    & this.etiquetaRcorrecta4.isVisible()){
                        JOptionPane.showMessageDialog(rootPane, "Todas sus respuestas son correctas", "¡Muy bien!", JOptionPane.INFORMATION_MESSAGE);
                                etiquetaRincorrecta1.setVisible(false);
                                etiquetaRincorrecta2.setVisible(false);
                                etiquetaRincorrecta3.setVisible(false);
                                etiquetaRincorrecta4.setVisible(false);
                                etiquetaRcorrecta1.setVisible(true);
                                etiquetaRcorrecta2.setVisible(true);
                                etiquetaRcorrecta3.setVisible(true);
                                etiquetaRcorrecta4.setVisible(true);
                                    etiquetaPregunta1.setVisible(false);
                                    etiquetaPregunta2.setVisible(false);
                                    etiquetaPregunta3.setVisible(false);
                                    etiquetaPregunta4.setVisible(false);
                                        campoRespuesta1.setVisible(false);
                                        campoRespuesta2.setVisible(false);
                                        campoRespuesta3.setVisible(false);
                                        campoRespuesta4.setVisible(false);
                                            botonVrespuestas.setVisible(false);
                                etiquetaContraseña.setVisible(true);
                                etiquetaContraseña2.setVisible(true);
                                campoContraseña.setVisible(true);
                                campoContraseña2.setVisible(true);
                                botonActualizar.setVisible(true);
                                botonActualizar.setEnabled(false);
                                etiquetaRcorrecta1.setVisible(false);
                                etiquetaRcorrecta2.setVisible(false);
                                etiquetaRcorrecta3.setVisible(false);
                                etiquetaRcorrecta4.setVisible(false);
                                // Le damos la bienvenida al usuario porque se ha identificado
                                ArrayList<Usuario> usuarios = new ArrayList();
                                String username;
                                                                    try {
                                                                        usuarios = s.VnomUsuario();
                                                                    } catch (SQLException ex) {
                                                                        Logger.getLogger(RecupContra.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                        // Contamos los usuarios
                                        int tUsuarios = usuarios.size();
                                        if(tUsuarios > 1){
                                            JOptionPane.showMessageDialog(rootPane, "Existe más de un usuario registrado en el sistema", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                                            dispose();
                                        }
                                        else if(tUsuarios < 1){
                                            JOptionPane.showMessageDialog(rootPane, "No se encontró ningún usuario registrado", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                                            dispose();
                                        }
                                        else{
                                            username = usuarios.get(0).getNomUsuario();
                                            etiquetaBienvenida.setText("¡Bienvenido " + username + "!");
                                        }
                }
                
                if (!respuesta1.isEmpty() & !respuesta2.isEmpty() & !respuesta3.isEmpty() & !respuesta4.isEmpty()) {
                        botonVrespuestas.setEnabled(true);                          
                } else{
                        botonVrespuestas.setEnabled(false);
                }
                
    }//GEN-LAST:event_campoRespuesta4KeyReleased

    private void etiquetaRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etiquetaRegresarMouseClicked
        Login l = new Login();
        l.setVisible(true);
        dispose();
    }//GEN-LAST:event_etiquetaRegresarMouseClicked

    private void campoContraseñaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoContraseñaKeyReleased
        if (evt.getSource() == campoContraseña) {
                                            passw = campoContraseña.getText();
                                            passw2 = campoContraseña2.getText();
                if (!passw.isEmpty() & !passw2.isEmpty()) {
                        botonActualizar.setEnabled(true);
                } else{
                        botonActualizar.setEnabled(false);
                }
        }
    }//GEN-LAST:event_campoContraseñaKeyReleased

    private void campoContraseña2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoContraseña2KeyReleased
        if (evt.getSource() == campoContraseña2) {
                                            passw = campoContraseña.getText();
                                            passw2 = campoContraseña2.getText();
                if (!passw.isEmpty() & !passw2.isEmpty()) {
                        botonActualizar.setEnabled(true);
                } else{
                        botonActualizar.setEnabled(false);
                }
        }
    }//GEN-LAST:event_campoContraseña2KeyReleased

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
                                            passw = campoContraseña.getText();
                                            passw2 = campoContraseña2.getText();
        if(!passw2.equals(passw)){
            campoContraseña.setText("");
            campoContraseña2.setText("");
            JOptionPane.showMessageDialog(rootPane, "Las contraseñas no coinciden, verifique que las haya escrito correctamente", "Las contraseñas no coinciden", JOptionPane.ERROR_MESSAGE);
            
        }else{
            Updates upd = new Updates();
            boolean verificador = false;
            verificador = upd.ActualizarC(passw);
                if(verificador != true){
                    JOptionPane.showMessageDialog(rootPane, "La contraseña no se ha actualizado correctamente, intentelo de nuevo," + 
                                                                        " si persiste el problema contacte al desarrollador de la aplicación", "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                } 
                else{
                    int conf = JOptionPane.showConfirmDialog(rootPane, 
                            "Está apunto de modificar la contraseña, ¿desea continuar?", "¿Desea confinuar?", JOptionPane.OK_CANCEL_OPTION);
                    if(conf == 0){
                        Login l = new Login();
                        l.setVisible(true);
                        dispose();
            JOptionPane.showMessageDialog(rootPane, "La contraseña se ha actualizado correctamente, ahora puede acceder a su cuenta", "¡Éxito!", 
                    JOptionPane.INFORMATION_MESSAGE);
                    }
                }
        }
                                            
    }//GEN-LAST:event_botonActualizarActionPerformed

    private void botonActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualizarMouseEntered
        passw = campoContraseña.getText();
        passw2 = campoContraseña2.getText();
        
        if(botonActualizar.isEnabled() == true){
            if(passw.isEmpty() | passw2.isEmpty()){
                botonActualizar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_botonActualizarMouseEntered

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonVrespuestas;
    private javax.swing.JPasswordField campoContraseña;
    private javax.swing.JPasswordField campoContraseña2;
    private javax.swing.JTextField campoRespuesta1;
    private javax.swing.JTextField campoRespuesta2;
    private javax.swing.JTextField campoRespuesta3;
    private javax.swing.JTextField campoRespuesta4;
    private javax.swing.JLabel etiquetaBienvenida;
    private javax.swing.JLabel etiquetaContraseña;
    private javax.swing.JLabel etiquetaContraseña2;
    private javax.swing.JLabel etiquetaMensaje1;
    private javax.swing.JLabel etiquetaMensaje2;
    private javax.swing.JLabel etiquetaPregunta1;
    private javax.swing.JLabel etiquetaPregunta2;
    private javax.swing.JLabel etiquetaPregunta3;
    private javax.swing.JLabel etiquetaPregunta4;
    private javax.swing.JLabel etiquetaRcorrecta1;
    private javax.swing.JLabel etiquetaRcorrecta2;
    private javax.swing.JLabel etiquetaRcorrecta3;
    private javax.swing.JLabel etiquetaRcorrecta4;
    private javax.swing.JLabel etiquetaRegresar;
    private javax.swing.JLabel etiquetaRincorrecta1;
    private javax.swing.JLabel etiquetaRincorrecta2;
    private javax.swing.JLabel etiquetaRincorrecta3;
    private javax.swing.JLabel etiquetaRincorrecta4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
