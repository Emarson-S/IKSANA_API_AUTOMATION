package base;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DataBaseClass {


    String mongoURI="mongodb://iksana:tjjxagyfxnxu%252567u*h%255Eg23gau@135.181.193.65:27017";
    String dataBase="db_welldercare_dev";
    String TBL_ONE_TIME_PASSWORD ="tbl_one_time_password";


    public void otpQuery(){
    MongoClient mongoClients = MongoClients.create(mongoURI);
    MongoDatabase db = mongoClients.getDatabase(dataBase);
    MongoCollection<Document> tables = db.getCollection(TBL_ONE_TIME_PASSWORD);
    FindIterable<Document> records = tables.find(new Document("phoneNo","7305652515")).sort(new Document("_id",-1)).limit(1);

    for(Document record:records){
        // System.out.println(record);
        System.out.println(record.get("otp"));
    }
}
}
