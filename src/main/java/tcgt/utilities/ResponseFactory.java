/**
 * 
 */
package tcgt.utilities;

import org.springframework.stereotype.Component;

import tcgt.response.Response;



/**
 * @author hyoung
 *
 */
@Component
public class ResponseFactory {

	
	public Response getResponse(){
		return new Response();
	}
	
}
