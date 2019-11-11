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
          Upload
          <v-icon right dark>cloud_upload</v-icon>
        </v-btn>
      </v-layout>
    </v-layout>
  </v-container>
</template>
<script>
import "v-markdown-editor/dist/index.css";
import Editor from "v-markdown-editor";
import axios from "axios";
import { read } from "fs";
import MainRepository from '../MainRepository';

const SERVER_CONFIG = require("../../serverconfig.json");
const BASE_URL = SERVER_CONFIG.SERVER_BASE_URL;

export default {
  components: {
    Editor
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
  created() {},
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
      let fd = new FormData();
      let imgsource = null;
      if (this.selectedFile != null)
        fd.append("image", this.selectedFile, this.selectedFile.name);
      else
        fd.append("image", null, null);

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
            .post(BASE_URL + "/rest/language/", this.language)
            .then(response => {
              if (response.data) {
                alert("등록성공!");
                this.$router.push("/language");
              } else {
                alert("등록실패!");
              }
            });
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
