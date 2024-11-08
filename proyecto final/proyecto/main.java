/**
 * Esta clase despliega todo lo necesario para que la clase "Rutas" funcione correctamente
 * y es la encargada de preguntar al usuario cual es su pais de origen y su pais de destino para 
 * buscar su camino en la clase "Rutas"
 * 
 * @Eder Ceballos 
 * @3.1
 */

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para calcular la ruta y distancia más corta entre dos países
 */
public class main {

    /**
     * Método principal que ejecuta el programa.
     * Permite al usuario ingresar un país de origen y destino, y calcula la distancia
     * y la ruta más corta entre ambos países, leyendo los datos de una matriz almacenada en un archivo.
     *
     * @param args Argumentos de línea de comando (no utilizados en este programa).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  /** Escáner para leer la entrada del usuario*/

        try {
            /** Crear instancia de la clase Rutas y leer la matriz de distancias desde un archivo*/
            Rutas r = new Rutas("matriz.txt");

            /** Solicitar al usuario el país de origen*/
            System.out.println("Ingrese el país de origen:");
            String paisOrigen = scanner.nextLine();

            /** Solicitar al usuario el país de destino*/
            System.out.println("Ingrese el país de destino:");
            String paisFinal = scanner.nextLine();

            /** Calcular la distancia más corta entre el país de origen y el de destino*/
            int distance = r.Distancias(paisOrigen, paisFinal);

            /** Verificar si existe una ruta entre los dos países*/
            if (distance == Rutas.infinito) {
                System.out.println("No hay ruta disponible de " + paisOrigen + " a " + paisFinal + ".");
            } else {
                /** Mostrar la distancia más corta*/
                System.out.println("\nDistancia más corta de " + paisOrigen + " a " + paisFinal + ": " + distance + " km");

                /** Obtener la ruta más corta y mostrar los países en el camino*/
                List<String> path = r.rutaInversa(paisOrigen, paisFinal);
                System.out.println("Ruta más corta de " + paisOrigen + " a " + paisFinal + ":");
                for (String country : path) {
                    System.out.print(country + " ");
                }
                System.out.println();  /** Imprimir salto de línea al final de la ruta*/
            }

        } catch (IOException e) {
            /** Manejar error si ocurre un problema al leer el archivo de matriz de distancias*/
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            /** Manejar error si el usuario ingresa un país no válido*/
            System.out.println(e.getMessage());
        }
    }
}
