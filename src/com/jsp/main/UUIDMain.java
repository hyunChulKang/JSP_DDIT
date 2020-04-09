package com.jsp.main;

import java.util.UUID;

public class UUIDMain {
	public static void main(String[] args) {
			String uuid = UUID.randomUUID().toString();
			System.out.println(uuid.toUpperCase());
	}
}
