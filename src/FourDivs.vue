<template>
    <div class="four-divs">
        <div class="div-item" v-for="n in 4" :key="n">
                <img :src="Clipboard.image" alt="immagine" />
                <p>{{ Clipboard.name }}</p>
        </div>
    </div>
</template>

<script>
    import cardsData from '../public/card.json';

    export default{
        data(){
            return{
                cards: cardsData
            };
        }
    }
</script>

<style scoped>
    .four-divs{
        display: flex;
        justify-content: space-around;
    }

    div.item{
        width: 200;
        text-align: center;
        margin: 10px;
        background-color: grey;
    }

    .div-item img{
        width: 50%;
        height: auto;
    }
</style>