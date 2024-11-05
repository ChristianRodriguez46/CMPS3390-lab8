package controllers;

import org.json.JSONObject;
import views.MainView;
import views.ResultsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SearchController {
    private final MainView view;

    public SearchController(MainView view){
        this.view = view;

        view.attachSearchListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = view.getSearchTerm();
                String searchText = view.getSearchText();

                String html = postSearch(searchTerm, searchText);

                ResultsView resultsView = new ResultsView("Search", html);
                new ResultsController(resultsView);

                resultsView.setVisible(true);
            }
        });
    }

    private String postSearch(String term, String text){
        URL url = null;
        Scanner scanner = null;
        JSONObject postBody = new JSONObject();
        StringBuilder htmlRes = new StringBuilder();

        postBody.put("search", term);
        postBody.put("text", text);

        // MAKE API REQUEST AND BUILD RESPONSE STRING
        try {
            url = new URI("https://cs.csub.edu/~paul/3390/lab08/search.php").toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty( "Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "text/html");

            OutputStream os = conn.getOutputStream();
            os.write(postBody.toString().getBytes(StandardCharsets.UTF_8));
            os.close();

            if(conn.getResponseCode() != 200){
                throw new RuntimeException("Invalid Server Request");
            }

            scanner = new Scanner(conn.getInputStream());
            while(scanner.hasNext()){
                htmlRes.append(scanner.nextLine());
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            view.displayError("Unable to get quote!");
        }

        // PARSE JSON OBJECT FROM RESPONSE
        return htmlRes.toString();
   }
}
