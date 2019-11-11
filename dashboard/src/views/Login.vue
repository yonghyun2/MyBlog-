<template>
  <v-container>
    <v-layout column>
      <v-flex>
        <v-text-field
          v-model="signObj.m_email"
          label="Email*"
          required
        />
      </v-flex>
      <v-flex>
        <v-text-field
          v-model="signObj.m_password"
          label="Password*"
          type="password"
          required
        />
      </v-flex>
      <v-flex>
        <v-btn
          color="primary"
          @click="loginAction"
        >
          Login
        </v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  data () {
    return {
      signObj: {}
    }
  },
  methods: {
    ...mapActions(['login']),
    loginAction () {
      this.login(this.signObj)
    }
  }
}
</script>
