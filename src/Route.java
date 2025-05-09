import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Route extends JPanel {
    private static JLabel hargaTiket;

    public Route(JFrame frame) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(400, 250));
        // setLayout(new FlowLayout(FlowLayout.LEFT));
        // setPreferredSize(new Dimension(300, 700));

        JLabel inputLokasiAwal = new JLabel("Pilih lokasi awal anda: ");
        JComboBox<String> lokasiAwal = new JComboBox<>(
                new String[]{"Wilangan", "Ngawi", "Gendingan", "Solo", "Kartosuro", "Jogja"});
        // lokasiAwal.setPreferredSize(new Dimension(10, 30));
        lokasiAwal.setSelectedIndex(0);

        JLabel inputLokasiAkhir = new JLabel("Pilih lokasi tujuan anda: ");
        JComboBox<String> lokasiAkhir = new JComboBox<>(
                new String[]{"Ngawi", "Gendingan", "Solo", "Kartosuro", "Jogja", "Magelang"});
        // lokasiAkhir.setPreferredSize(new Dimension(10, 30));

        lokasiAkhir.setSelectedIndex(0);

        // Map<String, String> lokasi = new HashMap<>();
        String invalid = "Rute bus tidak valid";
        String[][] harga = {
                {invalid, invalid, invalid, invalid, invalid, invalid, invalid},
                {invalid, "Rp 35.000", "Rp 45.000", "Rp 55.000", "Rp 60.000", "Rp 70.000", "Rp 85.000"},
                {invalid, invalid, "Rp 25.000", "Rp 30.000", "Rp 40.000", "Rp 45.000", "Rp 60.000"},
                {invalid, invalid, invalid, "Rp 25.000", "Rp 35.000", "Rp 40.000", "Rp 55.000"},
                {invalid, invalid, invalid, invalid, "Rp 15.000", "Rp 15.000", "Rp 30.000"},
                {invalid, invalid, invalid, invalid, invalid, "Rp 15.000", "Rp 30.000"},
                {invalid, invalid, invalid, invalid, invalid, invalid, "Rp 15.000"},
        };

        add(inputLokasiAwal);
        add(lokasiAwal);
        add(inputLokasiAkhir);
        add(lokasiAkhir);
        JPanel info = new JPanel();
        info.setPreferredSize(new Dimension(200, 30));
        info.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(info);
        hargaTiket = new JLabel("Harga tiket: " + harga[1][1]);
        hargaTiket.setForeground(new Color(68, 189, 100));
        info.add(hargaTiket);

        // action listener

        ActionListener updateHarga = new ActionListener() {
            boolean changed = false;
            public void actionPerformed(ActionEvent e) {
                    String selectedItem = (String) lokasiAwal.getSelectedItem();
                    String selectedItem2 = (String) lokasiAkhir.getSelectedItem();
                    int index = switch (selectedItem) {
                        case "Wilangan" -> 1;
                        case "Ngawi" -> 2;
                        case "Gendingan" -> 3;
                        case "Solo" -> 4;
                        case "Kartosuro" -> 5;
                        case "Jogja" -> 6;
                        default -> 0;
                    };
                    int index2 = switch (selectedItem2) {
                        case "Ngawi" -> 1;
                        case "Gendingan" -> 2;
                        case "Solo" -> 3;
                        case "Kartosuro" -> 4;
                        case "Jogja" -> 5;
                        case "Magelang" -> 6;
                        default -> 0;
                    };
                    if (harga[index][index2].equals(invalid)) {
                        hargaTiket.setText(invalid);
                        hargaTiket.setForeground(Color.RED);
                    } else {
                        hargaTiket.setText("Harga tiket: " + harga[index][index2]);
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

        lokasiAwal.addActionListener(updateHarga);
        lokasiAkhir.addActionListener(updateHarga);
    }

    public static JLabel getHargaTiket(){
        return hargaTiket;
    }

//    private
//    }

//    public static
//    }
}