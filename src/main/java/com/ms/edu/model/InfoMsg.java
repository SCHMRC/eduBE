package com.ms.edu.model;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoMsg 
{
	public LocalDate data;
	
	public String message;
	
}
