/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private int num1;
    private int num2;
    private int result;

    /**
     * @throws java.io.IOException
     */
    public Client() throws IOException {
        try {

            //Conectam aplicatia la Server
            Socket server = new Socket("127.0.0.1", 8080);
            DataInputStream dis = new DataInputStream(server.getInputStream());
            DataOutputStream dos = new DataOutputStream(server.getOutputStream());

            //Interfata GUI
            Frame f = new Frame();
            f.setLayout(null);
            f.setTitle("Calculator");
            f.setBounds(100, 100, 435, 300);

            Label calc = new Label("Calculator v0.2");
            calc.setBounds(149, 48, 109, 24);
            calc.setFont(new Font("Arial", Font.PLAIN, 14));
            calc.setAlignment(Label.CENTER);

            TextField numar1 = new TextField();
            numar1.setBounds(36, 106, 83, 22);

            Label label_1 = new Label("+");
            label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
            label_1.setBounds(139, 106, 13, 22);

            TextField numar2 = new TextField();
            numar2.setBounds(175, 106, 83, 22);

            Label egal = new Label("=");
            egal.setFont(new Font("Dialog", Font.PLAIN, 16));
            egal.setBounds(264, 106, 13, 22);

            TextField rezultat = new TextField();
            rezultat.setBounds(283, 106, 109, 22);
            rezultat.setEditable(false);
            rezultat.setText("");

            //        Button reset = new Button("Reset");
            //   reset.setBounds(175, 185, 83, 22);
            //    reset.addActionListener(new ActionListener() {
            //          @Override
            //          public void actionPerformed(ActionEvent e) {
            //              rezultat.setText("");
            //              numar1.setText("");
            //              numar2.setText("");
            //          }
            //       });
            Button calcul = new Button("Calculeaza");
            calcul.setBackground(SystemColor.activeCaption);
            calcul.setForeground(SystemColor.textText);
            calcul.setBounds(0, 227, 434, 34);

            calcul.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int num1 = Integer.parseInt(numar1.getText());
                    int num2 = Integer.parseInt(numar2.getText());
                    //Send Values

                    try {

                        dos.writeInt(num1);
                        dos.writeInt(num2);

                        //Get Result
                        int answer;
                        answer = dis.readInt();

                        //Set value to "Result text field"
                        rezultat.setText(Integer.toString(answer));

                    } catch (IOException ex) {
                        System.out.print(ex);
                    }

                }

            });

            f.add(calc);
            f.add(numar1);
            f.add(label_1);
            f.add(numar2);
            f.add(egal);
            //f.add(reset);
            f.add(rezultat);
            f.add(calcul);

            f.setVisible(true);
            f.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent we) {

                    System.exit(0);

                }

            });

        } catch (HeadlessException | IOException ex) {
        }

    }

    public static void main(String[] args) throws IOException {

        Client cl = new Client();

    }
}
