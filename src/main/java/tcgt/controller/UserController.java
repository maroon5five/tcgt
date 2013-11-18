package tcgt.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import tcgt.dao.UserDao;
import tcgt.entities.User;
import tcgt.helpers.Login;
import tcgt.helpers.Registration;
import tcgt.response.Response;
import tcgt.utilities.ResponseFactory;
import tcgt.utilities.UserFactory;


/**
 * UserController
 * 
 * @author hyoung
 * 
 */
@Controller
public class UserController {

	private String INVALIDATE_ERROR = "ERROR: COULD NOT INVALIDATE SESSION";

	@Autowired
	private UserDao userDao;

	@Autowired
	private Registration register;

	@Autowired
	private Login login;
	

	private ResponseFactory responseFactory = new ResponseFactory();
	private UserFactory uFactory = new UserFactory();


	/**
	 * Persists user for registration.
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @return
	 */
	@RequestMapping(value = "/UserPersist", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Response createUser(final HttpServletRequest request) {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		return register.createUser(firstName, lastName, userName, email,
				password, request);
	}
	

	
	@RequestMapping(value = "/UserProfile")
	public String userProfile(){
		
		return "profile.jsp";
	}

	/**
	 * 
	 * returns to Welcome page
	 */

	@RequestMapping(value = "/welcome")
	public String displayWelcomePage() {

		return "welcome";
	}

	/**
	 * 
	 * returns login page
	 */
	@RequestMapping(value = "/login")
	public String loginPage(final HttpServletRequest request) {

		return "login";
	}
	
	@RequestMapping(value = "/common")
	public String commonPage(final HttpServletRequest request) {

		return "common";
	}

	@RequestMapping(value = "/denied")
	public String deniedPage(final HttpServletRequest request) {

		return "denied";
	}
	
	@RequestMapping(value = "/main")
	public String mainPage(final HttpServletRequest request) {

		return "welcome";
	}
	
	/**
	 * 
	 * returns registration page
	 */
	@RequestMapping(value = "/Register")
	public String registerPage(final HttpServletRequest request) {

		return "registration.jsp";
	}
	@RequestMapping(value = "/Waiting")
	public String waiting(final HttpServletRequest request) {

		return "waiting.jsp";
	}



	
	/**
	 * testing purposes
	 * 
	 * @param userDao
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}



}
