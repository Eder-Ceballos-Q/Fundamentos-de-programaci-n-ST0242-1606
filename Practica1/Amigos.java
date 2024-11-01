
/**
 * Write a description of class Amigos here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Amigos
{   
    int Cedula;
    String nombre;
  
    public Amigos(int Cedula, String nombre){
        this.Cedula = Cedula;
        this.nombre = nombre;
    }
 
    public int getCedula(){
        return Cedula;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void mostrarAmigos(){
        System.out.println("nombre: " + nombre + " Cedula " + Cedula);
    }
}
