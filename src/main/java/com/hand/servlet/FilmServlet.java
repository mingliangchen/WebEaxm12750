package com.hand.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.tomcat.jni.File;

import com.hand.dao.FilmDao;
import com.hand.dao.LanguageDao;
import com.hand.entity.Film;
import com.hand.entity.Language;


@WebServlet("/FilmServlet")
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FilmDao dao =new FilmDao();   
    public FilmServlet() {
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if("showlist".equals(command)){
			showFilmList(request,response);
		}if("delete".equals(command)){
			deleteFilm(request,response);
		}if("getOneFilm".equals(command)){
			To_Update(request,response);
		}if("modify".equals(command)){
			modifyFilm(request,response);
		}if("to_add".equals(command)){
			LanguageDao ldao=new LanguageDao();
			List<Object> languages;
			try {
				languages = ldao.getLanguage();
				request.setAttribute("languages", languages);
				request.getRequestDispatcher("/jsp/insertfilm.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("insert".equals(command)){
			insertFilm(request, response);
		}
	}

	private void insertFilm(HttpServletRequest request, HttpServletResponse response) {
		Film film=new Film();
		try {
			BeanUtils.populate(film, request.getParameterMap());
			
			int i=dao.insertFilm(film);
			showFilmList(request, response);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void modifyFilm(HttpServletRequest request, HttpServletResponse response) {
		Film film=new Film();
		try {
			BeanUtils.populate(film, request.getParameterMap());
			
			int i=dao.updateFilm(film);
			showFilmList(request, response);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void To_Update(HttpServletRequest request, HttpServletResponse response) {
		String film_id1=request.getParameter("film_id");
		int film_id=Integer.parseInt(film_id1);
		System.out.println("film_id:"+film_id);
		LanguageDao ldao=new LanguageDao();
		try {
			List<Object> flist =dao.getFilmById(film_id);
			request.setAttribute("film", flist.get(0));
			
			List<Object> languages= ldao.getLanguage();
			request.setAttribute("languages", languages);
			
			
			request.getRequestDispatcher("/jsp/updatefilm.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteFilm(HttpServletRequest request, HttpServletResponse response) {
		String film_id1=request.getParameter("film_id");
		Integer film_id=Integer.parseInt(film_id1);
		int i=dao.deleteFilm(film_id);
		showFilmList(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	private void showFilmList(HttpServletRequest request, HttpServletResponse response){
		
		try {
			List<Object> filmlist=dao.getAllFilm();
			
			request.setAttribute("filmlist", filmlist);
			request.getRequestDispatcher("/jsp/filmlist.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
