package Main;

 
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
/*
This class crops the BufferedImages so that it fits within the 16x16 tiles 
*/
public class UtilityTool {
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		// Scale image
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		return scaledImage;
	}
}
