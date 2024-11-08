/**
 * Esta clase lee una matriz traida desde un documeto .txt que contiene las distancias de
 * distintos paises de europa y su funcion es encontrar la ruta mas corta de un pais a otro. 
 * 
 * @Eder Ceballos 
 * @4.2
 */
import java.io.*;
import java.util.*;

public class Rutas {
    static final int infinito = 999999; /** Representa "infinito"*/
    private int[][] Distancia; /**Almacena la distancia mas corta por cada par de pais*/ 
    private int[][] predecesores; /** predecesores de cada nodo en Distancia*/
    private Map<String, Integer> indices; /** Asignamos un indice de la matriz a y ponemos el pais cmo clave*/
    private Map<Integer, String> indiceAPais; /** Convertimos el indice en nobres de paises*/

    /**Metodo constructor el cual inicializa el Map definido como indices y se le asigna una clave
       a cada indice*/
    public Rutas(String fileName) throws IOException {
        indices = new HashMap<>();
        indices.put("España", 0);
        indices.put("Francia", 1);
        indices.put("Alemania", 2);
        indices.put("Italia", 3);
        indices.put("Reino Unido", 4);
        indices.put("Países Bajos", 5);
        indices.put("Bélgica", 6);
        indices.put("Suiza", 7);
        indices.put("Polonia", 8);
        indices.put("Grecia", 9);
        
        /**inicializamos el Map indice a pais y realizamos un for que sera de tipo del metodo
           Map.Entry que es una interfaz que dice que cada indice debe tener su clave asignada
           y le decimos que obtenga los conjuntos clave-valor de indices con el metodo entry.set
           ya luego vamos agregando las claves de indiceAPais que seran los indices de la matriz 
           y su valor sera la clave que devuelva indice en este caso el nombre de los paises, esto
           con el fin de invertir lo que se hizo en indices*/
        indiceAPais = new HashMap<>();
        for (Map.Entry<String, Integer> entry : indices.entrySet()) {
            indiceAPais.put(entry.getValue(), entry.getKey());
        }
        /**
           Lee la matriz de distancias desde el archivo especificado en
           el parámetro fileName, utilizando el método LeerMatriz.*/
        Distancia = LeerMatriz(fileName);
        /**
         * Inicializar la matriz de predecesores utilizando el método initializePredecessors
           */
        predecesores = new int[Distancia.length][Distancia.length];
        initializepredecesores();
        OrdenDePredecesores();
    }

