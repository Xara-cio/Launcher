
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package paquete;

import java.awt.Dimension; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author User
 */
public class Game extends javax.swing.JPanel {
    // Declaración de variables    
    private int indiceActual = 0; // Índice de la imagen actual
    public int indiceGame = 0; // Índice del juego
    public int indiceHome = 0; // Índice de la página de inicio
    private  String key; // Clave para obtener las imágenes
    private final String[] imagenes; // Array de imágenes   
    private JLabel[] puntosCarrusel; // Array de JLabel para los puntos del carrusel
 
    
    private final Dimension dimensionflecha = new Dimension(22, 35); // Ejemplo de ancho y alto 
    private final Dimension dimensionflechamax = new Dimension(32, 45); 
    private final Dimension dimensionP = new Dimension(15, 15);
    private final Dimension dimensionPmax = new Dimension(20, 20);    
    private final Dimension dimensionB = new Dimension (253, 44);
    private final Dimension dimensionBmax = new Dimension (303, 50);       
            
    /** Creates new form Home
     * @param indiceGame
     * @param indiceHome
     **/
    public Game(int indiceGame, int indiceHome) {
        initComponents();  // Inicializa los componentes de la interfaz
            
        this.indiceGame = indiceGame; // Asigna el índice del juego
        this.indiceHome = indiceHome; // Asigna el índice de la página de inicio (grado)
                             
        imagenes = obtenerImagenesPorCategoria(); // Obtiene las imágenes por categoría
        
        JLabel[] puntosCarrusel = new JLabel[] {Punto1, Punto2, Punto3, Punto4, Punto5};         

        // Inicializa manualmente el array con los JLabel creados en el diseño
        inicializarPuntosCarrusel();
        actualizarImagen(); // Actualiza la imagen actual
        actualizarPuntosCarrusel(indiceActual); // Actualiza los puntos del carrusel
        
        // Actualizar el título y la descripcion en el JLabel
        actualizarTitulo();
        actualizarDescripcion(); 
        
                                     
        // Agregar MouseListener para accion clicked boton comenzar
        botoncomenzar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                Home hm = new Home(Main.instance.Panelbody, indiceHome);
//                Utilidades.ShowPanel(Main.instance.Panelbody, hm); 
                System.out.println("Botón Comenzar pulsado");  // Verifica si el evento se dispara
                try {
//                    // Ruta del archivo JSON
//                    String jsonPath = "src/Resources/BaseDeDatos.json";  // Asegúrate de que esta ruta es correcta
//                    System.out.println("Ruta del archivo JSON: " + jsonPath);  // Verifica la ruta del archivo JSON
//
//                    // Cargar JSON
//                    JSONNavigator.cargarJSON(jsonPath);
//                    System.out.println("JSON cargado exitosamente");  // Verifica que el JSON se cargue correctamente
//
//                    // Obtener la ruta del ejecutable desde el JSON
//                    Object rutaObj = JSONNavigator.acceder("grados", String.valueOf(indiceHome), "juegos", String.valueOf(indiceGame), "ruta");
//                    System.out.println("Ruta del ejecutable: " + rutaObj);  // Verifica si la ruta se obtiene correctamente desde el JSON

                    Object rutaObj = Main.instance.obtenerDatoJuego("ruta", String.valueOf(indiceHome), String.valueOf(indiceGame));

                    if (rutaObj instanceof String) {
                        String rutaEjecutable = (String) rutaObj;
                        System.out.println("Ruta Ejecutable: " + rutaEjecutable);  // Verifica que la ruta del ejecutable sea válida
                         // Verificación si el archivo existe

                        // Verificar si el archivo existe antes de ejecutarlo
                        File archivo = new File(rutaEjecutable);
                        System.out.println("Ruta absoluta del ejecutable: " + archivo.getAbsolutePath());
                        if (archivo.exists()) {
                            System.out.println("El archivo existe, procediendo a ejecutar el juego.");  // Verifica si el archivo existe
                            // Ejecutar el juego
                            ProcessBuilder pb = new ProcessBuilder(rutaEjecutable);
                            pb.start();
                        } else {
                            System.out.println("El archivo no existe en la ruta: " + rutaEjecutable);  // Muestra un mensaje si el archivo no existe
                            JOptionPane.showMessageDialog(null, "El archivo no existe en la ruta: " + rutaEjecutable, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println("No se encontró la ruta del juego en el JSON");  // Muestra un mensaje si no se encuentra la ruta
                        JOptionPane.showMessageDialog(null, "No se encontró la ruta del juego en el JSON", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al ejecutar el juego: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(botoncomenzar, "src/images/pics/Comenzar.png", dimensionBmax, true); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(botoncomenzar, "src/images/pics/Comenzar.png", dimensionB, true); 
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(botoncomenzar, "src/images/pics/Comenzar.png", dimensionB, true); 
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(botoncomenzar, "src/images/pics/Comenzar.png", dimensionBmax, true); 
            }
        });
           
        //clicked puntos acorde con imagenes
        for (int i = 0; i < puntosCarrusel.length; i++) {
            final int indiceActualLocal = i;
            // Agregar MouseListener
            puntosCarrusel[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    indiceActual = indiceActualLocal;
                    actualizarImagen();
                    actualizarPuntosCarrusel(indiceActual);
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    Utilidades.SetImageLabel(puntosCarrusel[indiceActualLocal], "src/images/pics/PuntoCarruselFilled.png", dimensionPmax, true); 
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if (indiceActual != indiceActualLocal) {
                        Utilidades.SetImageLabel(puntosCarrusel[indiceActualLocal], "src/images/pics/PuntoCarruselEmpty.png", dimensionP, true); 
                    }
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    Utilidades.SetImageLabel(puntosCarrusel[indiceActualLocal], "src/images/pics/PuntoCarruselFilled.png", dimensionP, true);                    
                }
                
                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    if (indiceActual != indiceActualLocal) {
                        Utilidades.SetImageLabel(puntosCarrusel[indiceActualLocal], "src/images/pics/PuntoCarruselEmpty.png", dimensionPmax, true); 
                    }
                }
            });            
        }
        // Agregar MouseListener a la flecha izquierda
        flechaizquierda.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenAnterior();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(flechaizquierda, "src/images/pics/Flecha izquierda.png", dimensionflechamax, true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(flechaizquierda, "src/images/pics/Flecha izquierda.png", dimensionflecha, true);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(flechaizquierda, "src/images/pics/Flecha izquierda.png", dimensionflecha, true);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(flechaizquierda, "src/images/pics/Flecha izquierda.png", dimensionflechamax, true);
            }
        });

        // Agregar MouseListener a la flecha derecha
        flechaderecha.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                siguienteImagen();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(flechaderecha, "src/images/pics/Flecha derecha.png", dimensionflechamax, true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(flechaderecha, "src/images/pics/Flecha derecha.png", dimensionflecha, true);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(flechaderecha, "src/images/pics/Flecha derecha.png", dimensionflecha, true);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Utilidades.SetImageLabel(flechaderecha, "src/images/pics/Flecha derecha.png", dimensionflechamax, true);
            }
        });               
    }
    
    /**
     * Obtiene el título del juego desde Main y lo muestra en el JLabel "titulo"
     */
    private void actualizarTitulo() {
        if (Main.instance != null) { // Verificar si Main está inicializado
            Object tituloJuego = Main.instance.obtenerDatoJuego("titulo", String.valueOf(indiceHome), String.valueOf(indiceGame));
            titulo.setText(tituloJuego.toString()); // Asignar el título al JLabel
        } else {
            System.out.println("Error: Main.instance no está inicializado.");
        }
    }

    /**
     * Obtiene la descripción del juego desde Main y lo muestra en el JLabel "texto"
     */
    private void actualizarDescripcion() {
        if (Main.instance != null) { // Verificar si Main está inicializado       
            Object descripcionJuego = Main.instance.obtenerDatoJuego("descripcion", String.valueOf(indiceHome), String.valueOf(indiceGame));

            texto.setText(descripcionJuego.toString());
        } else {
            System.out.println("Error: Main.instance no está inicializado.");
        }
    }    
    
    private void inicializarPuntosCarrusel() {
        // Asigna manualmente los JLabel del diseño al array
        puntosCarrusel = new JLabel[5]; 
        puntosCarrusel[0] = Punto1;
        puntosCarrusel[1] = Punto2;
        puntosCarrusel[2] = Punto3;
        puntosCarrusel[3] = Punto4;
        puntosCarrusel[4] = Punto5;
    }    

    private String[] obtenerImagenesPorCategoria() {
        String[] array = new String[] {}; // Crea un array vacío
        //pasar los dos indices a mi bbdd para que me devuelva el string de la palabra clave
        
        // Llamamos a la función imprimirImagen desde Main para obtener la categoría
        Object categoria = Main.instance.obtenerDatoJuego("imagen", String.valueOf(indiceHome), String.valueOf(indiceGame));
        
            if (categoria != null) {
                // Asignamos el valor de la categoría obtenida a 'key'
                key = categoria.toString();  // Convertimos la categoría a String
                // Verificamos si la ruta de las imágenes existe
                File dir = new File("src/images/pics/");
                try {
                    if (!dir.exists() || !dir.isDirectory()) {
                        throw new FileNotFoundException("La ruta especificada no existe o no es un directorio válido.");
                }
                // Asignamos los valores para el array usando el key obtenido
                array = Utilidades.CreateStringList("src/images/pics/", key, "png", 5).toArray(String[]::new);

                    // Comprobamos si el array tiene imágenes
                    if (array.length == 0) {
                        System.out.println("No se encontraron imágenes para la categoría: " + key);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("No se encontró una categoría válida.");
            }
            return array;// Devuelve el array de imágenes
    }
        
    private void actualizarImagen() {
        Utilidades.SetImageLabel(carruselimages, imagenes[indiceActual], new Dimension(640,480), true);
    } // Actualiza la imagen del carrusel
    
    //actualiza los puntos del carrusel según el índice actual
        private void actualizarPuntosCarrusel(int indiceActual) {
        //System.out.println("Índice actual: " + indiceActual); // Depuración
        //System.out.println("Índice calculado: " + (indiceActual % puntosCarrusel.length)); // Depuración        

        // Recorre los JLabel (los puntos del carrusel)
        for (int i = 0; i < puntosCarrusel.length; i++) {

            // Comprueba si el índice actual coincide con el índice del punto
            if (i == indiceActual) {

                // Si el índice actual es igual al índice del punto, "punto relleno"
                //puntosCarrusel[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselFilled.png")));
                Utilidades.SetImageLabel(puntosCarrusel[i], "src/images/pics/PuntoCarruselFilled.png", dimensionP, true);
            } else {
                // Si no es el índice actual, "punto vacío"
                //puntosCarrusel[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselEmpty.png")));
                Utilidades.SetImageLabel(puntosCarrusel[i], "src/images/pics/PuntoCarruselEmpty.png", dimensionP, true);                   
            } 
        }
    }
    
    private void siguienteImagen() {
        if (indiceActual < imagenes.length - 1) { // Si no es la última imagen
            indiceActual++; // Incrementa el índice de la imagen actual           
        }
        else {
            indiceActual = 0;// Reinicia el índice a 0
        }
        actualizarImagen();// Actualiza la imagen
        actualizarPuntosCarrusel(indiceActual); // Actualiza los puntos del carrusel
    }

    private void imagenAnterior() {
        if (indiceActual > 0) { // Si no es la primera imagen
            indiceActual--; // Decrementa el índice de la imagen actual
        }
        else {
            indiceActual = imagenes.length - 1; // Establece el índice a la última imagen
        }
        actualizarImagen();
        actualizarPuntosCarrusel(indiceActual); // Actualiza los puntos del carrusel
    }
    
    
    
          
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botoncomenzar = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        texto = new javax.swing.JLabel();
        Punto1 = new javax.swing.JLabel();
        Punto2 = new javax.swing.JLabel();
        Punto4 = new javax.swing.JLabel();
        Punto5 = new javax.swing.JLabel();
        Punto3 = new javax.swing.JLabel();
        barrita = new javax.swing.JLabel();
        carruselimages = new javax.swing.JLabel();
        flechaizquierda = new javax.swing.JLabel();
        flechaderecha = new javax.swing.JLabel();
        fondocarrusel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1561, 878));
        setMinimumSize(new java.awt.Dimension(1561, 878));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1561, 878));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botoncomenzar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botoncomenzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Comenzar.png"))); // NOI18N
        botoncomenzar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botoncomenzar.setMaximumSize(new java.awt.Dimension(303, 50));
        botoncomenzar.setMinimumSize(new java.awt.Dimension(303, 50));
        botoncomenzar.setPreferredSize(new java.awt.Dimension(303, 50));
        botoncomenzar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botoncomenzarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botoncomenzarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botoncomenzarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                botoncomenzarMouseReleased(evt);
            }
        });
        add(botoncomenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 780, -1, -1));

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo.setText("Embarque y desembarque en helicoptero");
        titulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 600, 700, 40));

        texto.setForeground(new java.awt.Color(255, 255, 255));
        texto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        texto.setText("<html>La vista Game muestra un carrusel amplio y centrado en su parte superior, en él se muestran capturas del contenido del simulador, las imágenes podrán alternarse al interactuar con 2 botones de derecha e izquierda situados a los lados del carrusel.<br>La vista Game muestra un carrusel amplio y centrado en su parte superior, en él se muestran capturas del contenido del simulador, las imágenes podrán alternarse al interactuar con 2 botones de derecha e izquierda situados a los lados del carrusel.<br>La vista Game muestra un carrusel amplio y centrado en su parte superior, en él se muestran capturas del contenido del simulador, las imágenes podrán alternarse al interactuar con 2 botones de derecha e izquierda situados a los lados del carrusel, La vista Game muestra un carrusel amplio y centrado en su parte superior, en él se muestran capturas del contenido del simulador, las imágenes podrán alternarse al interactuar con 2 botones de derecha e izquierda situados a los lados del carrusel.</html>");
        texto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 650, 730, 170));

        Punto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Punto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselFilled.png"))); // NOI18N
        Punto1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Punto1.setMaximumSize(new java.awt.Dimension(20, 20));
        Punto1.setMinimumSize(new java.awt.Dimension(20, 20));
        Punto1.setPreferredSize(new java.awt.Dimension(20, 20));
        add(Punto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, -1, -1));

        Punto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Punto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselEmpty.png"))); // NOI18N
        Punto2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Punto2.setMaximumSize(new java.awt.Dimension(20, 20));
        Punto2.setMinimumSize(new java.awt.Dimension(20, 20));
        Punto2.setPreferredSize(new java.awt.Dimension(20, 20));
        add(Punto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 510, -1, -1));

        Punto4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Punto4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselEmpty.png"))); // NOI18N
        Punto4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Punto4.setMaximumSize(new java.awt.Dimension(20, 20));
        Punto4.setMinimumSize(new java.awt.Dimension(20, 20));
        Punto4.setPreferredSize(new java.awt.Dimension(20, 20));
        add(Punto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 510, -1, -1));

        Punto5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Punto5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselEmpty.png"))); // NOI18N
        Punto5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Punto5.setMaximumSize(new java.awt.Dimension(20, 20));
        Punto5.setMinimumSize(new java.awt.Dimension(20, 20));
        Punto5.setPreferredSize(new java.awt.Dimension(20, 20));
        add(Punto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 510, -1, -1));

        Punto3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Punto3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/PuntoCarruselEmpty.png"))); // NOI18N
        Punto3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Punto3.setMaximumSize(new java.awt.Dimension(20, 20));
        Punto3.setMinimumSize(new java.awt.Dimension(20, 20));
        Punto3.setPreferredSize(new java.awt.Dimension(20, 20));
        add(Punto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 510, -1, -1));

        barrita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barrita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Barrita aislada descripción.png"))); // NOI18N
        barrita.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(barrita, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, 60, 100));

        carruselimages.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carruselimages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Embarque0.png"))); // NOI18N
        carruselimages.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        carruselimages.setMaximumSize(new java.awt.Dimension(1150, 470));
        carruselimages.setMinimumSize(new java.awt.Dimension(1150, 470));
        carruselimages.setPreferredSize(new java.awt.Dimension(1150, 470));
        add(carruselimages, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 43, -1, -1));

        flechaizquierda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        flechaizquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Flecha izquierda.png"))); // NOI18N
        flechaizquierda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        flechaizquierda.setMaximumSize(new java.awt.Dimension(32, 45));
        flechaizquierda.setMinimumSize(new java.awt.Dimension(32, 45));
        flechaizquierda.setPreferredSize(new java.awt.Dimension(32, 45));
        flechaizquierda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flechaizquierdaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                flechaizquierdaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                flechaizquierdaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                flechaizquierdaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                flechaizquierdaMouseReleased(evt);
            }
        });
        add(flechaizquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, -1));

        flechaderecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        flechaderecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Flecha derecha.png"))); // NOI18N
        flechaderecha.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        flechaderecha.setMaximumSize(new java.awt.Dimension(32, 45));
        flechaderecha.setMinimumSize(new java.awt.Dimension(32, 45));
        flechaderecha.setPreferredSize(new java.awt.Dimension(32, 45));
        flechaderecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flechaderechaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                flechaderechaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                flechaderechaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                flechaderechaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                flechaderechaMouseReleased(evt);
            }
        });
        add(flechaderecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 270, -1, -1));

        fondocarrusel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondocarrusel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pics/Cuadrado fondo enfocado.png"))); // NOI18N
        fondocarrusel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fondocarrusel.setMaximumSize(new java.awt.Dimension(1150, 470));
        fondocarrusel.setMinimumSize(new java.awt.Dimension(1150, 470));
        fondocarrusel.setPreferredSize(new java.awt.Dimension(1150, 470));
        add(fondocarrusel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 43, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void flechaizquierdaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaizquierdaMouseEntered
//        Utilidades.SetImageLabel(flechaizquierda, "src/images/pics/Flecha izquierda.png", dimensionflechamax, true);
    }//GEN-LAST:event_flechaizquierdaMouseEntered

    private void flechaizquierdaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaizquierdaMouseExited
