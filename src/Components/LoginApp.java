package Components;
import javax.swing.*;
import java.awt.event.*;

public class LoginApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login - Meu App");
        JLabel emailLabel = new JLabel("Email:");
        JLabel senhaLabel = new JLabel("Senha:");
        JTextField emailField = new JTextField();
        JPasswordField senhaField = new JPasswordField();
        JButton loginButton = new JButton("Entrar");

        frame.setSize(350, 200);
        frame.setLayout(null);

        emailLabel.setBounds(30, 30, 80, 25);
        emailField.setBounds(120, 30, 180, 25);
        senhaLabel.setBounds(30, 70, 80, 25);
        senhaField.setBounds(120, 70, 180, 25);
        loginButton.setBounds(120, 110, 100, 30);

        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(senhaLabel);
        frame.add(senhaField);
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());

            // Autenticação simples (substituir por acesso a banco de dados)
            if (email.equals("usuario@email.com") && senha.equals("123456")) {
                JOptionPane.showMessageDialog(frame, "Login realizado com sucesso!");
                InstagramConnector.openInstagramOAuth();
                InstagramConnector.openInstagramOAuth();
                InstagramCodeReceiver.solicitarCodigo();
            } else {
                JOptionPane.showMessageDialog(frame, "Credenciais inválidas.");
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

