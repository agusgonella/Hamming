package com.company;

import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
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
            case 1:{
                select = 0;
                System.out.println("CARGADO");
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
                System.out.println("DESPROTEGIDO S CORREGIR");
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



