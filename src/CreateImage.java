
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class CreateImage {

    public void rectangle(String onderwerp, Color color, int witdh, int y, Graphics2D graphics2D, int i) {
        graphics2D.setColor(color);
        graphics2D.drawString(onderwerp, 25, y);
        graphics2D.drawRect(200, y - 13, witdh, 20);
        graphics2D.fillRect(200, y - 13, witdh, 20);
    }

    public String staafDiagram(long[] score) throws IOException {

        // Dimensions of the background of the image
        int width = 500;
        int height = 1000;

        //Vertical position of the rectangles
        int y = 50;

        // Maak een nieuw BufferedImage
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Maak een Graphics2D-object van het BufferedImage
        Graphics2D graphics2D = bufferedImage.createGraphics();

        // Onderwerpen en kleuren voor de rechthoeken
        String[] onderwerp = {"Back-end Engineer:", "Front-end Engineer:", "Robot UI:", "Robot Technical:", "ICT Ondernemer:"};
        Color[] kleuren = {Color.BLUE, Color.orange, Color.RED, Color.green, Color.magenta};

        // Stel de achtergrondkleur van het BufferedImage in op wit
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, width, height);

        // Maak een instantie van CreateImage
        CreateImage imageCreator = new CreateImage();

        // Sorteer de score array van hoog naar laag
        Integer[] sortedIndexes = new Integer[score.length];
        for (int i = 0; i < score.length; i++) {
            sortedIndexes[i] = i; //elke index i van de sortedIndices-array gevuld met dezelfde waarde als de oorspronkelijke index van de score-array.
        }

        Arrays.sort(sortedIndexes, Comparator.comparingLong(i -> -score[i]));
        String hoogste = null;

        // Teken de rechthoeken in de gesorteerde volgorde
        for (int i = 0; i < onderwerp.length; i++) {
            int index = sortedIndexes[i];
            // Teken rechthoek
            imageCreator.rectangle(onderwerp[index], kleuren[index], (int) score[index], y, graphics2D, i);
            if (i==0){
                hoogste = onderwerp[index];
            }
            y += 30;
        }
        System.out.println(hoogste);

        // Maak de Graphics2D schoon
        graphics2D.dispose();

        // Bestand waarin de afbeelding wordt opgeslagen
        File file = new File("./QRcode/QRcodes/results/result.png");

        // Schrijf het BufferedImage naar het bestand als een PNG-afbeelding
        ImageIO.write(bufferedImage, "png", file);
        return hoogste;
    }


}