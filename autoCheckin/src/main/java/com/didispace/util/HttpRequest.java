package com.didispace.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

//import net.sf.json.JSONObject;

public class HttpRequest {
	/**
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param param
	 *            ��������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return URL ����Զ����Դ����Ӧ���
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			if(param!=null){
				param=URLEncoder.encode(param, "utf-8");
				param = param.replaceAll("%3D", "=").replace("%26", "&");
			}
			String urlNameString = url + "?" + param;
			System.out.println("sendGet"+urlNameString);
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
/*			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}*/
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("GET *******" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendGetGBK(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			System.out.println("sendGet"+urlNameString);
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
/*			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}*/
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"GBK"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("GET *******" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	
	public static String sendTokenGet(String url, String param, String token) {
		String result = "";
		BufferedReader in = null;
		try {
			if(param!=null){
				param=URLEncoder.encode(param, "utf-8");
				param = param.replaceAll("%3D", "=").replace("%26", "&");
			}
			String urlNameString = url + "?" + param;
			System.out.println("sendTokenGet"+urlNameString);
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.setRequestProperty("Authorization", "bearer "+token);
			System.out.println(token);
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
/*			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}*/
			// ���� BufferedReader����������ȡURL����Ӧ
			if (connection.getResponseCode() != 200) {    
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(),"utf-8"));    
				String line = null;    
				StringBuffer errorMsg = new StringBuffer();    
				if ((line = reader.readLine()) != null) {     
					errorMsg.append(line).append("\n");    
				}    
				reader.close();
				System.out.println(errorMsg.toString());
				return errorMsg.toString();
			}
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("GET *******" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ��ָ�� URL ����POST����������
	 * 
	 * @param url
	 *            ��������� URL
	 * @param param
	 *            ��������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return ����Զ����Դ����Ӧ���
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			if(param!=null){
				param=URLEncoder.encode(param, "utf-8");
				param = param.replaceAll("%3D", "=").replace("%26", "&");
			}
			URL realUrl = new URL(url);
			System.out.println("sendPost"+realUrl);
			// �򿪺�URL֮�������
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			if (conn.getResponseCode() != 200) {    
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"utf-8"));    
				String line = null;    
				StringBuffer errorMsg = new StringBuffer();    
				if ((line = reader.readLine()) != null) {     
					errorMsg.append(line).append("\n");    
				}    
				reader.close();
				System.out.println(errorMsg.toString());
				return errorMsg.toString();
			}
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("POST************" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String sendBasicPost1(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			if(param!=null){
				param=URLEncoder.encode(param, "utf-8");
				param = param.replaceAll("%3D", "=").replace("%26", "&");
			}
			URL realUrl = new URL(url);
			System.out.println("sendBasicPost"+realUrl);
			// �򿪺�URL֮�������
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			
			String author = "Basic " + Base64.encode(("rongtoudai2016" + ":" + "G9w0BkqhkiAQEFAASCBK").getBytes());  
	        conn.setRequestProperty("Authorization", author); 
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
		
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			if (conn.getResponseCode() != 200) {    
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"utf-8"));    
				String line = null;    
				StringBuffer errorMsg = new StringBuffer();    
				if ((line = reader.readLine()) != null) {     
					errorMsg.append(line).append("\n");    
				}    
				reader.close();
				System.out.println(errorMsg.toString());
				return errorMsg.toString();
			}
			System.out.println(conn.getResponseMessage());
			conn.getInputStream();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("POST************" + e);
			System.out.println("POST************getMessage" + e.getMessage());
			e.getMessage();
			
			
			
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendBasicPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			if(param!=null){
				param=URLEncoder.encode(param, "utf-8");
				param = param.replaceAll("%3D", "=").replace("%26", "&");
			}
			URL realUrl = new URL(url);
			System.out.println("sendTokenPost"+realUrl);
			// �򿪺�URL֮�������
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			String author = "Basic " + Base64.encode(("rongtoudai2016" + ":" + "G9w0BkqhkiAQEFAASCBK").getBytes());  
	        conn.setRequestProperty("Authorization", author); 
			
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			System.out.println(conn.getResponseCode());
			if (conn.getResponseCode() != 200) {    
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"utf-8"));    
				String line = null;    
				StringBuffer errorMsg = new StringBuffer();    
				if ((line = reader.readLine()) != null) {     
					errorMsg.append(line).append("\n");    
				}    
				reader.close();
				System.out.println(errorMsg.toString());
				return errorMsg.toString();
			}
	        
