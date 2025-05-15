import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;

class DataBooking {
    private String nama;
    private String nik;
    private String notelp;
    private int awal;
    private int akhir;
    private int seatIndex;
    private String label;
    private String harga;

    private static final String[] Kota = {"Wilangan", "Ngawi", "Gendingan", "Solo", "Kartosuro", "Jogja", "Magelang"};

    public DataBooking(String nama, String nik, String telepon, int awal, int akhir, int seatIndex) {
        this.nama = nama;
        this.nik = nik;
        this.notelp = telepon;
        this.awal = awal;
        this.akhir = akhir;
        this.seatIndex = seatIndex;

        if (seatIndex < 18) {
            this.label = "A" + (seatIndex + 1);
        } else {
            this.label = "B" + (seatIndex - 17);
        }
        this.harga = getHarga(awal, akhir);
    }

    private String getHarga(int awal, int akhir) {
        String[][] harga = {
                {"Rute bus tidak valid", "Rp 35.000", "Rp 45.000", "Rp 55.000", "Rp 60.000", "Rp 70.000", "Rp 85.000"},
                {"Rp 35.000", "Rute bus tidak valid", "Rp 25.000", "Rp 30.000", "Rp 40.000", "Rp 45.000", "Rp 60.000"},
                {"Rp 45.000", "Rp 25.000", "Rute bus tidak valid", "Rp 25.000", "Rp 35.000", "Rp 40.000", "Rp 55.000"},
                {"Rp 55.000", "Rp 30.000", "Rp 25.000", "Rute bus tidak valid", "Rp 15.000", "Rp 15.000", "Rp 30.000"},
                {"Rp 60.000", "Rp 40.000", "Rp 35.000", "Rp 15.000", "Rute bus tidak valid", "Rp 15.000", "Rp 30.000"},
                {"Rp 70.000", "Rp 45.000", "Rp 40.000", "Rp 15.000", "Rp 15.000", "Rute bus tidak valid", "Rp 15.000"},
                {"Rp 85.000", "Rp 60.000", "Rp 55.000", "Rp 30.000", "Rp 30.000", "Rp 15.000", "Rute bus tidak valid"}
        };
        return harga[awal][akhir];
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    public String getNotelp(){
        return notelp;
    }

    public String getNamaLocAwal() {
        return Kota[awal];
    }

    public String getNamaLocAkhir() {
        return Kota[akhir];
    }

    public String getLabel() {
        return label;
    }

    public String getHarga() {
        return harga;
    }

    public int getSeatIndex() {
        return seatIndex;
    }

    public int getLokasiAwal() {
        return awal;
    }

    public int getLokasiAkhir() {
        return akhir;
    }
//    public int getTotalHarga(){
//        return (getHarga()*);
//    }
}


public class Booking extends JPanel {
    private static ArrayList<DataBooking> DataBooking = new ArrayList<>();
    private JTable tabelPemesanan;
    private DefaultTableModel modelTabel;
    private JFrame frameInduk;

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
        JFrame framePemesanan = new JFrame("Data Pemesanan Bus Eka");
        framePemesanan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePemesanan.setSize(700, 500);

        Booking panelPemesanan = new Booking(framePemesanan);
        panelPemesanan.perbaruiTabel();

        framePemesanan.add(panelPemesanan);
        framePemesanan.setLocationRelativeTo(null);
        framePemesanan.setVisible(true);
    }
}
