import { readdirSync, statSync, readFileSync } from "fs";
import { MongoClient } from "mongodb";
import { join } from "path";

// const filePath = "..\\ignored\\characters";
const uri = "mongodb://localhost:8090";
const dbName = "RickAndMorty";
const collectionName = "Characters";

// const folderPath = filePath;

// const files = readdirSync(folderPath);

async function insertData(data) {
  const client = new MongoClient(uri);

  try {
    await client.connect();
    const db = client.db(dbName);
    const collection = db.collection(collectionName);

    // for (const file of files) {
    //   const filePath = join(folderPath, file);
    //   const data = readFileSync(filePath, "utf8");

    // const jsonData = JSON.parse(data);
    // const characters = jsonData.results;
    // console.log(characters);

    if (Array.isArray(data)) {
      await collection.insertMany(data);
    } else {
      console.log("error");
    }
  } catch (error) {
    //}
    console.error("Error", error);
  } finally {
    await client.close();
  }
}

async function getCharacters(url) {
  const response = await fetch(url);
  if (!response.ok) throw new Error("error in the request");
  return await response.json();
}

// getCharacters("https://rickandmortyapi.com/api/character/");

async function main() {
  //   let next = "https://rickandmortyapi.com/api/character";
  //   do {
  //     const page = await getCharacters(next);
  //     await insertData(page.results);
  //     next = page.info.next;
  //     //console.log(page);
  //   } while (next != null);
  // const firstPageUrl = "https://rickandmortyapi.com/api/character";
  // recursiveFetch(firstPageUrl);
}
main();

async function recursiveFetch(url) {
  const page = await getCharacters(url);
  await insertData(page.results);
  if (page.info.next != null) {
    await recursiveFetch(page.info.next);
  }
}

async function reduceFetch(params) {}
