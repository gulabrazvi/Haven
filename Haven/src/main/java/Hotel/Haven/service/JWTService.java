package Hotel.Haven.service;


import Hotel.Haven.entity.UserProperty;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer.details}")
    private String issuer;

    @Value("${jwt.expire.time}")
    private long expireTime; // In milliseconds

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    private final static String USER_NAME = "email";

    public String genrateToken(UserProperty userProperty){
        return JWT.create()
                 .withClaim(USER_NAME, userProperty.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+expireTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }

}
