<template>
  <h1>{{ data?.name }}</h1>
  <p>Air Date</p>
  <p>{{ data?.air_date }}</p>
  <p>{{ data?.episode }}</p>
  <p>Number of characters in this episode: {{ data?.characters.length }}</p>
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
