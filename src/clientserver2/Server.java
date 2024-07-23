/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;
    private Socket connection;

    public static void main(String[] args) throws IOException {

        try (
                ServerSocket server = new ServerSocket(8080);
                Socket connection = server.accept();
                DataInputStream dis = new DataInputStream(connection.getInputStream());
                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());) {
            System.out.print("Connection Successful with the Calculator \n");
            //Get numbers
            while (true) {
                int num1 = dis.readInt();
                int num2 = dis.readInt();

                int answer = num1 + num2;

                //Send Results
                dos.writeInt(answer);
            }
        } catch (IOException ie) {
            System.out.println(ie);
        }
    }
}
