import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class Bus extends JLayeredPane{
    private static int[][][] seatAvail = new int[7][7][40];
    private static final JButton[][][] seat = new JButton[7][7][40];
    private static boolean init = true;
    private static JFrame frame;
    private static JPanel leftPanel = new JPanel();
    private static JPanel rightPanel = new JPanel();
    private static JPanel order;
    private static JLayeredPane bus;
    private static boolean flag = false;
    private static JPanel overlay = new JPanel();


    public Bus(JPanel order, JFrame frame){
        this.frame = frame;
        this.order = order;
        JPanel mainPanel = new JPanel();
//        bus = Main.getTopPanel();
        setLayout(null);
        setPreferredSize(new Dimension(400, 450));


        //leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(180, 450));
        //mainPanel.add(leftPanel, BorderLayout.WEST);


        //rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setPreferredSize(new Dimension(180, 450));
        //mainPanel.add(rightPanel, BorderLayout.EAST);



        // nanti kalo datanya ada, init = false;
        if(init){
            for (int i = 0; i < 7; i++) {
                for(int j = 0; j<7; j++){
                    for(int k = 0; k<40; k++){
                        seatAvail[i][j][k] = 0;
                        seat[i][j][k] = new JButton();
                    }
                }
            }
        }

        updateSeat();
        init = true;
        overlay.setOpaque(true);
        overlay.setBackground(new Color(220,160,166,50));
        overlay.setLayout(null);
        overlay.setPreferredSize(new Dimension(400, 450));
        leftPanel.setBounds(0, 0, 200, 450);
        rightPanel.setBounds(200, 0, 200, 450);
        overlay.setBounds(0, 0, 400, 450);

        JLabel dereng = new JLabel("Silahkan Pilih Rute Terlebih Dahulu");
        overlay.add(dereng);
        add(leftPanel, JLayeredPane.DEFAULT_LAYER);
        add(rightPanel, JLayeredPane.DEFAULT_LAYER);
        //add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        add(overlay, JLayeredPane.PALETTE_LAYER);
        setVisible(true);

    }

    public void setObj(JLayeredPane bus){
        this.bus = bus;
    }

    public static JPanel getOverlay(){
        return overlay;
    }

    public static void updateSeat(){
        if(flag){
            leftPanel.removeAll();
            rightPanel.removeAll();
        }

        if(!init){
            try{
                BufferedReader reader = new BufferedReader(new FileReader("seat/txt"));
                String line;
                while((line = reader.readLine()) != null){
                    String[] idx = line.split(",");
                    seatAvail[Integer.parseInt(idx[0])][Integer.parseInt(idx[1])][Integer.parseInt(idx[2])] = Integer.parseInt(idx[3]);
                    // logic
                } reader.close();
            } catch (IOException e){
                System.err.println("Terjadi kesalahan saat membaca file");
            }
        }

//        for (int i = 0; i < 7; i++) {
//            for(int j = 0; j<7; j++){
//                for(int k = 0; k<40; k++){
//                    System.out.print(seatAvail[i][j][k]+" ");
//                    // seat[i][j][k] = new JButton();
//                } System.out.println();
//            } System.out.println();
//        }

        JLabel pintuDepan = new JLabel("| PINTU DEPAN");
        JButton kondektur = new JButton("KONDEKTUR");
        kondektur.setBackground(new Color(0xDCA0A6));
        JPanel padding1 = new JPanel();
        JPanel padding2 = new JPanel();
        padding1.setPreferredSize(new Dimension(200, 10));
        padding2.setPreferredSize(new Dimension(200, 10));
        String[] A = { "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13", "A14", "A15",
                "A16", "A17", "A18" };

        JLabel pintuBelakang = new JLabel("| PINTU BELAKANG");
        JButton toilet = new JButton("TOILET");
        toilet.setBackground(new Color(0xDCA0A6));

        leftPanel.add(pintuDepan);
        leftPanel.add(kondektur);
        leftPanel.add(padding1);

        int start = Route.getStart();
        int end = Route.getEnd();

        for (int i = 0; i < 18; i++) {
            final int indexA = i;
            seat[start][end][i] = new JButton(A[i]);
            seat[start][end][i].setPreferredSize(new Dimension(80, 30));
            seat[start][end][i].addActionListener(
                    Order.chooseSeat(order, start, end, indexA));
            if (seatAvail[start][end][i] == 0)
                seat[start][end][i].setBackground(new Color(78, 153, 101));
            else if(seatAvail[start][end][i] == 1)
                seat[start][end][i].setBackground(new Color(181, 78, 78));
            else
                seat[start][end][i].setBackground(new Color(220, 160, 166));
            leftPanel.add(seat[start][end][i]);
        }
        leftPanel.add(padding2);
        leftPanel.add(pintuBelakang);
        leftPanel.add(toilet);

        JButton driver = new JButton("DRIVER");
        driver.setBackground(new Color(0xDCA0A6));
        JPanel padding = new JPanel();
        padding.setPreferredSize(new Dimension(200, 30));
        String[] B = { "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12", "B13", "B14", "B15",
                "B16", "B17", "B18", "B19", "B20", "B21", "B22" };

        rightPanel.add(driver);
        rightPanel.add(padding);
        for (int i = 18; i < 40; i++) {
            final int indexB = i;
            int idx = i-18;
            seat[start][end][i] = new JButton(B[idx]);
            seat[start][end][i].setPreferredSize(new Dimension(80, 30));
            seat[start][end][i].addActionListener(Order.chooseSeat(order, start, end, indexB));
            if (seatAvail[start][end][i] == 0)
                seat[start][end][i].setBackground(new Color(78, 153, 101));
            else if(seatAvail[start][end][i] == 1)
                seat[start][end][i].setBackground(new Color(181, 78, 78));
            else
                seat[start][end][i].setBackground(new Color(220, 160, 166));
            rightPanel.add(seat[start][end][i]);
        }
        if(flag){
            bus.revalidate();
            bus.repaint();
        } flag = true;

        frame.revalidate();
    }

    public static int[][][] getSeatAvail(){
        return seatAvail;
    }

    public static JButton[][][] getSeat(){
        return seat;
    }

    public static void setSeatAvail(int start, int end, int idx, int val){
        if(seatAvail[start][end][idx] == 1)  return;
        seatAvail[start][end][idx] = val;
        //System.out.println("Successfully changed "+seatAvail[start][end][idx]);
    }

    public static void setSeat(int start, int end, int idx, Color val){
        if(seatAvail[start][end][idx] == 1) return;
        seat[start][end][idx].setBackground(val);
        //System.out.println("Successfully changed "+start+" "+end+" "+idx+" to color:"+val);
    }
}