
/**
 * Write a description of class OrdenamientoBusqueda here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class OrdenamientoBusqueda
{
    static int N = 130000;
    static int [] arregloGrande;
    
    static int [] selectionSort(int [] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            int posicionMenor = i;
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] < nums[posicionMenor]) {
                    posicionMenor = j;
                }
            }
            if(i != posicionMenor) {
                swap(nums, i, posicionMenor);
            }
        }
        return nums;
    }
    
    static void swap(int [] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;        
    }
    
    static void llenarArregloGrande(int n) {
        arregloGrande = new int[n];
        for(int i = 0; i < arregloGrande.length; i++) {
            arregloGrande[i] = i;
        }
        
        for(int i = 0; i < arregloGrande.length; i++) {
            int j = (int) Math.random() * arregloGrande.length;
            swap(arregloGrande, i, j);
        }
    }
    
    static void llamarSelectionSort(int n) {
        System.out.println("Tamaño: " + n);
        llenarArregloGrande(n);
        long begin = System.currentTimeMillis();
        selectionSort(arregloGrande);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
    
    static void rangoDeLongitud() {
        for(int i = 10000; i < 150000; i = i + 10000) {
            llamarSelectionSort(i);
        }
    }
    
    static int busquedaBinaria(int [] nums, int elem) {
        int izq = 0;
        int der = nums.length - 1;
        while(izq <= der) {
            int medio = (izq + der) / 2;
            if(nums[medio] == elem) {
                return medio;
            }
            if(nums[medio] < elem) {
                izq = medio + 1;
            } else {
                der = medio - 1;
            }
        }
        return -1;
    }
    
    static int [] selectionSort2(int [] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            int posicionMenor = i;
            for(int j = i + 1; j < nums.length; j++) {
                if( nums[j] < nums[posicionMenor]) {
                    posicionMenor = j;
                }
            }
            swap2(nums, i, posicionMenor);
        }
        return nums;
    }
    
    static void swap2(int [] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    static int [] bubbleSort(int [] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = 0; j < nums.length - i - 1; j++) {
                if(nums[j] > nums[j+1]) {
                    swap2(nums, j, j+1);
                }
            }
        }
        return nums;
    }
}
