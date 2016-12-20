package com.hand.dao;

import java.util.ArrayList;
import java.util.List;

import com.hand.util.DBUtilExcute;


public class LanguageDao {
	public List<Object> getLanguage() throws Exception{
		List<Object> values = new ArrayList<Object>();
		String sql = "select * from language";
		return DBUtilExcute.executeQuery(sql, values);
	}
}
