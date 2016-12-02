package com.cmz.web1.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

	
    private String name;

	
    private Integer age;
	@NotNull
    @Size(min=2, max=30,message="{person.name}")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull
    @Min(18)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}