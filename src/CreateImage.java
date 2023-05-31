
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class CreateImage {
    private static final String BACKGROUND_IMAGE_PATH = "./assets/backgroundimageResults.png";

    /**
     * @param professionalProfiles
     * @param color
     * @param witdh
     * @param y
     * @param graphics2D
     */
    private void rectangle(String professionalProfiles, Color color, int witdh, int y, Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.setFont(new Font("Arial", Font.BOLD, 11));
        graphics2D.drawString(professionalProfiles, 35, y);
        graphics2D.drawRect(200, y - 13, witdh, 20);
        graphics2D.fillRect(200, y - 13, witdh, 20);
    }

    /**
     * @param color
     * @param witdh
     * @param y
     * @param graphics2D
     */

    private void overlay(Color color, int witdh, int y, Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.drawRect(0, y + 1, witdh, 1);
        graphics2D.fillRect(0, y + 1, witdh, 1);
    }


    /**
     * @param color
     * @param fontname
     * @param style
     * @param size
     * @param title
     * @param graphics2D
     */
    private void title(Color color, String fontname, int style, int size, String title, Graphics graphics2D) {
        graphics2D.setColor(color);
        graphics2D.setFont(new Font(fontname, style, size));
        graphics2D.drawString(title, 35, 100);
    }

    /**
     * @param score
     * @throws IOException
     */
    public String barChart(long[] score) throws IOException {

        // Dimensions of the background of the image.
        int width = 600;
        int height = 750;

        //Vertical position of the rectangles.
        int y = 150;

        // Create new bufferedImage.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


        //Create a Graphics2D-object of the bufferedImage.
        Graphics2D graphics2D = bufferedImage.createGraphics();

        //Professional profiles and colors for the rectangles of the bar chart.
        String[] professionalProfiles = {"Back-end Engineer:", "Front-end Engineer:", "Robot UI:", "Robot Technical:", "ICT Ondernemer:"};
        Color[] colors = {Color.BLUE, Color.orange, Color.RED, Color.green, Color.magenta};

        String str = "Als je de tekst wilt splitsen na elke 10 woorden voordat je deze tekent, kun je de bovenstaande regex gebruiken om de tekst te splitsen en vervolgens elke regel afzonderlijk tekenen met behulp va";
        String[] lines = str.replaceAll("((?:[^\\s]*\\s){9}[^\\s]*)\\s", "$1\n").split("\n");
        int z = 350;

        BufferedImage img = ImageIO.read(new File("./assets/fotonao.png")); // Lees de afbeelding van een bestand
        int imgX = 25; // x-coördinaat waar de afbeelding moet worden getekend
        int imgY = z + 100; // y-coördinaat waar de afbeelding moet worden getekend (20 pixels onder de laatste regel tekst)

        //Set backgroundcolor of bufferedImage to white.
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, width, height);

        BufferedImage backgroundImage = ImageIO.read(new File(BACKGROUND_IMAGE_PATH));
        // Draw the background image
        graphics2D.drawImage(backgroundImage, 0, 0, null);

        //Create an object of CreateImage
        CreateImage imageCreator = new CreateImage();

        //Sort the score array from high to low.
        Integer[] sortedIndexes = new Integer[score.length];
        for (int i = 0; i < score.length; i++) {
            sortedIndexes[i] = i;   //each index i of the sortedIndexes array populated with the same value as the original index of the score array.
        }

        Arrays.sort(sortedIndexes, Comparator.comparingLong(i -> -score[i]));
        // Draw rectangles in sorted order.
        for (int i = 0; i < professionalProfiles.length; i++) {
            int index = sortedIndexes[i];
            // Draw rectangles
            imageCreator.rectangle(professionalProfiles[index], colors[index], (int) score[index], y, graphics2D);
            y += 30; //space between bars.
        }
        imageCreator.title(Color.BLACK, "Arial", Font.BOLD, 17, "Your results: ", graphics2D);
        imageCreator.overlay(Color.BLACK, 600, 120, graphics2D);
        imageCreator.overlay(Color.BLACK, 600, 290, graphics2D);

        for (String line : lines) {
            graphics2D.setFont(new Font("Arial", Font.BOLD, 11));
            graphics2D.drawString(line, 25, z);
            z += 20; // Verhoog y-coördinaat met 20 pixels voor elke regel
        }
        graphics2D.drawImage(img, imgX, imgY, null);

        //Clean the Graphics2D.
        graphics2D.dispose();

        //File where the image is saved.
        File file = new File("./QRcode/QRcodes/results/result.png");

        //Write the BufferedImage to the file as a PNG image.
        ImageIO.write(bufferedImage, "png", file);
        return highestScore;
    }

}