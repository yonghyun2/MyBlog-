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
          color="green"
          title="Language List"
          :text="languagesLength"
        >
          <v-data-table
            :headers="headers1"
            :items="getLanguages"
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
              <td>{{ item.l_no }}</td>
              <td>{{ item.l_title }}</td>
              <td>{{ item.l_body }}</td>
            </template>
          </v-data-table>
        </material-card>
        <material-card
          v-else
          color="green"
          title="Language List"
          :text="languagesLength"
        >
          <v-data-table
            :headers="headers2"
            :items="getLanguages"
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
              <td>{{ item.l_no }}</td>
              <td>{{ item.l_title }}</td>
              <td>{{ item.l_body }}</td>
              <v-btn
                class="default v-btn--simple"
                dark
                icon
                @click="deleteItem(item.l_no)"
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
        value: 'l_no'
      },
      {
        sortable: false,
        text: 'Title',
        value: 'l_title'
      },
      {
        sortable: false,
        text: 'Context',
        value: 'l_body'
      }
    ],
    headers2: [
      {
        sortable: false,
        text: 'No',
        value: 'l_no'
      },
      {
        sortable: false,
        text: 'Title',
        value: 'l_title'
      },
      {
        sortable: false,
        text: 'Context',
        value: 'l_body'
      },
      {
        sortable: false,
        text: 'Delete'
      }
    ]
  }),
  computed: {
    getLanguages () {
      const r = this.$store.getters.getLanguages
      return (r ? r : [])
    },
    languagesLength () {
      return String(this.$store.getters.getLanguages.length) + '개의 Language가 있습니다.'
    }
  },
  created () {
    this.$store.dispatch('loadLanguages')
  },
  methods: {
    ...mapActions(['deleteLanguage']),
    deleteItem (no) {
      this.deleteLanguage(no)
    }
  }
}
</script>
