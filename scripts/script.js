const fs = require("fs");
const { MongoClient } = require("mongodb");

const filePath = "..\\ignored\\characters\\page1.json";
const uri = "mongodb://localhost:8090";
const dbName = "RickAndMorty";
const collectionName = "Characters";

async function insertData() {
  const client = new MongoClient(uri);

  try {
    await client.connect();
    console.log("ok");
    const db = client.db(dbName);
    const collection = db.collection(collectionName);

    const data = fs.readFileSync(filePath, "utf8");
    const jsonData = JSON.parse(data);

    const characters = jsonData.results;
    console.log(characters);

    if (Array.isArray(characters)) {
      await collection.insertMany(characters);
      console.log("ok2");
    } else {
      console.log("cazzo");
      await collection.insertOne(characters);
    }
  } catch (error) {
    console.error("Errore", error);
  } finally {
    await client.close();
  }
}

insertData();
