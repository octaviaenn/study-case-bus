import javax.swing.*;
import java.awt.*;

public class SignInPage {
    private BusBookingApp app;

    public SignInPage(BusBookingApp app) {
        this.app = app;
    }

    public JPanel createSignInPage() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(new Dimension(700, 700));
        panel.setBackground(new Color(0xB97179)); // Pink (#B97179)

        // Center content
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel title = new JLabel("Welcome Back");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel description = new JLabel("Masuk dan lanjutkan perjalananmu");
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setForeground(Color.WHITE);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Field nomor telepon
        JTextField phoneField = new JTextField("Phone Number");
        SignUpPage.erase(phoneField, panel);
        phoneField.setMaximumSize(new Dimension(250, 40));
        phoneField.setAlignmentX(Component.CENTER_ALIGNMENT);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 16));
        phoneField.setForeground(Color.DARK_GRAY);
        phoneField.setBackground(Color.WHITE);

        JLabel phoneErrorLabel = new JLabel("");
        phoneErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        phoneErrorLabel.setForeground(Color.BLACK);
        phoneErrorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Field email
        JTextField emailField = new JTextField("Email");
        SignUpPage.erase(emailField, panel);
        emailField.setMaximumSize(new Dimension(250, 40));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setForeground(Color.DARK_GRAY);
        emailField.setBackground(Color.WHITE);

        JLabel emailErrorLabel = new JLabel("");
        emailErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        emailErrorLabel.setForeground(Color.BLACK);
        emailErrorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label untuk password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(250, 40));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setForeground(Color.DARK_GRAY);
        passwordField.setBackground(Color.WHITE);

        JCheckBox rememberMe = new JCheckBox("Remember me");
        rememberMe.setBackground(new Color(0xB97179));
        rememberMe.setForeground(Color.WHITE);
        rememberMe.setFont(new Font("Arial", Font.PLAIN, 14));
        rememberMe.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signInButton = new JButton("SIGN IN");
        signInButton.setBackground(new Color(0xFFFFFF)); // Putih (#FFFFFF)
        signInButton.setForeground(new Color(0xB97179));
        signInButton.setFont(new Font("Arial", Font.BOLD, 16));
        signInButton.setFocusPainted(false);
        signInButton.setBorderPainted(false);
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInButton.setMaximumSize(new Dimension(200, 50));

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(description);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        centerPanel.add(phoneField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(phoneErrorLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(emailField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(emailErrorLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(passwordLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(passwordField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(rememberMe);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(signInButton);
        centerPanel.add(Box.createVerticalGlue());

        // Bottom panel untuk opsi gapunya akun
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        JLabel signUpPrompt = new JLabel("Don't have an account? Sign up");
        signUpPrompt.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpPrompt.setForeground(Color.WHITE);
        bottomPanel.add(signUpPrompt);

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0xFFFFFF)); // Putih (#FFFFFF)
        backButton.setForeground(new Color(0xB97179));
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.addActionListener(e -> app.showPage("GetStarted"));

        bottomPanel.add(backButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        signInButton.addActionListener(e -> {
            String phoneText = phoneField.getText().trim();
            String emailText = emailField.getText().trim();
            String passwordText = new String(passwordField.getPassword());

            // Validasi nomor telepon (harus angka)
            boolean isPhoneValid = true;
            try {
                Long.parseLong(phoneText);
                phoneErrorLabel.setText("");
            } catch (NumberFormatException ex) {
                phoneErrorLabel.setText("Nomor telepon harus berupa angka");
                isPhoneValid = false;
            }

            // Validasi email (harus @gmail.com)
            boolean isEmailValid = emailText.endsWith("@gmail.com") && emailText.length() > "@gmail.com".length();
            if (!isEmailValid) {
                emailErrorLabel.setText("Email tidak valid, harus @gmail.com");
            } else {
                emailErrorLabel.setText("");
            }

            // Validasi sederhana (misalnya, cocokkan password dengan data yang disimpan)
            if (isPhoneValid && isEmailValid) {
                // Simpan data pengguna
                User user = new User(phoneText, emailText, passwordText);
                app.setCurrentUser(user);
                JOptionPane.showMessageDialog(app, "Login berhasil!");
                Main.getApp(); // Beralih ke halaman pemesanan tiket bus
            }
        });

        signUpPrompt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                app.showPage("SignUp");
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUpPrompt.setForeground(new Color(0xF4C7CA));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUpPrompt.setForeground(Color.WHITE);
            }
        });

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        return panel;
    }
}