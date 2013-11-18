package tcgt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;




import tcgt.entities.User;
import tcgt.response.Response;
import tcgt.utilities.ResponseFactory;
import tcgt.utilities.UserFactory;
/**
 * UserDao Class
 * @author hyoung
 *
 */
@Component
public class UserDao{

	private ResponseFactory responseFactory = new ResponseFactory();
	private UserFactory uFactory = new UserFactory();
	private static final String SUCCESS = "SUCCESS";
	@PersistenceContext(unitName = "TCGTPU")
	private EntityManager em;
	
	/**
	 * Setting entity manager
	 */
	public void setEM(EntityManager em) {
		this.em = em;
	}
	
	/**
	 * 
	 * persists user to the database.
	 */
	@Transactional
	public void persist(User user) {
		em.persist(user);
	}

	/**
	 * 
	 * updates user in the database
	 */
	@Transactional
	public void update(User user) {
		em.merge(user);
	}
	

	
	
	public User getAuthenticatedUser() {
		String name = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		User user = uFactory.getUser();
		
		user = findByUserName(name);
		return user;
	}
	
	@Transactional
	public User findByUserName(String userName) {
		/*
		 * queries DB for user matching query params
		 */
		Query query = em.createNamedQuery("findByUserName");
		query.setParameter("userName", userName);
		User temp = new User();
		try {
			temp = (User) query.getSingleResult();

		} catch (Exception e) {
			return null;
		}
		return temp;
	}
	
	/**
	 * find users by their user ids. 
	 * @param userId
	 * @return
	 */
	@Transactional
	public User findUserByUserId(int userId) {
		User temp = null;

		Query query = em.createNamedQuery("findByUserId");
		query.setParameter("userId", userId);

		try {
			temp = (User) query.getSingleResult();

		} catch (RuntimeException e) {

		}
		return temp;

	}
	
	/**
	 * Gets a list of all users in the database
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllUsers() {

		Query query = em.createNamedQuery("findAllUsers");
		return query.getResultList();

	}
	
	@Transactional
	public String getUserNameByUserId(int userId){
		Query query = em.createNamedQuery("");
		query.setParameter("userId", userId);
		
		return (String) query.getSingleResult();
	}

@Transactional
public User getUser(int userId){
	User user = uFactory.getUser();
	
//	Query query = em.
	return user;
}
	

	
}
