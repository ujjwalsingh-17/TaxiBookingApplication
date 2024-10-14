package com.taxibooking.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="bookingform")
public class BookingForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="*Name cannot be empty")
	@NotBlank(message="*Name cannot be blank")
	@Size(min=2,max=30,message="*Invalid size of name")
	@Pattern(regexp="^[A-Za-z]+$", message="*Name should contain only characters")
	@Column(length=30)
	private String name;
	
	@NotEmpty(message="*Source cannot be empty")
	@NotBlank(message="*Source")
	@Size(min=2,max=100,message="*Invalid size of source")
	@Column(length=100)
	private String source;
	
	@NotEmpty(message="*Email cannot be empty")
	@NotBlank(message="*Email cannot be blank")
	@Size(min=7,max=50,message="Invalid size of email")
	@Column(length=50)
	private String email;
	
	@NotEmpty(message="*Destination cannot be empty")
	@NotBlank(message="*Destination cannot be blank")
	@Size(min=2,max=100,message="*Invalid size of destination")
	@Column(length=100)
	private String destination;
	
	@NotNull(message="*Time cannot be empty")
	private LocalTime time;
	
	@NotNull(message="*Date cannot be empty")
	private LocalDate date;
	
	@NotEmpty(message="*Comfort cannot be empty")
	@Column(length=20)
	private String comfort;
	
	@Min(value=1,message="*Adult should be atleast 1")
	@Max(value=4, message="*Adult can be atmost 4")
	private int adult;
	
	@Max(value=3, message="*Children can be atmost 3")
	private int children;
	
	@NotEmpty(message="*Message cannot be empty")
	@NotBlank(message="*Message cannot be blank")
	@Size(min=2,max=2000,message="*Invalid size of message")
	@Column(length=2000)
	private String message;
}
