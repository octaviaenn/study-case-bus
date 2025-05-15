import javax.swing.*;
import java.awt.*;

public class BusBookingApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private User currentUser; // Untuk menyimpan data pengguna yg login

    public BusBookingApp() {
        setTitle("Pemesanan Tiket Bus Busy3p");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new OnboardingPage1(this).createOnboardingPage1(), "OnboardingPage1");
        mainPanel.add(new OnboardingPage2(this).createOnboardingPage2(), "OnboardingPage2");
        mainPanel.add(new GetStartedPage(this).createGetStartedPage(), "GetStarted");
        mainPanel.add(new SignInPage(this).createSignInPage(), "SignIn");
        mainPanel.add(new SignUpPage(this).createSignUpPage(), "SignUp");

        // Panel pemesanan tiket bus
//        JPanel busBookingPanel = new JPanel(new BorderLayout());
//
//        Bus bus = new Bus(new Order(this, busBookingPanel), this);
//        busBookingPanel.add(bus, BorderLayout.CENTER);
//        busBookingPanel.add(new Route(this), BorderLayout.SOUTH);
//        mainPanel.add(busBookingPanel, "BusBooking");

        add(mainPanel);
        cardLayout.show(mainPanel, "OnboardingPage1"); // mastiin mulainya dari OnboardingPage1
        setVisible(true);
    }

    public void showPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }

    // Setter untuk nyimpan user yg lagi login
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Getter untuk mengakses data user
    public User getCurrentUser() {
        return currentUser;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BusBookingApp::new);
    }
}

// Class untuk menyimpan data pengguna
class User {
    private String phone;
    private String email;
    private String password;

    public User(String phone, String email, String password) {
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}