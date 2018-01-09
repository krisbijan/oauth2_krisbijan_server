package com.krisbijan.oauth2_krisbijan_server;

import java.util.Date;

/*this class is used for logging to connect different logs with actionId
it is constructed when action is created by some event and passed to all methods
that are invoked because of that event*/
public class Action {

	private static long id;
	static {
		id = 0;
	}
	private long actionId;
	private Date date;
	
	public Action() {
		actionId=id++;
		date = new Date();
	}

	@Override
	public String toString() {
		return "ActionID "+actionId + " - Started " + date + ": ";
	}


	
	
	

}
