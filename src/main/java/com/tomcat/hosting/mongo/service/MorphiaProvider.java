package com.tomcat.hosting.mongo.service;

import org.mongodb.morphia.Morphia;

import com.google.inject.Provider;

public class MorphiaProvider implements Provider<Morphia> {

    public Morphia get() {
        Morphia morphia = new Morphia();
        //for sake of simplicity, all properties code is removed
        //the package name is part of the properties
//        morphia.mapPackage("MODEL_PACKAGE", true); 
        return morphia;
    }
}
