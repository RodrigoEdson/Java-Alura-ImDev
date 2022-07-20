import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GeradoraDeFigurinhas {
    public void cria(InputStream inputStream, String nome) throws IOException {
        //InputStream inputStream = new FileInputStream(new File("entrada/filmeGrande.jpg"));
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        int novaLargura = originalImage.getWidth();
        int novaAltura = originalImage.getHeight() + 200;
        var newImage = new BufferedImage(novaLargura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 64));
        graphics.setColor(Color.ORANGE);
        graphics.drawString("Topzera", novaLargura / 2 - 50, novaAltura - (200 - 64) / 2);

        ImageIO.write(newImage, "png", new File("saida/"+nome+".png"));
    }

    public static void main(String[] args) {
        var gerador = new GeradoraDeFigurinhas();
//        try {
//            gerador.cria();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
