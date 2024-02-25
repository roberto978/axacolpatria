package com.rtoro.axacolpatria.customer.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Roberto Toro
 */

@Data
@Builder
public class ResponseDTO {
    private int codigo;
    private String estado;
}
