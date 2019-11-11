<template>
  <vue-displacement-slideshow
    :images="images"
    displacement=""
    :intensity.number="0.2"
    :speedIn.number="1.4"
    :speedOut.number="1.4"
    ease="Expo.easeInOut"
    ref="slideshow"></vue-displacement-slideshow>
</template>

<script>
import VueDisplacementSlideshow from "vue-displacement-slideshow";

export default {
  components: {
    VueDisplacementSlideshow,
  },
  computed: {
    images() {
      return [
        require("../assets/images/1.jpg"),
        require("../assets/images/2.jpg"),
        require("../assets/images/3.jpg"),
        require("../assets/images/4.jpg"),
        require("../assets/images/5.jpg"),
        require("../assets/images/6.jpg")
      ];
    }
  },
  methods: {
    init() {
      //We loop through all our images by calling the 'next' method of our component every 2 seconds
      window.slideScript = setInterval(() => {
        this.$refs.slideshow.next();
      }, 2000);
    }
  },
  mounted() {
    this.init();
  },
  destroyed() {
    clearInterval(window.slideScript);
  }
};
</script>
