package Components;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class InstagramTokenFetcher {
    private static final String CLIENT_ID = "SEU_CLIENT_ID";
    private static final String CLIENT_SECRET = "SEU_CLIENT_SECRET";
    private static final String REDIRECT_URI = "https://localhost/";

    public static void exchangeCodeForToken(String code) {
        try {
            URL url = new URL("https://api.instagram.com/oauth/access_token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String params = "client_id=" + CLIENT_ID +
                    "&client_secret=" + CLIENT_SECRET +
                    "&grant_type=authorization_code" +
                    "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8") +
                    "&code=" + URLEncoder.encode(code, "UTF-8");

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();
            String inputLine;
            StringBuilder response = new StringBuilder();

            int responseCode = conn.getResponseCode();
            BufferedReader reader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String json = response.toString();
                String accessToken = extrairAccessToken(json);
                if (accessToken != null) {
                    InstagramProfileFetcher.buscarPerfil(accessToken);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao extrair o access token.");
                }
                InstagramProfileFetcher.buscarPerfil(accessToken);
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }



            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            JOptionPane.showMessageDialog(null, "Resposta da API:\n" + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao obter access_token: " + e.getMessage());
        }
    }

    private static String extrairAccessToken(String json) {
        try {
            int start = json.indexOf("\"access_token\":\"");
            if (start == -1) return null;

            start += "\"access_token\":\"".length();
            int end = json.indexOf("\"", start);
            if (end == -1) return null;

            return json.substring(start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
