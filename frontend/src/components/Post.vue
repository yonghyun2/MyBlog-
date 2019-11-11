<template>
  <v-layout py-4 h-100 @click="readPost()">
    <v-flex row>
      <div class="caption">{{formatedDate}}</div>
      <h2 class="color-333 headline font-weight-light posttitle">{{title}}</h2>
      <p class="mb-1 color-666 font-weight-light subheading postbody">{{body}}</p>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  name: "Post",
  props: {
    num: { type: Number },
    date: { type: String },
    title: { type: String },
    body: { type: String },
    email: { type: String }
  },
  computed: {
    formatedDate() {
      let d = new Date(this.date);
      return `${d.getFullYear()}년 ${d.getMonth()}월 ${d.getDate()}일`;
    }
  },
  methods: {
    readPost() {
      this.$router.push({ name: 'postviewpage',
                          params: {
                            num: this.num,
                            date: this.date,
                            title: this.title,
                            body: this.body,
                            email: this.email
                          }})
    }
  }
};
</script>
<style>
.color-666 {
  color: #666;
}
.color-333 {
  color: #333;
}
.h-100 {
  height: 100%;
}
.posttitle {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.postbody {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>

