<template>
  <div>
    <h1>{{userInfo.m_name}} 님 환영합니다^^</h1>
  </div>
</template>


<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState({
      userInfo: state => state.login.userInfo
    })
  },
  mounted() {
    window.scrollTo(0, 0);
  }
};
</script>