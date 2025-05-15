import javax.swing.*;
import java.awt.*;

public class OnboardingPage1 {
    private BusBookingApp app;

    public OnboardingPage1(BusBookingApp app) {
        this.app = app;
    }

    public JPanel createOnboardingPage1() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Panel atas utk tombol Skip di pojok kanan atas
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10)); // Padding untuk posisi tombol

        JButton skipButtonTop = new JButton("Skip");
        skipButtonTop.setBackground(new Color(0xB97179));
        skipButtonTop.setForeground(Color.WHITE);
        skipButtonTop.setFocusPainted(false);
        skipButtonTop.setBorderPainted(false);
        skipButtonTop.setPreferredSize(new Dimension(80, 30));
        skipButtonTop.addActionListener(e -> app.showPage("GetStarted"));

        topPanel.add(skipButtonTop);
        panel.add(topPanel, BorderLayout.NORTH);

        // Panel tengah untuk teks dan gambar
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel title = new JLabel("Selamat Datang di Busy3p!");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(0xB97179));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel description = new JLabel("Pesan tiket bus dengan mudah dan cepat.");
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setForeground(new Color(0xB97179));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tempat untuk gambar (ukuran 500x400)
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("src\\Bus1.png");
        Image image = imageIcon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue()); // Spasi besar di atas
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Dorong teks lebih ke bawah
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(description);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spasi tambahan di bawah tulisan
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spasi tambahan di atas gambar
        centerPanel.add(imageLabel);
        centerPanel.add(Box.createVerticalGlue());

        panel.add(centerPanel, BorderLayout.CENTER);

        // Panel bawah untuk tombol
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);

        JButton nextButton = new JButton("Next");
        nextButton.setBackground(new Color(0xB97179));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setBorderPainted(false);
        nextButton.setPreferredSize(new Dimension(100, 30));
        nextButton.addActionListener(e -> app.showPage("OnboardingPage2"));

        bottomPanel.add(nextButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }
}