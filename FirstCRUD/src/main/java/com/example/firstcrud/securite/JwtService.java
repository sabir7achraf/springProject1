package com.example.firstcrud.securite;

import com.example.firstcrud.DTO.AuthentificationDTO;
import com.example.firstcrud.Entity.User;
import com.example.firstcrud.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    @Autowired
    private UserService userService;
    final String SecretKey="2722e6648a2a10ba4b772d7f6c9ad5ca9a5fb1b7791e5be35fcfcb11ddf76a7f";

    public Map<String,String> generate(String email){
        User user= (User) this.userService.loadUserByUsername(email);
        return this.generateJwt(user) ;
    }

    private Map<String, String> generateJwt(User user) {
        final long currentTime = System.currentTimeMillis();
        final long expirationTime =currentTime+30*60*1000;

        Map<String, String> claims = Map.of("nom", user.getFirstName(), "prenom", user.getLastName(), "email", user.getEmail());

        String barear = Jwts.builder().
                setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("barear",barear);
    }

    private Key getKey() {
        byte[] decode = Decoders.BASE64.decode(SecretKey);

        return Keys.hmacShaKeyFor(decode);
    }
}
