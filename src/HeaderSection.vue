<template>
  <header class="header-section">
    <h1>{{ title }}</h1>
  </header>
</template>

<script>
export default {
  data() {
    return {
      title: "The Rick And Morty API",
    };
  },
};
</script>

<style>
.header-section {
  padding: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  background-color: rgb(245, 245, 245);
}

h1 {
  font-size: 100px;
  margin: 0%;
  text-align: center;
  font-weight: 900;
  font-family: -apple-system, "BlinkMacSystemFont", "Segoe UI", "Roboto",
    "Helvetica", "Arial", sans-serif, "Apple Color Emoji", "Segoe UI Emoji",
    "Segoe UI Symbol";
}
</style>
