package com.tomcat.hosting.mongo.service;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.bson.types.ObjectId;

import com.google.inject.Inject;
import com.tomcat.hosting.mongo.dao.Clients;
import com.tomcat.hosting.mongo.dao.Vendors;

public class MorphiaService {
	 private MorphiaProvider morphiaProvider;
	 private MongoProvider mongoProvider;
	 private Morphia morphia;
	 private Datastore ds;
	 @Inject
    private MorphiaService(MorphiaProvider morphiaProvider, MongoProvider mongoProvider) {
	 	this.morphiaProvider = morphiaProvider;
        this.mongoProvider = mongoProvider;
        morphia = morphiaProvider.get();
		morphia.map(Vendors.class);
		ds = morphia.createDatastore(mongoProvider.get(), "snow");
    }
	 
	 public void getVendors() {        
		 for (Vendors e : ds.find(Vendors.class))
             System.out.println(e.toString());
	 }
	 
	 public Vendors getVendorById(String id) {        
		 return ds.get(Vendors.class, new ObjectId(id));
	 }
	 
	 public Clients getClientById(String id) {        
		 return ds.get(Clients.class, new ObjectId(id));
	 }
	 
	 public List<Clients> getClients() {   
		 Query<Clients> result = ds.find(Clients.class);
		 return result.asList();
	 }
	
}
