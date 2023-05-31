
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class CreateImage {
    /**
     * @param professionalProfiles
     * @param color
     * @param witdh
     * @param y
     * @param graphics2D
     * @param i
     */
    private void rectangle(String professionalProfiles, Color color, int witdh, int y, Graphics2D graphics2D, int i) {
        graphics2D.setColor(color);
        graphics2D.drawString(professionalProfiles, 25, y);
        graphics2D.drawRect(200, y - 13, witdh, 20);
        graphics2D.fillRect(200, y - 13, witdh, 20);
    }

    /**
     * @param score
     * @throws IOException
     */
    public String barChart(long[] score) throws IOException {

        // Dimensions of the background of the image.
        int width = 500;
        int height = 1000;

        //Vertical position of the rectangles.
        int y = 50;

        // Create new bufferedImage.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //Create a Graphics2D-object of the bufferedImage.
        Graphics2D graphics2D = bufferedImage.createGraphics();

        //Professional profiles and colors for the rectangles of the bar chart.
        String[] professionalProfiles = {"Back-end Engineer:", "Front-end Engineer:", "Robot UI:", "Robot Technical:", "ICT Ondernemer:"};
        Color[] colors = {Color.BLUE, Color.orange, Color.RED, Color.green, Color.magenta};

        //Set backgroundcolor of bufferedImage to white.
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, width, height);

        //Create an object of CreateImage
        CreateImage imageCreator = new CreateImage();

        //Sort the score array from high to low.
        Integer[] sortedIndexes = new Integer[score.length];
        for (int i = 0; i < score.length; i++) {
            sortedIndexes[i] = i;   //each index i of the sortedIndexes array populated with the same value as the original index of the score array.
        }

        Arrays.sort(sortedIndexes, Comparator.comparingLong(i -> -score[i]));
        String highestScore = null;

        // Draw rectangles in sorted order.
        for (int i = 0; i < professionalProfiles.length; i++) {
            int index = sortedIndexes[i];
            // Teken rechthoek
            imageCreator.rectangle(professionalProfiles[index], colors[index], (int) score[index], y, graphics2D, i);
            if (i==0){
                highestScore = professionalProfiles[index];
            }
            y += 30;
        }
        System.out.println(highestScore);

        //Clean the Graphics2D.
        graphics2D.dispose();

        //File where the image is saved.
        File file = new File("./QRcode/QRcodes/results/result.png");

        //Write the BufferedImage to the file as a PNG image.
        ImageIO.write(bufferedImage, "png", file);
        return highestScore;
    }
}