package com.cmz.web1.scripting.groovy;
// import the Messenger interface (written in Java) that is to be implemented
import com.cmz.web1.scripting.Messenger 
// define the implementation in Groovy
class GroovyMessenger implements Messenger { 
	String message
	public String getMessage() {
	// change the implementation to surround the message in quotes 
		return "'" + this.message + "'"
	}
	public void setMessage(String message) { 
		this.message = message
	}
}