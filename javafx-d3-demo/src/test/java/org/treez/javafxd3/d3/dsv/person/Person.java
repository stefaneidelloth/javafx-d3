package org.treez.javafxd3.d3.dsv.person;

public class Person {

	//#region ATTRIBUTES

	private final String name;

	private final int age;

	//#ene region

	//#region CONSTRUCTORS

	public Person(final String name, final int age) {
		this.name = name;
		this.age = age;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	//#end region

	//#region ACCESSORS

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	//#end region

}
