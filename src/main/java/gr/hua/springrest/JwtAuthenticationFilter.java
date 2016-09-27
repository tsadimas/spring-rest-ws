package gr.hua.springrest;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gr.hua.springrest.dao.JwtService;
import gr.hua.springrest.models.User;


public class JwtAuthenticationFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private  JwtService jwtService;
	
	public void setJwtService(JwtService jwtService){
		this.jwtService=jwtService;
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("AuthenticationFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		//System.out.println("Requested Resource::" + uri);

		String token = req.getHeader("Token");

		logger.info("--<<<->>>>---" + token);

		User u = new User();
		try {
			logger.info("--<<<INSIDE TRY->>>>---");
			u = jwtService.parseToken(token);
			logger.info("--<<<->>>>---" + u.getName());
			if (u == null) {
				logger.info("Unauthorized access request " + uri);
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			} else {
				// pass the request along the filter chain
				logger.info("Authorized access request " + uri);
				chain.doFilter(request, response);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		}

	}

	@Override
	public void destroy() {
		// close any resources here
	}

}
