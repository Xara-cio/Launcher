/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete;


// Importamos las clases necesarias para trabajar con JSON y manejar excepciones
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

/**
 *
 * @author User
 */
public class JSONNavigator {
    // Variable estática para almacenar el JSON cargado en memoria
    public static JSONObject jsonBase;

    /**
     * Método para cargar el JSON en memoria desde un archivo.
     * @param filePath Ruta del archivo JSON
     * @throws IOException Si ocurre un error al leer el archivo
     * @throws org.json.JSONException
     */
    public static void cargarJSON(String filePath) throws IOException, JSONException {
        // Lee el contenido del archivo JSON como una cadena de texto
        String content = Utilidades.readFileAsString(filePath);
        
        if (content == null || content.isEmpty()) {
        throw new JSONException("El archivo JSON está vacío o no se pudo leer.");
        }
        
        // Convierte el contenido en un objeto JSON y lo guarda en jsonBase
        jsonBase = new JSONObject(content);
        
        // Imprimir para verificar si se cargó bien
//        System.out.println("JSON cargado correctamente: " + jsonBase.toString(4));        
        
    }

    /**
     * Método para acceder dinámicamente a cualquier nivel del JSON.
     * @param claves Lista de claves que definen la ruta en la jerarquía JSON
     * @return Devuelve el valor encontrado o un mensaje de error si no existe
     */
    public static Object acceder(String... claves) {
        try {
            if (jsonBase == null) {
                return "Error: JSON no cargado.";
            }

            Object actual = jsonBase;
            for (String clave : claves) {
//                System.out.println("Intentando acceder a: " + clave + " en " + actual);

                if (actual instanceof JSONObject) {
                    JSONObject obj = (JSONObject) actual;
                    if (!obj.has(clave)) {
                        return "Error: Clave '" + clave + "' no encontrada en el objeto.";
                    }
                    actual = obj.get(clave);
                } else if (actual instanceof JSONArray) {
                    try {
                        int index = Integer.parseInt(clave);
                        JSONArray array = (JSONArray) actual;
                        if (index < 0 || index >= array.length()) {
                            return "Error: Índice fuera de rango '" + clave + "'";
                        }
                        actual = array.get(index);
                    } catch (NumberFormatException e) {
                        return "Error: Se esperaba un índice numérico en '" + clave + "'";
                    }
                } else {
                    return "Error: No se puede acceder más allá de '" + clave + "'";
                }
            }

            return actual;
        } catch (JSONException e) {
            return "Error de JSON: " + e.getMessage();
        }
    }
}
