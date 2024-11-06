<template>
  <div class="wrapper">
    <div class="cards-containers">
      <CardComponent
        v-for="card in cards"
        :key="card.id"
        :card="card"
      ></CardComponent>
    </div>
    <div class="button-container">
      <button
        :disabled="!page.prev"
        @click="
          getCards(`https://rickandmortyapi.com/api/character/?page=${1}`)
        "
      >
        First
      </button>
      <button
        :disabled="!page.prev"
        class="prev"
        @click="page.prev && getCards(page.prev)"
      >
        Prev
      </button>
      <button
        :disabled="!page.next"
        class="next"
        @click="page.next && getCards(page.next)"
      >
        Next
      </button>
      <button
        :disabled="!page.next"
        @click="
          getCards(
            `https://rickandmortyapi.com/api/character/?page=${page.pages}`,
          )
        "
      >
        Last
      </button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import CardComponent from './CardComponent.vue'
import { ref, onMounted } from 'vue'
import type { Card } from './CardComponent.vue'

interface Episode {
  url: string
  name: string
}

interface Results {
  id: number
  image: string
  url: string
  name: string
  status: string
  species: string
  location: {
    url: string
    name: string
  }
  episode: string[]
}

type Page = {
  next?: string
  prev?: string
  pages?: number
}

const cards = ref<Card[]>([])
const page = ref<Page>({})

onMounted(() => {
  getCards('http://localhost:8080/data')
})

async function getCards(url: string) {
  const [info, characters] = await fetchCharacters(url)
  const charactersCards = characters
    .filter(r => isResults(r))
    .map(r => resultsToCard(r))
  const cardsWithFirstEpisode = charactersCards.map(async card => {
    if (card.episode.length > 0) {
      const episode = await getEpisode(card.episode[0])
      return cardModifier(card, episode)
    }
    return card
  })
  const awaitedCards = await Promise.all(cardsWithFirstEpisode)

  cards.value = awaitedCards

  if (isPage(info)) page.value = info
}

function cardModifier(card: Card, episode: Episode): Card {
  card = {
    ...card,
    firstEpisode: { link: episode.url, name: episode.name },
  }
  return card
}

function isPage(page: unknown): page is Page {
  return (
    (page as Page).next !== undefined &&
    (page as Page).prev !== undefined &&
    (page as Page).pages !== undefined
  )
}

function isResults(results: unknown): results is Results {
  return (
    (results as Results).name !== undefined &&
    (results as Results).episode !== undefined &&
    (results as Results).id !== undefined &&
    (results as Results).image !== undefined &&
    (results as Results).location !== undefined &&
    (results as Results).species !== undefined &&
    (results as Results).status !== undefined &&
    (results as Results).url !== undefined
  )
}

function resultsToCard(results: Results): Card {
  return {
    id: results.id,
    episode: results.episode,
    image: results.image,
    location: results.location,
    name: results.name,
    species: results.species,
    status: results.status,
    url: results.url,
  }
}

async function getEpisode(url: string): Promise<Episode> {
  const response = await fetch(url)
  if (!response.ok) throw new Error('Erroe nella richiesta')
  const data = await response.json()
  if (isEpisode(data)) {
    return data
  } else {
    throw new Error('Il risultato non è di tipo Episode')
  }
}

function isEpisode(episode: unknown): episode is Episode {
  return (
    (episode as Episode).url !== undefined &&
    (episode as Episode).name !== undefined
  )
}
async function fetchCharacters(url: string): Promise<[unknown, unknown[]]> {
  const response = await fetch(url)
  if (!response.ok) throw new Error('errore nella richiesta')
  const data = await response.json()
  const results = data?.results
  const characters = Array.isArray(results) ? results : []
  const info = data?.info
  return [info, characters]
}
</script>

<style scoped>
.wrapper {
  background-color: #21242b;
  padding: 100px 10% 50px 10%;
}

.cards-containers {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(1fr, 1fr, 1fr);
  grid-gap: 30px;
  margin-bottom: 30px;
}

.button-container {
  display: flex;
  justify-content: center;
  gap: 10px;
}

button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 20px 40px;
  font-size: 1.2rem;
  cursor: pointer;
  border-radius: 10px;
  flex: 1;
  max-width: 200px;
}

button:hover {
  background-color: #2980b9;
}

button:disabled {
  background-color: grey;
  color: #bdc3c7;
  cursor: not-allowed;
}
</style>
