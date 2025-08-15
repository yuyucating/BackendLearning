package com.example.demo.configs;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String authHeader = request.getHeader("Authorization");
        // authHeader ->  BearerXXXXXXXX...
        if(authHeader==null || !authHeader.startsWith("Bearer ")){ //確認是不是正確的 Authorization
            filterChain.doFilter(request, response);
            return; //都不執行了
        }
        // 若順利取得且格式正確
        String jwtToken = authHeader.substring(7); // 取得第7各字元開始的資料(去掉"Bearer")
        String username = jwtService.getUsernameFromToken(jwtToken);
        
        //驗證 username 是否存在
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            //存在!
            Optional<User> user = userRepository.findByUsername(username);
            if(user.isPresent()){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.get(), null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // 以上為 filter, 確認後才進入下方程式

        filterChain.doFilter(request, response);
    }

}
