import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.URLEncoder;

public class WebScraperNEU {

    public static void main(String[] args) {

        String searchQuery = "Bezoar";

        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
            String searchUrl = "https://terraria.gamepedia.com/" + URLEncoder.encode(searchQuery, "UTF-8");
            HtmlPage page = client.getPage(searchUrl);
            String text = page.asText();
            String[] textArr = text.split("\n");
            for(int i = 0; i < textArr.length; i++) {
                if(textArr[i].replaceAll("[^A-Za-z0-9]","").equals("Bezoar")) {
                    System.out.println("Hewwo uwu");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


