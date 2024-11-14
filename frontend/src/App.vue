<script setup lang="ts">
import { ref, computed } from 'vue'
import Characters from './views/CharactersView.vue'
import Episodes from './views/EpisodesView.vue'

const routes: Record<string, unknown> = {
  '/': Characters,
  '/episodes': Episodes,
}

const currentPath = ref(window.location.hash)

window.addEventListener('hashchange', () => {
  currentPath.value = window.location.hash
})

const currentView = computed(() => {
  return routes[currentPath.value.slice(1) || '/']
})
</script>

<template>
  <component :is="currentView" />
</template>

<style>
html,
body {
  margin: 0;
  padding: 0;
  height: 100%;
}
::selection {
  background: black;
  color: rgb(255, 152, 0);
}
</style>
