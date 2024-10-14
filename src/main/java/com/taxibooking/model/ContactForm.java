package com.taxibooking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="contactform")
public class ContactForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
//	@Pattern(reg)
	@NotEmpty(message="Name cannot be empty") 
	@Size(min=2, max=30, message="Name length should be between 2 and 30")
	@Column(length=30)
	private String name;
	
	@NotEmpty(message="Email cannot be empty") 
	@Size(min=5, max=50, message="Email length should be between 5 and 50")
	@Column(length=50)
	private String email;
	
	@NotNull(message="Phone number cannot be empty")
	@Min(value=1000000000, message="Phone number must be atleast 10 digit.")
	@Max(value=9999999999L,message="Phone number must be 10 digit.")
	@Column(length=10)
	private Long phone;
	
	@NotEmpty(message="Message cannot be empty") 
	@Size(min=5, max=500, message="Message length should be between 5 and 500")
	@Column(length=500)
	private String message;
}
