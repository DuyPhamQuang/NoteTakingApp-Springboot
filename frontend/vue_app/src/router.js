import { createWebHistory, createRouter } from "vue-router";

const routes =  [
  {
    path: "/login",
    component: () => import("./components/LoginUser.vue")
  },

  {
    path: "/register",
    component: () => import("./components/RegisterUser.vue")
  },

  {
    path: "/",
    alias: "/notetaking/",
    name: "notelist",
    component: () => import("./components/NotesList")
  },

  {
    path: "/notetaking/:id",
    name: "note-detail",
    component: () => import("./components/NoteDetail")
  }

];

const router = createRouter({
  history: createWebHistory(),
  routes 
});

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/login');
  } else {
    next();
  }
});

export default router;

