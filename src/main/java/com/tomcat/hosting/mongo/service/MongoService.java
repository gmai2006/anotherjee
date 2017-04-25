package com.tomcat.hosting.mongo.service;

import org.bson.Document;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoService {
    private Provider<MongoClient> mongoProvider;

    @Inject
    private MongoService(Provider<MongoClient> mongoProvider) {
        this.mongoProvider = mongoProvider;
    }

    public void execute() {        
        MongoDatabase db2 = mongoProvider.get().getDatabase("snow");
        
        MongoCollection<Document> companies = db2.getCollection("vendors");
        
        FindIterable<Document> iterable = companies.find();
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
        	Document o = cursor.next();
        	System.out.println(o);
        }
    }
}
