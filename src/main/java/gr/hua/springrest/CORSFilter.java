package gr.hua.springrest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class CORSFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(CORSFilter.class);

    public CORSFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

    	HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
		logger.info("---CORSFilter---");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "token, x-requested-with, authorization");
        response.setHeader("Access-Control-Expose-Headers", "token, x-requested-with, authorization, Access-Control-Allow-Origin, Access-Control-Allow-Headers");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
    		logger.info("---CORSFilter OPTIONS---");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
    		logger.info("---CORSFilter doFilter---");
            chain.doFilter(req, res);
        }
        
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}