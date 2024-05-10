
package javi.tareas;


public class tarea16_Javier_Pozuelo {
    public static tarea16_vehiculo_Javier_Pozuelo[] dad = new tarea16_vehiculo_Javier_Pozuelo[3];
    
    
    public static void main(String[] args) {
        dad[0] = new tarea16_vehiculo_Javier_Pozuelo("Ferrari GT", "Ferrari", 2, 185000);
        dad[1] = new tarea16_vehiculo_Javier_Pozuelo("Lambo Huracan", "Lamborghini", 2, 755000);
        dad[2] = new tarea16_vehiculo_Javier_Pozuelo("corvette Stingray", "Chevrolet", 4, 2250000);
        
        for(int i = 0; i<dad.length; i++){
            System.out.println("Nombre: "+dad[i].nombre);
            System.out.println("Marca: "+dad[i].marca);
            System.out.println("Número de puertas: "+dad[i].num_puertas);
            System.out.println("Precio: "+dad[i].precio);
            System.out.println("");
        }
    }
    
}
