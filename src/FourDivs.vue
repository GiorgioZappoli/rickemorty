<template>
    <div class="four-divs">
        <div class="div-item" v-for="card in cards" :key="card">
                <img :src="card.image" alt="immagine" />
                    <ul>
                        <li>{{ card.name }}</li>
                        <li>{{ card.status }}-{{ card.species }}</li>
                        <li>{{ card.location.name }}</li>
                        <li>{{ card.episode[1] }}</li>
                    </ul>
        </div>
    </div>
</template>

<script>
    import cardsData from '../public/card.json';

    export default{
        data(){
            return{
                cards:cardsData              
            };
        }
    }
</script>

<style scoped>
* {
    margin: 0; 
    padding: 0; 
    box-sizing: border-box;
}


.four-divs {
    background-color: #272B33;
    width: 100%;
    padding: 81px 0;
    margin: 0; 
    display: flex;
    flex-wrap: wrap; 
    justify-content: space-between; 

}

.div-item {
    width: 50%; 
    text-align: left;
    background-color: rgb(32, 35, 41);
    box-sizing: border-box;
    display: flex;
    
    align-items: center;
    color: white;
    border-radius: 15px;
    /* max-width: 600px; */
    max-height: 220px;
}

.div-item ul {
    padding-left: 20px; 
    list-style: none;
}


.div-item img {
    
    height: 100%;
    border-radius: 15px 0 0 15px;
}
</style>

<!-- https://css-tricks.com/snippets/css/complete-guide-grid/ -->