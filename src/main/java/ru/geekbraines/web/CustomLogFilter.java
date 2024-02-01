package ru.geekbraines.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component // аннотацию ставим для метода extends OncePerRequestFilter
public class CustomLogFilter /*implements Filter*/ extends OncePerRequestFilter {

    // OncePerRequestFilter -- это фильтр, который отработает ровно один раз на один запрос


 /*   @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        // этот метод фильтр вызывается, когда в сервис прилетает запрос

    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("Incoming request: {}", request.getRequestURI());

        filterChain.doFilter(request, response);  // это надо обязательно вызвать в конце
        // есть очень много фильтров, формируется цепочка. И в конце каждого фильтра эта цепочка должна вызваться.
        // если этого не будет, реквест сдохнет.
    }
}


// ИТОГО, что мы сделали: создали фильтр, перехватили реквест, что-то сделали и послали запрос дальше - в цепочку.
// потом смотрим в логах:
// 2024-02-01T05:32:53.020+03:00  INFO 20964 --- [nio-8080-exec-7] ru.geekbraines.web.CustomLogFilter       : Incoming request: /customer
//Hibernate: select c1_0.id,c1_0.age,c1_0.name,c1_0.phone_number from customer c1_0