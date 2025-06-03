package Components;
import javax.swing.*;

public class InstagramCodeReceiver {
    public static void solicitarCodigo() {
        JFrame frame = new JFrame("Código do Instagram");
        JLabel label = new JLabel("Cole o código da URL:");
        JTextField codeField = new JTextField();
        JButton enviar = new JButton("Enviar");

        frame.setSize(400, 150);
        frame.setLayout(null);

        label.setBounds(20, 20, 200, 25);
        codeField.setBounds(20, 50, 350, 25);
        enviar.setBounds(140, 85, 100, 30);

        frame.add(label);
        frame.add(codeField);
        frame.add(enviar);

        enviar.addActionListener(e -> {
            String code = codeField.getText().trim();
            if (!code.isEmpty()) {
                InstagramTokenFetcher.exchangeCodeForToken(code);
            } else {
                JOptionPane.showMessageDialog(frame, "Código não pode ser vazio.");
            }
        });

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
