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
          v-if="deleteBtn === 'false'"
          :text="postsLength"
          color="green"
          title="Post List"
        >
          <v-data-table
            :headers="headers1"
            :items="getPosts"
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
              <td>{{ item.p_no }}</td>
              <td>{{ item.p_title }}</td>
              <td>{{ item.p_body }}</td>
            </template>
          </v-data-table>
        </material-card>
        <material-card
          v-else
          :text="postsLength"
          color="green"
          title="Post List"
        >
          <v-data-table
            :headers="headers2"
            :items="getPosts"
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
              <td>{{ item.p_no }}</td>
              <td>{{ item.p_title }}</td>
              <td>{{ item.p_body }}</td>
              <v-btn
                class="default v-btn--simple"
                dark
                icon
                @click="deleteItem(item.p_no)"
              >
                <v-icon>mdi-delete</v-icon>
              </v-btn>
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
  props: {
    deleteBtn: { type: String, default: 'true' }
  },
  data: () => ({
    headers1: [
      {
        sortable: false,
        text: 'No',
        value: 'p_no'
      },
      {
        sortable: false,
        text: 'Title',
        value: 'p_title'
      },
      {
        sortable: false,
        text: 'Context',
        value: 'p_body'
      }
    ],
    headers2: [
      {
        sortable: false,
        text: 'No',
        value: 'p_no'
      },
      {
        sortable: false,
        text: 'Title',
        value: 'p_title'
      },
      {
        sortable: false,
        text: 'Context',
        value: 'p_body'
      },
      {
        sortable: false,
        text: 'Delete'
      }
    ]
  }),
  computed: {
    getPosts () {
      const r = this.$store.getters.getPosts
      return (r ? r : [])
    },
    postsLength () {
      return String(this.$store.getters.getPosts.length) + '개의 Post가 있습니다.'
    }
  },
  created () {
    this.$store.dispatch('loadPosts')
  },
  methods: {
    ...mapActions(['deletePost']),
    deleteItem (no) {
      this.deletePost(no)
    }
  }
}
</script>
