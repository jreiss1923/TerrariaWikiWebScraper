import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

import java.net.URLEncoder;

public class WebScraperNEU {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("What item do you want?");
        String searched = input.nextLine();
        String[] arraySearched = searched.split(" ");
        for (int i = 0; i < arraySearched.length; i++) {
            arraySearched[i] = StringUtils.capitalize(arraySearched[i]);
        }
        String searchQuery = "".join("_", arraySearched);
        System.out.println(searchQuery);

        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
            String searchUrl = "https://terraria.gamepedia.com/" + URLEncoder.encode(searchQuery, "UTF-8");
            HtmlPage page = client.getPage(searchUrl);
            String text = page.asText();
            String[] textArr = text.split("\n");
            boolean wantToPrint = false;
            for (int i = 0; i < textArr.length; i++) {
                if (textArr[i].replaceAll("[^(A-Za-z0-9 ]","").equals("".join(" ", arraySearched))) {
                    wantToPrint = true;
                }
                else if (textArr[i].trim().equals("History[edit | edit source]")) {
                    break;
                }
                if (wantToPrint) {
                    System.out.println(textArr[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


