package com.ivillo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
public class App 
{
    public static void main( String[] args )
    {
        try {
            System.out.println("Server avviato");
            ServerSocket server = new ServerSocket(3000);

            Socket s = server.accept();
            System.out.println("Un client si è connesso");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            int number = (int) (Math.random() * 100);
            System.out.println("Numero generato: " + number);
            String stringaRicevuta = new String();
            int contatore = 0;
            do {
                stringaRicevuta = in.readLine();
                System.out.println("Client sceglie : " + stringaRicevuta);
                if(Integer.parseInt(stringaRicevuta) == -1){
                    out.writeBytes("-2" + '\n');
                    System.out.println("Client chiude il server");
                } else if(Integer.parseInt(stringaRicevuta) < number)
                {
                    out.writeBytes("-1" + '\n');
                    contatore++;
                } else if(Integer.parseInt(stringaRicevuta) > number)
                {
                    out.writeBytes("1" + '\n');
                    contatore++;
                } else {
                    out.writeBytes("0" + '\n');
                    out.writeBytes(Integer.toString(contatore) + '\n');
                    number = (int) (Math.random() * 100);
                    System.out.println("Numero generato: " + number);
                }
                
            } while (Integer.parseInt(stringaRicevuta) != -1);

            server.close();
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
    }
}
