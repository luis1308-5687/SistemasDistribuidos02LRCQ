/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejemplos;
import java.util.Scanner;
//TAREA 1
/**
 *
 * @author luisc
 */
public class Ejemplos {
    public static void main(String[] args) {
        int op = 0;
        int n = 0;
        Scanner sc = new Scanner(System.in);
        while(op!=5){
            System.out.println("1 Introducir n");
            System.out.println("2 Fibonacci");
            System.out.println("3 Factorial");
            System.out.println("4 Sumatoria");
            System.out.println("5 Salir");
            System.out.println("Introduzca opcion");
            op = sc.nextInt();
            switch(op){
                case 1:
                    System.out.println("Introduzca el valor de n");
                    n = sc.nextInt();
                    break;
                case 2:
                    System.out.println("El fibonacci es "+fibonacci(n));
                    break;
                case 3:
                    System.out.println("El factorial es "+factorial(n));
                    break;
                case 4:
                    System.out.println("La sumatoria es "+sumatoria(n));
                    
            }
        }  
    }
    
    public static int fibonacci(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
         
    }
    
    public static int factorial(int n){
        int res = 1;
        for(int i = 1; i <= n; i++){
            res *= i;
        }
        return res;
    }
    
    public static int sumatoria(int n){
        int res = 0;
        for(int i = 1; i<= n; i++){
            res += i;
        }
        return res;
        
    }
}
