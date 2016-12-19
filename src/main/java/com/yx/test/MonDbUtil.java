package com.yx.test;

import com.mongodb.*;

import java.util.Map;

public class MonDbUtil {
    public static void main(String args[]) {
        try {
            MongoClient mongoClient = new MongoClient("114.115.201.247", 27017);
            DB db = mongoClient.getDB("test");
            DBCollection collection = db.getCollection("person");
            DBCursor cursor = collection.find();
            DBObject object;
            Map map;
            while (cursor.hasNext()) {
                object = cursor.next();
                System.out.println(object);
            }
            System.out.println(collection.count());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}