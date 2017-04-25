package com.tomcat.hosting.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import com.tomcat.hosting.dao.AssignedCompanyV;
import com.tomcat.hosting.dao.Company;
import com.tomcat.hosting.dao.CompanyV;
import com.tomcat.hosting.dao.User;
import com.tomcat.hosting.dao.VendorLocation;
import com.tomcat.hosting.dao.ZipcodeGeo;
import com.tomcat.hosting.servlet.Utils;
import com.tomcat.hosting.utils.ApplicationUtils;

public class UserService {
	@Inject
	private Provider<EntityManager> entityManager;
	protected static final Logger logger = LoggerFactory
			.getLogger(UserService.class);
    @Transactional (rollbackOn = Exception.class)
    public void saveOrUpdate(Object e) throws Exception
    {
    	entityManager.get().persist(e);
    }
    
    @Transactional (rollbackOn = Exception.class)
    public void delete(Object entity) throws Exception
    {
    	EntityManager em = entityManager.get();
    	em.remove(em.contains(entity) ? entity : em.merge(entity));
    }
    public int getNumberofClients() {
    	return getIntByQuery("select count(*) from Company where companyType=1").intValue();
    }
    public int getNumberofVendors()  {
    	return getIntByQuery("select count(*) from Company where companyType=2").intValue();
    }
    
    public List<User> getAllusers() throws Exception
    {
    	return entityManager.get().createNamedQuery("User.findAll", User.class).getResultList();
    }
    public List<User> getUsersByRoles(int role) //throws Exception
    {
    	List<User> result = null;
    	try
    	{
    		result = entityManager.get().createNamedQuery("User.findByRole", User.class)
        			.setParameter("role", role).getResultList();
    	}
    	catch (NoResultException nre) { 
    		logger.error("User by role NOT FOUND" + role);
    		return new ArrayList<User>();	
    	}
    	return result;
    	
    }
    public List<CompanyV> getCompanyByType(int type) //throws Exception
    {
    	List<CompanyV> result = null;
    	try
    	{
    		result = entityManager.get().createNamedQuery("CompanyV.findByType", CompanyV.class)
        			.setParameter("type", type).getResultList();
    	}
    	catch (NoResultException nre) { 
    		logger.error("getCompanyByType NOT FOUND" + type);
    		return new ArrayList<CompanyV>();	
    	}
    	return result;
    	
    }
    
    public List<CompanyV> getAssignedVendors(int id) //throws Exception
    {
    	List<CompanyV> result = null;
    	try
    	{
    		String query = "from CompanyV where id in (select id.vendorId from VendorClient where id.clientId= :id)";
    		Query q = entityManager.get().createQuery (query);
	           q.setParameter("id", id);
	           result = q.getResultList();
    	}
    	catch (NoResultException nre) { 
    		logger.error("getAssignedVendors NOT FOUND" + id);
    		return new ArrayList<CompanyV>();	
    	}
    	return result;
    	
    }
    public List<CompanyV> getAssignedClients(int id) //throws Exception
    {
    	List<CompanyV> result = null;
    	try
    	{
    		String query = " from CompanyV where id in (select id.clientId from VendorClient where id.vendorId= :id)";
    		Query q = entityManager.get().createQuery (query);
	           q.setParameter("id", id);
	           result = q.getResultList();
    	}
    	catch (NoResultException nre) { 
    		logger.error("getAssignedClients NOT FOUND" + id);
    		return new ArrayList<CompanyV>();	
    	}
    	return result;
    	
    }
    
    public List<CompanyV> getNearestVendors(int id, int distance) {
    	CompanyV client = getCompanyById(id);
    	List<CompanyV> vendors = getCompanyByType(Company.VENDOR);
    	List<CompanyV> result = new ArrayList<CompanyV>();
    	for (CompanyV c : vendors) {
    		double dist = Utils.distance(client.getLat(), client.getLng(),
    				c.getLat(), c.getLng(), 'M');
    		if (dist <= distance) {
    			c.setDistance(dist);
    			result.add(c);
    		}
    	}
    	
    	Collections.sort(result, new Comparator<CompanyV>() {
			@Override
			public int compare(CompanyV arg0, CompanyV arg1) {
				if (arg0.getDistance() > arg1.getDistance()) return 1;
				if (arg0.getDistance() < arg1.getDistance()) return -1;
				else return 0;
			}
    		
    	});
    	return result;
    }
    
    public double distance(int zipcode1, int zipcode2) {
    	ZipcodeGeo geo1 = getGeoByZipcode(zipcode1);
    	ZipcodeGeo geo2 = getGeoByZipcode(zipcode2);
    	return Utils.distance(geo1.getLat(), 
    			geo1.getLng(), 
    			geo2.getLat(), 
    			geo2.getLng(), 'M');
//    	return Utils.calculateDistance(geo1.getLat(), 
//    			geo1.getLng(), 
//    			geo2.getLat(), 
//    			geo2.getLng());
    }
    
