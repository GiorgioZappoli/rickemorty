<template>
  <div class="app-container">
    <HeaderSection></HeaderSection>

    <div class="lower-section">
      <FourDivs></FourDivs>
    </div>
  </div>
</template>

<script setup lang="ts">
import HeaderSection from './components/HeaderSection.vue'
import FourDivs from './components/CardsContainer.vue'
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
  height: 100%;
}

.app-container {
  width: 100%;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
</style>
