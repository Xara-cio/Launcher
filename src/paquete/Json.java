/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

/**
 *
 * @author User
 */
public class Json {
    private static JSONObject jsonBase; // Almacena el JSON en memoria

    /**
     * Carga el JSON desde el archivo y lo almacena en memoria.
     * @param filePath
     * @throws java.io.IOException
     */
    public static void cargarJSON(String filePath) throws IOException {
        String content = Utilidades.readFileAsString(filePath);

        // Mostrar el contenido del JSON
        System.out.println("Contenido del archivo JSON:");
        System.out.println(content);

        // Verificar si el contenido está vacío
        if (content == null || content.isEmpty()) {
            System.err.println("Error: El archivo JSON está vacío o no se pudo leer.");
            return;
        }

        try {
            // Convertir el contenido en un objeto JSON
            jsonBase = new JSONObject(content);
        } catch (JSONException e) {
            System.err.println("Error al parsear JSON: " + e.getMessage());
        }
    }
    
    /**
     * Imprime en consola todos los grados y sus juegos.
     */
    public static void imprimirJSON() {
        if (jsonBase == null) {
            System.err.println("Error: El JSON no ha sido cargado.");
            return;
        }

        try {
            // Acceder al array "grados"
            JSONArray gradosArray = jsonBase.getJSONArray("grados");

            // Recorrer los grados
            for (int i = 0; i < gradosArray.length(); i++) {
                JSONObject grado = gradosArray.getJSONObject(i);
                System.out.println("Grado " + (i + 1) + ": " + grado.getString("nombre"));

                // Acceder al array "juegos" dentro del grado
                JSONArray juegosArray = grado.getJSONArray("juegos");

                // Recorrer los juegos dentro del grado
                for (int j = 0; j < juegosArray.length(); j++) {
                    JSONObject juego = juegosArray.getJSONObject(j);
                    System.out.println("Título: " + juego.getString("titulo"));
                }
            }
        } catch (JSONException e) {
            System.err.println("Error al procesar JSON: " + e.getMessage());
        }
    }    
    
}
