import { createApp } from 'vue'
import App from './App.vue'

import Paginate from "vuejs-paginate-next";

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { FontAwesomeIcon } from './plugins/font-awesome'

import router from './router'
import store from "./store";


createApp(App).use(router)
              .use(Paginate)
              .use(store)
              .component("font-awesome-icon", FontAwesomeIcon)
              .mount('#app')
