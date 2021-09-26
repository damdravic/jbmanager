package com.anaximel.bmanager.appsecurity.filter;

import com.anaximel.bmanager.appsecurity.domain.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static com.anaximel.bmanager.appsecurity.constant.SecurityConstant.FORBIDDEN_MESSAGE;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {



    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

        HttpResponse httpResponse = new HttpResponse(
                FORBIDDEN.value(), FORBIDDEN, FORBIDDEN.getReasonPhrase(), FORBIDDEN_MESSAGE);



        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream,httpResponse);
        outputStream.flush();

    }
}
