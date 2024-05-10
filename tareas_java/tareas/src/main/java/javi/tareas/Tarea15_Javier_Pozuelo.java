
package javi.tareas;

import java.util.Scanner;


public class Tarea15_Javier_Pozuelo {

    static String correo_user,nombre, dominio, extension, errorMSG;
    
    public static void main(String[] args) {
        input_correo();
    }
    
    
    public static void input_correo(){
        boolean correct_correo = false;
        boolean nombre_correcto = false;
        Scanner input_correo = new Scanner(System.in);
        while(!correct_correo){
            System.out.println("Introduce tu correo:");
            correo_user = input_correo.next();

            //Para obtener el nombre del correo
            nombre = "";
            dominio = "";
            extension = "";
            boolean pasado_arroba = false;
            boolean pasado_dom = false;
            if(arroba_checker(correo_user) && punto_checker(correo_user, true)){
                for(int i = 0; i < correo_user.length(); i++){
                    if(correo_user.charAt(i) == '@' && !pasado_arroba){
                        pasado_arroba = true;
                        i++;
                    }
                    if(!pasado_arroba){
                        nombre = nombre + correo_user.charAt(i);
                    }
                    if(correo_user.charAt(i) == '.' && pasado_arroba && !pasado_dom){
                        pasado_dom = true;
                        i++;
                    }
                    if(pasado_arroba && !pasado_dom){
                        dominio = dominio + correo_user.charAt(i);
                    }
                    if(pasado_arroba && pasado_dom){
                        extension = extension + correo_user.charAt(i);
                    }
                }
                correct_correo = true;
            }
            else if(!arroba_checker(correo_user)){
                if(!punto_checker(correo_user, false)){
                    System.out.println("Hace falta un arroba y un punto para definir la extension.");
                }
                else{
                    System.out.println("Hace falta un arroba.");
                }
            }
            else if(!punto_checker(correo_user, false)){
                System.out.println("Falta un punto para definir la extension.");
            }
        }
        
        if(nombre_revision(nombre)){
            if(dominio_revision(dominio)){
                if(extension_revision(extension)){
                    System.out.println("Correo correcto.");
                }
                else{
                    System.out.println(errorMSG);
                    input_correo();
                }
            }
            else{
                System.out.println(errorMSG);
                input_correo();
            }
        }
        else{
            System.out.println(errorMSG);
            input_correo();
        }
        
        
    }
    
    public static boolean arroba_checker(String correo){
        for(int i = 0; i<correo.length(); i++){
            if(correo.charAt(i) == '@'){
                return true;
            }
        }
        return false;
    }
    public static boolean punto_checker(String correo, boolean preArroba){
        //La variable preArroba es para comprobar si tiene punto teniendo en cuenta el arroba previo o no
        boolean tiene_arroba = false;
        if(preArroba){
            for(int i = 0; i<correo.length(); i++){
                if(correo.charAt(i) == '@'){
                    tiene_arroba = true;
                }
                if(correo.charAt(i) == '.' && tiene_arroba){
                    return true;
                }
            }
            return false;
        }
        else{
            for(int i = 0; i<correo.length(); i++){
                if(correo.charAt(i) == '.'){
                    return true;
                }
            }
            return false;
        }
        
    }
    
    public static boolean nombre_revision(String nombre){
        if(!Character.isDigit(nombre.charAt(0))){
            if(nombre.length() <= 10){
                return true;
            }
            else{
                errorMSG = "La primera seccion del correo es demasiado larga (max. 10 caracteres).";
                return false;
            }
        }
        else{
            errorMSG = "La primera letra no puede ser un número.";
            return false;
        }
    }
    
    public static boolean dominio_revision(String dominio){
        for(int i = 0; i < dominio.length(); i++){
            if(Character.isDigit(dominio.charAt(i))){
                errorMSG = "El dominio no puede tener numeros.";
                return false;
            }
        }
        return true;
    }
    
    public static boolean extension_revision(String ext){
        if(ext.length() <= 3 && ext.length() >= 2){
            return true;
        }
        else{
            errorMSG = "La extension solo puede tener 2 o 3 letras.";
            return false;
        }
    }
    
    
    
    
}
