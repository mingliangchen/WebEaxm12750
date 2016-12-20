package com.hand.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * �����ṩһЩͨ�õ�CRUD����
 * 
 * @author Administrator
 * 
 */
public class JdbcTemplate {

	/**
	 * 
	 * @param sql
	 *            �����ִ�е�SQL���
	 * @return ����-1��ʾִ��SQL�����쳣 >=0��ʾ�ɹ�ִ��SQL
	 */
	public static int insertOrUpdateOrDelete(String sql) {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = BaseDao.getConn();
			stmt = conn.createStatement();// �˶�����Է���sql�����ݿ�
			// String sql = "update emp set sal = sal+5";
			int res = stmt.executeUpdate(sql);
			// System.out.println("�˴β���Ӱ�������Ϊ��"+res);
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			BaseDao.free(null, stmt, conn);
		}
		return -1;
	}

	// ThreadLocal
	public static int insertOrUpdateOrDelete(String sql, Object[] values) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = BaseDao.getConn();
			stmt = conn.prepareStatement(sql);// �˶�����Է���sql�����ݿ�
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					stmt.setObject(i + 1, values[i]);
				}
			}
			int res = stmt.executeUpdate();
			// System.out.println("�˴β���Ӱ�������Ϊ��"+res);
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			BaseDao.free(null, stmt, conn);
		}
		return -1;
	}
	
	//��List����sql�����չ��
	public static int insertOrUpdateOrDelete(String sql, List<Object> values) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = BaseDao.getConn();
			stmt = conn.prepareStatement(sql);// �˶�����Է���sql�����ݿ�
			if (values != null) {
				for (int i = 0; i < values.size(); i++) {
					stmt.setObject(i + 1, values.get(i));
				}
			}
			int res = stmt.executeUpdate();
			// System.out.println("�˴β���Ӱ�������Ϊ��"+res);
			return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			BaseDao.free(null, stmt, conn);
		}
		return -1;
	}

	public static <T> PageUtil<T> queryDataByPage(String sql, Object[] values,
			int currentPage, int pageSize,Class<T> cls) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = BaseDao.getConn();
			// 1���ݴ���SQL���SQL��ѯ���ܼ�¼��
			String countSql = "select count(*) from (" + sql + ")";

			pstmt = conn.prepareStatement(countSql);

			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			rs = pstmt.executeQuery();
			int totalRecord = 0; // �ܼ�¼��
			while (rs.next()) {
				totalRecord = rs.getInt(1);// ȡ����һ�е�ֵ
			}

			// ����pageSize��5����currentPage��2���������ҳ�ĳ�ʼ�����ͽ�������
			int rn1 = (currentPage - 1) * pageSize;
			int rn2 = currentPage * pageSize;

			// 2����ҳ��ѯ
			String queryDataSql = " select * from ("
					+ "	select tmp.*,rownum rn from (" + sql
					+ ") tmp) where rn>" + rn1 + " and rn<=" + rn2;
			// sql����п�����?�ŵ�λ������ҪΪ�䴫�ݲ�������ҳ��ѯ�л�������������Ҫ����

			List<T> listData = queryData(queryDataSql, values,cls);
			// ȡ��ҳ��ѯ���
			// ������ҳ��:
			int pageCount = totalRecord / pageSize;
			if (totalRecord % pageSize != 0) {
				pageCount = totalRecord / pageSize + 1;
			}

			PageUtil<T> pageUtil = new PageUtil<T>(currentPage, pageSize,
					totalRecord, pageCount, listData);

			return pageUtil;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.free(rs, pstmt, conn);
		}

		return null;

	}

	//���ͷ���
	public static <T> List<T> queryData(String sql, Object[] values,Class<T> cls) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> data = new ArrayList<T>();
		try {
			connection = BaseDao.getConn();
			pstmt = connection.prepareStatement(sql);

			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			rs = pstmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			
			while (rs.next()) {
				HashMap<String, Object> rowData = new HashMap<String, Object>();//һ��Map��һ������
				
				//����ΪΪkey,����Ϊvalue����һ��HashMap
				//empno 7788
				//enname SCOTT;
				for(int i=0;i<columnCount;i++){
					//ȡ������
					String columnName =  metaData.getColumnName(i+1);
					//ȡ���е��������ͣ��ٵ���rs.getXXX����������ת����Ӧ��java����
					String columnType = metaData.getColumnTypeName(i+1);
					System.out.println(columnType);
				//	System.out.println("������"+columnName + "��������:"+columnType +" "+metaData.getScale(i+1));
					//��������ȡ��ֵ
					Object val = null;
					//�������ݿ����������ͣ�ת�ɳɶ�Ӧ��java��������
					//Ŀ���Ǽ��ٷ�������������ж�
					if(columnType.equals("TINYINT")){
						val=rs.getByte(columnName);
						System.out.println(val);
					}else if(columnType.equals("TINYINT UNSIGNED")){
						val=rs.getByte(columnName);
						System.out.println(val);
					}else if(columnType.equals("SMALLINT UNSIGNED")){
						val=rs.getShort(columnName);
						System.out.println(val);
					}else if (columnType.equals("NUMBER") && metaData.getScale(i+1)>0){//��С��λ
						val = rs.getDouble(columnName);
						System.out.println(val);
					}else if (columnType.startsWith("VARCHAR")){
						val = rs.getString(columnName);
						System.out.println("jdbc"+val);
					}else if (columnType.equals("NUMBER")){//û��С��λ��
						val = rs.getLong(columnName);
						System.out.println(val);
					}else if (columnType.equals("DATETIME")){//û��С��λ��
						val = rs.getDate(columnName);
						System.out.println(val);
					}else if (columnType.equals("DATE")){
						val = rs.getDate(columnName);
					}else if(columnType.equals("TIMESTAMP")){
						val = rs.getTimestamp(columnName);
					}else if(columnType.equals("CHAR")){
						val = rs.getString(columnName);
						System.out.println(val);
					}
					columnName =  columnName.replaceAll("_", "");
					System.out.println("columnName:"+columnName+"val:"+val);
					rowData.put(columnName.toLowerCase(), val);
					///rs.getObject(columnIndex)
					
				}
				
				System.out.println("----------------------------------");
				System.out.println("rowData"+rowData);
				T t =   MapValueToVo.setMapValueToVo(rowData, cls);//ʹ�÷��佫���ݷ�װ��Vo��
				data.add(t);

			}

		} finally {
			BaseDao.free(rs, pstmt, connection);
		}

		return data;
	}
	
	public static List<Object[]> queryData(String sql, Object[] values) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object[]> data = new ArrayList<Object[]>();
		try {
			connection = BaseDao.getConn();
			pstmt = connection.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			rs = pstmt.executeQuery();
			// Ԫ����
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				// ����columnCount������Ӧ���ȵ�Object[]����
				Object[] rowData = new Object[columnCount];

				// rs.getObject(1);
				for (int i = 1; i <= columnCount; i++) {
					rowData[i - 1] = rs.getObject(i);// ��ȡ���������ݷŵ�Object[]������
				}
				// �����ݼӵ�������
				data.add(rowData);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.free(rs, pstmt, connection);
		}

		return data;

	}
	
	public static List<Object[]> printResultSet(ResultSet rs){
		List<Object[]> list=null;
		try {
			ResultSetMetaData rm = rs.getMetaData();
			int size = rm.getColumnCount();
			for(int i=1;i<=size;i++){
				System.out.print(rm.getColumnName(i)+"	");
			}
			System.out.println();
			list = new ArrayList<Object[]>();
			while(rs.next()){
				for(int i=0;i<size;i++){
					Object[] obj = new Object[size];
					obj[i]=rs.getObject(i+1);
					System.out.print(obj[i]+"	");
					
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	public static void main(String[] args) {
		String sql = "select * from emp ";

		PageUtil pu=null;
		try {
			pu = queryDataByPage(sql, new Object[0], 2, 5,Object.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�ܼ�¼����" + pu.getTotalRecord() + " ��ҳ����"
				+ pu.getPageCount() + " ��ǰҳ:" + pu.getCurrentPage() + " ÿҳ��ʾ:"
				+ pu.getPageSize());

		List<Object[]> data = pu.getData();
		for (Object[] rowData : data) {
			System.out.println(Arrays.toString(rowData));
		}

		System.out.println("------------------------------------");

		sql = "select * from customer ";
		try {
			pu = queryDataByPage(sql, new Object[]{10}, 2, 2,Object.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�ܼ�¼����" + pu.getTotalRecord() + " ��ҳ����"
				+ pu.getPageCount() + " ��ǰҳ:" + pu.getCurrentPage() + " ÿҳ��ʾ:"
				+ pu.getPageSize());

		data = pu.getData();
		for (Object[] rowData : data) {
			System.out.println(Arrays.toString(rowData));
		}

		// String sql = "select * from emp where deptno=?";
	}

	public static List<Object[]> queryData(String sql, List values) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object[]> data = new ArrayList<Object[]>();
		try {
			connection = BaseDao.getConn();
			pstmt = connection.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.size(); i++) {
					pstmt.setObject(i + 1, values.get(i));
				}
			}
			rs = pstmt.executeQuery();
			// Ԫ����
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				// ����columnCount������Ӧ���ȵ�Object[]����
				Object[] rowData = new Object[columnCount];

				// rs.getObject(1);
				for (int i = 1; i <= columnCount; i++) {
					rowData[i - 1] = rs.getObject(i);// ��ȡ���������ݷŵ�Object[]������
				}
				// �����ݼӵ�������
				data.add(rowData);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.free(rs, pstmt, connection);
		}

		return data;

	}

}
