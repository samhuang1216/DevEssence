package com.gitdevessence.DevEsse.bitmapImg;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PixelArtConverterTest {

	@Test
	void testConvertToPixelArt() {
		String inputPath = "src/test/resources/input.jpg"; // 测试图片路径
		String outputPath = "src/test/resources/output_pixelated.jpg"; // 输出路径
		int pixelSize = 10;

		try {
			// 调用主方法
			PixelArtConverter.convertToPixelArt(inputPath, outputPath, pixelSize);

			// 验证输出文件是否生成
			File outputFile = new File(outputPath);
			assertTrue(outputFile.exists(), "像素化图像未生成");

			// 验证输出文件大小是否合理（简单检查）
			assertTrue(outputFile.length() > 0, "生成的像素化图像文件为空");
		}
		catch (Exception e) {
			fail("测试失败，出现异常：" + e.getMessage());
		}
	}

}