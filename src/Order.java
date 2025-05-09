import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Order extends JPanel{
    private static JFrame frame;
    private static String select = new String();

    public Order(JFrame frame){
        // frame, formPanel
        this.frame = frame;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(300, 700));
    }

    // action listener

    public static ActionListener chooseSeat(JPanel order, final int start, final int end, final int index){
        return new ActionListener(){
            int[][][] seatAvail = Bus.getSeatAvail();
            JButton[][][] seat = Bus.getSeat();
            JLabel hargaTiket = Route.getHargaTiket();
            public void actionPerformed(ActionEvent e) {
                if (seatAvail[start][end][index] == 1) {
                    JOptionPane.showMessageDialog(frame, "Kursi sudah dipilih!");
                    return;
                }
                else if(seatAvail[start][end][index] == 0){
                    seatAvail[start][end][index] = 2;
                    seat[start][end][index].setBackground(Color.PINK);
                    frame.revalidate();
                    select+=String.format("%d,%d,%d\n", start, end, index);
                } else{
                    select.replace(String.format("%d,%d,%d\n", start, end, index), "").trim();
                    seatAvail[start][end][index] = 0;
                    seat[start][end][index].setBackground(new Color(78, 153, 101));
                    frame.revalidate();
                }
                // JOptionPane.showMessageDialog(panel, "Kursi berhasil dipilih!\nnyoba doang
                // ges");
                // setVisible(false);
                order.removeAll();
                order.revalidate();
                order.repaint();

                JLabel inputNama = new JLabel("Isi nama anda: ");
                JLabel inputNik = new JLabel("Isi NIK anda: ");
                JLabel inputNoHp = new JLabel("Isi nomor HP anda: ");
                // String[] kota = {"Wilangan", "Ngawi", "Gendingan", "Solo", "Kartosuro",
                // "Jogja", "Magelang"};

                final JTextField nama = new JTextField();
                nama.setPreferredSize(new Dimension(180, 30));
                final JTextField nik = new JTextField();
                nik.setPreferredSize(new Dimension(180, 30));
                final JTextField noHp = new JTextField();
                noHp.setPreferredSize(new Dimension(180, 30));
                final JButton submit = new JButton("Submit");
                submit.setPreferredSize(new Dimension(100, 30));
                submit.setBackground(new Color(68, 189, 100));

                // JPanel space = new JPanel();
                // space.setPreferredSize(new Dimension(9999, 9999));
                nama.setPreferredSize(new Dimension(180, 30));
                nik.setPreferredSize(new Dimension(180, 30));
                noHp.setPreferredSize(new Dimension(180, 30));


                order.add(inputNama);
                // add(space);
                order.add(nama);
                // add(space);
                order.add(inputNik);
                // add(space);
                order.add(nik);
                // add(space);
                order.add(inputNoHp);
                // add(space);
                order.add(noHp);
                order.add(submit);
                JPanel info2 = new JPanel();
                info2.setPreferredSize(new Dimension(200, 30));
                info2.setLayout(new FlowLayout(FlowLayout.LEFT));
                order.add(info2);

                KeyListener clearError = new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        if (nama.getText().isEmpty() || nik.getText().isEmpty() || noHp.getText().isEmpty())
                            return;
                        info2.removeAll();
                        info2.revalidate();
                        info2.repaint();
                    }
                };

                nama.addKeyListener(clearError);
                nik.addKeyListener(clearError);
                noHp.addKeyListener(clearError);

                submit.addActionListener(new ActionListener() {
                    boolean isError = false;

                    public void actionPerformed(ActionEvent e) {
                        if (nama.getText().isEmpty() || nik.getText().isEmpty() || noHp.getText().isEmpty()) {
                            // JOptionPane.showMessageDialog(panel, "Data tidak boleh kosong!");
                            if (isError)
                                return;

                            isError = true;
                            int countError = 0;
                            String err = "";

                            if(nama.getText().isEmpty()){
                                err += "Nama";
                                countError++;
                            }
                            if(nik.getText().isEmpty()){
                                if(countError>0) err += ", ";
                                err += "Nik";
                            }
                            if(noHp.getText().isEmpty()){
                                if(countError>0) err += ", ";
                                err += "NoHp";
                            }

                            JLabel error = new JLabel(err+" tidak boleh kosong!");
                            error.setForeground(Color.RED);
                            info2.add(error);
                            info2.revalidate();
                            return;
                        }

                        if (hargaTiket.getText().equals("Rute bus tidak valid")) {
                            JOptionPane.showMessageDialog(frame, "Perbaiki pilihan lokasi anda!");
                            return;
                        }
                        seatAvail[start][end][index] = 1;
                        seat[start][end][index].setBackground(new Color(181, 78, 78));
                        JOptionPane.showMessageDialog(frame, "Tiket berhasil dipesan!");
                        // semua filewriter gbs keknya gara gara ga di ide macem intellij dah
                        // try (BufferedWriter writer = new BufferedWriter(new FileWriter("seat.txt",
                        // true))) {
                        // String text = String.valueOf(indexChar) + "," + String.valueOf(index) +
                        // "\n";
                        // writer.write(text);
                        // System.out.println("Write success.");
                        // } catch (IOException e) {
                        // e.printStackTrace();
                        // System.err.println("Gagal menulis ke file");
                        // }

                        // try (var writer = new FileWriter("seat.txt", true)) {
                        // String text = String.valueOf(indexChar) + String.valueOf(index) + ",";
                        // writer.write(text);
                        // } catch (IOException e) {
                        // System.err.println("Gagal menulis ke file");
                        // }
                        // try (FileWriter writer = new FileWriter("seat.txt", true)) {
                        // writer.write(String.valueOf(indexChar) + String.valueOf(index) + ",");
                        // //System.out.println(indexChar + index + ",");
                        // } catch (IOException e) {
                        // //System.err.println("Terjadi kesalahan saat menyimpan data ke seat.txt!");
                        // e.printStackTrace(); // Optional, for debugging
                        // }

                        // try(FileWriter writer = new FileWriter("data.txt", true)){
                        // writer.write(nama+" "+nik+" "+noHp+" "+lokasiAwal+" "+lokasiAkhir+"
                        // "+hargaTiket+"\n");
                        // JOptionPane.showMessageDialog(panel, "Data berhasil disimpan ke data.txt!")
                        // } catch(IOException e){
                        // JOptionPane.showMessageDialog(panel, "Terjadi kesalahan saat menyimpan data
                        // ke data.txt!");
                        // }
                        frame.revalidate();
                    }
                });

                order.setVisible(true);
            }
        };
    }
}