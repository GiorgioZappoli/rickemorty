import { readdirSync, statSync, readFileSync } from "fs";
import { MongoClient } from "mongodb";
import { join } from "path";

const filePath = "..\\ignored\\characters";
const uri = "mongodb://localhost:8090";
const dbName = "RickAndMorty";
const collectionName = "Characters";

const folderPath = filePath;

const files = readdirSync(folderPath);

insertData();

async function insertData() {
  const client = new MongoClient(uri);

  try {
    await client.connect();
    const db = client.db(dbName);
    const collection = db.collection(collectionName);

    for (let i = 0; i < files.length; i++) {
      const filePath = join(folderPath, files[i]);
      const data = readFileSync(filePath, "utf8");

      const jsonData = JSON.parse(data);
      const characters = jsonData.results;
      console.log(characters);

      if (Array.isArray(characters)) {
        await collection.insertMany(characters);
      } else {
        console.log("error");
      }
    }
  } catch (error) {
    console.error("Error", error);
  } finally {
    await client.close();
  }
}
