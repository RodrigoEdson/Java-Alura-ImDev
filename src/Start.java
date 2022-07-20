import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Start {
    public static void main(String[] args) throws IOException {
        var url = "https://alura-imdb-api.herokuapp.com/movies";
        var uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();

        String body;
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            body = response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        var geradora = new GeradoraDeFigurinhas();
        InputStream inputStream;
        for (Map<String, String> filme : listaDeFilmes) {
            inputStream = new URL(filme.get("image")).openStream();
            geradora.cria(inputStream, filme.get("title"));
            System.out.println(filme.get("title"));
        }
    }
}
