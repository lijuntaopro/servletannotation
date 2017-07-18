package persion.lijuntao.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Test;



public class Test3 {
	@Test
	public void test2() throws Exception{
		File file = new File("C:\\Users\\Administrator\\Desktop\\hh.jpg");
		
		
		byte[] bs = FileUtils.readFileToByteArray(file);
		
		BufferedImage image = ImageCompress.createRadiusRGBImage3(bs, 130, 130, 20);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", bos);
		
		ImageCompress.ThumbnailsCompressPic("C:\\Users\\Administrator\\Desktop\\hh压缩1.png", "C:\\Users\\Administrator\\Desktop\\hh压缩1-4.jpeg", 60, 1.0f);
		FileUtils.writeByteArrayToFile(new File("C:\\Users\\Administrator\\Desktop\\hh压缩1-3.jpeg"), bos.toByteArray());
	}
	
	@Test
	public void test3() throws Exception{
		File file = new File("C:\\Users\\Administrator\\Desktop\\hh.jpg");
		byte[] big = FileUtils.readFileToByteArray(file);
		
		File file2 = new File("C:\\Users\\Administrator\\Desktop\\hh压缩1-3.jpeg");
		byte[] small = FileUtils.readFileToByteArray(file2);
		
		BufferedImage image = NewImageUtils.watermark(big, small, 1000, 1000);
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\hebing.png");
		
		ImageIO.write(image, "png", fos);
	}
	
}
