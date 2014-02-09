package org.nishen.timesheet.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

public class DataUtils
{

	public static Document getDocumentFromFile(String filename) throws Exception
	{
		byte[] document = loadFile(filename);

		return getDocumentFromBytes(document);
	}

	public static Document getDocumentFromFile(File file) throws Exception
	{
		byte[] document = loadFile(file);
		document = (new String(document)).trim().getBytes();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = docBuilder.parse(new ByteArrayInputStream(document));

		return doc;
	}

	public static Document getDocumentFromBytes(byte[] data) throws Exception
	{
		data = (new String(data)).trim().getBytes();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = docBuilder.parse(new ByteArrayInputStream(data));

		return doc;
	}

	public static byte[] loadFile(String filename) throws Exception
	{
		File file = new File(filename.trim());
		return loadFile(file);
	}

	public static byte[] loadFile(File file) throws Exception
	{
		if (!file.exists() || !file.canRead())
		{
			String message = "Cannot read file: " + file.getCanonicalPath();
			System.err.println(message);
			throw new Exception(message);
		}

		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = fis.read(buf)) >= 0)
			data.write(buf, 0, len);
		fis.close();

		return data.toByteArray();
	}

	public static void saveDocument(String filename, byte[] document) throws Exception
	{
		try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setNamespaceAware(true);
			DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();

			Document doc = docBuilder.parse(new ByteArrayInputStream(document));

			File file = new File(filename.trim());
			String data = format(doc);
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			writer.print(data);
			writer.flush();
			writer.close();
		}
		catch (Exception e)
		{
			String message = "Unable to save file: " + filename;
			System.err.println(message);
			e.printStackTrace();
			throw new Exception(message, e);
		}
	}

	public static void saveDocument(String filename, Document doc) throws Exception
	{
		try
		{
			File file = new File(filename.trim());
			String data = format(doc);
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			writer.print(data);
			writer.flush();
			writer.close();
		}
		catch (Exception e)
		{
			String message = "Unable to save file: " + filename;
			System.err.println(message);
			e.printStackTrace();
			throw new Exception(message, e);
		}
	}

	public static String format(Document document, boolean declaration)
	{
		try
		{
			final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();

			writer.getDomConfig().setParameter("format-pretty-print", true);
			writer.getDomConfig().setParameter("xml-declaration", declaration);

			return writer.writeToString(document);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static String format(Document document) throws Exception
	{
		return format(document, true);
	}

	public static String format(byte[] document) throws Exception
	{
		try
		{
			final InputSource src = new InputSource(new ByteArrayInputStream(document));
			final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src);

			return format(doc);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static String format(String xml) throws Exception
	{
		return format(xml.getBytes());
	}

	public static String getHash(byte[] data) throws NoSuchAlgorithmException
	{
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] hash = digest.digest(data);

		String hexHash = byte2hex(hash);

		return hexHash;
	}

	/**
	 * Converts a hash into its hexadecimal string representation.
	 * 
	 * @param bytes the byte array to convert
	 * @return the hexadecimal string representation
	 */
	private static String byte2hex(byte[] bytes)
	{
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++)
		{
			sb.append(hexChars[(bytes[i] >> 4) & 0xf]);
			sb.append(hexChars[bytes[i] & 0xf]);
		}

		return new String(sb);
	}

}