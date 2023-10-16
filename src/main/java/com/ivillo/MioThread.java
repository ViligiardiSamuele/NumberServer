package com.ivillo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class MioThread extends Thread{

    private Socket s;
    private List<MioThread> threads;
    public MioThread(Socket s, List<MioThread> threads){
        this.s = s;
        this.threads = threads;
    }


    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            int number = (int) (Math.random() * 100);
            System.out.println("["+currentThread().getName() + "] Numero generato: " + number);
            String stringaRicevuta = new String();
            int contatore = 0;
            do {
                stringaRicevuta = in.readLine();
                System.out.println("["+currentThread().getName() + "] Client sceglie : " + stringaRicevuta);
                if (Integer.parseInt(stringaRicevuta) == -1) {
                    out.writeBytes("-2" + '\n');
                    System.out.println("["+currentThread().getName() + "] Client chiude la connessione");
                } else if (Integer.parseInt(stringaRicevuta) < number) {
                    out.writeBytes("-1" + '\n');
                    contatore++;
                } else if (Integer.parseInt(stringaRicevuta) > number) {
                    out.writeBytes("1" + '\n');
                    contatore++;
                } else {
                    out.writeBytes("0" + '\n');
                    out.writeBytes(Integer.toString(contatore) + '\n');
                    number = (int) (Math.random() * 100);
                    System.out.println("["+currentThread().getName() + "] Numero generato: " + number);
                }

            } while (Integer.parseInt(stringaRicevuta) != -1);
            s.close();
            threads.remove(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            threads.remove(this);
        }
    }
}