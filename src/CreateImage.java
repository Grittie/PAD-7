package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateImage {

    // Create Rectangle
    public void rectangle(String onderwerp, Color color, int witdh, int y, Graphics2D graphics2D, int i) {
        graphics2D.setColor(color);
        graphics2D.drawString(onderwerp, 25, y);
        graphics2D.drawRect(200, y -13, witdh, 20);
        graphics2D.fillRect(200, y -13, witdh, 20);

    }

    public void staafDiagram() throws IOException {
        int width = 400;
        int height = 200;
        int y = 50;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = bufferedImage.createGraphics();

        String[] onderwerp = {"Software-Engineering:", "Scrum-master:", "IT-ondernemer:", "Lars:", "Luka:"};
        int[] results = {140, 150, 100, 80, 90}; // results omrekenen voor juiste staafbreedte
        Color[] kleuren = {Color.BLUE, Color.orange, Color.RED, Color.green, Color.yellow};

        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, width, height);

        CreateImage imageCreator = new CreateImage();


        for (int i = 0; i < onderwerp.length; i++) {
            imageCreator.rectangle(onderwerp[i], kleuren[i], results[i], y, graphics2D, i);
            y += 30;
        }

        graphics2D.dispose();

        File file = new File("mijnImage.png");
        ImageIO.write(bufferedImage, "png", file);
    }
}
