package Components;

import java.awt.Desktop;
import java.net.URI;

public class InstagramConnector {
    // Pegue esses dados no Facebook Developers após criar seu app
    private static final String CLIENT_ID = "SEU_CLIENT_ID";
    private static final String REDIRECT_URI = "https://localhost/";
    private static final String AUTH_URL = "https://api.instagram.com/oauth/authorize";

    public static void openInstagramOAuth() {
        try {
            String url = AUTH_URL + "?client_id=" + CLIENT_ID
                    + "&redirect_uri=" + REDIRECT_URI
                    + "&scope=user_profile,user_media"
                    + "&response_type=code";

            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
