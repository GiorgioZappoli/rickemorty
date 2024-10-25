<template>
  <div class="wrapper">
    <div class="cards-containers">
      <div class="holder" v-for="card in cards" :key="card">
        <img :src="card.image" alt="immagine" />
        <div>
          <section>
            <a class="hover-effect" :href="card.url">
              <h2>{{ card.name }}</h2>
            </a>
            <p :class="['status-row', statusClass(card.status)]">
              {{ card.status }} - {{ card.species }}
            </p>
          </section>
          <section>
            <h5>Last known location:</h5>
            <p class="hover-effect">{{ card.location.name }}</p>
          </section>
          <section>
            <h5>First seen in:</h5>
            <a class="hover-effect" href="{{ card.episode[0] }}">{{
              card.episode[0]
            }}</a>
          </section>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import cardsData from "../public/card.json";
export default {
  data() {
    return {
      // cards: cardsData,
      cards: [],
      error: null,
    };
  },
  mounted() {
    fetch("https://rickandmortyapi.com/api/character/")
      .then((response) => {
        if (!response.ok) {
          throw new Error("errore nella richiesta");
        }
        return response.json();
      })
      .then((data) => {
        this.cards = this.getRandomCharacters(data.results, 6);
      })
      .catch((error) => {
        this.error = error.message;
      });
  },

  methods: {
    statusClass(status) {
      if (status === "Alive") return "status-alive";
      if (status === "Dead") return "status-dead";
      else return "status-unknown";
    },
    getRandomCharacters(characters, count) {
      const shuffled = characters.sort(() => 0.5 - Math.random());
      return shuffled.slice(0, count);
    },
  },
};
</script>

<style scoped>
.wrapper {
  background-color: #21242b;
  height: 100%;
}

.cards-containers {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(1fr, 1fr, 1fr);
  grid-gap: 30px;
  background-color: #21242b;
  width: 80%;
  height: auto;
  margin: auto;
  padding-top: 100px;
  padding-bottom: 100px;
}

.holder {
  display: flex;
  flex-direction: row;
  text-align: left;
  background-color: rgb(50, 55, 65);
  color: white;
  border-radius: 15px;
  font-family: -apple-system, "BlinkMacSystemFont", "Segoe UI", "Roboto",
    "Helvetica", "Arial", sans-serif, "Apple Color Emoji", "Segoe UI Emoji",
    "Segoe UI Symbol";
  font-size: large;
}

.holder div {
  display: flex;
  flex-direction: column;
}

.holder div section {
  margin: 0;
  padding-left: 15px;
}

.holder div section h2 {
  font-size: xx-large;
  margin: 0;
  margin-bottom: 0px;
}

.holder div section h5 {
  margin: 0px;
  font-weight: 50px;
  letter-spacing: 0.7px;
  color: grey;
}

.holder div section p {
  position: relative;
  margin-top: 0;
  letter-spacing: 1px;
}

.holder img {
  border-radius: 15px 0 0 15px;
  height: 240px;
}

.name {
  font-weight: 1000;
  list-style: none;
}

.holder div section p::before {
  content: "";
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 10px;
  height: 10px;
  border-radius: 50%;
}

.status-row {
  padding-left: 20px;
}

.status-alive::before {
  background-color: green;
}

.status-dead::before {
  background-color: red;
}

.status-unknown::before {
  background-color: gray;
}

.hover-effect:hover {
  color: rgb(255, 152, 0);
}

.hover-effect {
  color: inherit;
  text-decoration: none;
}
</style>

<!-- https://css-tricks.com/snippets/css/complete-guide-grid/ -->