			System.out.println(out.toString());
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("POST************" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendTokenPost(String url, String param,String token) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			System.out.println("sendTokenPost"+realUrl);
			if(param!=null){
				param=URLEncoder.encode(param, "utf-8");
				param = param.replaceAll("%3D", "=").replace("%26", "&");
			}
			System.out.println("param: "+param);
			// �򿪺�URL֮�������
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			conn.setRequestProperty("Authorization", "bearer "+token);
			System.out.println(token);
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			System.out.println(conn.getResponseCode());
			if (conn.getResponseCode() != 200) {    
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"utf-8"));    
				String line = null;    
				StringBuffer errorMsg = new StringBuffer();    
				if ((line = reader.readLine()) != null) {     
					errorMsg.append(line).append("\n");    
				}    
				reader.close();
				System.out.println(errorMsg.toString());
				return errorMsg.toString();
			}
	        
			System.out.println(out.toString());
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("POST************" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
//	public static String sendTokenPost(String url, JSONObject json,String token) {
//		PrintWriter out = null;
//		BufferedReader in = null;
//		String result = "";
//		try {
//			URL realUrl = new URL(url);
//			System.out.println("sendTokenPost"+realUrl);
//			// �򿪺�URL֮�������
//			URLConnection conn = realUrl.openConnection();
//			// ����ͨ�õ���������
//			conn.setRequestProperty("accept", "*/*");
//			conn.setRequestProperty("connection", "Keep-Alive");
//			conn.setRequestProperty("user-agent",
//					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//
//			conn.setRequestProperty("Authorization", "bearer "+token);
//			System.out.println(token);
//			// ����POST�������������������
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			// ��ȡURLConnection�����Ӧ�������
//			out = new PrintWriter(conn.getOutputStream());
//			// �����������
//			out.print(json.toString()/*.getBytes("UTF-8")*/);
//			// flush������Ļ���
//			out.flush();
//			// ����BufferedReader����������ȡURL����Ӧ
//			in = new BufferedReader(new InputStreamReader(
//					conn.getInputStream(), "UTF-8"));
//			String line;
//			while ((line = in.readLine()) != null) {
//				result += line;
//			}
//		} catch (Exception e) {
//			System.out.println("POST************" + e);
//			e.printStackTrace();
//		}
//		// ʹ��finally�����ر��������������
//		finally {
//			try {
//				if (out != null) {
//					out.close();
//				}
//				if (in != null) {
//					in.close();
//				}
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return result;
//	}
	
	public static String uploadFile(String urlStr, Map<String, Object> map,
			Map<String, String> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; // boundary就是request头和上传文件内容的分隔符
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (map != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = map.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}
			// file
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					String contentType = new MimetypesFileTypeMap()
							.getContentType(file);
					if (filename.endsWith(".png")) {
						contentType = "image/png";
					}
					if (contentType == null || contentType.equals("")) {
						contentType = "application/octet-stream";
					}
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(
							new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}
	
	public static void upfile() {
		
		try {
			//FileInputStream -> InputStreamReader
			FileInputStream fInputStream = new FileInputStream("D:\\pnr\\invest.zip");
			InputStreamReader inputStreamReader = new InputStreamReader(fInputStream,"utf-8");
			//InputStreamReader -> BufferedReader
			BufferedReader in = new BufferedReader(inputStreamReader);
			//BufferedReader -> StringBuffer
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append(in.readLine() + "\n");
			//StringBuffer -> String
			sBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}	
}