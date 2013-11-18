/**
 * 
 */
package tcgt.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tcgt.dao.UserDao;
import tcgt.entities.User;
import tcgt.utilities.UserFactory;

/**
 * @author hyoung
 *
 */
@Service
@Transactional
@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	private UserFactory uFactory = new UserFactory();

	
	public CustomUserDetailsService() {
        // default constructor
    }
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User user = uFactory.getUser();
		
		System.out.println("here");
		
		user = userDao.findByUserName(userName);

		return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(), true,
                true, true, true, getAuthorities(user.getUserRole()));
	}


			public Collection<GrantedAuthority>getAuthorities(Integer access) {

				System.out.println("granted authority");
				
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

			
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
			System.out.println("role user authlist");
			if(access.intValue() == 0){
				System.out.println("comparing access roles");
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}else if(access.intValue() == 1){
				authList.add(new SimpleGrantedAuthority("ROLE_USER"));
				System.out.println("comparing access roles");
			}
			return authList;

			}

		
			
}
