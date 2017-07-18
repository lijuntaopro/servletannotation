package persion.lijuntao.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NewImageUtils {

	/*
	 * 
	 */
	public static BufferedImage watermark(String filePath, String waterFilePath, int x, int y) throws IOException {
		File sourceFile = new File(filePath);
		File waterFile = new File(waterFilePath);
		BufferedImage buffImg = watermark(sourceFile, waterFile, x, y, 1.0f);
		return buffImg;
	}

	/*
	 * 
	 */
	public static BufferedImage watermark(byte[] filePath, byte[] waterFilePath, int x, int y) throws IOException {

		ByteArrayInputStream sourceStream = new ByteArrayInputStream(filePath);
		ByteArrayInputStream waterStream = new ByteArrayInputStream(waterFilePath);
		BufferedImage buffImg = watermark(sourceStream, waterStream, x, y, 1.0f);

		return buffImg;
	}

	/*
	 * 
	 */
	public static byte[] watermarkBytes(byte[] filePath, byte[] waterFilePath, int x, int y) throws IOException {

		ByteArrayInputStream sourceStream = new ByteArrayInputStream(filePath);
		ByteArrayInputStream waterStream = new ByteArrayInputStream(waterFilePath);
		BufferedImage buffImg = watermark(sourceStream, waterStream, x, y, 1.0f);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ImageIO.write(buffImg, "png", stream);
		return stream.toByteArray();
	}

	/*
	 * 
	 */
	public static BufferedImage watermark(ByteArrayInputStream file, ByteArrayInputStream waterFile, int x, int y,
			float alpha) throws IOException {
		// 获取底图
		BufferedImage buffImg = ImageIO.read(file);
		BufferedImage waterImg = ImageIO.read(waterFile);
		watermark(buffImg, waterImg, x, y, alpha);
		return buffImg;
	}

	/**
	 * 
	 * @Title: 构造图片
	 * @Description: 生成水印并返回java.awt.image.BufferedImage
	 * @param file
	 *            源文件(图片)
	 * @param waterFile
	 *            水印文件(图片)
	 * @param x
	 *            距离右下角的X偏移量
	 * @param y
	 *            距离右下角的Y偏移量
	 * @param alpha
	 *            透明度, 选择值从0.0~1.0: 完全透明~完全不透明
	 * @return BufferedImage
	 * @throws IOException
	 */
	public static BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) throws IOException {
		// 获取底图
		BufferedImage buffImg = ImageIO.read(file);
		// 获取层图
		BufferedImage waterImg = ImageIO.read(waterFile);
		watermark(buffImg, waterImg, x, y, alpha);
		return buffImg;
	}

	/*
	 * 
	 */
	public static BufferedImage watermark(BufferedImage sourceStream, BufferedImage waterImg, int x, int y,
			float alpha) {
		// 创建Graphics2D对象，用在底图对象上绘图
		Graphics2D g2d = sourceStream.createGraphics();
		int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
		int waterImgHeight = waterImg.getHeight();// 获取层图的高度
		// 在图形和图像中实现混合和透明效果
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		// 绘制
		g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
		g2d.dispose();// 释放图形上下文使用的系统资源
		return sourceStream;
	}
	
	/**
	 * 混合图片时，使第二图片为圆角
	 * @author 李俊涛
	 * @date 2017年7月14日
	 * @param sourceStream
	 * @param waterImg
	 * @param x
	 * @param y
	 * @param alpha
	 * @param radius
	 * @return
	 */
	public static BufferedImage watermarkWithRadius(BufferedImage sourceStream, BufferedImage waterImg, int x, int y,
			float alpha,int radius) {
		// 创建Graphics2D对象，用在底图对象上绘图
		Graphics2D g2d = sourceStream.createGraphics();
		int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
		int waterImgHeight = waterImg.getHeight();// 获取层图的高度
		// 在图形和图像中实现混合和透明效果
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setClip(new RoundRectangle2D.Double(x, y, waterImg.getWidth(), waterImg.getHeight(), radius, radius));
		
		// 绘制
		g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
		g2d.dispose();// 释放图形上下文使用的系统资源
		return sourceStream;
	}

	/**
	 * 输出水印图片
	 * 
	 * @param buffImg
	 *            图像加水印之后的BufferedImage对象
	 * @param savePath
	 *            图像加水印之后的保存路径
	 */
	@SuppressWarnings("unused")
	private void generateWaterFile(BufferedImage buffImg, String savePath) {
		int temp = savePath.lastIndexOf(".") + 1;
		try {
			ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/*
	 * 根据文件生产byte数组
	 */
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/*
	 * 根据byte数组，生成文件 
	 */
    public static void getFile(byte[] bfile, String filePath) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                   e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    }
	
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 *             IO异常直接抛出了
	 * @author bls
	 */
	public static void main(String[] args) throws IOException {
		// String sourceFilePath = "D:/终端项目/erweima/shoudan.png";
		// String waterFilePath = "D:/终端项目/erweima/erweima.png";
		// String saveFilePath = "D:/终端项目/erweima/new.png";
		// NewImageUtils newImageUtils = new NewImageUtils();
		// // 构建叠加层
		// BufferedImage buffImg = NewImageUtils.watermark(sourceFilePath,
		// waterFilePath, 100, 250);
		// // 输出水印图片
		// newImageUtils.generateWaterFile(buffImg, saveFilePath);

		byte[] sourceBytes = getBytes("D:/终端项目/erweima/shoudan.png");
		byte[] waterBytes = getBytes("D:/终端项目/erweima/erweima.png");
		String saveFilePath = "D:/终端项目/erweima/new2.png";
		//NewImageUtils newImageUtils = new NewImageUtils();
//		BufferedImage buffImg = NewImageUtils.watermark(sourceBytes, waterBytes, 100, 250);
//		newImageUtils.generateWaterFile(buffImg, saveFilePath);
		
		byte[] bytes = NewImageUtils.watermarkBytes(sourceBytes, waterBytes, 100, 250);
		getFile(bytes,saveFilePath);
	}
	
	/**
	 * 在指定的坐标中，补白指定大小的有边框和圆角的区域
	 * @author 李俊涛
	 * @date 2017年7月14日
	 * @param bs
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param border
	 * @param radius
	 * @return
	 */
	public static BufferedImage createRGBImageAndFillWhite(byte[] bs, int x, int y, int w, int h, int border ,int radius){
		BufferedImage changeBufImg = null;
		try {
			if(bs !=null){
				BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(bs));
				Image img =bufImg.getScaledInstance(bufImg.getWidth(), bufImg.getHeight(), Image.SCALE_SMOOTH);//
				changeBufImg = new BufferedImage(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics2d = changeBufImg.createGraphics();
				graphics2d.drawImage( img, 0, 0, null);  
				
				graphics2d.setColor(Color.WHITE);
				graphics2d.drawRoundRect(x-border, y-border, w+ 2*border-1, h + 2*border-1, radius, radius); 
				graphics2d.fillRoundRect(x-border, y-border, w+ 2*border-1, h + 2*border-1, radius, radius); 
			}
		} catch (IOException e) {
			throw new RuntimeException("指定的坐标中，补白指定大小的有边框和圆角的区域执行出错", e);
		}
		return changeBufImg;
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
				changeBufImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics2d = changeBufImg.createGraphics();
				if(radius > 0){
					graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
}