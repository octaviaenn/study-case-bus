import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bus extends JPanel{
    private static int[][][] seatAvail = new int[7][7][40];
    private static final JButton[][][] seat = new JButton[7][7][40];
    private boolean init = true;
    private static JFrame frame;
    private static JPanel leftPanel = new JPanel();
    private static JPanel rightPanel = new JPanel();
    private static JPanel order;
    private static JPanel bus;
    private static boolean flag = false;


    public Bus(JPanel order, JFrame frame){
        this.frame = frame;
        this.order = order;
//        bus = Main.getTopPanel();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 450));


        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(200, 450));
        add(leftPanel, BorderLayout.WEST);


        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setPreferredSize(new Dimension(200, 450));
        add(rightPanel, BorderLayout.EAST);



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
        setVisible(true);

    }

    public void setObj(JPanel bus){
        this.bus = bus;
    }

    public static void updateSeat(){
        if(flag){
            leftPanel.removeAll();
            rightPanel.removeAll();
        }

        for (int i = 0; i < 7; i++) {
            for(int j = 0; j<7; j++){
                for(int k = 0; k<40; k++){
                    System.out.print(seatAvail[i][j][k]+" ");
                   // seat[i][j][k] = new JButton();
                } System.out.println();
            } System.out.println();
        }

        JLabel pintuDepan = new JLabel("| PINTU DEPAN");
        JButton kondektur = new JButton("KONDEKTUR");
        kondektur.setBackground(Color.LIGHT_GRAY);
        JPanel padding1 = new JPanel();
        JPanel padding2 = new JPanel();
        padding1.setPreferredSize(new Dimension(200, 10));
        padding2.setPreferredSize(new Dimension(200, 10));
        String[] A = { "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13", "A14", "A15",
                "A16", "A17", "A18" };

        JLabel pintuBelakang = new JLabel("| PINTU BELAKANG");
        JButton toilet = new JButton("TOILET");
        toilet.setBackground(Color.LIGHT_GRAY);

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
                seat[start][end][i].setBackground(Color.PINK);
            leftPanel.add(seat[start][end][i]);
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
                seat[start][end][i].setBackground(Color.PINK);
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
        System.out.println("Successfully changed "+seatAvail[start][end][idx]);
    }

    public static void setSeat(int start, int end, int idx, Color val){
        if(seatAvail[start][end][idx] == 1) return;
        seat[start][end][idx].setBackground(val);
        System.out.println("Successfully changed "+start+" "+end+" "+idx+" to color:"+val);
    }
}