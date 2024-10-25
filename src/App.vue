<template>
  <div class="app-container">
    <HeaderSection></HeaderSection>

    <div class="lower-section">
      <FourDivs :cardsData="cardsData"></FourDivs>
    </div>
  </div>
</template>

<script>
import HeaderSection from "./HeaderSection.vue";
import FourDivs from "./FourDivs.vue";

export default {
  components: {
    HeaderSection,
    FourDivs,
  },
};
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
