<template>
  <v-layout mt-5 wrap>
    <v-flex v-for="language in slicedLanguages" v-bind:key="language.l_no" xs4>
      <Language
        class="ma-3"
        :num="language.l_no"
        :date="language.l_date"
        :title="language.l_title"
        :body="language.l_body"
        :preview="language.l_preview"
        :imgSrc="language.l_img"
        :email="language.l_email"
      ></Language>
    </v-flex>

    <v-flex xs12 text-xs-center round my-5 v-if="more">
      <v-btn color="info" dark v-on:click="loadMorelanguages">
        <v-icon size="25" class="mr-2">fa-plus</v-icon>더 보기
      </v-btn>
    </v-flex>
  </v-layout>
</template>
<script>
import Language from "@/components/Language";
import Api from "../services/Api";
import MainRepository from '../MainRepository';

export default {
  name: "languagesList",
  props: {
    limits: { type: Number, default: 3 },
    loadMore: { type: Boolean, default: false }
  },
  data() {
    return {
      l: this.limits,
      more: this.loadMore
    };
  },
  components: {
    Language
  },
  mounted() {
    this.getlanguages();
  },
  computed: {
    languages() {
      return MainRepository.languageManager.getLanguages();
    },
    slicedLanguages() {
      return this.languages.slice(0, this.l);
    }
  },
  methods: {
    async getlanguages() {
      MainRepository.languageManager.getAllLanguages();
    },
    loadMorelanguages() {
      this.l += 6;
      if (this.l >= this.languages.length)
        this.more = false;
    }
  }
};
</script>
<style>
.mw-700 {
  max-width: 700px;
  margin: auto;
}
</style>
