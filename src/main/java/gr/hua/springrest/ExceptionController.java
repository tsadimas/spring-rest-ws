package gr.hua.springrest;


import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice // To Handle Exceptions
public class ExceptionController {

     @ExceptionHandler({HttpUnauthorizedException.class})
     @ResponseBody
     @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
     Map<String, String> unauthorizedAccess(Exception e) {
         Map<String, String> exception = new HashMap<String, String>();

        // log.error("unauthorized Access to the API: " + e.getMessage(), e);
         exception.put("code", "401");
         exception.put("reason", e.getMessage());

         return exception;
     }
}
