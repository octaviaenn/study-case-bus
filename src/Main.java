import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    //public static Bus topPanel;

    public static void getApp() {
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
        JPanel bawah = new JPanel();
        bawah.setLayout(new BorderLayout());

        Route bottomPanel = new Route(frame, topPanel);
        panel.add(bottomPanel);

        JPanel tombol = new JPanel();
        tombol.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton liatData = new JButton("DATA PEMESANAN");
        liatData.setPreferredSize(new Dimension(150, 45));
        liatData.setBackground(new Color(0xDCA0A6));
        liatData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Booking.View();
            }
        });
        tombol.add(liatData);
        bawah.add(tombol, BorderLayout.NORTH);
        panel.add(bawah, BorderLayout.WEST);

        frame.add(panel, BorderLayout.WEST);
        frame.add(formPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
}