package com.hand.util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class MapValueToVo {

	public static <T> T setMapValueToVo(HashMap<String, Object> hashMap,
			Class<T> cls) throws Exception {
		System.out.println("MapValueToVo"+hashMap);
		T vo;
		if(cls.getName().equals("java.lang.Double")){
			vo=(T) new Double(Double.parseDouble(hashMap.get(hashMap.keySet().toArray()[0]).toString()));
		}else if(cls.getName().equals("java.lang.Long")){
			vo=(T) new Long(Long.parseLong(hashMap.get(hashMap.keySet().toArray()[0]).toString()));
		}else{
			vo= cls.newInstance();
		}
		 

		// �õ�vo�е����й�������
		Method[] methods = cls.getMethods();

		for (Method m : methods) {
			String methodName = m.getName();// �õ���������

			if (methodName.startsWith("set")) {
				// ֻ������һ�������� set����

				Class[] parameterTypes = m.getParameterTypes();// �����б���������
				if (parameterTypes != null && parameterTypes.length == 1) {
					Class paramType = parameterTypes[0];// �����ĵ�һ����������������

					// Ҫ���ݷ����Ĳ������ͣ�����һ����ȫƥ������ݸ�����
					// ��map��ȡ������ֵ����һ���뷽���Ĳ�������ƥ�䣬����Ҫ��������ת���ɷ�������ƥ�����������

					// ȡ��������set�ĺ��Σ�ת��Сд�����ݸ�ֵ ��map����Ӧ������
					methodName = methodName.substring(3).toLowerCase();
					System.out.println("方法名：" + methodName + "参数名"
							+ paramType.getName());

					Object mapValue = null;
					//判断Map中是否存在和属性名一样的key值，有则获取value
					if(hashMap.containsKey(methodName)){
						mapValue = hashMap.get(methodName);// "hiredate";
					}
					if (mapValue != null) {
						//value类型
						String valueType = mapValue.getClass().getName();
						System.out.println("值:"+mapValue+"参数类型:" + paramType.getName()
								+ "值类型:" + valueType);
						// m.invoke(vo, mapValue);
						
						String paramTypeStr = paramType.getName();
						
						if (paramTypeStr.equals("int")
								|| paramTypeStr.equals("java.lang.Integer")) {
							if (mapValue instanceof Integer) {
								m.invoke(vo, mapValue);
							} else {
								String val = mapValue.toString();
								if (val.matches("[0-9]+")) {
									m.invoke(vo, Integer.parseInt(val));
								}
							}
						} else if (paramTypeStr.equals("float")
								|| paramTypeStr.equals("java.lang.Float")) {
							if (mapValue instanceof Float) {
								m.invoke(vo, mapValue);
							} else {
								// 123.0F
								String val = mapValue.toString();// ��int���������������ת�����ַ���
								if (val.matches("[0-9]+([.][0-9]+)?(F|f)?")) {
									m.invoke(vo, Float.parseFloat(val));
								}
							}
						} else if (paramTypeStr.equals("double")
								|| paramTypeStr.equals("java.lang.Double")) {
							if (mapValue instanceof Double) {
								m.invoke(vo, mapValue);
							} else {
								// 123.0F
								String val = mapValue.toString();// ��int���������������ת�����ַ���
								if (val.matches("[0-9]+([.][0-9]+)?(D|d)?")) {
									m.invoke(vo, Double.parseDouble(val));
								}
							}// 8�����������ж�
						}else if (paramTypeStr.equals("java.sql.Date")) {
							if (mapValue instanceof java.sql.Date) {
								m.invoke(vo, mapValue);
							} else {
								String val = mapValue.toString();// ��int���������������ת�����ַ���
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								try {
									java.util.Date utilDate = sdf.parse(val);
									java.sql.Date sqlDate = new java.sql.Date(
											utilDate.getTime());
									m.invoke(vo, sqlDate);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						} else if (paramTypeStr.equals("java.util.Date")) {
							if (mapValue instanceof java.util.Date) {
								m.invoke(vo, mapValue);
							} else {
								String val = mapValue.toString();// ��int���������������ת�����ַ���
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd HH:mm:ss");
								try {
									java.util.Date utilDate = sdf.parse(val);
									m.invoke(vo, utilDate);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}else if (paramTypeStr.equals("java.lang.String")){
							m.invoke(vo, mapValue.toString());
						} else if (paramTypeStr.equals("long")
								|| paramTypeStr.equals("java.lang.Long")) {
							if (mapValue instanceof Long) {
								m.invoke(vo, mapValue);
							} else {
								// 123.0F
								String val = mapValue.toString();// ��int���������������ת�����ַ���
								if (val.matches("[0-9]+([.][0-9]+)?(L|l)?")) {
									m.invoke(vo, Long.parseLong(val));
								}
							}
						} else if (paramTypeStr.equals("byte")
								|| paramTypeStr.equals("java.lang.Byte")) {
							if (mapValue instanceof Long) {
								m.invoke(vo, mapValue);
							} else {
								// 123.0F
								String val = mapValue.toString();// ��int���������������ת�����ַ���
								if (val.matches("[0-9]+([.][0-9]+)?")) {
									m.invoke(vo, Long.parseLong(val));
								}
							}
						}
					}
				}

			}

		}
		return vo;

	}

}
