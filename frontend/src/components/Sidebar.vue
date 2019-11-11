<template>
  <v-layout wrap class="sidebar">
    <div class="sidebartoggle">
      <v-btn color="indigo" large dark fab @click.stop="drawer = !drawer">
        <v-icon>navigate_next</v-icon>
      </v-btn>
    </div>
    <v-navigation-drawer class="drawer" v-model="drawer" fixed temporary>
      <v-list>
        <v-list-tile v-for="item in items" :key="item.title">
          <v-list-tile-action>
            <div class="changeCursor">
              <v-icon>{{ item.icon }}</v-icon>
            </div>
          </v-list-tile-action>
          <v-list-tile-content>
            <div class="changeCursor">
              <v-list-tile-title @click="go(item.path)">{{ item.title }}</v-list-tile-title>
            </div>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
  </v-layout>
</template>
<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      drawer: null,
      items: [
        { title: "Home", icon: "dashboard", path: "/" },
        { title: "Language", icon: "fas fa-list", path: "/language" },
        { title: "Post", icon: "far fa-newspaper", path: "/post" },
        { title: "Repository", icon: "fas fa-project-diagram", path: "/repository" }
      ]
    };
  },
  methods: {
    go(target) {
      this.$router.push(target);
    }
  },
  computed: {
    ...mapState(["isLogin"])
  }
};
</script>
<style>
.sidebartoggle {
  position: fixed;
  left: -30px;
  top: 50%;
  z-index: 3;
}
.drawer {
  z-index: 15;
}
.changeCursor {
  cursor: pointer;
}
</style>
