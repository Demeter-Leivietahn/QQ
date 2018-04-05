package Util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class photo {
	public Icon ph(int width, int height, String filepath) {
		try {
			File file = new File(filepath);
			BufferedImage image = ImageIO.read(file);

			double scaleWidth = (double) width / (double) image.getWidth();
			double scaleHeight = (double) height / (double) image.getHeight();

			BufferedImage dstImage = new BufferedImage(width, height, image.getType());

			AffineTransform affineTransform = new AffineTransform();
			affineTransform.scale(scaleWidth, scaleHeight);
			AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, 2);
			affineTransformOp.filter(image, dstImage);

			Icon logoImage1 = new ImageIcon(dstImage);
			return logoImage1;

		} catch (Exception e) {

		}
		return null;
	}
}
