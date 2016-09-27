package gr.hua.springrest.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gr.hua.springrest.HomeController;
import gr.hua.springrest.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static java.time.ZoneOffset.UTC;

@Component
public class JwtService {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private static final String ISSUER = "gr.hua.dit";
    private SecretKeyProvider secretKeyProvider;

    @SuppressWarnings("unused")
    public JwtService() {
        this(null);
    }

    @Autowired
    public JwtService(SecretKeyProvider secretKeyProvider) {
        this.secretKeyProvider = secretKeyProvider;
    }

    public String tokenFor(User user) throws IOException, URISyntaxException {
        String secretKey = "testKEY";
		logger.info("------" + secretKey);

        
        Date expiration = Date.from(LocalDateTime.now().plusHours(2).toInstant(UTC));
        return Jwts.builder()
                .setSubject(user.getName())
                .setId(String.valueOf(user.getId()))
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
    
    public User parseToken(String token) throws Exception {
    	 String secretKey = "testKEY";
    	 logger.info("------INDIDE  parseToken");
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            logger.info("------BODY " + body);
            User u = new User();
            u.setName(body.getSubject());
            u.setId(Integer.parseInt((String) body.getId()));
            
            logger.info("------>>>" + u.getName());

            return u;

        } catch (JwtException | ClassCastException e) {
        	 logger.info("------ inside jwt exception");
        	return null;
        }
    }
}