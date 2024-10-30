<template>
  <div class="wrapper">
    <div class="cards-containers">
      <CardsComponent
        v-for="card in cards"
        :key="card.id"
        :card="card"
      ></CardsComponent>
    </div>
    <button class="prev button">Prev</button>
    <button class="next button">Next</button>
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
}

const cards = ref<Card[]>([])
const error = ref(null)
const page = ref<Page>({})

onMounted(() => {
  getCards('https://rickandmortyapi.com/api/character/')
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
  height: 100%;
}

.cards-containers {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(1fr, 1fr, 1fr);
  grid-gap: 30px;
  background-color: #21242b;
  width: 80%;
  height: auto;
  margin: auto;
  padding-top: 100px;
  padding-bottom: 100px;
}
</style>
