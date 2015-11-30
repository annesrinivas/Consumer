package com.cpsot.excercise;
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.DBObject;


@Path("/")
public class Consumer {
	@POST
	@Path("/consumerService")
	@Consumes(MediaType.APPLICATION_JSON)
	
	// Consumer REST Service to accept JSON Data
	public Response consumerREST(InputStream incomingData) {
		
		// Read data from POST
		StringBuilder jsonBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				jsonBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received via POST: " + jsonBuilder.toString());
		
	 // Insert into MongoDB
	  try {
		/* Connect to MongoDB */
	 	//MongoClient mongo = new MongoClient("localhost", 27017);
	 	
	 	/* Get database */
		//DB db = mongo.getDB("customer_event_repository");
		
		/* Get collection from 'customer_events' */
		//DBCollection table = db.getCollection("customer_events");
		
		/* Parse JSON to build DBObject and insert into collection */
		DBObject dbo = (DBObject) com.mongodb.util.JSON.parse(jsonBuilder.toString());
		
		//List<DBObject> list = new ArrayList<>();
		//list.add(dbo);
		
		new MongoClient().getDB("customer_event_repository").getCollection("customer_events").insert(dbo);
		
		System.out.println("Done inserting into Mongo");

	    } catch (UnknownHostException e) {
		e.printStackTrace();
	    } catch (MongoException e) {
		e.printStackTrace();
	    }
	    
		// return HTTP response 200 in case of success
		return Response.status(200).entity(jsonBuilder.toString()).build();
	}
 
	
	// Simple REST service to check status
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "Consumer REST Service is running";
 
		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}
 
}