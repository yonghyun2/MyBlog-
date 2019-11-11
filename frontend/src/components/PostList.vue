<template>
  <v-layout row wrap mw-700>
    <v-flex
      v-for="post in slicedPosts"
      :class="'xs' + 12 / column"
      v-bind:key="post.p_no"
      px-3
    >
      <Post
        :num="post.p_no"
        :date="post.p_date"
        :title="post.p_title"
        :body="post.p_body"
        :email="post.p_email"
      ></Post>
      <v-divider></v-divider>
    </v-flex>
    <v-flex xs12 text-xs-center round my-5 v-if="more">
      <v-btn color="info" dark v-on:click="loadMorePosts">
        <v-icon size="25" class="mr-2">fa-plus</v-icon>더 보기
      </v-btn>
    </v-flex>
  </v-layout>
</template>
<script>
import Post from "@/components/Post";
import Api from "../services/Api";
import MainRepository from '../MainRepository';

export default {
  name: "PostList",
  props: {
    column: { type: Number, default: 1 },
    limits: { type: Number, default: 4 },
    loadMore: { type: Boolean, default: false }
  },
  data() {
    return {
      l: this.limits,
      more: this.loadMore
    };
  },
  components: {
    Post
  },
  mounted() {
    this.getPosts();
  },
  computed: {
    posts() {
      return MainRepository.postManager.getPosts();
    },
    slicedPosts() {
      return this.posts.slice(0, this.l);
    }
  },
  methods: {
    async getPosts() {
      MainRepository.postManager.getAllPosts();
    },
    loadMorePosts() {
      this.l += 6;
      if (this.l >= this.posts.length)
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