    /** Lee la matriz desde un archivo de texto y la convierte en una matriz de enteros*/
    private int[][] LeerMatriz(String fileName) throws IOException {
        /**iniciamos una lista de arrays dinamicos de enteros*/
        List<int[]> matrix = new ArrayList<>();
        
        /**iniciamos un trry(Manejo de errores) y al tiempo inicializamos el BufferedReader para 
           leer el archivo que le pasamos como parametro*/
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            /**creamos un String para asignarle como valor las lineas leidas de br mierntras
               aun haya lineas por leer*/
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split("\\s+"); /** line.trim().split("\\s+") 
                elimina espacios adicionales al principio y al final, y luego divide la línea 
                en partes usando espacios en blanco como delimitador.*/
                
                /**creamos un array de enteros row para guardar los datos de cada fila de la
                   matriz*/
                int[] row = new int[values.length];
                
                /**Recorremos cada valor de values. Si el valor es "INF", asignamos 
                 * el valor infinito (999999) en row[i]. Esto indica que no hay conexión directa.*/
                for (int i = 0; i < values.length; i++) {
                    /**Si el valor no es "infinito", lo convertimos a un entero con 
                     * Integer.parseInt(values[i]) y lo asignamos a row[i].*/
                    row[i] = values[i].equals("infinito") ? infinito : Integer.parseInt(values[i]);
                }
                /**agregamos la fila que acabamos de obtener al array matrix*/
                matrix.add(row);
            }
        }

        /**crea un array bidimensional
         * (int[][] Distancia) con el número de filas igual al tamaño de matrix.*/
        int[][] Distancia = new int[matrix.size()][matrix.size()];
        
        /**recorremos la longitud total del array de filas y vamos agregando elemento por elemento
           a la matriz Distancia*/
        for (int i = 0; i < matrix.size(); i++) {
            Distancia[i] = matrix.get(i);
        }

        return Distancia;
    }

    /**Este método configura una matriz llamada predecesores, donde cada elemento predecesores[i][j]
     * representa el nodo (país) previo a j en la ruta más corta desde el nodo i.
     * La matriz predecesores facilita el seguimiento de la ruta de cada nodo a otro al mantener
     * el nodo anterior en el camino más corto.*/
    private void initializepredecesores() {
        /**guardamos la longitud de la matriz que almacena las Distancias*/
        int n = Distancia.length;
        
        /**recorre todos los pares de nodos (i, j) en la matriz.*/
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                /**validamos que i y j no sean iguales ya que significa que estamos en origen,
                   confirmamos que la pocision [i][j] no sea infinito osea que tenga un camino directo*/
                if (i != j && Distancia[i][j] != infinito) {
                    predecesores[i][j] = i; /** El predecesor inicial es el nodo de origen*/
                } else {
                    predecesores[i][j] = -1; /** -1 indica que no hay predecesor*/
                }
            }
        }
    }

    private void OrdenDePredecesores() {
        /**guardamos la longitud de la matriz que almacena las Distancias*/
        int n = Distancia.length;
        
        /**iniciamos 3 for todos con la misma estructura, uno simbolizara el punto de origen,
           otro el punto final y el otro un punto intermedio por donde podria pasar*/
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    /**primero validamos que la distancia desde origen hasta punto medio [i][k], y
                       de punto medio a final [k][j] no sean infinito, luego validamos si 
                       la suma de la distancia del origen al punto medio mas la
                       distancia del punto medio al final es mejor a ir directamente del
                       origen al final [i][k]+[k][j]<[i][j]?*/
                    if (Distancia[i][k] != infinito && Distancia[k][j] != infinito && Distancia[i][k] + Distancia[k][j] < Distancia[i][j]) {
                        Distancia[i][j] = Distancia[i][k] + Distancia[k][j];/**Actualizamos la distancia
                        más corta desde i a j con la nueva distancia que pasa por k.*/
                        predecesores[i][j] = predecesores[k][j]; /** Actualizamos el predecesor de
                        j en la ruta desde i. Esto establece el nodo previo en el
                        camino más corto actual desde i a j pasando por k.*/
                    }
                }
            }
        }
    }

    /**recibe como parametros tu pais de origen y el pais de destino para convertirlos en indices
       y posteriormente retornar su distancia*/
    public int Distancias(String paisOrigen, String paisFinal) {
        /**Almacenamos los indices de inicio y Final, acudiendo al Map indices, en el cual
           se definieron los indices asociados a una clave de referencia que son los nombres de los
           paises definidos en el constructor "Rutas"*/
        Integer inicio = indices.get(paisOrigen);
        Integer Final = indices.get(paisFinal);
        /**Verificamos que inicio y Final si existan como clave asignada*/
        if (inicio == null || Final == null) {
            throw new IllegalArgumentException("País no encontrado.");
        }
        return Distancia[inicio][Final];
    }

    public List<String> rutaInversa(String paisOrigen, String paisFinal) {
        /**Almacenamos los indices de inicio y Final, acudiendo al Map indices, en el cual
           se definieron los indices asociados a una clave de referencia que son los nombres de los
           paises definidos en el constructor "Rutas"*/
        Integer inicio = indices.get(paisOrigen);
        Integer Final = indices.get(paisFinal);

        /**Verificamos que inicio y Final si existan como clave asignada*/
        if (inicio == null || Final == null) {
            throw new IllegalArgumentException("País no encontrado.");
        }

        /**Creamos una lista de Strings (recorrido) para almacenar la ruta más corta de los países
         * que componen el camino.*/
        List<String> recorrido = new ArrayList<>();
        
        /**Almacenamos el indice del destino*/
        int at = Final;
         /**verificamos que el destino ea diferente al inicio*/
        while (at != inicio) {
            /**agregamos a "recorrido" la posicion de destino*/
            recorrido.add(indiceAPais.get(at));
            /**Actualizamos at para que sea el predecesor del nodo actual (at), 
             * lo que permite ir "retrocediendo" desde el destino hasta el inicio.*/
            at = predecesores[inicio][at];
            
            /**verificamos la existencia de un predecesor*/
            if (at == -1) {
                return Collections.emptyList(); /** No hay ruta*/
            }
        }
        /**agregamos el punto de origen al final del recorrido*/
        recorrido.add(indiceAPais.get(inicio));
        
        /**invertimos el array para que quede la ruta a recorrer para llegar de inicio a final*/
        Collections.reverse(recorrido);
        return recorrido;
    }
}
