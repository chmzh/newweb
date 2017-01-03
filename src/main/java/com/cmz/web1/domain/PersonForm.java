package com.cmz.web1.domain;


import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 语言包位置 /WEB-INF/messages/message*.properties
 * @author C
 *
 */
public class PersonForm {

	
    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
    
    private Integer age;
    @NotNull
    @Size(min=2, max=30,message="person.name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull
    @Min(value=18,message="person.age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
