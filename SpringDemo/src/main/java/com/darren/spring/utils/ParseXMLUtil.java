package com.darren.spring.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ParseXMLUtil {
	 /**
     * <读取XML流> <功能详细描述>
     * 
     * @param in InputStream
     * @return
     * @throws
     * @return Object [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getReqXml(InputStream in)
    {
        String cs = "UTF-8";
        StringBuffer txt = new StringBuffer(AdapterConstants.KILO);
        InputStreamReader isr = null;
        try
        {
            CharBuffer buf = CharBuffer.allocate(AdapterConstants.FIVE);
            isr = new InputStreamReader(in, cs);
            while (0 < isr.read(buf))
            {
                buf.flip();
                txt.append(buf.toString());
                buf.clear();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != isr)
            {
                try
                {
                    isr.close();
                }
                catch (IOException e)
                {
                    e.getMessage();
                }
            }
        }
        return txt.toString();
    }
    
    /**
     * 解析XML文件，将解析的实体封装到集合中,xml的格式位单层
     * 
     * @param xml xml格式字符串
     * @return map集合
     * @throws Exception 系统异常
     */
    public static Map<String, String> doParseXML(String xml) throws Exception
    {

        Map<String, String> map = new HashMap<String, String>();
        // 把xml内容存入输入流中
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes("utf-8"));
        // 创建Document对象
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbfactory.newDocumentBuilder();
        // 解析xml
        Document document = documentBuilder.parse(inputStream);
        // 获取xml文件根目录
        Element rootElement = document.getDocumentElement();
        // 获取xml文件所有子节点
        NodeList nodeList = rootElement.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {                
                String nodeValue = "";
                if (node.getFirstChild() == null)
                {
                    // 把节点名和节点的值加入map中
                    map.put(node.getNodeName(), nodeValue);
                   
                }
                else
                {
                    // 把节点名和节点的值加入map中
                    map.put(node.getNodeName(), node.getFirstChild().getNodeValue());
                }
               
            }
        }

        return map;
    }

    /**
     * 解析XML文件，将解析的实体封装到集合中,xml的格式为多层
     * 
     * @param xml xml格式的字符串
     * @return list集合，list中存放的是map集合，集合中存放了每层xml中的key和value
     */
    public static List<String> doMultifoldParseXML(String xml)
    {
        List<String> list = new ArrayList<String>();

        // 把xml内容存入输入流中
        InputStream inputStream;
        try
        {
            inputStream = new ByteArrayInputStream(xml.getBytes("utf-8"));
            // 创建Document对象
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbfactory.newDocumentBuilder();
            // 解析xml
            Document document = documentBuilder.parse(inputStream);

            // 获取xml文件根目录
            Element rootElement = document.getDocumentElement();
            // 获取xml文件所有子节点
            NodeList nodeList = rootElement.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                getNodeChildInfo(node, list);
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
			e.printStackTrace();
		}

        return list;
    }

    /**
     * 获取节点信息
     * 
     * @param node 节点
     * @param list list表
     */
    public static void getNodeChildInfo(Node node, List<String> list)
    {

        try
        {
            if (node != null)
            {
                if (node.getFirstChild() != null)
                {
                    if (node.getFirstChild().getNodeValue() == null)
                    {
                        NodeList childNodeList = node.getChildNodes();
                        for (int i = 0; i < childNodeList.getLength(); i++)
                        {
                            Node childNode = childNodeList.item(i);
                            getNodeChildInfo(childNode, list);
                        }
                    }
                    else
                    {

                        String data = node.getNodeName() + "," + node.getFirstChild().getNodeValue();
                        list.add(data);
                    }
                }

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 解析xml，获取result
     * 
     * @param xml xml文件
     * @return map 集合
     * @throws Exception 系统异常
     */
    public static Map<String, String> doResultXML(String xml) throws Exception
    {

        Map<String, String> map = new HashMap<String, String>();
        // 创建Document对象
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbfactory.newDocumentBuilder();
        // 把xml内容存入输入流中
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes("utf-8"));
        // 解析xml
        Document document = documentBuilder.parse(inputStream);
        // 获取xml文件根目录
        Element rootElement = document.getDocumentElement();
        // 获取xml文件所有子节点
        NodeList nodeList = rootElement.getElementsByTagName(AdapterConstants.RESULTCODE);

        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                // 把节点名和节点的值加入map中
                map.put(node.getNodeName(), node.getFirstChild().getNodeValue());
            }
        }
        return map;
    }
}
