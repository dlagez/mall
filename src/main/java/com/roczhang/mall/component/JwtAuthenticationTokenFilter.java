package com.roczhang.mall.component;

import com.roczhang.mall.common.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 授权过滤器，用户的每次请求都会经过这个过滤器。
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // authHeader的值是Bearer eyJhbGciOiJIUzUxMiJ9.， 就是我们在前端页面设置的Authorize
        String authHeader = request.getHeader(this.tokenHeader);
        // 首先是非空，并且开头是Bearer
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            // authToken是生成的代码 eyJhbGciOiJIUzUxMiJ9.
            String authToken = authHeader.substring(this.tokenHead.length());
            // username 是解析authToken出来的值。authToken有三段，其中第二段里面装载着用户名，创建时间，装载时间等等
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            LOGGER.info("checking username: {}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 根据用户名查询出user，这里应该是从数据库查询出来的。
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                // 这里的验证也就是从authToken里面解析出username，和userDetail里面的username进行对比。并且判断token是否过期
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user: {}", username);
                    // 这里应该是设置授权，让请求通过。
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
