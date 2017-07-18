package persion.lijuntao.image;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import net.coobird.thumbnailator.Thumbnails;


public class ImageCompress {
	public static byte[] compress(BufferedImage megerImage,float f){
		ByteArrayOutputStream megerBos = new ByteArrayOutputStream();
		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
		ImageWriteParam param = writer.getDefaultWriteParam();
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Needed see javadoc
		param.setCompressionQuality(f); // Highest quality
		IIOImage iIamge = new IIOImage(megerImage, null, null); 
		try {
			writer.setOutput(ImageIO  
			        .createImageOutputStream(megerBos));
			writer.write(null, iIamge, param); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return megerBos.toByteArray();
	}
	
	/**
	 * 按指定大小获取圆角彩色类型图片
	 * @author 李俊涛
	 * @date 2017年7月14日
	 * @param bs
	 * @param w
	 * @param h
	 * @param radius
	 * @return
	 */
	public static BufferedImage createRadiusRGBImage(byte[] bs,int w, int h, int radius){
		BufferedImage changeBufImg = null;
		try {
			if(bs !=null){
				BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(bs));
				Image img =bufImg.getScaledInstance(w, h, Image.SCALE_SMOOTH);//
				changeBufImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				Graphics2D graphics2d = changeBufImg.createGraphics();
				graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 
				//BasicStroke stroke = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
				//graphics2d.setStroke(stroke);
				if(radius > 0){
					graphics2d.setClip(new RoundRectangle2D.Double(0, 0, w, h, radius, radius));
				}
				
				graphics2d.drawImage( img, 0, 0, null);  
				
				graphics2d.setColor(Color.WHITE);
				graphics2d.drawRoundRect(-1, -1, w+1, h+1, radius, radius);    
				
			}
		} catch (IOException e) {
			throw new RuntimeException("按大小转换为新的彩色圆角图片流出错", e);
		}
		return changeBufImg;
	}
	
	public static BufferedImage createRadiusRGBImage2(byte[] bs,int w, int h, int radius){
		BufferedImage changeBufImg = null;
		try {
			if(bs !=null){
				BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(bs));
				Graphics2D graphics = bufImg.createGraphics();
				graphics.setBackground(Color.WHITE);
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics.setClip(new RoundRectangle2D.Double(0, 0, bufImg.getWidth(), bufImg.getHeight(), bufImg.getWidth()/w*radius, bufImg.getWidth()/w*radius));
				
				if(1 == 1){
					return bufImg;
				}
			//	graphics.fill(new RoundRectangle2D.Double(0, 0, bufImg.getWidth(), bufImg.getHeight(), bufImg.getWidth()/w*radius, bufImg.getWidth()/w*radius));
				Image img =bufImg.getScaledInstance(w, h, Image.SCALE_SMOOTH);//
				changeBufImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics2d = changeBufImg.createGraphics();
				if(radius > 0){
					int bold = 5; 
					
					graphics2d.setStroke(new BasicStroke(bold, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL)); 
					graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				//	graphics2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
				//	graphics2d.setClip(new RoundRectangle2D.Double(0, 0, w, h, radius, radius));
				}
				
				graphics2d.drawImage( img, 0, 0, null);  
				
				//graphics2d.setColor(Color.WHITE);
				//graphics2d.drawRoundRect(-1, -1, w+1, h+1, radius, radius);    
				
			}
		} catch (IOException e) {
			throw new RuntimeException("按大小转换为新的彩色圆角图片流出错", e);
		}
		return changeBufImg;
	}
	
	public static BufferedImage createRadiusRGBImage3(byte[] bs,int w, int h, int radius){
		DateFormat format = new SimpleDateFormat("HH:mm:ss,SSS");
		System.out.println(format.format(new Date()));
		BufferedImage changeBufImg = null;
		try {
			if(bs !=null){
				BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(bs));
				
				BufferedImage bufImg2 = new BufferedImage(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.TYPE_INT_RGB); 
				Graphics2D graphics = bufImg2.createGraphics();
				graphics.setBackground(Color.WHITE);
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics.setClip(new RoundRectangle2D.Double(0, 0, bufImg.getWidth(), bufImg.getHeight(), bufImg.getWidth()/w*radius, bufImg.getHeight()/w*radius));
				Image image = bufImg.getScaledInstance(bufImg2.getWidth(),bufImg2.getHeight(), Image.SCALE_DEFAULT);
				
				graphics.drawImage(image, 0, 0 , null);
				
				//	graphics.fill(new RoundRectangle2D.Double(0, 0, bufImg.getWidth(), bufImg.getHeight(), bufImg.getWidth()/w*radius, bufImg.getWidth()/w*radius));
				Image img =bufImg2.getScaledInstance(w, h, Image.SCALE_SMOOTH);//
				changeBufImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics2d = changeBufImg.createGraphics();
				if(radius > 0){
					//int bold = 5; 
					
					//graphics2d.setStroke(new BasicStroke(bold, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL)); 
					//graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					//	graphics2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
					//	graphics2d.setClip(new RoundRectangle2D.Double(0, 0, w, h, radius, radius));
				}
				
				graphics2d.drawImage( img, 0, 0, null);  
				
				//graphics2d.setColor(Color.WHITE);
				//graphics2d.drawRoundRect(-1, -1, w+1, h+1, radius, radius);    
				
			}
			System.out.println(format.format(new Date()));
		} catch (IOException e) {
			throw new RuntimeException("按大小转换为新的彩色圆角图片流出错", e);
		}
		return changeBufImg;
	}
	
	public static boolean ThumbnailsCompressPic(String inputFile, String outputFile, int size, float quality) {
        File input = new File(inputFile);
        try {
            Thumbnails.Builder<File> fileBuilder = Thumbnails.of(input).scale(1.0).outputQuality(1.0);
            BufferedImage src = fileBuilder.asBufferedImage();
            if(src.getHeight(null) > size || src.getWidth(null) > size) {
                Thumbnails.Builder<File> builder = Thumbnails.of(input);
                builder.size(size, size); //取最大的尺寸变成size，然后等比缩放
                builder.outputQuality(quality).toFile(outputFile);
            } else {
                Thumbnails.of(input).scale(1.0).outputQuality(quality).toFile(outputFile);
            }
            return true;
        } catch (IOException e) {
        	
        }
        return false;
    }
}
