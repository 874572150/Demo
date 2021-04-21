// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// 全局变量
import globalConstant from './constant/globalConstant.js'
import $ from 'jquery'

Vue.config.productionTip = false

// 配置全局常量
Vue.prototype.GLOBAL = globalConstant;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
