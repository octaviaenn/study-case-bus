import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;


public class Booking extends JPanel {
    private static ArrayList<DataBooking> DataBooking = new ArrayList<>();
    private JTable tabelPemesanan;
    private DefaultTableModel modelTabel;
    private JFrame frameInduk;
    private static JFrame framePemesanan = new JFrame("Data Pemesanan Bus Eka");

    public Booking(JFrame frame) {
        this.frameInduk = frame;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(650, 500));

        JLabel labelJudul = new JLabel("Data Pemesanan Tiket Bus Eka", SwingConstants.CENTER);
        labelJudul.setFont(new Font("Arial", Font.BOLD, 18));
        add(labelJudul, BorderLayout.NORTH);

        String[] namaKolom = {"Nama", "NIK", "No. HP", "Asal", "Tujuan", "Kursi", "Harga"};
        modelTabel = new DefaultTableModel(namaKolom, 0);
        tabelPemesanan = new JTable(modelTabel);
        JTableHeader header = tabelPemesanan.getTableHeader();
        header.setBackground(new Color(0xDCA0A6));
        header.setFont(new Font("Arial", Font.PLAIN, 14));
        tabelPemesanan.getColumnModel().getColumn(0).setPreferredWidth(120); //nama
        tabelPemesanan.getColumnModel().getColumn(1).setPreferredWidth(80);  //nik
        tabelPemesanan.getColumnModel().getColumn(2).setPreferredWidth(80);  //notelp
        tabelPemesanan.getColumnModel().getColumn(3).setPreferredWidth(70);  //asal
        tabelPemesanan.getColumnModel().getColumn(4).setPreferredWidth(70);  //tujuan
        tabelPemesanan.getColumnModel().getColumn(5).setPreferredWidth(50);  //kursi
        tabelPemesanan.getColumnModel().getColumn(6).setPreferredWidth(80);  //harga

        JScrollPane panelGulir = new JScrollPane(tabelPemesanan);
        add(panelGulir, BorderLayout.CENTER);

        JPanel panelFilter = new JPanel();
        panelFilter.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelFilter = new JLabel("Filter berdasarkan:");
        panelFilter.add(labelFilter);

        String[] opsiFilter = {"Semua", "Wilangan", "Ngawi", "Gendingan", "Solo", "Kartosuro", "Jogja", "Magelang"};
        JComboBox<String> filterAsal = new JComboBox<>(opsiFilter);
        JComboBox<String> filterTujuan = new JComboBox<>(opsiFilter);

        panelFilter.add(new JLabel("Dari:"));
        panelFilter.add(filterAsal);
        panelFilter.add(new JLabel("Ke:"));
        panelFilter.add(filterTujuan);

        JButton tombolFilter = new JButton("Filter");
        panelFilter.add(tombolFilter);
        tombolFilter.setBackground(new Color(0xDCA0A6));

        JButton tombolReset = new JButton("Reset");
        panelFilter.add(tombolReset);
        tombolReset.setBackground(new Color(0xDCA0A6));

        JButton back = new JButton("Close");
        panelFilter.add(back);
        back.setBackground(new Color(0xDCA0A6));

        add(panelFilter, BorderLayout.SOUTH);

        tombolFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kotaAsal = (String) filterAsal.getSelectedItem();
                String kotaTujuan = (String) filterTujuan.getSelectedItem();
                filterPemesanan(kotaAsal, kotaTujuan);
            }
        });

        tombolReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterAsal.setSelectedIndex(0);
                filterTujuan.setSelectedIndex(0);
                perbaruiTabel();
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                framePemesanan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                framePemesanan.dispose();
            }
        });
    }

    public static void addOrder(String nama, String nik, String telepon, int awal, int akhir, int indexKursi) {
        DataBooking pemesanan = new DataBooking(nama, nik, telepon, awal, akhir, indexKursi);
        DataBooking.add(pemesanan);
    }

    private void filterPemesanan(String kotaAsal, String kotaTujuan) {
        modelTabel.setRowCount(0);

        for (DataBooking pemesanan : DataBooking) {
            boolean cocokAsal = kotaAsal.equals("Semua") || kotaAsal.equals(pemesanan.getNamaLocAwal());
            boolean cocokTujuan = kotaTujuan.equals("Semua") || kotaTujuan.equals(pemesanan.getNamaLocAkhir());

            if (cocokAsal && cocokTujuan) {
                modelTabel.addRow(new Object[]{
                        pemesanan.getNama(),
                        pemesanan.getNik(),
                        pemesanan.getNotelp(),
                        pemesanan.getNamaLocAwal(),
                        pemesanan.getNamaLocAkhir(),
                        pemesanan.getLabel(),
                        pemesanan.getHarga()
                });
            }
        }
    }

    public void perbaruiTabel() {
        modelTabel.setRowCount(0);

        for (DataBooking pemesanan : DataBooking) {
            modelTabel.addRow(new Object[]{
                    pemesanan.getNama(),
                    pemesanan.getNik(),
                    pemesanan.getNotelp(),
                    pemesanan.getNamaLocAwal(),
                    pemesanan.getNamaLocAkhir(),
                    pemesanan.getLabel(),
                    pemesanan.getHarga()
            });
        }
    }

    public static void View() {

        framePemesanan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePemesanan.setSize(700, 500);

        Booking panelPemesanan = new Booking(framePemesanan);
        panelPemesanan.perbaruiTabel();

        framePemesanan.add(panelPemesanan);
        framePemesanan.setLocationRelativeTo(null);
        framePemesanan.setVisible(true);
    }
}
