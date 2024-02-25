package com.rtoro.axacolpatria.customer.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Roberto Toro
 */

@Data
@Builder
public class ErrorDTO {

    private String code;
    private String message;
}
