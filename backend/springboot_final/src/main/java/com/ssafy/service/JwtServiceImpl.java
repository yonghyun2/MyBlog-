package com.ssafy.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssafy.vo.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	private String secretKey = "ThisSecretKey";	
	private Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.ssafy.service.JwtService#makeJwt(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String makeJwt(Member member) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Date expireTime = new Date();
		System.out.println(expireTime);
		expireTime.setTime(expireTime.getTime()+1000*600*1);
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
		
		Map<String,Object> headerMap = new HashMap<String,Object>();
		headerMap.put("typ", "JWT");
		headerMap.put("alg", "HS256");
		
		
		Map<String, Object> map= new HashMap<String, Object>();
        String email = member.getM_email();
        
        map.put("email", email);
        
        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
        						.setClaims(map)
        						.setExpiration(expireTime)        						
        						.signWith(signatureAlgorithm, signingKey);
        
       
        
		return builder.compact();
	}
	
	/* (non-Javadoc)
	 * @see com.ssafy.service.JwtService#checkJwt(java.lang.String)
	 */
	@Override
	public String checkJwt(String jwt) throws Exception {
		  try {
	            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
	                    .parseClaimsJws(jwt).getBody(); // 정상 수행된다면 해당 토큰은 정상토큰

	            logger.info("expireTime :" + claims.getExpiration());	           
	            logger.info("Email :" + claims.get("email"));	            

	            return (String) claims.get("email");
	        } catch (ExpiredJwtException exception) {
	            logger.info("토큰 만료");
	            return null;
	        } catch (JwtException exception) {
	            logger.info("토큰 변조");
	            return null;
	        }
	    }

	@Override
	public String makeRefreshJwt(Member member) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Date expireTime = new Date();
		expireTime.setTime(expireTime.getTime()+1000*6000*1); //6000초
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
		
		Map<String,Object> headerMap = new HashMap<String,Object>();
		headerMap.put("typ", "JWT");
		headerMap.put("alg", "HS256");
		
		Map<String, Object> map= new HashMap<String, Object>();
        int no = member.getM_no();
        
        map.put("no", no);
        
        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
        						.setClaims(map)
        						.setExpiration(expireTime)        						
        						.signWith(signatureAlgorithm, signingKey);
        
		return builder.compact();
	}

	@Override
	public String checkRefreshJwt(String jwt) throws Exception {
		// TODO Auto-generated method stub
		  try {
	            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
	                    .parseClaimsJws(jwt).getBody(); // 정상 수행된다면 해당 토큰은 정상토큰

	            logger.info("expireTime :" + claims.getExpiration());	           
	            logger.info("no :" + claims.get("no"));	            

	            return  claims.get("no")+"";
	        } catch (ExpiredJwtException exception) {
	            logger.info("refresh token 토큰 만료");
	            return "false";
	        } catch (JwtException exception) {
	            logger.info("refresh token 토큰 변조");
	            return "false";
	        }
	}

}
