package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateImage {

    public void rectangle(String onderwerp, int witdh, int y, Graphics2D graphics2D) {
        graphics2D.setColor(Color.black);
        graphics2D.drawString(onderwerp, 20, y);
        graphics2D.drawRect(50, 50, witdh, 20);
        graphics2D.fillRect(50, 50, witdh, 20);

    }

    public void staafDiagram() throws IOException {
        int width = 300;
        int height = 300;


        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = bufferedImage.createGraphics();

        String[] onderwerp = {"Software-Engineering", "BIM"};
        int[] results = {140, 150};


        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, width, height);

//        graphics2D.setColor(Color.black);
//        graphics2D.drawString("1.", 20, 60);                // Y + 50
//        graphics2D.drawRect(50, 50, score2, 20);
//        graphics2D.fillRect(50, 50, 140, 20);
//
//        graphics2D.setColor(Color.black);
//        graphics2D.drawString("2.", 20, 110);
//        graphics2D.drawRect(50, 100, score1, 20);
//
        int y = 50;
        for (int i = 0; i < onderwerp.length; i++) {
            this.rectangle(onderwerp[i],results[i],y, graphics2D);
            y += 50;
        }

        graphics2D.dispose();

        File file = new File("mijnImage.png");
        ImageIO.write(bufferedImage, "png", file);
    }
}
