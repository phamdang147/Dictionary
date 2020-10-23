package UI.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translate {
    public static String call_me(String target) throws Exception {
        target = URLEncoder.encode(target, "ISO-8859-1");
        String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl="
                + "auto" + "&tl=" + "vi" + "&dt=t&q=" + target;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder result = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            boolean haveContains = inputLine.contains("\",\"");
            if (haveContains && !inputLine.endsWith("\"]")) {
                String[] arr = inputLine.split("\"", 3);
                result.append(arr[1]);
            }
        }
        in.close();

        return result.toString();
    }
}
