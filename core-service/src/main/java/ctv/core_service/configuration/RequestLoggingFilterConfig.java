//package ctv.core_service.configuration;
//
//import java.io.IOException;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.CommonsRequestLoggingFilter;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Configuration
//@Slf4j
//public class RequestLoggingFilterConfig {
//
//    @Bean
//    public CommonsRequestLoggingFilter logFilter() {
//        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
//        filter.setIncludeClientInfo(true);
//        filter.setIncludeQueryString(true);
//        filter.setIncludeHeaders(true);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(10000);
//        filter.setAfterMessagePrefix("REQUEST DATA : ");
//        return filter;
//    }
//
//    @Bean
//    public Filter accessLogFilter() {
//        return new OncePerRequestFilter() {
//            @Override
//            protected void doFilterInternal(
//                    HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//                    throws ServletException, IOException {
//
//                ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
//                ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
//
//                filterChain.doFilter(requestWrapper, responseWrapper);
//
//                // Ghi lại các thông tin request và response sau khi đã xử lý
//                String requestBody = new String(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
//                String responseBody =
//                        new String(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
//
//                // Bạn có thể ghi lại thông tin này theo cách tương tự như trong mã ban đầu
//                log.info("Request body: {}", requestBody);
//                log.info("Response body: {}", responseBody);
//
//                // Copy response body trở lại response thực tế để trả cho client
//                responseWrapper.copyBodyToResponse();
//            }
//        };
//    }
//}
