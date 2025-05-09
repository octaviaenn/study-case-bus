import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bus extends JPanel{
    private static boolean[][] seatAvail = new boolean[2][22];
    private static final JButton[][] seat = new JButton[2][22];

    public Bus(JPanel order){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 450));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(200, 450));
        add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setPreferredSize(new Dimension(200, 450));
        add(rightPanel, BorderLayout.EAST);

        JLabel pintuDepan = new JLabel("| PINTU DEPAN");
        JButton kondektur = new JButton("KONDEKTUR");
        kondektur.setBackground(Color.LIGHT_GRAY);
        JPanel padding1 = new JPanel();
        JPanel padding2 = new JPanel();
        padding1.setPreferredSize(new Dimension(200, 10));
        padding2.setPreferredSize(new Dimension(200, 10));
        String[] A = { "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13", "A14", "A15",
                "A16", "A17", "A18" };


        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 22; j++) {
                seatAvail[i][j] = true;
            }
        }

        JLabel pintuBelakang = new JLabel("| PINTU BELAKANG");
        JButton toilet = new JButton("TOILET");
        toilet.setBackground(Color.LIGHT_GRAY);

        leftPanel.add(pintuDepan);
        leftPanel.add(kondektur);
        leftPanel.add(padding1);

        for (int i = 0; i < 18; i++) {
            final int indexChar1 = 0;
            final int indexNum1 = i;
            seat[0][i] = new JButton(A[i]);
            seat[0][i].setPreferredSize(new Dimension(80, 30));
            seat[0][i].addActionListener(
                    Order.chooseSeat(order, indexChar1, indexNum1));
            if (seatAvail[0][i] == true)
                seat[0][i].setBackground(new Color(78, 153, 101));
            else
                seat[0][i].setBackground(new Color(181, 78, 78));
            leftPanel.add(seat[0][i]);
        }
        leftPanel.add(padding2);
        leftPanel.add(pintuBelakang);
        leftPanel.add(toilet);

        JButton driver = new JButton("DRIVER");
        driver.setBackground(Color.LIGHT_GRAY);
        JPanel padding = new JPanel();
        padding.setPreferredSize(new Dimension(200, 30));
        String[] B = { "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12", "B13", "B14", "B15",
                "B16", "B17", "B18", "B19", "B20", "B21", "B22" };

        rightPanel.add(driver);
        rightPanel.add(padding);
        for (int i = 0; i < 22; i++) {
            final int indexChar = 1;
            final int indexNum = i;
            seat[1][i] = new JButton(B[i]);
            seat[1][i].setPreferredSize(new Dimension(80, 30));
            seat[1][i].addActionListener(Order.chooseSeat(order, indexChar, indexNum));
            if (seatAvail[1][i] == true)
                seat[1][i].setBackground(new Color(78, 153, 101));
            else
                seat[1][i].setBackground(new Color(181, 78, 78));
            rightPanel.add(seat[1][i]);
        }

    }

    public static boolean[][] getSeatAvail(){
        return seatAvail;
    }

    public static JButton[][] getSeat(){
        return seat;
    }
}