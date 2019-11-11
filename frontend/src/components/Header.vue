<template>
  <v-toolbar class="pos" id="header">
    <v-toolbar-title class="changeCursor" @click="go('/')">Obey Your thirst Sprite</v-toolbar-title>
    <v-spacer></v-spacer>
    <div id="google_translate_element"></div>
    <h4 hidden-xs-only id="name" v-if="login">{{userInfo.m_name}}</h4>
    <v-toolbar-items>
      <v-btn flat @click="bookmark()">
        <v-icon>star</v-icon>
      </v-btn>
    </v-toolbar-items>
    <v-toolbar-items class="hidden-sm-and-down">
      <v-btn flat v-for="item in menu" :key="item.name" @click="go(item.path)">{{ item.name }}</v-btn>
    </v-toolbar-items>

    <!-- LoginModal Component -->
    <v-toolbar-items v-if="login" class="hidden-sm-and-down">
      <v-btn flat router :to="{name:'mypage'}">MyPage</v-btn>
      <v-btn flat @click="logout">LogOut</v-btn>
    </v-toolbar-items>
    <loginModal v-else class="hidden-sm-and-down" ref="modal" />

    <v-menu class="hidden-md-and-up">
      <v-toolbar-side-icon slot="activator"></v-toolbar-side-icon>
      <v-list>
        <v-list-tile v-for="item in menu" :key="item.name">
          <v-list-tile-content>
            <div class="changeCursor">
              <v-list-tile-title @click="go(item.path)">{{ item.name }}</v-list-tile-title>
            </div>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile v-if="login">
          <v-list-tile-content>
            <div class="changeCursor">
              <v-list-tile-title @click="go('/mypage')">MyPage</v-list-tile-title>
            </div>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile v-if="login==false">
          <v-list-tile-content>
            <div class="changeCursor">
              <v-list-tile-title @click="openModal">Login</v-list-tile-title>
            </div>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile v-if="login">
          <v-list-tile-content>
            <div class="changeCursor">
              <v-list-tile-title v-on:click="logout">Logout</v-list-tile-title>
            </div>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-menu>
  </v-toolbar>
</template>

<script>
import LoginModal from "./LoginModal";
import MainRepository from "../MainRepository";
import { mapState, mapActions, mapGetters } from "vuex";
import router from "../router";

export default {
  name: "Header",
  data() {
    return {
      lang: "",
      menu: [
        { name: "Language", path: "/language" },
        { name: "Post", path: "/post" },
        { name: "Repository", path: "/repository" }
      ]
    };
  },
  created: function() {
    window.addEventListener("scroll", function() {
      if (window.scrollY > 0) {
        document.getElementById("header").classList.add("v-toolbar--fixed");
      } else {
        document.getElementById("header").classList.remove("v-toolbar--fixed");
      }
    });
  },
  components: {
    LoginModal
  },
  computed: {
    ...mapState({
      login: state => state.login.login,
      userInfo: state => state.login.userInfo
    })
  },
  methods: {
    go(target) {
      this.$router.push(target);
    },
    bookmark() {
      alert("Ctrl + D를 눌러 즐겨 찾기를 해주세요!");
    },
    openModal() {
      MainRepository.uiManager.openLoginDialog();
    },
    logout() {
      MainRepository.loginManager.logoutProcess();
    }
  }
};
</script>
<style>
.pos {
  z-index: 15 !important;
}
#name {
  height: 100px;
  line-height: 100px;
  display: block;
  text-align: center;
}
.changeCursor {
  cursor: pointer;
}
</style>
