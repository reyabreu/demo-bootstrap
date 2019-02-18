package org.reyabreu.bootstrap.persistence.model;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    public Person() {
        //
    }

    Person(Builder builder) {
        this.id = builder._id;
        this.firstName = builder._firstName;
        this.lastName = builder._lastName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .toString();
    }

    public static class Builder {

        private long _id;
        private String _firstName;
        private String _lastName;

        public Builder id(long id) {
            this._id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this._firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this._lastName = lastName;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

    }
}
