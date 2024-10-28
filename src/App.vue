<template>
  <div class="app-container">
    <HeaderSection></HeaderSection>

    <div class="lower-section">
      <FourDivs :cardsData="cardsData"></FourDivs>
    </div>
  </div>
</template>

<script setup>
import HeaderSection from "./HeaderSection.vue";
import FourDivs from "./CardsContainer.vue";
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
