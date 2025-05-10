import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Route extends JPanel {
    private static JLabel hargaTiket = new JLabel("Rute bus tidak valid");
    private static int start=0;
    private static int end=0;

    public Route(JFrame frame) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(400, 250));
        // setLayout(new FlowLayout(FlowLayout.LEFT));
        // setPreferredSize(new Dimension(300, 700));

        String[] data = {
                "Wilangan", "Ngawi", "Gendingan", "Solo", "Kartosuro", "Jogja", "Magelang"
        };

        JLabel inputLokasiAwal = new JLabel("Pilih lokasi awal anda: ");
        JComboBox<String> lokasiAwal = new JComboBox<>(data);
        // lokasiAwal.setPreferredSize(new Dimension(10, 30));
        lokasiAwal.setSelectedIndex(0);

        JLabel inputLokasiAkhir = new JLabel("Pilih lokasi tujuan anda: ");
        JComboBox<String> lokasiAkhir = new JComboBox<>(data);
        // lokasiAkhir.setPreferredSize(new Dimension(10, 30));

        lokasiAkhir.setSelectedIndex(0);

        // Map<String, String> lokasi = new HashMap<>();
        String invalid = "Rute bus tidak valid";
        String[][] harga = {
                {invalid, "Rp 35.000", "Rp 45.000", "Rp 55.000", "Rp 60.000", "Rp 70.000", "Rp 85.000"},
                {"Rp 35.000", invalid, "Rp 25.000", "Rp 30.000", "Rp 40.000", "Rp 45.000", "Rp 60.000"},
                {"Rp 45.000", "Rp 25.000", invalid, "Rp 25.000", "Rp 35.000", "Rp 40.000", "Rp 55.000"},
                {"Rp 55.000", "Rp 30.000", "Rp 25.000", invalid, "Rp 15.000", "Rp 15.000", "Rp 30.000"},
                {"Rp 60.000", "Rp 40.000", "Rp 35.000", "Rp 15.000", invalid, "Rp 15.000", "Rp 30.000"},
                {"Rp 70.000", "Rp 45.000", "Rp 40.000", "Rp 15.000", "Rp 15.000", invalid, "Rp 15.000"},
                {"Rp 85.000", "Rp 60.000", "Rp 55.000", "Rp 30.000", "Rp 30.000", "Rp 15.000", invalid}
        };

        add(inputLokasiAwal);
        add(lokasiAwal);
        add(inputLokasiAkhir);
        add(lokasiAkhir);
        JPanel info = new JPanel();
        info.setPreferredSize(new Dimension(200, 30));
        info.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(info);
        //hargaTiket = new JLabel(harga[0][0]);
        hargaTiket.setForeground(Color.RED);
        info.add(hargaTiket);

        // action listener

        ActionListener updateData = new ActionListener() {
            boolean changed = false;
            public void actionPerformed(ActionEvent e) {
                    String selectedItem = (String) lokasiAwal.getSelectedItem();
                    String selectedItem2 = (String) lokasiAkhir.getSelectedItem();
                    start = switch (selectedItem) {
                        case "Wilangan" -> 0;
                        case "Ngawi" -> 1;
                        case "Gendingan" -> 2;
                        case "Solo" -> 3;
                        case "Kartosuro" -> 4;
                        case "Jogja" -> 5;
                        case "Magelang" -> 6;
                        default -> 0;
                    };
                    end = switch (selectedItem2) {
                        case "Wilangan" -> 0;
                        case "Ngawi" -> 1;
                        case "Gendingan" -> 2;
                        case "Solo" -> 3;
                        case "Kartosuro" -> 4;
                        case "Jogja" -> 5;
                        case "Magelang" -> 6;
                        default -> 0;
                    };
                    if (harga[start][end].equals(invalid)) {
                        hargaTiket.setText(invalid);
                        hargaTiket.setForeground(Color.RED);
                    } else {
                        Bus.updateSeat();
                        hargaTiket.setText("Harga tiket: " + harga[start][end]);
                        hargaTiket.setForeground(new Color(68, 189, 100));
                    }

                    if (changed) {
                        info.remove(hargaTiket);
                        info.revalidate();
                        info.repaint();
                        info.add(hargaTiket);
                    }

                changed = true;

            }
            };

        lokasiAwal.addActionListener(updateData);
        lokasiAkhir.addActionListener(updateData);
    }

    public static JLabel getHargaTiket(){
        return hargaTiket;
    }

    public static int getStart(){
        return start;
    }

    public static int getEnd(){
        return end;
    }

//    private
//    }

//    public static
//    }
}