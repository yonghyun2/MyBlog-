<template>
  <div id="app">
    <div v-if="!image">
      <h2>Select an image</h2>
      <input type="file" @change="onFileChange" />
    </div>
    <div v-else>
      <img :src="image" />
      <button @click="removeImage">Remove image</button>
      <button @click="getImgUrl">Submit</button>
    </div>
  </div>
</template>

<script>
import ImgurService from "@/services/ImgurService";
export default {
  name: "ImgUpload",
  data() {
    return {
      image: "",
      f: ""
    };
  },
  methods: {
    onFileChange(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      this.f = files[0];
      this.createImage(files[0]);
    },
    createImage(file) {
      var image = new Image();
      var reader = new FileReader();
      var vm = this;

      reader.onload = e => {
        vm.image = e.target.result;
      };

      reader.readAsDataURL(file);
    },
    removeImage: function(e) {
      this.image = "";
      this.f = "";
    },
    getImgUrl() {
      ImgurService.getImgUrl(this.f);
    }
  }
};
</script>

<style>
#app {
  text-align: center;
}
img {
  width: 30%;
  margin: auto;
  display: block;
  margin-bottom: 10px;
}
</style>
