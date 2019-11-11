<template>
  <v-app>
    <VueScrollProgressBar height="0.5rem" />
    <Header />
    <Sidebar />
    <v-content>
      <transition name="viewtransition" mode="out-in">
        <router-view />
      </transition>
    </v-content>
    <go-top :bottom="150" bg-color="#4287f5" :size="45"></go-top>
    <Footer />
  </v-app>
</template>

<script>
import store from "./store";
import Header from "./components/Header";
import Footer from "./components/Footer";
import GoTop from "@inotom/vue-go-top";
import Sidebar from "./components/Sidebar";
import VueScrollProgressBar from '@guillaumebriday/vue-scroll-progress-bar'

export default {
  name: "App",
  store,
  components: {
    Header,
    Footer,
    GoTop,
    Sidebar,
    VueScrollProgressBar
  },
  data() {
    return {
      //
    };
  }
};
</script>

<style>
.viewtransition-enter-active {
  transition: transform .3s, opacity .3s;
  transform: translateY(0px);
  opacity: 1;
}
.viewtransition-enter {
  opacity: 0.5;
  transform: translateY(50px);
}
.viewtransition-leave-active {
  transition: transform .3s, opacity .3s;
  opacity: 0;
  transform: translateY(-50px);
}
.viewtransition-leave {
  opacity: 0.5;
  transform: translateY(0px);
}

/* horizontal slide */
/* .viewtransition-enter-active, .viewtransition-leave-active {
  transition: opacity .3s, transform .3s;
}
.viewtransition-enter, .viewtransition-leave-to {
  opacity: 0;
  transform: translateX(-30%);
} */
</style>
