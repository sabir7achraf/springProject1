package com.example.firstcrud.securite;

import com.example.firstcrud.Entity.Jwt;
import com.example.firstcrud.Entity.User;
import com.example.firstcrud.Repository.JwtRepository;
import com.example.firstcrud.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class JwtService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtRepository jwtRepository;
    final String SecretKey="2722e6648a2a10ba4b772d7f6c9ad5ca9a5fb1b7791e5be35fcfcb11ddf76a7f";

    public String generate(String email){
        User user= (User) this.userService.loadUserByUsername(email);
        this.disableTokens(user);
        final String barear = generateJwt(user);
        final Jwt jwt=Jwt
                .builder()
                .valeur(barear)
                .expiration(false)
                .validation(true)
                .user(user)
                .build();
                this.jwtRepository.save(jwt);

        return  barear;
    }

    private String generateJwt(User user) {
        final long currentTime = System.currentTimeMillis();
        final long expirationTime =currentTime+30*60*1000;

        Map<String, Object> claims = Map.of("nom", user.getFirstName(), "prenom", user.getLastName(), Claims.SUBJECT, user.getEmail(),Claims.EXPIRATION,new Date(expirationTime));

        String barear = Jwts.builder().
                setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return barear;
    }

    private Key getKey() {
        byte[] decode = Decoders.BASE64.decode(SecretKey);

        return Keys.hmacShaKeyFor(decode);
    }

    public String loadUserName(String token) {
        return this.getClaim(token ,Claims::getSubject);
    }
    private void disableTokens(User utilisateur) {
        final List<Jwt> jwtList = this.jwtRepository.findUser(utilisateur.getEmail()).peek(
                jwt -> {
                    jwt.setValidation(false);
                    jwt.setExpiration(true);
                }
        ).collect(Collectors.toList());

        this.jwtRepository.saveAll(jwtList);
    }

    public Boolean isExpired(String token) {
        Date expiration =this.getClaim(token ,Claims::getExpiration);
        return expiration.before(new Date());
    }



    private <T> T getClaim(String token, Function<Claims,T> function ) {
        Claims claims=  getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Jwt findByvaleur(String token) {
        return jwtRepository.findByValeur(token).orElseThrow(()->new RuntimeException("Token inconnue"));
    }

    public void deconnexion() {
        User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Jwt jwt= this.jwtRepository.findUserValidationToken(user.getEmail(),true,false).orElseThrow(()->new RuntimeException("Token invalid"));
      jwt.setExpiration(true);
      jwt.setValidation(false);
      this.jwtRepository.save(jwt);
    }

    @Scheduled(cron = "@daily")
    public void removeUselessJwt() {
        this.jwtRepository.deleteAllByExpirationAndValidation(true,false);
    }


}
