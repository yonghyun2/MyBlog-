<template>
  <figure class="snip1273" @click="readLanguage()">
    <clazy-load :src="imgSrc">
      <img :src="imgSrc" height="200px" width="100%"/>
      <div class="preloader" slot="placeholder">
        <img src="/static/img/loading_spinner.gif" />
      </div>
    </clazy-load>
    <figcaption>
      <div id="head">{{title}}</div>
      <hr>
      <p id="truncate">{{ previewText }}</p>
    </figcaption>
  </figure>
</template>

<style>
@import url(https://fonts.googleapis.com/css?family=Raleway:400,500,700);
.snip1273 {
  font-family: 'Raleway', Arial, sans-serif;
  position: relative;
  margin: 10px;
  min-width: 310px -60px;
  max-width: 310px;
  width: 100%;
  color: #ffffff;
  text-align: left;
  background-color: #000000;
}
.snip1273 * {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-transition: all 0.4s ease-in;
  transition: all 0.4s ease-in;
}
.snip1273 img {
  position: relative;
  max-width: 100%;
  vertical-align: top;
}
.snip1273 figcaption {
  position: absolute;
  top: 0;
  right: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
  opacity: 0;
  padding: 20px 30px;
}
.snip1273 figcaption:before,
.snip1273 figcaption:after {
  width: 1px;
  height: 0;
}
.snip1273 figcaption:before {
  right: 0;
  top: 0;
}
.snip1273 figcaption:after {
  left: 0;
  bottom: 0;
}
.snip1273 h3,
.snip1273 p {
  line-height: 1.5em;
}
.snip1273 h3 {
  margin: 0 0 5px;
  font-weight: 700;
  text-transform: uppercase;
}
.snip1273 p {
  font-size: 0.8em;
  font-weight: 500;
  margin: 0 0 15px;
}
.snip1273 a {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  z-index: 1;
}
.snip1273:before,
.snip1273:after,
.snip1273 figcaption:before,
.snip1273 figcaption:after {
  position: absolute;
  content: '';
  background-color: #ffffff;
  z-index: 1;
  -webkit-transition: all 0.4s ease-in;
  transition: all 0.4s ease-in;
  opacity: 0.8;
}
.snip1273:before,
.snip1273:after {
  height: 1px;
  width: 0%;
}
.snip1273:before {
  top: 0;
  left: 0;
}
.snip1273:after {
  bottom: 0;
  right: 0;
}
.snip1273:hover img,
.snip1273.hover img {
  opacity: 0.4;
}
.snip1273:hover figcaption,
.snip1273.hover figcaption {
  opacity: 1;
}
.snip1273:hover figcaption:before,
.snip1273.hover figcaption:before,
.snip1273:hover figcaption:after,
.snip1273.hover figcaption:after {
  height: 100%;
}
.snip1273:hover:before,
.snip1273.hover:before,
.snip1273:hover:after,
.snip1273.hover:after {
  width: 100%;
}
.snip1273:hover:before,
.snip1273.hover:before,
.snip1273:hover:after,
.snip1273.hover:after,
.snip1273:hover figcaption:before,
.snip1273.hover figcaption:before,
.snip1273:hover figcaption:after,
.snip1273.hover figcaption:after {
  opacity: 0.1;
}
#truncate {
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
#head {
  display: inline-block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: normal;
  line-height: 1.2;
  height: 1.2em;
  text-align: left;
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}
</style>

<script src="https://unpkg.com/vue-clazy-load/dist/vue-clazy-load.min.js"></script>

<script>
export default {
  name: "Language",
  props: {
    num: { type: Number },
    date: { type: String },
    title: { type: String },
    body: { type: String },
    preview: { type: String},
    imgSrc: { type: String },
    email: { type: String }
  },
  data() {
    return {
      previewText: ''
    };
  },
  mounted() {
    var temp = document.createElement("div");
    temp.innerHTML = this.preview;
    this.previewText = temp.textContent;
  },
  methods: {
    readLanguage() {
      this.$router.push({ name: 'languageviewpage', 
                          params: {
                            num: this.num,
                            date: this.date,
                            imgSrc: this.imgSrc, 
                            title: this.title,
                            body: this.body,
                            email: this.email
                          }})
    }
  }
};
</script>