//       Utilidades.SetImageLabel(flechaizquierda, "src/images/pics/Flecha izquierda.png", dimensionflecha, true);
    }//GEN-LAST:event_flechaizquierdaMouseExited

    private void flechaderechaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaderechaMouseEntered
//        Utilidades.SetImageLabel(flechaderecha, "src/images/pics/Flecha derecha.png", dimensionflechamax, true);
    }//GEN-LAST:event_flechaderechaMouseEntered

    private void flechaderechaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaderechaMouseExited
//        Utilidades.SetImageLabel(flechaderecha, "src/images/pics/Flecha derecha.png", dimensionflecha, true);
    }//GEN-LAST:event_flechaderechaMouseExited

    private void flechaizquierdaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaizquierdaMouseClicked
//        imagenAnterior();
    }//GEN-LAST:event_flechaizquierdaMouseClicked

    private void flechaderechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaderechaMouseClicked
//       siguienteImagen();
    }//GEN-LAST:event_flechaderechaMouseClicked

    private void flechaizquierdaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaizquierdaMousePressed
//        Utilidades.SetImageLabel(flechaizquierda, "src/images/pics/Flecha izquierda.png", dimensionflecha, true);
    }//GEN-LAST:event_flechaizquierdaMousePressed

    private void flechaizquierdaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaizquierdaMouseReleased
