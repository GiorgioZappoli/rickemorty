<template>
  <HeaderSection></HeaderSection>
  <RouterLink to="/">characters</RouterLink>
  <EpisodeComponent :episodeId="episodeId"></EpisodeComponent>
</template>

<script setup lang="ts">
import HeaderSection from '@/components/HeaderSection.vue'
import EpisodeComponent from '@/components/EpisodeComponent.vue'
import { RouterLink, useRoute } from 'vue-router'

const route = useRoute()
const episodeId = Array.isArray(route.params.id)
  ? Number(route.params.id[0])
  : Number(route.params.id)
</script>

<style scoped></style>
