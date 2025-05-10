import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    //public static Bus topPanel;

    public static void main(String[] args) {

        final JFrame frame = new JFrame("Pemesanan Bus Eka");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 800));

        Order formPanel = new Order(frame, panel);

        Bus topPanel = new Bus(formPanel, frame);
        topPanel.setObj(topPanel);

        panel.add(topPanel, BorderLayout.NORTH);


        Route bottomPanel = new Route(frame);
        panel.add(bottomPanel);
        // bottomPanel.add(new JLabel("Bottom Panel"));



        // formPanel.add(new JLabel("Form Panel"));

        frame.add(panel, BorderLayout.WEST);
        frame.add(formPanel, BorderLayout.EAST);

        // batas layout BARU

        // ActionListener submitted = new ActionListener(){
        // public void actionPerformed(ActionEvent e){
        // JOptionPane.showMessageDialog(panel, "Tiket berhasil dipesan!");
        // }
        // }


        // change the boolean seat into 3d array or ist of 2d array

        // read the data
        // try{
        // BufferedReader reader = new BufferedReader(new FileReader("seat.txt"));
        // String line;
        // while((line = reader.readLine()) != null){
        // String[] seatTaken = line.split(",");
        // seatAvail[Integer.parseInt(seatTaken[0])][Integer.parseInt(seatTaken[1])] =
        // false;
        // }
        // reader.close();
        // } catch(IOException e){
        // System.err.println("Terjadi kesalahan saat membaca file seat.txt!");
        // }





        // int indexChar, indexNum;


        // seat[][].addActionListener(new ActionListener(){
        // public void actionPerformed(ActionEvent e){
        // JOptionPane.showMessageDialog(panel, "Kursi berhasil dipilih!\nnyoba doang
        // ges");
        // }
        // })
        frame.setVisible(true);

    }

//    public static Bus getTopPanel() {
//        return topPanel;
//    }

}