    public List<VendorLocation> getLocationByUser(String userId) throws Exception
    {
    	List<VendorLocation> result = null;
    	try
    	{
    		result = entityManager.get().createNamedQuery("Location.findByUser", VendorLocation.class)
        			.setParameter("id", userId).getResultList();
    	}
    	catch (NoResultException nre) { 
    		logger.error("Location by user NOT FOUND" + userId);
    		return new ArrayList<VendorLocation>();	
    	}
    	return result;
    }
    public User getUserbyId(String userId)
    {
    	User result = null;
    	try
    	{
    		result = entityManager.get().createNamedQuery("User.findUserById", User.class)
        			.setParameter("id", userId).getSingleResult();
    	}
    	catch (NoResultException nre) { 
    		logger.error("getUserbyId" + userId + " " + nre);
    		return null;
    	}
    	return result;
    }
    public CompanyV getCompanyById(int id)
    {
    	CompanyV result = null;
    	try
    	{
    		result = entityManager.get().createNamedQuery("CompanyV.findById", CompanyV.class)
        			.setParameter("id", id).getSingleResult();
    	}
    	catch (NoResultException nre) { 
    		logger.error("getCompanyById" + id + " " + nre);
    		return null;
    	}
    	return result;
    }
    
    public ZipcodeGeo getGeoByZipCodeFromGoogle(int zipcode) {
    	ZipcodeGeo geo = null;
    	try {
	    	JSONObject json = Utils.getGeoLocationFromZipcode(String.valueOf(zipcode));
			JSONArray array = (JSONArray)json.get("results");
			JSONObject entity = (JSONObject)array.get(0);
	//		System.out.println(entity.get("formatted_address"));
			JSONObject geometry = (JSONObject)entity.get("geometry");
			JSONObject location = (JSONObject)geometry.get("location");
			
			addZipCodeGeo(zipcode, 
					(Double)location.get("lat"), 
					(Double)location.get("lng"), 
				(String)entity.get("formatted_address"));
			geo = new ZipcodeGeo();
			geo.setAddress((String)entity.get("formatted_address"));
			geo.setLat(((Double)location.get("lat")));
			geo.setLng(((Double)location.get("lng")));
    	}
    	catch (Exception e) {}
    	return geo;
    }
    public ZipcodeGeo getGeoByZipcode(int zipcode)
    {
    	ZipcodeGeo result = null;
    	try
    	{
    		result = entityManager.get().createNamedQuery("ZipcodeGeo.findByZipcode", ZipcodeGeo.class)
        			.setParameter("zipcode", zipcode).getSingleResult();
    		if (null == result) {
    			return getGeoByZipCodeFromGoogle(zipcode);
    		}
    	}
    	catch (NoResultException nre) { 
    		logger.info("getGeoByZipcode" + zipcode + " " + nre);
    		return getGeoByZipCodeFromGoogle(zipcode);
    	}
    	return result;
    }
    
    public void addZipCodeGeo(int zipcode, Double lat, Double lng, String address) throws Exception {
    	ZipcodeGeo geo = new ZipcodeGeo();
    	geo.setZipcode(zipcode);
    	geo.setLat(lat);
    	geo.setLng(lng);
    	geo.setAddress(address);
    	saveOrUpdate(geo);
    	logger.info("created geo successfully: " + zipcode);
    }
    
    public User createNewUser(String userId, int role) throws Exception {
		UUID uuid = UUID.randomUUID();
		String code = uuid.toString();
		User user = new User();
		user.setUserId(userId);
		user.setPassword(ApplicationUtils.encrypt(code));
		user.setUserRole(role);
		user.setStatus(0);
		user.setEmailAddress(userId);
		user.setInvalidLogin(0);
		user.setSignupTime(new java.sql.Timestamp(System.currentTimeMillis()));
		user.setActivationCode(code);
		saveOrUpdate(user);
		logger.info("created user successfully: " + user.getUserId() + ", "
				+ user.getActivationCode() + ", " + user.getSignupTime()
				+ ", user role " + user.getUserRole());
		return user;
	}
    public Number getIntByQuery(String query)
    {
    	Number result = null;
    	try
    	{
    		Query q = entityManager.get()
        			.createQuery (query);
        	result = (Number) q.getSingleResult ();
    	}
    	catch (NoResultException nre) { 
    		logger.error("getIntByQuery" + query + " " + nre);
    		return -1;
    	}
    	return result;
    }
    
    @Transactional (rollbackOn = Exception.class)
    public int updateByQuery(String query, Map<String,Object> params) throws Exception
    {
    	int updatecount = 0;
		Query q = entityManager.get().createQuery (query);
		 if (params!=null && !params.isEmpty()) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                q.setParameter(key, params.get(key));
            }
        }
		updatecount = q.executeUpdate();
    	return updatecount;
    }
}
