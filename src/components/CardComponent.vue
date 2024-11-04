<template>
  <div class="holder">
    <img :src="card.image" alt="immagine" />
    <div>
      <section>
        <a class="hover-effect" :href="card.url">
          <h2>{{ card.name }}</h2></a
        >
        <p :class="['status-row', statusClass(card.status)]">
          {{ card.status }} - {{ card.species }}
        </p>
      </section>
      <section>
        <h5>Last known location:</h5>
        <a class="hover-effect" :href="card.location.url">{{
          card.location.name
        }}</a>
      </section>
      <section v-if="card.firstEpisode">
        <h5>First seen in:</h5>
        <a class="hover-effect" :href="card.firstEpisode.link">
          {{ card.firstEpisode.name }}
        </a>
      </section>
    </div>
  </div>
</template>

<script lang="ts" setup>
export interface Card {
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
  firstEpisode?: {
    link: string
    name: string
  }
  episode: string[]
}

type Props = {
  card: Card
}

defineProps<Props>()

function statusClass(status: string) {
  if (status === 'Alive') return 'status-alive'
  else if (status === 'Dead') return 'status-dead'
  else return 'status-unknown'
}
</script>

<style scoped>
.holder div section p::before {
  content: '';
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 10px;
  height: 10px;
  border-radius: 50%;
}

.status-row {
  padding-left: 20px;
}

.status-alive::before {
  background-color: green;
}

.status-dead::before {
  background-color: red;
}

.status-unknown::before {
  background-color: gray;
}

.holder {
  display: flex;
  flex-direction: row;
  text-align: left;
  background-color: rgb(50, 55, 65);
  color: white;
  border-radius: 15px;
  font-family: -apple-system, 'BlinkMacSystemFont', 'Segoe UI', 'Roboto',
    'Helvetica', 'Arial', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji',
    'Segoe UI Symbol';
  font-size: large;
}

.holder div {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-top: 10px;
  padding-bottom: 10px;
}

.holder div section {
  padding-left: 15px;
}

.holder div section h2 {
  font-size: xx-large;
  margin: 0;
}

.holder div section h5 {
  margin: 0px;
  font-weight: 50px;
  letter-spacing: 0.7px;
  color: grey;
}

.holder div section p {
  position: relative;
  margin-top: 0;
  letter-spacing: 1px;
}

.holder img {
  border-radius: 15px 0 0 15px;
  height: 240px;
}

.hover-effect:hover {
  color: rgb(255, 152, 0);
}

.hover-effect {
  color: inherit;
  text-decoration: none;
}
</style>
