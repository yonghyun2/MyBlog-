<template>
  <div>
    <ImgBanner imgSrc="https://source.unsplash.com/5brvJbR1Pn8/1600x900">
      <div style="line-height:1.2em;font-size:1.2em;" slot="text">Language</div>
    </ImgBanner>
    <v-container>
      <!-- Language -->
      <v-layout>
        <v-flex xs12>
          <languageList :limits="6" :loadMore="true"></languageList>
        </v-flex>
      </v-layout>
      <div class="text-xs-right">
        <v-btn color="info" @click="go('/languagewrite')">Write</v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import LanguageList from "../components/LanguageList";

export default {
  name: "LanguagePage",
  components: {
    ImgBanner,
    LanguageList
  },
  mounted() {
    window.scrollTo(0, 0);
  },
  methods: {
    go(target) {
      this.$router.push(target);
    }
  }
};
</script>
