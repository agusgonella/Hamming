package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main{
    static FileReader r_archive;
    static FileWriter w_auxiliar;
    static BufferedWriter b_w_auxiliar;
    static BufferedReader b_r_archive;
    static ArrayList<Character> lista = new ArrayList<>();
    
    public static File leerArchivo(File archive){ //se lee la info que esta en el archivo archive

        try{

            r_archive = new FileReader(archive);
            b_r_archive = new BufferedReader(r_archive);
                
                int caracterL = r_archive.read();
                
                while(caracterL != -1){
                    char caracter = (char) caracterL;
                    System.out.println(caracter);
                    lista.add(caracter);
                    caracterL = r_archive.read(); //lee los demas caracteres
                }

            b_r_archive.close();
            
            System.out.println(lista);

                
        }catch(IOException e){
                System.out.println("El archivo no existe\n");
        }
        return null;
    }

    //Se podria tambien pasar a la lista cada bit individualmente pero nos quedarian listas enormess

    public static ArrayList<String> aBinario(ArrayList<Character> lista)throws IOException{
        ArrayList<String> listaAuxiliar = new ArrayList<>();
        for (Character caracter : lista) {
            int nroAscii = caracter.charValue();
            String n = Integer.toBinaryString(nroAscii);
            listaAuxiliar.add(n);
        }
        System.out.println("Lista despues de aplicar aBinario:\n");
        System.out.println(listaAuxiliar);
        return listaAuxiliar;
    }

    //Funcion extra por las dudas, se puede cambiar y adaptarla a nuestras necesidades mas adelante

    public static void aAuxiliar(String ruta, String contenido) throws IOException{
        File auxiliar = new File(ruta);

        if (!auxiliar.exists()) {
            auxiliar.createNewFile();
        }

        w_auxiliar = new FileWriter(auxiliar);
        b_w_auxiliar = new BufferedWriter(w_auxiliar);
        b_w_auxiliar.write(contenido);
        b_w_auxiliar.close();

    }

    //Funciones que nos serviran más adelante

    public static File crearArchivos(File entrada, int tipo, int bloque){
        //TIPO 0 -> HE ->Hamming con Error en Archivo
        //TIPO 1 -> DE ->Archivo Decodificado con Error sobre Archivo
        //TIPO 2 -> DC ->Archivo Decodificado Corregido sobre Archivo

        String url = generarNombres(entrada, tipo, bloque); //cada archivo tiene un nombre diferente segun el tipo y la cant de bits que se toman por bloque
        String ruta = entrada.getParent()+"\\"+url;
        File fileOutput = new File(ruta); 
        try {
            if (fileOutput.createNewFile()) { //se crea el archivo con su debido nombre
                System.out.println("Archivo creado: " + fileOutput.getName());
            }
            else {
                fileOutput.delete();
                fileOutput = new File(ruta);
                fileOutput.createNewFile();
            }
        } catch (IOException ex) {
            System.out.println("No se pudo crear el archivo\n");
        }
        return fileOutput;
    }
    
    public static String generarNombres(File entrada, int tipo, int bloque){
        //TIPO 0 -> HE ->Hamming con Error en Archivo
        //TIPO 1 -> DE ->Archivo Decodificado con Error sobre Archivo
        //TIPO 2 -> DC ->Archivo Decodificado Corregido sobre Archivo
        String nombre = entrada.getName().split("\\.")[0];
        switch(tipo){
            case 0 ->{return nombre.concat(".HE"+String.valueOf(bloque));}
            case 1 ->{return nombre.concat(".DE"+String.valueOf(bloque));}
            case 2 ->{return nombre.concat(".DC"+String.valueOf(bloque));}
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        int select = 0;
        Scanner scan = new Scanner(System.in);

	    System.out.println("MENU\n\n");
        System.out.println("1- CARGAR UN ARCHIVO\n");
        System.out.println("2- PROTEGER ARCHIVO\n");
        System.out.println("3- INTRODUCIR ERRORES\n");
        System.out.println("4- DESPROTEGER ARCHIVO SIN CORREGIR\n");
        System.out.println("5- DESPROTEGER ARCHIVO CORRIGIENDO\n");
        System.out.println("6- SALIR\n");

        select = scan.nextInt();

        System.out.println("\n -----------------");

        switch (select){

            //Deberia cambiar leerArchivo porque funciona bien pero lee tambien los saltos de linea y eso
            //en realidad no se como seria, queria preguntartelo porque por ahi vos lo entendias mas
            //pero no lo  cambie por las dudas. Es lo unico que funciona medio dudoso jajan´t

            //ABRO ARCHIVO -> LEO ARCHIVO -> CADA UNO DE LOS CARACTERES EL ARCHIVO LO PONGO EN UN ARRAY LIST
            //SERIA ALGO ASI: | H | O | L | A | _ | C | O | M | O | _ | E | S | T | A | S |
            //LUEGO ESTA INFORMACION PODRIA PASARLA A BINARIO Y DEJARLA EN EL ARRAYLIST
            //ENTONCES, POR EJ: | H | O | L | I | S | 
            // | 1101000 | 1101111 | 1101100 | 1101001 | 1110011 |
            // Hice una funcion que te pasa la info de un array list a string y la escribe en un archivo pero
            //ni idea, la hice por que pinto. Se puede adaptar o cambiarla
            //A continuacion deberiamos ir agarrando los bits de cada una de las posiciones del arraylist
            // | 1101000 | ...... |
            // Agarramos los 4 primeros y comenzamos hamming..
            // _ _ 1 _ 1 0 1 
            //Una vez que tenemos todo el hamming realizado, aplicamos los errores y mandamos a un archivo tipo 0
            //Decodificamos el archivo CON el error y mandamos a un archivo tipo 1
            //Decodificamos el archivo SIN el error y mandamos a un archivo tipo 2

            //No quiero seguir programando porque quiero que veamos algunas cosas juntos, cuando puedas hablame
            //y lo vemos

            case 1:{                                                                                                                                                                                                                                               
                select = 0;
                System.out.println("CARGADO");
                File archive = new File("src/com/company/archivo.txt");
                ArrayList<String> listaAux= new ArrayList<>();
                String rutaAuxiliar = "src/com/company/auxiliar.txt";

                leerArchivo(archive); //se lee el archivo archive
                listaAux = aBinario(lista); //se pasa a binario el arraylist lista 

                StringBuilder sb = new StringBuilder(); //Pasamos la lista a string
                for(String s : listaAux){
                    sb.append(s);
                    sb.append("\t");
                }
                //System.out.println(sb.toString());

                aAuxiliar(rutaAuxiliar, sb.toString()); //escribimos en el archivo auxiliar el string sb(que es el arraylist lista)

                break;
            }
            case 2:{
                int select1 = 0;
                System.out.println("MENU SELECCIONAR PROTECCION\n");
                System.out.println("1- BLOQUE DE 8 bits\n");
                System.out.println("2- BLOQUE DE 256 bits\n");
                System.out.println("3- BLOQUE DE 8192 bits\n");
                System.out.println("4- BLOQUE DE 262144 bits\n");
                System.out.println("5- SALIR\n");
                select = scan.nextInt();

                switch (select1){
                    case 1:{
                        System.out.println("8");
                        break;
                    }
                    case 2:{
                        System.out.println("256");
                        break;
                    }
                    case 3:{
                        System.out.println("8192");
                        break;
                    }
                    case 4:{
                        System.out.println("262144");
                        break;
                    }
                    case 5:{
                        break;
                    }
                }
                break;
            }
            case 3:{
                select = 0;
                System.out.println("COLOCAR ERROR");
                break;
            }
            case 4:{
                select = 0;
                System.out.println("DESPROTEGIDO SIN CORREGIR");
                break;
            }
            case 5:{
                select = 0;
                System.out.println("DESPROTEGIDO CORREGIDO");
                break;
            }

        }

    }

    
}



