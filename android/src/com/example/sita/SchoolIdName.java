package com.example.sita;

public class SchoolIdName {
	
	private int id;
	private String name;
	
	public SchoolIdName (int id, String name)
	{
		this.id =id;
		this.name = name;		
	}
	
	public String getSchoolName()
	{
		return name;
	}
	
	public int getSchoolId()
	{
		return id;
	}

}
