package com.test;

import com.qk.daoImpl.AdminDaoImpl;

public class testGetViewString {
	public static void main(String[] args) {
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		System.out.println(adminDaoImpl.returnViewAerticleString(1, 20));
		System.out.println(adminDaoImpl.returnViewAerticleString(2, 20));
	}

}