//        Utilidades.SetImageLabel(flechaizquierda, "src/images/pics/Flecha izquierda.png", dimensionflechamax, true);
    }//GEN-LAST:event_flechaizquierdaMouseReleased

    private void flechaderechaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaderechaMousePressed
//       Utilidades.SetImageLabel(flechaderecha, "src/images/pics/Flecha derecha.png", dimensionflecha, true);
    }//GEN-LAST:event_flechaderechaMousePressed

    private void flechaderechaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaderechaMouseReleased
//        Utilidades.SetImageLabel(flechaderecha, "src/images/pics/Flecha derecha.png", dimensionflechamax, true);
    }//GEN-LAST:event_flechaderechaMouseReleased

    private void botoncomenzarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botoncomenzarMouseEntered
//       Utilidades.SetImageLabel(botoncomenzar, "src/images/pics/Comenzar.png", dimensionBmax, true);
    }//GEN-LAST:event_botoncomenzarMouseEntered

    private void botoncomenzarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botoncomenzarMouseExited
//       Utilidades.SetImageLabel(botoncomenzar, "src/images/pics/Comenzar.png", dimensionB, true);
    }//GEN-LAST:event_botoncomenzarMouseExited

    private void botoncomenzarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botoncomenzarMousePressed
//       Utilidades.SetImageLabel(botoncomenzar, "src/images/pics/Comenzar.png", dimensionB, true);
    }//GEN-LAST:event_botoncomenzarMousePressed

    private void botoncomenzarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botoncomenzarMouseReleased
//        Utilidades.SetImageLabel(botoncomenzar, "src/images/pics/Comenzar.png", dimensionBmax, true);
    }//GEN-LAST:event_botoncomenzarMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Punto1;
    private javax.swing.JLabel Punto2;
    private javax.swing.JLabel Punto3;
    private javax.swing.JLabel Punto4;
    private javax.swing.JLabel Punto5;
    private javax.swing.JLabel barrita;
    private javax.swing.JLabel botoncomenzar;
    private javax.swing.JLabel carruselimages;
    private javax.swing.JLabel flechaderecha;
    private javax.swing.JLabel flechaizquierda;
    private javax.swing.JLabel fondocarrusel;
    private javax.swing.JLabel texto;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables

}


