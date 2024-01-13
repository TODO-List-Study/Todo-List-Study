package study.todolist.base.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // jwt 토큰은 header 에 Authorization Bearer로 값 전달
        // head에서 토큰 추출
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();


        if (StringUtils.hasText(jwt)) {
            Map<String, String> resultMap = tokenProvider.validateToken(jwt);
            if (resultMap.get("result").equals("SUCCESS")) {
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                //유효한 토큰을 SecurityContextHolder 에 둔다
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}",
                        authentication.getName(), requestURI);
            } else {
                servletRequest.setAttribute("tokenexception", resultMap);
                //access token이 없을 경우에
                //토큰에 대한 정보를 저장해서 보내야 한다
                logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);

            }
        } else  //아예 인증정보 없이 왔을때도 별도의 에러처리가 필요할 수도 있다
        {
            Map<String, String> map = new HashMap<>();
            map.put("result", "FAIL");
            map.put("msg", "인증정보가 없습니다");
            servletRequest.setAttribute("tokenexception", map);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    //토큰 분리
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }


}
