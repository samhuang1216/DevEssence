package com.gitdevessence.DevEsse.bitmapImg;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PixelArtConverter {

	/**
	 * 将图像转换为像素化效果。
	 * @param inputPath 输入图像路径
	 * @param outputPath 输出图像路径
	 * @param pixelSize 像素块大小（越大像素化效果越明显）
	 * @throws Exception 如果处理图像时出错
	 */
	public static void convertToPixelArt(String inputPath, String outputPath, int pixelSize) throws Exception {
		// 加载输入图像
		BufferedImage inputImage = ImageIO.read(new File(inputPath));
		int width = inputImage.getWidth();
		int height = inputImage.getHeight();

		// 创建像素化图像
		BufferedImage pixelatedImage = new BufferedImage(width, height, inputImage.getType());
		Graphics2D g2d = pixelatedImage.createGraphics();

		// 遍历图像块
		for (int y = 0; y < height; y += pixelSize) {
			for (int x = 0; x < width; x += pixelSize) {
				// 获取当前块的颜色
				int pixelColor = inputImage.getRGB(x, y);

				// 绘制当前块
				g2d.setColor(new Color(pixelColor, true));
				g2d.fillRect(x, y, pixelSize, pixelSize);
			}
		}

		g2d.dispose();

		// 保存像素化图像
		ImageIO.write(pixelatedImage, "jpg", new File(outputPath));
	}

}