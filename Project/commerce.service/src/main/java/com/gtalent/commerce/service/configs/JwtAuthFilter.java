package com.gtalent.commerce.service.configs;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
    //過濾請求/賦予權限
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
         String authHeader = request.getHeader("Authorization");

        if(authHeader==null || !authHeader.startsWith("Bearer ")){ //確認格式是不是正確的 Authorization
            //該次請求過濾器結束生命週期
            filterChain.doFilter(request, response);
            return; //都不執行了
        }
        // 若順利取得且格式正確
        String jwtToken = authHeader.substring(7); // 取得第7各字元開始的資料(去掉"Bearer")
        String email = jwtService.getEmailFromToken(jwtToken);

        //驗證 username 是否存在
        if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            //存在! 開始找 db 中的 username
            Optional<User> user = userRepository.findByEmail(email);
            //todo: 驗證 token 是否過期或無效
            if(user.isPresent()){ //判斷 user 是否存在
                Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.get().getRole())); 
                // ↑ 此 token 為 Spring Security 使用的 token (包含 user & authorities)
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.get(), null, authorities);
                // 將使用者提出請求對應授權(token)且允許授權的結果塞給 SecurityContextHolder (springboot)
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // 以上為 filter, 確認後才進入下方程式
        filterChain.doFilter(request, response);
    }

}
