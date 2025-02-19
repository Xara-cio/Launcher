/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package paquete;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static paquete.JSONNavigator.jsonBase;

/**
 *
 * @author User
 */
public class Main extends javax.swing.JFrame {
    public static Main instance; // para acceder al main desde cualquier lugar
    
    // Crear una instancia de Dimension
    Dimension dimension = new Dimension(100, 100); // ancho y alto     

    /**
     * Creates new form Main
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
       
    public Main() throws IOException, JSONException {
        instance = this; // Guardar la instancia al iniciar
        
        // Cargar el JSON en memoria al iniciar la aplicación
        JSONNavigator.cargarJSON("src/Resources/BaseDeDatos.json");   

        initComponents();
        
//        Game gameInstance = new Game(0, 0);
        
//        // Cargar el JSON en memoria al iniciar la aplicación
//        Json.cargarJSON("src/Resources/BaseDeDatos.json");
//
//        // Imprimir en consola los grados y juegos
//        Json.imprimirJSON();
             
        // Ejemplo de acceso a diferentes datos en distintos momentos
//        Object titulo = imprimirTitulo(String.valueOf(gameInstance.indiceHome), String.valueOf(gameInstance.indiceGame));
//        Object ruta = imprimirRuta("0", "1");    // Segundo juego del primer grado
//        Object imagen = imprimirImagen("0", "0");  // Imagen del primer juego del primer grado
//        Object descripcion = imprimirDescripcion("0", "1");  // Descripción del segundo juego del primer grado
       
        Home hm = new Home(Panelbody, 0);
        Utilidades.ShowPanel(Panelbody, hm);
        
        iconos = new JLabel[] {icono1, icono2, icono3, icono4, icono5, icono6, icono7, icono8, icono9, icono10, icono11, icono12, icono13, icono14};
        rutas = Utilidades.CreateStringList("src/images/pics", "LauncherButton", "png", iconos.length).toArray(String[]::new);

        for (int i = 0; i < rutas.length; i++) {
            final int finalIndex = i;
            //asignar imagen de ruta correspondiente con el label correspondiente
            Utilidades.SetImageLabel(iconos[i], rutas[i], dimension, true);
            //asignar eventos a cada label correspondiente
            
            // Agregar MouseListener
            iconos[i].addMouseListener(new MouseAdapter() {                
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Acción al hacer clic
                    Home hm = new Home(Panelbody, finalIndex);
                    Utilidades.ShowPanel (Panelbody, hm);
                    // Aquí puedes añadir la lógica que necesites
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Acción al pasar el mouse sobre el JLabel
                    Utilidades.SetImageLabel(iconos[finalIndex], rutas[finalIndex], true);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    // Acción al salir el mouse del JLabel
                    Utilidades.SetImageLabel(iconos[finalIndex], rutas[finalIndex], dimension, true);
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    // Acción al presionar el mouse sobre el JLabel
                    Utilidades.SetImageLabel(iconos[finalIndex], rutas[finalIndex], true);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    // Acción al liberar el mouse sobre el JLabel
                    Utilidades.SetImageLabel(iconos[finalIndex], rutas[finalIndex], dimension, true);
                }
            });
        }

        // Mostrar el fondobase
        FondoBase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); //establece la alineación horizontal del contenido dentro del JLabel
        FondoBase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Fondo base.png"))); // Aquí se está estableciendo una imagen para el JLabel FondoBase
        FondoBase.setBounds(0, 0, 1920, 1080); // establece la posición y el tamaño del JLabel en el contenedor que lo contiene (en este caso, PanelBase).
        PanelBase.add(FondoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080)); // agrega el JLabel FondoBase al panel PanelBase 
        
               
//        // Para leer el archivo localmente dentro del proyecto
//        // Pasamos directamente la ruta del archivo
//        String fileContent = Utilidades.readFileAsString("src/Resources/BaseDeDatos.json");
//        
//        // Imprimir el directorio de trabajo actual
//        System.out.println("Directorio de trabajo actual: " + System.getProperty("user.dir"));
//
//        // Mostrar el contenido del archivo
//        System.out.println("\nContenido del archivo json:");
//        System.out.println(fileContent);
//        
//        // Lee el contenido del archivo JSON
//        String filePath = "src/Resources/BaseDeDatos.json"; // Asegúrate de que la ruta sea correcta
//        String content = Utilidades.readFileAsString(filePath);
//
//        // Imprime el contenido para depuración
//        System.out.println("Contenido del archivo JSON:");
//        System.out.println(content);

//        // Leer el JSON desde el archivo
//        String filePath = "src/Resources/BaseDeDatos.json";
//        String content = Utilidades.readFileAsString(filePath);
//        
//        System.out.println("Contenido del archivo JSON:");
//        System.out.println(content);        
//
//        // Verificar si el contenido está vacío
//        if (content == null || content.isEmpty()) {
//            System.err.println("Error: El archivo JSON está vacío o no se pudo leer.");
//            return;
//        }        
//
//        // Procesar el JSON
//        try {
//            // Convertir el contenido en un objeto JSON
//            JSONObject jsonBase = new JSONObject(content);
//
//            // Acceder al array "grados"
//            JSONArray gradosArray = jsonBase.getJSONArray("grados");
//
//            // Recorrer los grados
//            for (int i = 0; i < gradosArray.length(); i++) {
//                JSONObject grado = gradosArray.getJSONObject(i);
//                System.out.println("\n Grado " + (i + 1) + ": " + grado.getString("nombre"));
//
//                // Acceder al array "juegos" dentro del grado
//                JSONArray juegosArray = grado.getJSONArray("juegos");
//
//                // Recorrer los juegos dentro del grado
//                for (int j = 0; j < juegosArray.length(); j++) {
//                    JSONObject juego = juegosArray.getJSONObject(j);
//                    System.out.println("Título: " + juego.getString("titulo"));
//                }
//            }
//        } catch (JSONException e) {
//            System.err.println("Error al parsear JSON: " + e.getMessage());
//        }
    }  
    

        /**
         * Método genérico para obtener información de un juego usando los índices actuales y la clave proporcionada.
         */
    public Object obtenerDatoJuego(String clave, String indiceHome, String indiceGame) {
        // Ahora no necesitas convertirlos, ya están en formato String
        Object dato = JSONNavigator.acceder("grados", indiceHome, "juegos", indiceGame, clave);

        // Imprime en consola el valor obtenido para depuración
        System.out.println("[" + indiceHome + "][" + indiceGame + "] " + clave + ": " + dato);

        // Devuelve el dato obtenido del JSON
        return dato;
    }
  
    
//    /**
//     * Método para imprimir el título de un juego.
//     */
//    public Object imprimirTitulo(String grado, String juego) {
//        Object titulo = JSONNavigator.acceder("grados", grado, "juegos", juego, "titulo");
//        System.out.println("Título del juego [" + grado + "][" + juego + "]: " + titulo);
//        return titulo;
//    }
//
//    /**
//     * Método para imprimir la ruta de un juego.
//     */
//    public Object imprimirRuta(String grado, String juego) {
//        Object ruta = JSONNavigator.acceder("grados", grado, "juegos", juego, "ruta");
//        System.out.println("Ruta del juego [" + grado + "][" + juego + "]: " + ruta);
//        return ruta;
//    }
//
//    /**
//     * Método para imprimir la imagen de un juego.
//     */
//    public Object imprimirImagen(String grado, String juego) {
//        Object imagen = JSONNavigator.acceder("grados", grado, "juegos", juego, "imagen");
//        System.out.println("Imagen del juego [" + grado + "][" + juego + "]: " + imagen);
//        return imagen;
//    }
//
//    /**
//     * Método para imprimir la descripción de un juego.
//     */
//    public Object imprimirDescripcion(String grado, String juego) {
//        Object descripcion = JSONNavigator.acceder("grados", grado, "juegos", juego, "descripcion");
//        System.out.println("Descripción del juego [" + grado + "][" + juego + "]: " + descripcion);
//        return descripcion;
//    }    
    
    
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelBase = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        icono1 = new javax.swing.JLabel();
        icono2 = new javax.swing.JLabel();
        icono3 = new javax.swing.JLabel();
        icono4 = new javax.swing.JLabel();
        icono5 = new javax.swing.JLabel();
        icono6 = new javax.swing.JLabel();
        icono7 = new javax.swing.JLabel();
        icono8 = new javax.swing.JLabel();
        icono9 = new javax.swing.JLabel();
        icono10 = new javax.swing.JLabel();
        icono11 = new javax.swing.JLabel();
        icono12 = new javax.swing.JLabel();
        icono13 = new javax.swing.JLabel();
        icono14 = new javax.swing.JLabel();
        Panelbody = new javax.swing.JPanel();
        FondoBase = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        PanelBase.setMaximumSize(new java.awt.Dimension(1920, 1080));
        PanelBase.setMinimumSize(new java.awt.Dimension(1920, 1080));
        PanelBase.setOpaque(false);
        PanelBase.setPreferredSize(new java.awt.Dimension(1920, 1080));
        PanelBase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setOpaque(false);
        Header.setLayout(new java.awt.GridLayout(1, 0));

