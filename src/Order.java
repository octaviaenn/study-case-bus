import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Order extends JPanel{
    private static JFrame frame;
    private static JPanel panel;
    private static String select = new String();
    private static boolean flag = false;

    public Order(JFrame frame, JPanel panel){
        // frame, formPanel
        this.frame = frame;
        this.panel = panel;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(300, 700));
    }

    // action listener

    public static ActionListener chooseSeat(JPanel order, final int start, final int end, final int index){
        return new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                int[][][] seatAvail = Bus.getSeatAvail();
                JButton[][][] seat = Bus.getSeat();
                JLabel hargaTiket = Route.getHargaTiket();

                if (seatAvail[start][end][index] == 1) {
                    JOptionPane.showMessageDialog(frame, "Kursi sudah dipilih!");
                    return;
                }
                else if(seatAvail[start][end][index] == 0){
                    System.out.println("Masuk else if buat select seatAvail:"+seatAvail[start][end][index]);
                    for(int i=0; i<6; i++){
                        for(int j=i+1; j<=6; j++){
                            Bus.setSeatAvail(i, j, index, 2);
                            Bus.setSeat(i, j, index, Color.PINK);
                            Bus.setSeatAvail(j, i, index, 2);
                            Bus.setSeat(j, i, index, Color.PINK);
                        }
                    }
                    Bus.updateSeat();
                    frame.revalidate();
                    select+=String.format("%d,%d,%d\n", start, end, index);
                } else{
                    System.out.println("Masuk else buat unselect");
                    select.replace(String.format("%d,%d,%d\n", start, end, index), "").trim();
                    for(int i=0; i<6; i++){
                        for(int j=i+1; j<=6; j++){
                            Bus.setSeatAvail(i, j, index, 0);
                            Bus.setSeat(i, j, index, new Color(78, 153, 101));
                            Bus.setSeatAvail(j, i, index, 0);
                            Bus.setSeat(j, i, index, new Color(78, 153, 101));
                        }
                    }
                    Bus.updateSeat();
//                    seatAvail[start][end][index] = 0;
//                    seat[start][end][index].setBackground(new Color(78, 153, 101));
                    frame.revalidate();
                }
                // JOptionPane.showMessageDialog(panel, "Kursi berhasil dipilih!\nnyoba doang
                // ges");
                // setVisible(false);
                if(flag) return;
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
//                        if (nama.getText().isEmpty() || nik.getText().isEmpty() || noHp.getText().isEmpty())
//                            return;
                        info2.removeAll();

                        int countError = 0;
                        String err = "", err2 = "";

                        if(nama.getText().isEmpty()){
                            err += "Nama";
                            countError++;
                        }
                        if(nik.getText().isEmpty()){
                            if(countError>0) err += ", ";
                            countError++;
                            err += "Nik";
                        }
                        if(noHp.getText().isEmpty()){
                            if(countError>0) err += ", ";
                            err += "No HP";
                        }

                        JLabel error = new JLabel();
                        JLabel error2 = new JLabel();

                        if(err!=""){
                            error.setText(err+" tidak boleh kosong!");
                            error.setForeground(Color.RED);
                            info2.add(error);
                        }

                        boolean isError2 = false;
                        try{
                            int num = Integer.parseInt(nik.getText());
                        } catch(NumberFormatException ex){
                            err2+="NIK";
                            isError2 = true;
                        }

                        try{
                            int num = Integer.parseInt(noHp.getText());
                        } catch(NumberFormatException ex){
                            if(isError2) err2+=", ";
                            err2+="No HP";
                        }

                        if(err2!=""){
                            error2.setText(err2+" harus berupa angka");
                            error2.setForeground(Color.RED);
                            info2.add(error2);
                        }
                        info2.revalidate();
                        info2.repaint();
                    }
                };

                nama.addKeyListener(clearError);
                nik.addKeyListener(clearError);
                noHp.addKeyListener(clearError);
                flag = true;

                submit.addActionListener(new ActionListener() {
                    boolean isError = false;

                    public void actionPerformed(ActionEvent e) {

                        if (nama.getText().isEmpty() || nik.getText().isEmpty() || noHp.getText().isEmpty()) {
                            // JOptionPane.showMessageDialog(panel, "Data tidak boleh kosong!");
//                            if (isError)
//                                return;

                            isError = true;
                            return;
                        }

                        if (hargaTiket.getText().equals("Rute bus tidak valid")) {
                            JOptionPane.showMessageDialog(frame, "Perbaiki pilihan lokasi anda!");
                            return;
                        }

                        String[] selected = select.split("\n");
                        for(int i=0; i<selected.length; i++){
                            String[] idx = selected[i].split(",");
                            int st, en, in;
                            st = Integer.parseInt(idx[0]);
                            en = Integer.parseInt(idx[1]);
                            in = Integer.parseInt(idx[2]);

                            if(st<en){
                                for(int j=st+1; j<=en; j++){
                                    for(int k=j-1; k>=0; k--){
                                        //seatAvail[k][j][in] = 1;
                                        Bus.setSeatAvail(k, j, in, 1);
                                        //seat[k][j][in].setBackground();
                                        Bus.setSeat(k, j, in, new Color(181, 78, 78));
                                    }
                                }
                            } else{
                                for(int j=end; j<=start; j++){
                                    for(int k=j+1; k<=6; k++){
                                        //seatAvail[k][j][in] = 1;
                                        Bus.setSeatAvail(k, j, in, 1);
                                        //seat[k][j][in].setBackground();
                                        Bus.setSeat(k, j, in, new Color(181, 78, 78));
                                    }
                                }
                            }


                        }



                        select="";
                        flag = false;
                        order.setVisible(false);
                        Bus.updateSeat();

                        panel.revalidate();
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

                        //frame.revalidate();
                    }
                });

                order.setVisible(true);
            }
        };
    }
}