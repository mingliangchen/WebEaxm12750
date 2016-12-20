package com.hand.dao;

import java.util.ArrayList;
import java.util.List;

import com.hand.entity.Customer;
import com.hand.util.JdbcTemplate;

public class CustomerDao {
	public List<Customer> getCustomerByName(String first_name) throws Exception{
		
		String sql="select * from customer where first_name = ?";
		List<Customer> clist= JdbcTemplate.queryData(sql,new Object[]{first_name} , Customer.class);
		return clist;
	}
}
