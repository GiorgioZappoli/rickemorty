import Characters from './views/CharactersView.vue'
import Episodes from './views/EpisodesView.vue'
import { createWebHistory, createRouter } from 'vue-router'

const routes = [
  { path: '/', component: Characters },
  { path: '/episodes/:id', component: Episodes },
]

export const router = createRouter({
    history: createWebHistory(),
    routes,
})