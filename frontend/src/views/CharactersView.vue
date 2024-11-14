<template>
  <main>
    <HeaderSection></HeaderSection>
    <a href="#/a">test</a>
    <CardsContainer></CardsContainer>
  </main>
</template>

<script setup lang="ts">
import HeaderSection from '../components/HeaderSection.vue'
import CardsContainer from '../components/CardsContainer.vue'
</script>

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
