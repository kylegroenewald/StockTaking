/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkersstocktakingapp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Statement;

/**
 *
 * @author kylegroenewald
 */
public class ServerStcokTakingApp {

    public static void main(String[] args) throws Exception {
        try {
            ServerSocket ss = new ServerSocket(8000);
            System.out.println("Server is starting.....");
            Socket s = ss.accept();

            DataInputStream din = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter p_Out = new PrintWriter(s.getOutputStream(), true);

            String prodName = "";
            String prodType = "";
            double prodPrice = 0;

            do {
                p_Out.println("Enter Product Name:");
                prodName = din.readUTF();
                p_Out.flush();

                if (prodName.equals("stop")) {
                    System.out.println("Server is stopping...");
                    break;
                }

                p_Out.println("Enter Product Type:");
                prodType = din.readUTF();
                p_Out.flush();

                p_Out.println("Enter Product Price:");
                prodPrice = Double.parseDouble(din.readUTF());
                p_Out.flush();

                System.out.println("Server received Product Details: " + prodName + " " + prodType + " " + prodPrice);

                try {
                    java.sql.Connection con = (java.sql.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/checkersproducts","root","");
                    System.out.println("Connected to the database checkersproducts");
                    
                    //Adding products to database
                    String query = "INSERT INTO products (prodName, prodType, prodPrice) " + "VALUES ('" + prodName + "', '" + prodType + "', " + prodPrice + ");";
                    Statement ps = con.createStatement();
                    ps.executeUpdate(query);
                    
                    
                    System.out.println("Product has been added to the database");
                    p_Out.println("Product has been added");
                    p_Out.flush();
                    
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } while (!prodName.equals("stop"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
