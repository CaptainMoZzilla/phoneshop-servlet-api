package com.es.phoneshop.web.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DosFilter implements Filter {
    private static int MAX_COUNT = 10;
    private static int INTERVAL_IN_SECONDS = 5;
    private Map<String, AtomicInteger> counterMap;
    private Map<String, AtomicLong> timerMap;

    @Override
    public void init(FilterConfig filterConfig) {
        counterMap = Collections.synchronizedMap(new HashMap<>());
        timerMap = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String address = request.getRemoteAddr();
        AtomicInteger counter = counterMap.get(address);

        if (counter == null) {
            timerMap.put(address, new AtomicLong(System.currentTimeMillis()));
            counterMap.put(address, new AtomicInteger(1));

        } else if (counter.intValue() > MAX_COUNT) {
            Long quotient = System.currentTimeMillis() - timerMap.get(address).longValue();
            AtomicLong tempValue = new AtomicLong(quotient);

            if (tempValue.longValue() / INTERVAL_IN_SECONDS  <  1000) {
                ((HttpServletResponse)response).sendError(429);
                return;
            } else {
                counterMap.put(address, new AtomicInteger(1));
                timerMap.put(address, new AtomicLong(System.currentTimeMillis()));
            }
        } else {
            counter.incrementAndGet();
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
