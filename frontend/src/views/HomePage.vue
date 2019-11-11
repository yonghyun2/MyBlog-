<template>
  <div v-resize="resizingFontSize" :style="{ fontSize: fontSize + 'px' }">
    <SlideShow id="image">
    </SlideShow>
    <div style="line-height:1.2em; z-index: 10; position: relative; top: -500px; left: 10% ; font-size: 50px" slot="text">
      Great things in project are never done by one person.<br>They're done by a team of people.
      <br />We always have.
    </div>

    <v-container>
      <!-- About Me -->
      <v-layout my-5>
        <v-flex xs3>
        </v-flex>
        <v-flex xs6>
          <figure class="snip1200">
            <img :src="getImgUrl('profile.png')" height="600px" width="100%"/>
            <figcaption>
              <p>안녕하세요?<br>SSAFY 서울 2반 12조입니다.<br>사이다..좋아해요..?</p>
              <div class="heading">
                <h2>About<span> Us</span></h2>
              </div>
            </figcaption>
            <a href="#"></a>
          </figure>
        </v-flex>
        <v-flex xs3>
        </v-flex>
      </v-layout>

      <!-- Language -->
      <v-layout my-5>
        <v-flex xs12>
          <h2 class="headline my-5 text-xs-center">Language</h2>
          <LanguageList></LanguageList>
        </v-flex>
      </v-layout>

      <!-- Post -->
      <v-layout my-5>
        <v-flex xs12>
          <h2 class="headline my-5 text-xs-center">Post</h2>
          <PostList :column="2"></PostList>
        </v-flex>
      </v-layout>

      <!-- Github -->
      <v-layout my-5>
        <v-flex xs12>
          <h2 class="headline my-5 text-xs-center">Project</h2>
          <RepositoryList :limits="1" :loadMore="true"></RepositoryList>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import LanguageList from "../components/LanguageList";
import PostList from "../components/PostList";
import RepositoryList from "../components/RepositoryList";
import SlideShow from "../components/SlideShow"

export default {
  name: "HomePage",
  data() {
    return {
      width: 0,
      fontSize: 10
    };
  },
  components: {
    ImgBanner,
    LanguageList,
    PostList,
    RepositoryList,
    SlideShow
  },
  mounted() {
    window.scrollTo(0, 0);
  },
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    resizingFontSize() {
      this.fontSize = 15 + Math.floor((window.innerWidth - this.width) / 100);
    }
  }
};
</script>
<style>
@import url(https://fonts.googleapis.com/css?family=Raleway:400,500,800);
figure.snip1200 {
  font-family: 'Raleway', Arial, sans-serif;
  position: relative;
  overflow: hidden;
  margin: 10px;
  min-width: 220px;
  max-width: 600px;
  max-height: 600px;
  width: 100%;
  background: #000000;
  color: #ffffff;
  text-align: center;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.15);
}
figure.snip1200 * {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-transition: all 0.45s ease-in-out;
  transition: all 0.45s ease-in-out;
}
figure.snip1200 img {
  max-width: 100%;
  position: relative;
  opacity: 0.9;
}
figure.snip1200 figcaption {
  position: absolute;
  top: 45%;
  left: 7%;
  right: 7%;
  bottom: 45%;
  border: 1px solid white;
  border-width: 1px 1px 0;
}
figure.snip1200 .heading {
  overflow: hidden;
  -webkit-transform: translateY(50%);
  transform: translateY(50%);
  position: absolute;
  bottom: 0;
  width: 100%;
}
figure.snip1200 h2 {
  display: table;
  margin: 0 auto;
  padding: 0 10px;
  position: relative;
  text-align: center;
  width: auto;
  text-transform: uppercase;
  font-weight: 400;
}
figure.snip1200 h2 span {
  font-weight: 800;
}
figure.snip1200 h2:before,
figure.snip1200 h2:after {
  position: absolute;
  display: block;
  width: 1000%;
  height: 1px;
  content: '';
  background: white;
  top: 50%;
}
figure.snip1200 h2:before {
  left: -1000%;
}
figure.snip1200 h2:after {
  right: -1000%;
}
figure.snip1200 p {
  top: 50%;
  -webkit-transform: translateY(-50%);
  transform: translateY(-50%);
  position: absolute;
  width: 100%;
  padding: 0 20px;
  margin: 0;
  opacity: 0;
  line-height: 1.6em;
  font-size: 0.9em;
}
figure.snip1200 a {
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  position: absolute;
  z-index: 1;
}
figure.snip1200:hover img,
figure.snip1200.hover img {
  opacity: 0.25;
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
figure.snip1200:hover figcaption,
figure.snip1200.hover figcaption {
  top: 7%;
  bottom: 7%;
}
figure.snip1200:hover p,
figure.snip1200.hover p {
  opacity: 1;
  -webkit-transition-delay: 0.35s;
  transition-delay: 0.35s;
}
#image {
  width: 1600px;
  height: 900px;
}
</style>
