package com.rtoro.axacolpatria.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Roberto Toro
 */

public class CustomerDTO implements Serializable{

    private long id;
    
    @NotBlank(message = "Por favor, agregue el nombre")
    @Size(max = 50, message = "El nombre debe tener máximo 50 caracteres")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{1,}\s[A-Z]{1}[a-z]{1,}", message = "El campo name debe tener formato camel case")
    private String name;
    
    private int age;
    
    @NotBlank(message = "Por favor, agregue el numero telefonico")
    @Pattern(regexp = "[0-9]{10}", message = "El formato de telefono es invalido (9999999999)")
    private String phoneNumber;
    
    @NotBlank(message = "Por favor, agregue la direccion")
    @Pattern(regexp = "^[a-zA-Z]{0,}\s[1-9]{2}\s[#]\s[1-9]{2}[a-zA-Z]{0,}\s[-]\s[1-9]{2}[a-zA-Z]{0,}", message = "El formato de la direccion es invalido (XXXX 99 # 99XXX – 99XXX)")
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
