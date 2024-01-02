import { createWebHistory, createRouter } from "vue-router";

const routes =  [
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
  routes,
});

export default router;