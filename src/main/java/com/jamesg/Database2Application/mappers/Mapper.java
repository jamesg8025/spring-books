package com.jamesg.Database2Application.mappers;

// Encapsulates all the logic for mapping between different object models
public interface Mapper<A, B> {

    // Maps an object of type A to an object of type B
    B mapTo(A a);

    // Maps an object of type B to an object of type A
    A mapFrom(B b);
}
