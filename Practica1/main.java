
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class main {
    public static void main(String[] args) {
        
        Amigos amigo1 = new Amigos(12345, "EDER");
        Amigos amigo2 = new Amigos(54321, "Pablo");
        Amigos amigo3 = new Amigos(15243, "Pedrito");
        
 
        ColeccionAmigos.agregarAmigos(amigo1);
        ColeccionAmigos.agregarAmigos(amigo2);
        ColeccionAmigos.agregarAmigos(amigo3);

        System.out.println("Amigos ordenados por cédula:");
        ColeccionAmigos.ordenarPorCedula();
        System.out.println();
        System.out.println();
        int cedulaBuscada = 12345;
        ColeccionAmigos.buscarAmigoPorCedula(cedulaBuscada);
    }
}

