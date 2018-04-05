package Util;

import java.net.Socket;

public class User {
	
	
		private String name;
		private String acount;

		public User(String name, String acount) {
			this.name = name;
			this.acount = acount;
		}

		public String toString() {
			return name + "  " + acount;
		}
	

}
