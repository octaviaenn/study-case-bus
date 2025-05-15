import javax.swing.*;
import java.awt.*;

public class GetStartedPage {
    private BusBookingApp app;

    public GetStartedPage(BusBookingApp app) {
        this.app = app;
    }

    public JPanel createGetStartedPage() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(700, 700));
        panel.setBackground(new Color(0xB97179)); // Pink utama (#B97179)

        // Center content
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel title = new JLabel("Get Started");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel description = new JLabel("Start with sign up or sign in");
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setForeground(Color.WHITE);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signInButton = new JButton("SIGN IN");
        signInButton.setBackground(new Color(0xFFFFFF)); // Putih (#FFFFFF)
        signInButton.setForeground(new Color(0xB97179));
        signInButton.setFont(new Font("Arial", Font.BOLD, 16));
        signInButton.setFocusPainted(false);
        signInButton.setBorderPainted(false);
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInButton.setMaximumSize(new Dimension(150, 50));

        JButton signUpButton = new JButton("SIGN UP");
        signUpButton.setBackground(new Color(0xF4C7CA)); // Pink muda (#F4C7CA)
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16));
        signUpButton.setFocusPainted(false);
        signUpButton.setBorderPainted(false);
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setMaximumSize(new Dimension(150, 50));

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(description);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));  //jarak tulisan start with dengan tombol sign in
        centerPanel.add(signInButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(signUpButton);
        centerPanel.add(Box.createVerticalGlue());

        // Action Listeners
        signInButton.addActionListener(e -> app.showPage("SignIn"));
        signUpButton.addActionListener(e -> app.showPage("SignUp"));

        panel.add(centerPanel, BorderLayout.CENTER);

        // Panel untuk tombol Back di pojok kiri bawah
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0)); // Padding untuk posisi tombol

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0xFFFFFF)); // Putih (#FFFFFF)
        backButton.setForeground(new Color(0xB97179));
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.addActionListener(e -> app.showPage("OnboardingPage2"));

        bottomPanel.add(backButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }
}