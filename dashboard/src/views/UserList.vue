<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
  >
    <v-layout
      justify-center
      wrap
    >
      <v-flex
        md12
      >
        <material-card
          :text="usersLength"
          color="green"
          title="User List"
        >
          <v-data-table
            :headers="headers"
            :items="getUsers"
            hide-actions
          >
            <template
              slot="headerCell"
              slot-scope="{ header }"
            >
              <span
                class="subheading font-weight-light text-success text--darken-3"
                v-text="header.text"
              />
            </template>
            <template
              slot="items"
              slot-scope="{ item }"
            >
              <td>{{ item.m_email }}</td>
              <td>{{ item.m_role }}</td>
              <td>
                <v-btn
                  v-if="item.m_role === 'team'"
                  class="default v-btn--simple"
                  dark
                  icon
                  @click="down(item.m_email)"
                >
                  <v-icon>mdi-arrow-down-bold</v-icon>
                </v-btn>
                <v-btn
                  v-if="item.m_role === 'visitor'"
                  class="default v-btn--simple"
                  dark
                  icon
                  @click="up(item.m_email)"
                >
                  <v-icon>mdi-arrow-up-bold</v-icon>
                </v-btn>
              </td>
              <td>
                <v-btn
                  class="default v-btn--simple"
                  dark
                  icon
                  @click="deleteItem(item.m_email)"
                >
                  <v-icon>mdi-delete</v-icon>
                </v-btn>
              </td>
            </template>
          </v-data-table>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  data: () => ({
    headers: [
      {
        sortable: false,
        text: 'Email',
        value: 'm_email'
      },
      {
        sortable: false,
        text: 'Role',
        value: 'm_role'
      },
      {
        sortable: false,
        text: 'Changing Role'
      },
      {
        sortable: false,
        text: 'Delete'
      }
    ]
  }),
  computed: {
    getUsers () {
      return this.$store.getters.getUsers
    },
    usersLength () {
      return String(this.$store.getters.getUsers.length) + '명의 User가 있습니다.'
    }
  },
  created () {
    this.$store.dispatch('loadUsers')
  },
  methods: {
    ...mapActions(['upRole', 'downRole', 'deleteUser']),
    down (email) {
      this.downRole(email)
    },
    up (email) {
      this.upRole(email)
    },
    deleteItem (email) {
      this.deleteUser(email)
    }
  }
}
</script>
