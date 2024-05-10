
package javi.tareas;
import java.util.Scanner;

public class Tarea17_Javier_Pozuelo {
    static Scanner input_user = new Scanner(System.in);
    static float precio_final;
    static boolean max_cantidad;
    
    //variables ej 3
    static byte cantidad_productos;
    
    public static tarea17_producto_Javier_Pozuelo[] productos = new tarea17_producto_Javier_Pozuelo[3];
    
    static final float DESCUENTO1_VALOR = 0.1f;
    static final float DESCUENTO2_VALOR = 0.2f;
    static final float DESCUENTO3_VALOR = 0.3f;
    
    
    public static void main(String[] args) {
        max_cantidad = true;
        productos[0] = new tarea17_producto_Javier_Pozuelo("Melones", 5);
        productos[1] = new tarea17_producto_Javier_Pozuelo("Peras", 3);
        productos[2] = new tarea17_producto_Javier_Pozuelo("Pasta", 7);
        compra_productos();
        //impresora(ej_3());
    }
    
    public static void compra_productos(){
        System.out.println("Actualmente tenemos los siguientes productos(pulse el numero correspondiente):");
        for(int i = 0; i<productos.length; i++){
            System.out.println(i+". "+productos[i].nombre + ": " + productos[i].precio + "€");
        }
        System.out.println("\n");
        boolean item_selected = false;
        while(!item_selected){
            Byte selected_ID = input_user.nextByte();
            
            if(selected_ID >= 0 && selected_ID <= productos.length){
                impresora(ej_3(), selected_ID);
                item_selected = true;
            }
            else{
                System.out.println("Numero erroneo, pruebe de nuevo:");
            }
        }
    }
    
    
    public static float ej_3(){
            while(max_cantidad){
                
                System.out.println("¿Cuantos productos quieres comprar?(máx. 120):");
                cantidad_productos = input_user.nextByte();
                
                if(cantidad_productos == 1 || cantidad_productos == 2){
                    return DESCUENTO1_VALOR;
                }
                else if(cantidad_productos >= 3 && cantidad_productos <= 5){
                    return DESCUENTO2_VALOR;
                }
                else if(cantidad_productos >= 6 && cantidad_productos <= 120){
                    return DESCUENTO3_VALOR;
                }
                else if(cantidad_productos == 0){
                    precio_final = 0f;
                    max_cantidad = false;
                }
                else{
                    System.out.println("Cantidad máxima de productos superada, introduce una cantidad válida.");
                }
            }
            return 0f;
    }
    
    public static void impresora(float descuento, Byte ID){
        precio_final = (productos[ID].precio - (productos[ID].precio * descuento)) * cantidad_productos;
        System.out.println("Tu pedido de " + productos[ID].nombre + " sale a un total de: " + precio_final + "€ por tus " + cantidad_productos + " productos.");
        max_cantidad = false;
    }
    
}
