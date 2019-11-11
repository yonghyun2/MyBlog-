<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
  >
    <v-layout wrap>
      <v-flex
        md12
        lg6
      >
        <LanguageList delete-btn="false"/>
      </v-flex>
      <v-flex
        md12
        lg6
      >
        <PostList delete-btn="false"/>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import UserList from './UserList'
import LanguageList from './LanguageList'
import PostList from './PostList'

export default {
  components: {
    UserList,
    LanguageList,
    PostList
  },
  methods: {
    complete (index) {
      this.list[index] = !this.list[index]
    }
  }
}
</script>
