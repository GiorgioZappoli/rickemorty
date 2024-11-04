<template>
  <main>
    <HeaderSection></HeaderSection>
    <CardsContainer></CardsContainer>
  </main>
</template>

<script setup lang="ts">
import HeaderSection from './components/HeaderSection.vue'
import CardsContainer from './components/CardsContainer.vue'
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
  height: 100%;
}
</style>
