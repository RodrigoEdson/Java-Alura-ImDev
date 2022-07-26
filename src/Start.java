import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Start {
    public static void main(String[] args) throws IOException {
        //var url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-10&end_date=2022-07-20";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var clienteHttp = new ClienteHttp();
        String json = clienteHttp.buscaDados(url);


        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        InputStream inputStream;
        for (Conteudo conteudo : conteudos) {
            inputStream = new URL(conteudo.getUrlImagem()).openStream();
            geradora.cria(inputStream, conteudo.getTitulo());
            System.out.println(conteudo.getTitulo());
        }
    }
}
