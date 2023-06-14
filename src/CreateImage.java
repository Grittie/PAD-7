package src;

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
     *
     * @param score The scores for the bar charts.
     * @return The highest score.
     * @throws IOException
     */
    public String barChart(long[] score) throws Exception {
        int width = 600;
        int height = 750;
        int y = 150;

        BufferedImage bufferedImage = createBufferedImage(width, height);
        Graphics2D graphics2D = bufferedImage.createGraphics();

        String[] professionalProfiles = {"Back-end Engineer:", "Front-end Engineer:", "Robot UI:", "Robot Technical:", "ICT Entrepreneur:"};
        Color[] colors = {Color.BLUE, Color.orange, Color.RED, Color.green, Color.magenta};

        Integer[] sortedIndexes = sortScores(score);
        drawBarChart(professionalProfiles, colors, sortedIndexes, score, y, graphics2D);
        String highestScore = professionalProfiles[sortedIndexes[0]];

        title(Color.BLACK, "Arial", Font.BOLD, 17, "Your results: ", graphics2D);
        overlay(Color.BLACK, 600, 120, graphics2D);
        overlay(Color.BLACK, 600, 290, graphics2D);

        processPresentationData(highestScore, graphics2D);

        graphics2D.dispose();
        File file = new File("./QRcode/QRcodes/results/result.png");
        ImageIO.write(bufferedImage, "png", file);

        return highestScore;
    }

    /**
     * Creates a BufferedImage with the specified width and height.
     *
     * @param width  The width of the image.
     * @param height The height of the image.
     * @return The created BufferedImage.
     * @throws IOException
     */
    private BufferedImage createBufferedImage(int width, int height) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, width, height);

        BufferedImage backgroundImage = ImageIO.read(new File(BACKGROUND_IMAGE_PATH));
        graphics2D.drawImage(backgroundImage, 0, 0, null);

        return bufferedImage;
    }

    /**
     * Sorts the scores in descending order and returns the sorted indexes.
     *
     * @param scores The scores to be sorted.
     * @return The sorted indexes.
     */
    private Integer[] sortScores(long[] scores) {
        Integer[] sortedIndexes = new Integer[scores.length];
        for (int i = 0; i < scores.length; i++) {
            sortedIndexes[i] = i;
        }
        Arrays.sort(sortedIndexes, Comparator.comparingLong(i -> -scores[i]));
        return sortedIndexes;
    }

    /**
     * Draws the bar chart using the professional profiles, colors, sorted indexes, scores, and graphics object.
     *
     * @param professionalProfiles The professional profiles.
     * @param colors               The colors for the bars.
     * @param sortedIndexes        The sorted indexes.
     * @param scores               The scores.
     * @param y                    The vertical position of the bars.
     * @param graphics2D           The Graphics2D object.
     */
    private void drawBarChart(String[] professionalProfiles, Color[] colors, Integer[] sortedIndexes, long[] scores, int y, Graphics2D graphics2D) {
        for (int i = 0; i < professionalProfiles.length; i++) {
            int index = sortedIndexes[i];
            rectangle(professionalProfiles[index], colors[index], (int) scores[index] * 5, y, graphics2D);
            y += 30;
        }
    }

    /**
     * Processes the presentation data for the highest score and draws it on the image.
     *
     * @param highestScore The highest score professional profile.
     * @param graphics2D   The Graphics2D object.
     * @throws IOException
     */
    private void processPresentationData(String highestScore, Graphics2D graphics2D) throws IOException {
        Presentations presentations = new Presentations();
        String str = presentations.parseJson(highestScore);
        String[] lines = str.replaceAll("((?:[^\\s]*\\s){9}[^\\s]*)\\s", "$1\n").split("\n");

        int StringY = 350;
        for (String line : lines) {
            graphics2D.setFont(new Font("Arial", Font.BOLD, 11));
            graphics2D.drawString(line, 25, StringY);
            StringY += 20;
        }

        BufferedImage img = ImageIO.read(new File("./assets/fotonao.png"));
        int imgX = 25;
        int imgY = StringY + 100;
        graphics2D.drawImage(img, imgX, imgY, null);
    }

    /**
     * Draws a rectangle with the specified professional profile, color, width, y position, and graphics object.
     *
     * @param professionalProfiles The professional profile.
     * @param color                The color of the rectangle.
     * @param width                The width of the rectangle.
     * @param y                    The y position of the rectangle.
     * @param graphics2D           The Graphics2D object.
     */
    private void rectangle(String professionalProfiles, Color color, int width, int y, Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.setFont(new Font("Arial", Font.BOLD, 11));
        graphics2D.drawString(professionalProfiles, 35, y);
        graphics2D.drawRect(200, y - 13, width, 20);
        graphics2D.fillRect(200, y - 13, width, 20);
    }

    /**
     * Draws the title with the specified color, font, style, size, text, and graphics object.
     *
     * @param color     The color of the title.
     * @param fontName  The font name of the title.
     * @param style     The style of the title.
     * @param size      The size of the title.
     * @param title     The text of the title.
     * @param graphics2D The Graphics2D object.
     */
    private void title(Color color, String fontName, int style, int size, String title, Graphics graphics2D) {
        graphics2D.setColor(color);
        graphics2D.setFont(new Font(fontName, style, size));
        graphics2D.drawString(title, 35, 100);
    }

    /**
     * Draws an overlay line with the specified color, width, y position, and graphics object.
     *
     * @param color     The color of the overlay.
     * @param width     The width of the overlay.
     * @param y         The y position of the overlay.
     * @param graphics2D The Graphics2D object.
     */
    private void overlay(Color color, int width, int y, Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.drawRect(0, y + 1, width, 1);
        graphics2D.fillRect(0, y + 1, width, 1);
    }
}
