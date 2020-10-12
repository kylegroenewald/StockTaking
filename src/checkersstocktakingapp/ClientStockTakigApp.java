/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.    public static void main(String[] args) {
 */
package checkersstocktakingapp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author kylegroenewald
 */
public class ClientStockTakigApp {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 8000);
            System.out.println("Welcome to Checkers Stock Taking App");

            DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader reader_2 = new BufferedReader(new InputStreamReader(System.in));

            String prodName = "";
            String prodType = "";
            String prodPrice = "";

            do {

                System.out.println(reader.readLine());
                prodName = reader_2.readLine();
                dataOut.writeUTF(prodName);
                dataOut.flush();

                if (prodName.equals("stop")) {
                    System.out.println("Exiting Checkers Stock Taking App, Good Bye!");
                    break;
                }

                System.out.println(reader.readLine());
                prodType = reader_2.readLine();
                dataOut.writeUTF(prodType);
                dataOut.flush();

                System.out.println(reader.readLine());
                prodPrice = reader_2.readLine();
                dataOut.writeUTF(prodPrice);
                dataOut.flush();

                System.out.println("Server says: " + reader.readLine());

            } while (!prodName.equals("stop"));

            s.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
