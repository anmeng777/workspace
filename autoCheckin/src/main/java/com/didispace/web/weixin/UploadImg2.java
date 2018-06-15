package com.didispace.web.weixin;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class UploadImg2 {

	/**
	 * 模拟表单进行多媒体传输
	 * 
	 * @param requestUrl
	 *            （请求地址）
	 * @param requestMethod
	 *            (默认方法)
	 * @param fileName
	 *            （文件名字）
	 * @param contentType
	 *            (文件类型)
	 * @param file
	 *            (文件内容)
	 * @return
	 * @throws Exception
	 */
	public static JsonObject uploadMultimedia(String requestUrl, String requestMethod, String fileName,
			String contentType, String filePath) throws Exception {
		String result = null;
		JsonObject jsonObject = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod(requestMethod); // 以Post方式提交表单，默认get方式
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false); // post方式不能使用缓存

			// 设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			// 设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			StringBuilder sb = new StringBuilder();
			sb.append("--"); // 必须多两道线
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + fileName + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");

			byte[] head = sb.toString().getBytes("utf-8");
			OutputStream out = new DataOutputStream(con.getOutputStream());
			out.write(head);

			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();

			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
			out.write(foot);

			out.flush();
			out.close();

			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				if (result == null) {
					result = buffer.toString();
				}
			} catch (IOException e) {
				System.out.println("发送POST请求出现异常！" + e);
				e.printStackTrace();
				throw new IOException("数据读取异常");
			} finally {
				if (reader != null) {
					reader.close();
				}

			}
			String str = buffer.toString();
			System.out.println("结果：" + str);
			jsonObject = new JsonParser().parse(buffer.toString()).getAsJsonObject();
		} catch (Exception e) {
			// logger.debug("抓取HTTPS请求出错: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		return jsonObject;
	}
}
