package com.crawler.app.MongoDB;

import com.crawler.app.Logic.Crawling;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.crawler.app.Config.Strings.*;

public class MongoDBConnection implements Serializable {
    private static MongoDatabase database = null;
    private static MongoClient mongoClient = null;

    /**
     * @return Stabilisce la connessione al database locale
     */
    public static MongoDatabase getConnectionDB() {
        Logger mongoLogger = Logger.getLogger(DRIVER_MONGO);
        mongoLogger.setLevel(Level.SEVERE);

        mongoClient = new MongoClient(HOST, PORT);
        database = mongoClient.getDatabase(DATABASE_NAME);
        return null;
    }

    /**
     * Controlla se MongoDB è installato oppure se Mongod è in esecuzione sul sistema
     * andando a verificare l'esistenza dell'indirizzo al database
     *
     * @return true / false
     */
    public boolean checkMongo() {
        ServerAddress address;

        try {
            address = mongoClient.getAddress();
        } catch (MongoException me) {
            return false;
        }
        return address != null;
    }

    /**
     * Elimina l'intero database
     */
    public void dropDatabase() {
        MongoDBConnection.database.drop();
    }

    /**
     * Verifica se la collection esiste e se è vuota o meno
     *
     * @return true / false
     */
    public boolean checkCollectionExists() {
        MongoCollection<Document> collection = database.getCollection(CRAWLING_COLLECTION);

        boolean collectionExists = mongoClient.getDatabase(DATABASE_NAME).listCollectionNames()
                .into(new ArrayList<>()).contains(CRAWLING_COLLECTION);

        boolean collectionIsEmpty = collection.count() <= 0;

        if (collectionExists) {
            return !collectionIsEmpty;
        }
        return false;
    }

    /**
     * Conta i documenti all'interno di una collection
     * Utile per capire quanti seed ci sono e di conseguenza quante
     * attività di crawling sono state svolte
     *
     * @return numero di documenti
     */
    public int countElementsInCollection() {
        MongoCollection<Document> collection = database.getCollection(CRAWLING_COLLECTION);
        return (int) collection.count();
    }

    /**
     * Inserisce il seed e i relativi url in Visited e Crawl_frontier
     *
     * @param document, documento relativo all'attività di crawling
     */
    public void insertUrls(Document document) {
        MongoCollection<Document> collection = database.getCollection(CRAWLING_COLLECTION);
        collection.insertOne(document);
    }

    /**
     * Restituisce tutti gli url presi dall'array Crawl_frontier
     * oppure dall'array Visiteds, relativi ad un'attività di crawling
     *
     * @param urlSet nome dell'array
     * @param idSeed id della specifica ricerca
     * @return lista di url dell'array specificato
     */
    public List<String> getUrls(String urlSet, int idSeed) {
        MongoCollection<Document> collection = database.getCollection(CRAWLING_COLLECTION);

        List<String> list = new ArrayList<>();

        BasicDBObject obj = new BasicDBObject();
        obj.put(ID_SEED, idSeed);

        for (Document cur : collection.find(obj)) {
            list = (List<String>) cur.get(urlSet);
        }

        return list;
    }

    /**
     * Restituisce una lista contenente tutti i seeds
     *
     * @return lista di seeds
     */
    public List<Crawling> getAllSeeds() {
        MongoCollection<Document> collection = database.getCollection(CRAWLING_COLLECTION);
        List<Crawling> seedsList = new ArrayList<>();

        for (Document cur : collection.find()) {
            seedsList.add(new Crawling((int) cur.get(ID_SEED), cur.get(SEED).toString()));
        }
        return seedsList;
    }

    /**
     * Restituisce tutti gli urls dall'array Crawl_frontier
     * o dall'array Visiteds, specificando un range di id
     *
     * @param urlSet nome dell'array
     * @param idSeed id della specifica ricerca
     * @param idFrom id inizio range
     * @param idTo   id fine range
     * @return lista di urls che rientrano nel range
     */
    public List<String> getRangeUrls(String urlSet, int idSeed, int idFrom, int idTo) {
        MongoCollection<Document> collection = database.getCollection(CRAWLING_COLLECTION);

        List<String> list = new ArrayList<>();
        List<String> listFiltered = new ArrayList<>();

        BasicDBObject obj = new BasicDBObject();
        obj.put(ID_SEED, idSeed);

        for (Document cur : collection.find(obj)) {
            list = (List<String>) cur.get(urlSet);
        }

        for (int i = idFrom - 1; i < idTo; i++) {
            listFiltered.add(list.get(i));
        }

        return listFiltered;
    }

    /**
     * Elimina un documento dalla collection dato in input l'id del seed
     *
     * @param idSeed id della specifica attività di crawling
     */
    public void deleteCrawling(int idSeed) {
        MongoCollection<Document> collection = database.getCollection(CRAWLING_COLLECTION);

        Document document = new Document(ID_SEED, idSeed);
        collection.deleteOne(document);

        this.updateIdSeeds();
    }

    /**
     * Aggiorna gli id della collection a seguito di una cancellazione
     */
    private void updateIdSeeds() {
        MongoCollection<Document> collection = database.getCollection(CRAWLING_COLLECTION);
        Document oldSeed;
        Document newSeed;
        Document updatedSeed;

        for (int i = 0; i < countElementsInCollection(); i++) {
            oldSeed = new Document(ID_SEED, getAllSeeds().get(i).idSeed);
            newSeed = new Document(ID_SEED, i + 1);

            updatedSeed = new Document();
            updatedSeed.append(SET, newSeed);

            collection.updateOne(oldSeed, updatedSeed);
        }
    }

}