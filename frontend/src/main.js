// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import "./plugins/axios";
import App from "./App";
import router from "./router";
import store from "./store";

import Vuetify from "vuetify";
import "vuetify/dist/vuetify.min.css";
import "font-awesome/css/font-awesome.min.css";

import VueSimplemde from "vue-simplemde";
import "simplemde/dist/simplemde.min.css";
import VueClazyLoad from 'vue-clazy-load';

import VueAnalytics from 'vue-analytics'

import MainRepository from "./MainRepository";

Vue.config.productionTip = false;

Vue.use(VueSimplemde);
Vue.use(VueClazyLoad);

const APIKEY = require('../ApiKey.json');
Vue.use(VueAnalytics, {
  id: APIKEY.GoogleAnalytics.trackingid,
  router
})

Vue.use(Vuetify, {
  iconfont: "fa",
  theme: {
    primary: "#3f51b5",
    secondary: "#b0bec5",
    accent: "#8c9eff",
    error: "#b71c1c"
  }
});

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  store,
  beforeCreate() {
    MainRepository.loginManager.getMemberInfo();
  },
  render: h => h(App)
});

/*
* PWA 공부 참고 자료
* https://developers.google.com/web/fundamentals/primers/service-workers/?hl=ko
* https://developers.google.com/web/fundamentals/primers/async-functions?hl=ko
* https://developers.google.com/web/fundamentals/primers/promises?hl=ko
*/
