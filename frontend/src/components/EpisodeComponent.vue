<template>
  <div class="wrapper">
    <div class="episode-container">
      <h1>{{ data?.name }}</h1>
      <ul>
        <li class="gray">Air Date:</li>
        <li>{{ data?.air_date }}</li>
        <li class="gray">Episode:</li>
        <li>
          {{ data?.episode }}
        </li>
        <li class="gray">Number of characters in this episode:</li>
        <li class="last-row">{{ data?.characters.length }}</li>
      </ul>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'

export interface Episode {
  url: string
  air_date: string
  name: string
  episode: string
  characters: string[]
}

const props = defineProps<{ episodeId: number }>()
const data = ref<Episode>()

watch(
  () => props.episodeId,
  async newId => {
    if (newId) {
      await fetchEpisodeData(newId)
    }
  },
)

onMounted(async () => {
  if (props.episodeId) {
    await fetchEpisodeData(props.episodeId)
  }
})

async function fetchEpisodeData(Id: number) {
  try {
    const episode = await getEpisode(
      `https://rickandmortyapi.com/api/episode/${Id}`,
    )
    data.value = episode
  } catch (error) {
    console.error('error in the loading', error)
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
    (episode as Episode).name !== undefined &&
    (episode as Episode).air_date !== undefined &&
    (episode as Episode).episode !== undefined &&
    (episode as Episode).characters !== undefined
  )
}
</script>

<style scoped>
.wrapper {
  background-color: rgb(33, 36, 43);
  padding-top: 100px;
}
.episode-container {
  background-color: rgb(50, 55, 65);
  color: white;
  width: 60%;
  margin: 0 auto;
  border-radius: 15px;
  height: inherit;
  font-family: -apple-system, 'BlinkMacSystemFont', 'Segoe UI', 'Roboto',
    'Helvetica', 'Arial', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji',
    'Segoe UI Symbol';
  font-size: large;
}

ul {
  list-style: none;
  padding-left: 40%;
  text-align: left;
}
li {
  margin-bottom: 20px;
}
.last-row {
  padding-bottom: 50px;
}
.gray {
  color: gray;
  margin-bottom: 0px;
}
</style>