        icono1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton0.png"))); // NOI18N
        icono1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono1);

        icono2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton1.png"))); // NOI18N
        icono2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono2);

        icono3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton2.png"))); // NOI18N
        icono3.setToolTipText("");
        icono3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono3);

        icono4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton3.png"))); // NOI18N
        icono4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono4);

        icono5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton4.png"))); // NOI18N
        icono5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono5);

        icono6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton5.png"))); // NOI18N
        icono6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono6);

        icono7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton6.png"))); // NOI18N
        icono7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono7);

        icono8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton7.png"))); // NOI18N
        icono8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono8);

        icono9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton8.png"))); // NOI18N
        icono9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono9);

        icono10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton9.png"))); // NOI18N
        icono10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono10);

        icono11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton10.png"))); // NOI18N
        icono11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono11);

        icono12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton11.png"))); // NOI18N
        icono12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono12);

        icono13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton12.png"))); // NOI18N
        icono13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono13);

        icono14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/LauncherButton13.png"))); // NOI18N
        icono14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header.add(icono14);

        PanelBase.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 1540, 130));

        Panelbody.setMaximumSize(new java.awt.Dimension(1560, 880));
        Panelbody.setMinimumSize(new java.awt.Dimension(1560, 880));
        Panelbody.setOpaque(false);
        Panelbody.setLayout(new java.awt.BorderLayout());
        PanelBase.add(Panelbody, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 1560, 880));

        FondoBase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FondoBase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Fondo base.png"))); // NOI18N
        FondoBase.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelBase.add(FondoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }                    
            }
        });
    }
      
    JLabel[] iconos;    
    String[] rutas;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FondoBase;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel PanelBase;
    public javax.swing.JPanel Panelbody;
    private javax.swing.JLabel icono1;
    private javax.swing.JLabel icono10;
    private javax.swing.JLabel icono11;
    private javax.swing.JLabel icono12;
    private javax.swing.JLabel icono13;
    private javax.swing.JLabel icono14;
    private javax.swing.JLabel icono2;
    private javax.swing.JLabel icono3;
    private javax.swing.JLabel icono4;
    private javax.swing.JLabel icono5;
    private javax.swing.JLabel icono6;
    private javax.swing.JLabel icono7;
    private javax.swing.JLabel icono8;
    private javax.swing.JLabel icono9;
    // End of variables declaration//GEN-END:variables
}
