package inmobi.guess_number.configuration;

import inmobi.guess_number.exception.AppException;
import inmobi.guess_number.exception.ErrorCode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class RateLimitingFilter implements Filter {

    private final Map<String, AtomicInteger> requestCountsPerIpAddress = new ConcurrentHashMap<>();

    // Maximum requests allowed per minute
    private static final int MAX_REQUESTS_PER_MINUTE = 5;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String path = httpServletRequest.getRequestURI();
        String clientIpAddress = httpServletRequest.getRemoteAddr();

        if(path.endsWith("/guess")){
            //Init Request count for client IP
            requestCountsPerIpAddress.putIfAbsent(clientIpAddress, new AtomicInteger(0));
            AtomicInteger requestCount = requestCountsPerIpAddress.get(clientIpAddress);

            //Increment the request count
            int requests = requestCount.incrementAndGet();

            //Check if the request limit has been exceeded
            if (requests >= MAX_REQUESTS_PER_MINUTE) {
                httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                httpServletResponse.setContentType("text/plain;charset=UTF-8");
                httpServletResponse.getWriter().write("Too many requests. Please try again later.");
                return;
            }
        }



        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
    }
}
