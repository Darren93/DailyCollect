package com.darren.spring.utils;

import java.lang.reflect.Field;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class XMLMappingConfig {
	 // 创建XSTREAM实例
    private XStream xstream = new XStream(new DomDriver("_转换", new XmlFriendlyNameCoder(
    		"_-", "_")));

    /**
     * 转换为对象
     * 
     * @param reqXML XML
     * @param object 对象
     * @return object
     */
    public Object convertXMLToObj(String reqXML, Object object)
    {
        // 设置XML与对象对应关系
        confMappingRelation(object, true);
        object = xstream.fromXML(reqXML);

        return object;
    }

    /**
     * 转换为XML
     * 
     * @param object 对象
     * @return xml
     */
    public String convertObjToXML(Object object)
    {
        // 设置对象与XML对应关系
        confMappingRelation(object, true);
        String xml = xstream.toXML(object);
        //WriteLogUtil.writeRunInfoLog("resp-xml:"+xml, xml);
        return xml;
    }

    /**
     * 设置子对象
     * 
     * @param object 对象
     */
    @SuppressWarnings("unchecked")
    public void setChildObject(Object object)
    {
        Class clz = object.getClass();
        if (clz.isAnnotationPresent(XMLMapping.class))
        {
            XMLMapping xmlMapping = (XMLMapping) clz.getAnnotation(XMLMapping.class);

            xstream.alias(xmlMapping.value(), object.getClass());
            
            Field[] fieldList = clz.getDeclaredFields();

            for (Field field : fieldList)
            {
                if (field.isAnnotationPresent(XMLMapping.class))
                {
                    XMLMapping fieldXMLMap = (XMLMapping) field.getAnnotation(XMLMapping.class);
                    
                    xstream.aliasField(fieldXMLMap.value(), clz, field.getName());
//                    System.out.println(fieldXMLMap.value() + "-------" + clz+"---"+field.getName());
                }
            }
        }

    }

    /**
     * [简要描述]:<br/> [详细描述]:<br/>
     * 
     * @param object
     * @param flag
     * @exception
     */
    @SuppressWarnings("unchecked")
    private void confMappingRelation(Object object, boolean flag)
    {
        Class clz = object.getClass();

        if (clz.isAnnotationPresent(XMLMapping.class))
        {
            XMLMapping xmlMapping = (XMLMapping) clz.getAnnotation(XMLMapping.class);

            xstream.alias(xmlMapping.value(), object.getClass());

            Field[] fieldList = clz.getDeclaredFields();

            for (Field field : fieldList)
            {
                if (field.isAnnotationPresent(XMLMapping.class))
                {
                    XMLMapping fieldXMLMap = (XMLMapping) field.getAnnotation(XMLMapping.class);
                    if (flag)
                    {
                        xstream.aliasField(fieldXMLMap.value(), clz, field.getName());
                    }
                    else
                    {
                        xstream.aliasField(field.getName(), clz, fieldXMLMap.value());
                    }

                }
            }
        }
    }
}
