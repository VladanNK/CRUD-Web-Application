import { createRouter, createWebHistory } from 'vue-router'
import Proizvod from '@/components/Proizvod.vue'
import App from '@/App.vue'
import '@/assets/style.css';

export const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: 'App',
      component: App
    }
  ]
})
