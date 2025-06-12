package com.xubank.Dto;

import com.xubank.Enums.TipoConta;

import lombok.Data;

@Data
public class NovaContaDto {
    
    private TipoConta tipo;
    private String clienteCpf; 

} 