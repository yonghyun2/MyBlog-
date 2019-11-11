<template>
  <div>
    <ImgBanner imgSrc="https://source.unsplash.com/5brvJbR1Pn8/1600x900">
      <div style="line-height:1.2em;font-size:1.2em;" slot="text">Post</div>
    </ImgBanner>
    <v-container>
      <!-- Post -->
      <v-layout>
        <v-flex xs12>
          <PostList :limits="6" :loadMore="true"></PostList>
        </v-flex>
      </v-layout>
      <div class="text-xs-right">
        <v-btn color="info" @click="go('/postwrite')">Write</v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import PostList from "../components/PostList";

export default {
  name: "PostPage",
  components: {
    ImgBanner,
    PostList
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
