<template>
  <div>
    <HeaderSection></HeaderSection>
    <EpisodeComponent :episodeId="episodeId"></EpisodeComponent>
    <RouterLink to="/" class="router">&#8592;Back</RouterLink>
  </div>
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
<style scoped>
div {
  background-color: rgb(33, 36, 43);
  padding-top: 0px;
}
.router {
  display: block;
  color: white;
  text-decoration: none;
  font-weight: bold;
  text-align: center;
  font-size: 50px;
  font-family: -apple-system, 'BlinkMacSystemFont', 'Segoe UI', 'Roboto',
    'Helvetica', 'Arial', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji',
    'Segoe UI Symbol';
}

.router:hover {
  color: rgb(255, 152, 0);
  text-decoration: underline;
}
</style>
