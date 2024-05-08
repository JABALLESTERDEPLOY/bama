package com.tfg.bamashop.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pdf {
	private String html;
	private String target;
	private String subject;
	private String body;
}
