package com.darren.spring.test;

import com.darren.spring.utils.XMLMappingConfig;
import com.darren.spring.vo.PersonVO;

public class TestXmlParse {
	public static void main(String [] args){
		XMLMappingConfig xmlMappingConfig=new XMLMappingConfig();
//		PersonVO personVO=new PersonVO("测试","男","测试地址",1);
//		String xmlObj=xmlMappingConfig.convertObjToXML(personVO);
//		System.out.println(xmlObj);
		
		String xmlObj="<PersonVO><name>测试</name><gender>男</gender><address>测试地址</address><id>1</id></PersonVO>";
		PersonVO personVO=new PersonVO();
		personVO=(PersonVO)
				xmlMappingConfig.convertXMLToObj(xmlObj, personVO);
		System.out.println(personVO.getAddress());
		
	}
}
