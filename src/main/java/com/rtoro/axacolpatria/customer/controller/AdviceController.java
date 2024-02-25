package com.rtoro.axacolpatria.customer.controller;

import com.rtoro.axacolpatria.customer.dto.CustomerDTO;
import com.rtoro.axacolpatria.customer.dto.ErrorDTO;
import com.rtoro.axacolpatria.customer.exceptions.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Roberto Toro
 */
@RestControllerAdvice
public class AdviceController{
    
    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView runtimeExceptionHandler(HttpServletRequest request, RuntimeException ex){
        ErrorDTO error = ErrorDTO.builder().code("C-500").message(ex.getMessage()).build();
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("error", error);
        return mav; 
    }
    
    @ExceptionHandler(value = BusinessException.class)
    public ModelAndView businessExceptionHandler(HttpServletRequest request, BusinessException ex){
        //ex.printStackTrace();
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getCode(), ex.getMessage());
        String view = getView(request.getRequestURL().toString());
                if("add".equalsIgnoreCase(view) || "edit".equalsIgnoreCase(view)){
                CustomerDTO customerDTO = getCustomerDTO(request.getParameterMap());
                ModelAndView mav = new ModelAndView(view);
                mav.addObject("customer", customerDTO);
                mav.addObject("errors", errors);
                return mav; 
        }
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("error", errors);
        return mav; 
    }
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ModelAndView methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
//        for (Map.Entry<String, String> entry : errors.entrySet()) {
//            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
//        }
//ex.printStackTrace();
        String view = getView(request.getRequestURL().toString());
        if("add".equalsIgnoreCase(view) || "edit".equalsIgnoreCase(view)){
                CustomerDTO customerDTO = getCustomerDTO(request.getParameterMap());
                ModelAndView mav = new ModelAndView(view);
                mav.addObject("customer", customerDTO);
                mav.addObject("errors", errors);
                return mav; 
        }
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("error", errors);
        return mav; 
    }

    
    private CustomerDTO getCustomerDTO(Map<String,String[]> map){
        
        CustomerDTO cus = new CustomerDTO();
        if(map.get("id") != null){
            cus.setId(Long.parseLong(map.get("id")[0]));
        }
        cus.setName(map.get("name")[0]);
        cus.setPhoneNumber(map.get("phoneNumber")[0]);
        cus.setAge(Integer.parseInt(map.get("age")[0]));
        cus.setAddress(map.get("address")[0]);
         return cus;      
    }
    
    private String getView(String uri){
        if(StringUtils.isNotBlank(uri)){
            String[] parts = uri.split("/");
            if(parts.length > 0){
                return parts[parts.length-1];
            }
        }
        
        return "error";
    }
}
