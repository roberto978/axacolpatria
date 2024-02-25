package com.rtoro.axacolpatria.customer.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rtoro.axacolpatria.customer.dto.CustomerDTO;
import com.rtoro.axacolpatria.customer.dto.ResponseDTO;
import com.rtoro.axacolpatria.customer.service.CustomerService;
import com.rtoro.axacolpatria.customer.singleton.ClientHttp;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Roberto Toro
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private WebClient.Builder webClientBuilder;
    
    @Autowired
    private CustomerService customerService;
    
    @GetMapping()
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("index");
		List<CustomerDTO> list = getCustomers();
                
                List<CustomerDTO> listDB = customerService.find();
                list.addAll(listDB);
		mav.addObject("customers", list);
		return mav;
    }
    
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("add");
        mav.addObject("customer", new CustomerDTO());
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute CustomerDTO customerDTO) {
        ResponseDTO response = postCustomer(customerDTO);
        System.out.println(response.getEstado());
        if(response.getCodigo() == 200){
            customerDTO = customerService.create(customerDTO);
        }
        ModelAndView mav = new ModelAndView("add");
        mav.addObject("customer", customerDTO);
        mav.addObject("addOK", "El registro se ha agregado satisfactoriamente");
        return mav;
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView update(@PathVariable long id) {
        CustomerDTO customerDTO = customerService.find(id);
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("customer", customerDTO);
        return mav;
    }
    @PostMapping("/edit")
    public ModelAndView update(@Valid @ModelAttribute CustomerDTO customerDTO) {
        customerDTO = customerService.update(customerDTO);
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("customer", customerDTO);
        mav.addObject("updateOK", "El registro se ha actualizado satisfactoriamente");
        return mav;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable long id) {
        CustomerDTO customerDTO = customerService.find(id);
        ModelAndView mav = new ModelAndView("delete");
        mav.addObject("customer", customerDTO);
        return mav;
    }
    
    @PostMapping("/delete")
    public ModelAndView delete(@Valid @ModelAttribute CustomerDTO customerDTO) {
        customerService.delete(customerDTO.getId());
        ModelAndView mav = new ModelAndView("delete");
        mav.addObject("customer", new CustomerDTO());
        mav.addObject("deleteOK", "El registro se ha borrado satisfactoriamente");
        return mav;
    }
    
    
        private List<CustomerDTO> getCustomers() {
                WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(ClientHttp.getInstance().client))
                        .baseUrl("https://8e7c6b8a-fc46-4674-a529-4ebec57295d3.mock.pstmn.io")
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();

                List<CustomerDTO> list = build.method(HttpMethod.GET).uri("/customers")
                        .retrieve()
                        .bodyToMono(String.class)
                        .map(str -> {
                            str = str.replaceAll("“", "\"");
                            str = str.replaceAll("”", "\"");
                            Gson gson = new Gson();
                            final java.lang.reflect.Type typeCustomers = new TypeToken<List<CustomerDTO>>(){}.getType();
                            final List<CustomerDTO> customers = gson.fromJson(str, typeCustomers);
                            return customers;
                        }).block()
                        
                        ;
                return list;
        }

        private ResponseDTO postCustomer(CustomerDTO customer) {
                WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(ClientHttp.getInstance().client))
                        .baseUrl("https://8e7c6b8a-fc46-4674-a529-4ebec57295d3.mock.pstmn.io")
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();
                String uri = "/customers?name="+customer.getName()+"&age="+customer.getAge()+"&phoneNumber="+customer.getPhoneNumber()+"&address="+customer.getAddress();
                ResponseDTO responseDTO = build.method(HttpMethod.POST).uri(uri)
                        .retrieve()
                        .bodyToMono(String.class)
                        .map(str -> {
                            str = str.replaceAll("“", "\"");
                            str = str.replaceAll("”", "\"");
                            Gson gson = new Gson();
                            final java.lang.reflect.Type typeResponse = new TypeToken<ResponseDTO>(){}.getType();
                            final ResponseDTO response = gson.fromJson(str, typeResponse);
                            return response;
                        }).block()
                        ;
                return responseDTO;
        }
}
