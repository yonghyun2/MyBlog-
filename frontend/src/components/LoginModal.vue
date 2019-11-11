<template>
  <v-toolbar-items>
    <v-dialog v-model="loginDialog" max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn flat v-on="on">Login</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Login</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-flex xs12>
                <v-text-field label="Email*" v-model="signObj.m_email" required></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field
                  label="Password*"
                  type="password"
                  v-model="signObj.m_password"
                  required
                ></v-text-field>
              </v-flex>
            </v-layout>
          </v-container>
          <!-- login button area -->
          <v-btn color="primary" @click="login">Login</v-btn>
          <v-btn color="primary">Facebook Login</v-btn>
          <!-- JoinModal Component -->
          <JoinModal />
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-toolbar-items>
</template>

<script>
import JoinModal from './JoinModal'
import MainRepository from "../MainRepository";
import { mapState, mapGetters } from "vuex";

export default {
  name: "loginModal",
  data() {
    return {
      signObj: {}
    };
  },
  components: {
    JoinModal
  },
  computed: {
    loginDialog: {
      get() {
        return MainRepository.uiManager.getLoginDialogStatus();
      },
      set(flag) {
        MainRepository.uiManager.updateLoginDialog(flag);
      }
    },
    ...mapGetters(["login/isLogin", "login/isLoginError"])
  },
  methods: {
    login() {
      MainRepository.loginManager.loginProcess(this.signObj);
    }
  }
};
</script>