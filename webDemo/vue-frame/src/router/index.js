import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'login',
      component: resolve => require(['@/views/login.vue'], resolve),
    },
    {
      path: '/nav',
      name: 'nav',
      component: resolve => require(['@/frames/BasicFrame.vue'], resolve),
    },
  ]
})
