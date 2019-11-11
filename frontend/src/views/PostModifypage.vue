<template>
  <v-container>
    <br />
    <v-form ref="form">
      <v-text-field v-model="post.p_title" :counter="50" label="Title" required></v-text-field>
    </v-form>
    <markdown-editor v-model="post.p_body" class="markdown"></markdown-editor>
    <v-layout>
      <v-layout justify-end>
        <v-btn
          :loading="loading3"
          :disabled="loading3"
          color="blue-grey"
          class="white--text"
          @click="loader = 'loading3'"
        >
          Modify
          <v-icon right dark>cloud_upload</v-icon>
        </v-btn>
      </v-layout>
    </v-layout>
  </v-container>
</template>
<script>
import MainRepository from '../MainRepository'
import "v-markdown-editor/dist/index.css";
import Editor from "v-markdown-editor";
import axios from "axios";
import { read } from "fs";

const SERVER_CONFIG = require("../../serverconfig.json");
const BASE_URL = SERVER_CONFIG.SERVER_BASE_URL;

export default {
  components: {
    Editor
  },
  props: {
    num: { type: Number },
    title: { type: String },
    body: { type: String },
    email: { type: String }
  },
  data: function() {
    return {
      loader: null,
      loading3: false,
      selectedFile: null,
      post: {}
    };
  },
  created() {
    this.post.p_no = this.num;
    this.post.p_title = this.title;
    this.post.p_body = this.body;
  },
  methods: {
    onUpload() {
      this.post.p_email = MainRepository.loginManager.getCurrentUser().m_email;
      axios
        .put(BASE_URL + "/rest/post", this.post)
        .then(response => {
          if (response.data) {
            alert("수정성공!");
            this.$router.push("/post");
          } else {
            alert("수정실패!");
          }
        });
    }
  },
  watch: {
    loader () {
      const l = this.loader
      this[l] = !this[l]
      setTimeout(() => (this[l] = false), 3000)
      this.onUpload()
    }
  }
};
</script>
<style>
@import "simplemde/dist/simplemde.min.css";

.markdown {
  z-index: 0;
}
.custom-loader {
  animation: loader 1s infinite;
  display: flex;
}
@-moz-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@-webkit-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@-o-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>
