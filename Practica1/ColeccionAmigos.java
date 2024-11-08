
/**
 *
 * @author Eder Ceballos
 * @version 1
 */
public class ColeccionAmigos
{

    private static Amigos[] amigos = new Amigos[5];
    private static int Count = 0;
    
    public static void agregarAmigos(Amigos a){
        amigos[Count] = a;
        Count++;
    }
    
    public static void mostrarAmigos(){
        for(int i = 0; i < Count; i++){
            amigos[i].mostrarAmigos();
        }
    }
    
    public static void ordenarPorCedula() {
        for (int i = 0; i < Count - 1; i++) {
            int numMenor = i;

            for (int j = i + 1; j < Count; j++) {
                if (amigos[j].getCedula() < amigos[numMenor].getCedula()) {
                    numMenor = j;
                }
            }

            if (i != numMenor) {
                swap(amigos, i, numMenor);
            }
        }
        
        for (int i = 0; i < Count; i++) {
            amigos[i].mostrarAmigos();  
        }
    }

    static void swap(Amigos[] amigos, int i, int j) {
        Amigos temp = amigos[i];
        amigos[i] = amigos[j];
        amigos[j] = temp;
    }
    
    public static void buscarAmigoPorCedula(int cedula) {  
    int inicio = 0;
    int fin = Count - 1;

    while (inicio <= fin) {
        int medio = (inicio + fin) / 2;
        int cedulaMedio = amigos[medio].getCedula();

        if (cedulaMedio == cedula) {
            System.out.println("encontrado: " + amigos[medio].getNombre());
            return; 
        }

        if (cedulaMedio < cedula) {
            inicio = medio + 1;
        } else {
            fin = medio - 1;
        }
    }

    System.out.println("No se encontró un amigo con la cédula " + cedula);
}

}



