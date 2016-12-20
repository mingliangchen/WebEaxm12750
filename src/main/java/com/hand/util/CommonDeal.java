package com.hand.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.beanutils.BeanUtilsBean;
//import org.apache.commons.beanutils.ConvertUtilsBean;
//import org.apache.commons.beanutils.Converter;
//import org.apache.commons.beanutils.PropertyUtilsBean;

public class CommonDeal {

	public static String toUTF8(String str) throws UnsupportedEncodingException {
		// �����
		if (str != null) {
			if(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1").equals(str)){
				return new String(str.getBytes("ISO-8859-1"), "utf-8");
			}
		}
		return str;
	}

	public static void setUTF8(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
	}

	public static void setI81ToUTF8(Object obj)
			throws IllegalArgumentException, IllegalAccessException,
			UnsupportedEncodingException {
		Field[] field = obj.getClass().getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			if (field[i].getGenericType().toString()
					.equals(String.class.toString())) {
				field[i].setAccessible(true);
				// ���ԭ����ֵ
				if (field[i].get(obj) != null) {
					String s = field[i].get(obj).toString();
					// ��ʼ���ñ���
					if(new String(s.getBytes("ISO-8859-1"), "ISO-8859-1").equals(s)){
						field[i].set(obj, new String(s.getBytes("ISO-8859-1"),
							"utf-8"));
					}
				}
			}
		}
	}

//	public static void transMap2Bean(Map map, Object obj) {
//		try {
//			DateTimeConverter dtConverter = new DateTimeConverter();
//			ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
//			convertUtilsBean.deregister(Date.class);
//			convertUtilsBean.register(dtConverter, Date.class);
//			BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean,
//					new PropertyUtilsBean());
//			beanUtilsBean.populate(obj, map);
//		} catch (Exception e) {
//		}
//		return;
//	}
}
