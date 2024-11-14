<script setup lang="ts">
import { ref, computed } from 'vue'
import Characters from './views/CharactersView.vue'

const routes: Record<string, unknown> = {
  '/': Characters,
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
