import javax.swing.*;
import java.awt.*;

public class OnboardingPage2 {
    private BusBookingApp app;

    public OnboardingPage2(BusBookingApp app) {
        this.app = app;
    }

    public JPanel createOnboardingPage2() {
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
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JLabel title = new JLabel("Jelajahi Perjalanan Anda!");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(0xB97179));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mengatur teks deskripsi
        JTextArea description = new JTextArea(
                "Pilih rute, tentukan kursi favoritmu, cek ketersediaan tempat duduk " +
                        "dan nikmati harga tiket yang terjangkau!"
        );
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setForeground(new Color(0xB97179));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setMaximumSize(new Dimension(450, 50));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tempat untuk gambar (ukuran 500x400)
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("Bus2.png");
        Image image = imageIcon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Menambahkan elemen untuk musatin secara vertikal
        centerPanel.add(Box.createVerticalGlue()); // Spasi di atas untuk memusatkan
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spasi antara judul dan deskripsi
        centerPanel.add(description);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spasi antara deskripsi dan gambar
        centerPanel.add(imageLabel);
        centerPanel.add(Box.createVerticalGlue()); // Spasi di bawah untuk memusatkan

        panel.add(centerPanel, BorderLayout.CENTER);

        // Panel bawah untuk tombol
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0xB97179));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> app.showPage("OnboardingPage1"));

        JButton nextButton = new JButton("Next");
        nextButton.setBackground(new Color(0xB97179));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setBorderPainted(false);
        nextButton.setPreferredSize(new Dimension(100, 30));
        nextButton.addActionListener(e -> app.showPage("GetStarted"));

        bottomPanel.add(backButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        bottomPanel.add(nextButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }
}