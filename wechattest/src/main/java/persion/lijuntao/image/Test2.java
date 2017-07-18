package persion.lijuntao.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;



public class Test2 {
	public static void main(String[] args) {
		try {
			File file = new File("C:\\Users\\Administrator\\Desktop\\hh压缩1.png");
			
			
			byte[] bs = FileUtils.readFileToByteArray(file);
			
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(bs));
			byte[] compress = ImageCompress.compress(image, 1.0f);
			FileUtils.writeByteArrayToFile(new File("C:\\Users\\Administrator\\Desktop\\hh压缩1-2.jpeg"), compress);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
