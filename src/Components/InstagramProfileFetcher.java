package Components;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class InstagramProfileFetcher {
    public static void buscarPerfil(String accessToken) {
        try {
            String urlStr = "https://graph.instagram.com/me"
                    + "?fields=id,username,account_type"
                    + "&access_token=" + URLEncoder.encode(accessToken, "UTF-8");

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            BufferedReader reader;

            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder resposta = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();

            JOptionPane.showMessageDialog(null, "Dados do perfil:\n" + resposta.toString());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao buscar perfil: " + e.getMessage());
        }
    }
}

