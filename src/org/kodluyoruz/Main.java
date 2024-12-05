package org.kodluyoruz;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int n ;
        System.out.println("Tablo boyutunu gir");
        Scanner scanner = new Scanner(System.in);
        n=scanner.nextInt();

        int userScore =0, pcScore =0;
        char[][] table = getNewTable(n);
        System.out.println(n + "x" + n + "lik bir tabloda oynayacaksın");


        char userLetter, pcLetter;
        boolean isUserTurn=new Random().nextBoolean();
        if(new Random().nextBoolean()){
            userLetter='S';
            pcLetter='O';
        }else {
            userLetter='O';
            pcLetter='S';
        }
        System.out.println("Senin Harfin:"+userLetter+" Bilgisayarın Harfi:"+pcLetter);


        do{
            if (isUserTurn) {
                table=userChoice(table,userLetter,n);
                int currentHit = currentHit(table,n,userScore+pcScore);
                userScore = userScore + currentHit;
                if (currentHit == 0) {
                    isUserTurn = !isUserTurn;
                }else{
                    System.out.println("Bildin, tekrar gir");
                }
            }else{
                table=pcChoice(table,pcLetter,n);
                int currentHit = currentHit(table,n,userScore+pcScore);
                pcScore = pcScore + currentHit;
                if (currentHit == 0) {
                    isUserTurn = !isUserTurn;
                }else{
                    System.out.println("Bilgisayar bildi, tekrar giriyor...");
                }
            }
            printTable(table,n);
            System.out.println("Senin Score:"+userScore+" Bilgisayarın Score:"+pcScore);
        }while(!Complete(table,n));
        printResult(userScore,pcScore);
    }

    public static void printResult(int userScore,int pcScore){
        if(userScore > pcScore){
            System.out.println("Kazandın");
        } else if(userScore < pcScore){
            System.out.println("Kaybettin");
        } else {
            System.out.println("Berabere");
        }
    }

    public static int currentHit(char[][] table, int n,int currentScore){
        return tableHit(table,n) - currentScore;
    }

    public static int tableHit(char[][] table, int n){
        int score=0;
        for (int i = 1; i < n-1; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i-1][j] == 'S' && table[i][j] == 'O' && table[i+1][j] == 'S' ) {
                    score++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n-1; j++) {
                if (table[i][j-1] == 'S' && table[i][j] == 'O' && table[i][j+1] == 'S' ) {
                    score++;
                }
            }
        }

        for (int i = 1; i < n-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (table[i+1][j-1] == 'S' && table[i][j] == 'O' && table[i-1][j+1] == 'S' ) {
                    score++;
                }
            }
        }

        for (int i = 1; i < n-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (table[i-1][j-1] == 'S' && table[i][j] == 'O' && table[i+1][j+1] == 'S' ) {
                    score++;
                }
            }
        }
        return score;
    }

    public static char[][] pcChoice(char[][] table,char pcLetter,int n){
        int x,y;
        do{
            x = new Random().nextInt(n);
            y = new Random().nextInt(n);
        } while(table[x][y] != '_');
        table[x][y]=pcLetter;
        System.out.println("Bilgisayarın seçimi: "+ x + " x " + y);
        return table;
    }

    public static char[][] userChoice(char[][] table,char userLetter,int n){
        System.out.println("Satır ve sütun değeri gir");
        Scanner scanner = new Scanner(System.in);
        int x,y;
        do{
            x = scanner.nextInt();
            y = scanner.nextInt();

            if(x>=n || y>=n){
                System.out.println("Hatalı boyut, tekrar dene");
            }else if (table[x][y] != '_') {
                System.out.println("Gatalı giriş, tekrar dene");
            }
        } while((x>=n || y>=n) || table[x][y] != '_');
        table[x][y]=userLetter;
        return table;
    }

    public static boolean Complete(char[][] table, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(table[i][j] == '_'){
                    return false;
                }
            }
        }
        return true;
    }

    public static char[][] getNewTable(int n){
        char[][] table = new char[7][7];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = '_';
            }
        }
        return table;
    }



    public static void printTable(char[][] table, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(table[i][j]+" | ");
                if(n-j==1){
                    System.out.println();
                }
            }
        }
    }
}
