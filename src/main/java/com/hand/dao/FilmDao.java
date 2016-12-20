package com.hand.dao;

import java.util.ArrayList;
import java.util.List;

import com.hand.entity.Film;
import com.hand.util.DBUtilExcute;
import com.hand.util.JdbcTemplate;

public class FilmDao {
	public List<Object> getAllFilm() throws Exception{
		List<Object> values = new ArrayList<Object>();
		String sql="select f.film_id,f.title,f.description,l.name from film f,language l where f.language_id = l.language_id";
		List<Object> flist= DBUtilExcute.executeQuery(sql, values);
		return flist;
	}
	public List<Object[]> getAllFilmByJdbcTemplete() throws Exception{
		String sql="select f.film_id,f.title,f.description,l.name from film f,language l where f.language_id = l.language_id";
		List<Object[]> ls=JdbcTemplate.queryData(sql,new Object[]{});
		return ls;
		

	}
	
	public int insertFilm(Film film){
		String sql = "insert into film(title,description,language_id) values(?,?,?)";
		int i=JdbcTemplate.insertOrUpdateOrDelete(sql, new Object[]{film.getTitle(),film.getDescription(),film.getLanguage_id()});
		return i;
	}
	public List<Object> getFilmById(int film_id) throws Exception{
		List<Object> values = new ArrayList<Object>();
		values.add(film_id);
		String sql="select * from film where film_id=?";
		List<Object> flist= DBUtilExcute.executeQuery(sql, values);
		
		return flist;
	}
	public int deleteFilm (int film_id){
		String sql = "delete from film where film_id = ?";
		int i=JdbcTemplate.insertOrUpdateOrDelete(sql, new Object[]{film_id});
		return i;
	}
	public int  updateFilm(Film film){
		String sql = "update film set title = ?,description=?,language_id=? where film_id = ?";
		String title=film.getTitle();
		String description=film.getDescription();
		int language_id=film.getLanguage_id();
		int film_id=film.getFilm_id();
		int i=JdbcTemplate.insertOrUpdateOrDelete(sql, new Object[]{title,description,language_id,film_id});
		return i;
	}
}
