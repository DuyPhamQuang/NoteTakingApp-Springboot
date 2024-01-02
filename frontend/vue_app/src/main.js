import { createApp } from 'vue'
import App from './App.vue'

import Paginate from "vuejs-paginate-next";

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import router from './router'


createApp(App).use(router).use(Paginate).mount('#app')
