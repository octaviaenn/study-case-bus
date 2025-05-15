import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SignUpPage {
    private BusBookingApp app;

    public SignUpPage(BusBookingApp app) {
        this.app = app;
    }

    public static void erase(JTextField input, JPanel panel){
        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                input.setText("");
                input.removeKeyListener(this);
                panel.revalidate();
            }
        };
        input.addKeyListener(keyListener);
    }

    public JPanel createSignUpPage() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(new Dimension(700, 700));
        panel.setBackground(new Color(0xB97179)); // Pink (#B97179)

        // Center content
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel title = new JLabel("Sign Up");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel description = new JLabel("Nikmati pemesanan tiket bus yang lebih mudah dan aman");
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setForeground(Color.WHITE);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Field nomor telepon
        JTextField phoneField = new JTextField("Phone Number");
        erase(phoneField, panel);
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
        erase(emailField, panel);
        emailField.setMaximumSize(new Dimension(250, 40));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setForeground(Color.DARK_GRAY);
        emailField.setBackground(Color.WHITE);

        JLabel emailErrorLabel = new JLabel("");
        emailErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        emailErrorLabel.setForeground(Color.BLACK);
        emailErrorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label utk password
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

        // Label utk confirm password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setMaximumSize(new Dimension(250, 40));
        confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmPasswordField.setForeground(Color.DARK_GRAY);
        confirmPasswordField.setBackground(Color.WHITE);

        JLabel passwordErrorLabel = new JLabel("");
        passwordErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordErrorLabel.setForeground(Color.BLACK);
        passwordErrorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JCheckBox privacyPolicy = new JCheckBox("I agree with privacy policy");
        privacyPolicy.setBackground(new Color(0xB97179));
        privacyPolicy.setForeground(Color.WHITE);
        privacyPolicy.setFont(new Font("Arial", Font.PLAIN, 14));
        privacyPolicy.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signUpButton = new JButton("SIGN UP");
        signUpButton.setBackground(new Color(0xF4C7CA)); // Pink muda (#F4C7CA)
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16));
        signUpButton.setFocusPainted(false);
        signUpButton.setBorderPainted(false);
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setMaximumSize(new Dimension(200, 50));

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
        centerPanel.add(confirmPasswordLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(confirmPasswordField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(passwordErrorLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(privacyPolicy);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(signUpButton);
        centerPanel.add(Box.createVerticalGlue());

        // Bottom panel untuk opsi sudah punya akun
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        JLabel signInPrompt = new JLabel("You already have an account? Sign in");
        signInPrompt.setFont(new Font("Arial", Font.PLAIN, 14));
        signInPrompt.setForeground(Color.WHITE);
        bottomPanel.add(signInPrompt);

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0xFFFFFF)); // Putih (#FFFFFF)
        backButton.setForeground(new Color(0xB97179));
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.addActionListener(e -> app.showPage("SignIn"));

        bottomPanel.add(backButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        signUpButton.addActionListener(e -> {
            String phoneText = phoneField.getText().trim();
            String emailText = emailField.getText().trim();
            String passwordText = new String(passwordField.getPassword());
            String confirmPasswordText = new String(confirmPasswordField.getPassword());

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

            // Validasi password dan confirm password (harus sama)
            boolean isPasswordValid = passwordText.equals(confirmPasswordText);
            if (!isPasswordValid) {
                passwordErrorLabel.setText("Password tidak sesuai");
            } else {
                passwordErrorLabel.setText("");
            }

            // Validasi kebijakan privasi
            boolean isPrivacyAccepted = privacyPolicy.isSelected();
            if (!isPrivacyAccepted) {
                JOptionPane.showMessageDialog(app, "Silakan setujui kebijakan privasi!");
                return;
            }

            // Jika semua valid, simpan data dan lanjut ke halaman utama
            if (isPhoneValid && isEmailValid && isPasswordValid) {
                // Simpan data pengguna
                User user = new User(phoneText, emailText, passwordText);
                app.setCurrentUser(user);
                JOptionPane.showMessageDialog(app, "Akun berhasil dibuat!");
                Main.getApp(); // pindah ke halaman pemesanan tiket bus
            }
        });

        signInPrompt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                app.showPage("SignIn");
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signInPrompt.setForeground(new Color(0xF4C7CA));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signInPrompt.setForeground(Color.WHITE);
            }
        });

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        return panel;
    }
}