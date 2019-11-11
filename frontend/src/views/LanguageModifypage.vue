<template>
  <v-container>
    <br />
    <v-form ref="form">
      <v-text-field v-model="language.l_title" :counter="50" label="Title" required></v-text-field>
    </v-form>
    <markdown-editor v-model="language.l_body" class="markdown"></markdown-editor>
    <v-layout>
      <v-flex>
        <v-layout>
          <img id="preview" :src="previewImageData" width="300" alt="로컬에 있는 이미지가 보여지는 영역" />
        </v-layout>
        <v-layout>
          <input type="file" id="getfile" @change="onFileSelected" />
        </v-layout>
      </v-flex>
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
    email: { type: String },
    imgSrc: { type: String }
  },
  data: function() {
    return {
      loader: null,
      loading3: false,
      selectedFile: null,
      language: {},
      previewImageData: null
    };
  },
  created() {
    this.language.l_no = this.num;
    this.language.l_title = this.title;
    this.language.l_body = this.body;
  },
  methods: {
    onFileSelected(event) {
      this.selectedFile = event.target.files[0];
      var input = event.target;

      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = e => {
          this.previewImageData = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      } else {
        this.previewImageData = null;
      }
    },
    onUpload() {
      if (this.selectedFile != null)
      {
        let fd = new FormData();
        let imgsource = null;
        fd.append("image", this.selectedFile, this.selectedFile.name);

        axios
        .post(BASE_URL + "/rest/imgUpload/", fd, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(response => {
          this.language.l_img = response.data;
          this.language.l_email = MainRepository.loginManager.getCurrentUser().m_email;
          axios
            .put(BASE_URL + "/rest/language/", this.language)
            .then(response => {
              if (response.data) {
                alert("수정성공!");
                this.$router.push("/language");
              } else {
                alert("수정실패!");
              }
            });
        });
      }
      else
      {
        this.language.l_img = this.imgSrc;
        this.language.l_email = MainRepository.loginManager.getCurrentUser().m_email;
          axios
            .put(BASE_URL + "/rest/language/", this.language)
            .then(response => {
              if (response.data) {
                alert("수정성공!");
                this.$router.push("/language");
              } else {
                alert("수정실패!");
              }
            });
      }
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

.preview {
  margin: 1em 0;
  display: block;
  background: rgb(240, 240, 240);
  border: 1px solid rgb(0, 0, 0);
}
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
