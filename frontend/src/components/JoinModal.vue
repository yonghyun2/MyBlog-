<template>
  <v-dialog v-model="joinDialog" max-width="600px">
    <template v-slot:activator="{ on }">
      <v-btn color="primary" v-on="on" @click='openJoinDialog'>Join</v-btn>
    </template>
    <v-card>
      <v-toolbar flat>
        <v-toolbar-title>Join</v-toolbar-title>
      </v-toolbar>
      <div class="pa-3">
        <v-text-field label="이름을 입력하세요" v-model.trim="user.m_name"></v-text-field>
        <v-text-field label="이메일입력하세요" v-model.trim="user.m_email"></v-text-field>

        <v-text-field type="password" label="패스워드를 입력하세요" v-model.trim="user.m_password"></v-text-field>
        <v-btn color="primary" depressed large block @click="join">회원가입</v-btn>
      </div>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from "axios";
import MainRepository from '../MainRepository'

const SERVER_CONFIG = require("../../serverconfig.json");
const BASE_URL = SERVER_CONFIG.SERVER_BASE_URL;

export default {
  data() {
    return {
      user: {}
    };
  },
  computed: {
    joinDialog: {
      get() {
        return MainRepository.uiManager.getJoinDialogFlag();
      },
      set(flag) {
        MainRepository.uiManager.updateJoinDialog(flag);
      }
    },
  },
  methods: {
    openJoinDialog() {
      MainRepository.uiManager.closeLoginDialog();
      MainRepository.uiManager.openJoinDialog();
    },
    join() {
      if (this.user.m_name == "" || this.user.m_name == null) {
        alert("이름을 입력하세요!");
        return;
      }
      if (this.user.m_email == "" || this.user.m_email == null) {
        alert("이메일을 입력하세요!");
        return;
      }
      if (this.user.m_password == "" || this.user.m_password == null) {
        alert("비밀번호를 입력하세요!");
        return;
      }
      axios.post(BASE_URL + "/jwt/join", this.user).then(res => {
        if (res.data) {
          alert("회원가입이 되었습니다.");
          MainRepository.uiManager.closeJoinDialog();
          MainRepository.loginManager.loginProcess({ m_email: this.user.m_email,
                                              m_password: this.user.m_password });
          this.$router.push({ name: "home" });
        } else {
          console.log(res.data);
          alert("회원가입에 실패했습니다.");
        }
      });
    }
  }
};
</script>