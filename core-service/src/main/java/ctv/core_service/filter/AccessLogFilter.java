package ctv.core_service.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

import com.google.gson.GsonBuilder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class AccessLogFilter extends OncePerRequestFilter {
    private static final Gson gson = new Gson();
    private static final Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create(); // Format JSON

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        var reqWrapper = new ContentCachingRequestWrapper(req);
        var resWrapper = new ContentCachingResponseWrapper(res);
        long start = System.currentTimeMillis();

        chain.doFilter(reqWrapper, resWrapper);
        logRequest(reqWrapper, resWrapper, System.currentTimeMillis() - start);
        resWrapper.copyBodyToResponse();
    }

    private void logRequest(ContentCachingRequestWrapper req, ContentCachingResponseWrapper res, long time) {
        String maskedRequestBody = maskSensitiveData(formatJsonPretty(req.getContentAsByteArray()));
        String maskedResponseBody = maskSensitiveData(formatJsonPretty(res.getContentAsByteArray()));

        log.info(
            "\nIP: {}\n"
            + "Method: {}\n"
            + "URI: {}\n"
            + "Params: {}\n"
            + "Status: {}\n"
            + "Time: {}ms\n"
            + "Request:\n{}\n"
            + "Response:\n{}\n",
            Optional.ofNullable(req.getHeader("X-FORWARDED-FOR")).orElse(req.getRemoteAddr()),
            req.getMethod(),
            req.getRequestURI(),
            gson.toJson(req.getParameterMap()),
            res.getStatus(),
            time,
            maskedRequestBody,
            maskedResponseBody
        );
    }

    private String maskSensitiveData(String json) {
        try {
            var data = gson.fromJson(json, Map.class);
            if (data.containsKey("password")) {
                data.put("password", "******");
            }
            if (data.containsKey("token")) {
                data.put("token", "******");
            }
            return gsonPretty.toJson(data); // Format JSON
        } catch (Exception e) {
            return json;
        }
    }

    private String formatJsonPretty(byte[] data) {
        try {
            return gsonPretty.toJson(gson.fromJson(new String(data, StandardCharsets.UTF_8), Object.class));
        } catch (Exception e) {
            return new String(data, StandardCharsets.UTF_8);
        }
    }
}