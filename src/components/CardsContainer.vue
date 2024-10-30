<template>
  <div class="wrapper">
    <h2></h2>
    <div class="cards-containers">
      <CardsComponent
        v-for="card in cards"
        :key="card.id"
        :card="card"
      ></CardsComponent>
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
import CardsComponent from './CardComponent.vue'
import { ref, onMounted } from 'vue'
import type { Card } from './CardComponent.vue'

interface Episode {
  url: string
  name: string
}

type Page = {
  next?: string
  prev?: string
  pages?: number
}

const cards = ref<Card[]>([])
const error = ref(null)
const page = ref<Page>({})

onMounted(() => {
  getCards('https://rickandmortyapi.com/api/character/?page=1')
})

function getCards(url: string) {
  fetch(url)
    .then(response => {
      if (!response.ok) {
        throw new Error('errore nella richiesta')
      }
      return response.json()
    })
    .then(data => {
      cards.value = data.results
      page.value = data.info

      for (let i = 0; i < data.results.length; i++) {
        fetch(cards.value[i].episode[0])
          .then(response => {
            if (!response.ok) {
              throw new Error("errore nella richiesta dell'url")
            }
            return response.json()
          })
          .then(data => {
            cards.value[i] = cardModifier(cards.value[i], data)
          })
          .catch(er => {
            error.value = er.message
          })
      }
    })
    .catch(er => {
      error.value = er.message
    })
}

function cardModifier(card: Card, episode: Episode) {
  card = {
    ...card,
    firstEpisode: { link: episode.url, name: episode.name },
  }
  return card
}
</script>

<style scoped>
.wrapper {
  background-color: #21242b;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  padding: 50px 0;
}

.cards-containers {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(1fr, 1fr, 1fr);
  grid-gap: 30px;
  background-color: #21242b;
  width: 80%;
  padding-top: 100px;
  padding-bottom: 30px;
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
  border-radius: 5px;
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
