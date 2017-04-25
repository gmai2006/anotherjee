package com.tomcat.hosting.mongo.service;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;

@Singleton
public class MongoProvider implements Provider<MongoClient> {
    private MongoClient mongo;
    @Override
    public MongoClient get() {
        if (mongo == null) {
         mongo = new MongoClient("localhost", 27017);
        }
        return mongo;
    }
}
