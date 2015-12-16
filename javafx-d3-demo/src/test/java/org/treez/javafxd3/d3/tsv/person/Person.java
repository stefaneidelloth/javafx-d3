package org.treez.javafxd3.d3.tsv.person;

public class Person {
	private final String name;

	private final int age;

	public Person(final String name, final int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
