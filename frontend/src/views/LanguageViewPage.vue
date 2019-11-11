<template>
  <div>
    <!-- Header image and title-->
    <clazy-load :src="imgSrc">
      <div class="preloader" slot="placeholder">
        <img src="/static/img/loading_spinner.gif" height="300px" width="100%" />
      </div>
      <v-img aspect-ratio="1.7" :src="imgSrc" height="300px" width="100%" gradient="rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)">
        <div id="category">
          Language
        </div>
        <v-layout align-center justify-center row fill-height>
          <v-flex text-xs-center>
            <span class="text-shadow display-2 font-weight-light">
              <transition appear name="fade">
                <div class="languageTitle" style="line-height:1.2em;">{{ title }}</div>
              </transition>
              <transition appear name="fade">
                <div class="languageTitle languageDate">{{ date }}</div>
              </transition>
            </span>
          </v-flex>
        </v-layout>
      </v-img>
    </clazy-load>

    <!-- Body Area -->
    <v-container>
      <transition appear name="fade">
        <div>
          <div id="languagebody">
          </div>
          <v-layout justify-start>
            <v-btn
              color="blue darken-3"
              class="white--text"
              @click="go({ name: 'language'})"
            >
              List
              <v-icon>list</v-icon>
            </v-btn>
            <template v-if="currentUserEmail == email">
              <v-spacer></v-spacer>
              <v-btn 
                color="blue darken-3"
                class="white--text"
                @click="modifyLanguage()"
              >
                Edit
                <v-icon>edit</v-icon>
              </v-btn>
              <v-btn
                color="blue darken-3"
                class="white--text"
                @click="deleteLanguage()"
              >
                Delete
                <v-icon>delete</v-icon>
              </v-btn>
            </template>
          </v-layout>
        </div>
      </transition>
      <!-- Language Footer Area using Disqus -->
      <div id="disqus_thread" style="padding-top: 50px;"></div>
      <noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
    </v-container>
  </div>
</template>

<script src="https://unpkg.com/vue-clazy-load/dist/vue-clazy-load.min.js"></script>
<script>
import MainRepository from '../MainRepository';
import Api from '../services/Api'

const APIKEY = require('../../ApiKey.json')
const SERVER_CONFIG = require("../../serverconfig.json");
const BASE_URL = SERVER_CONFIG.SERVER_BASE_URL;

export default {
  name: "LanguageViewPage",
  props: {
    num: { type: Number },
    email: { type: String },
    date: { type: String },
    title: { type: String },
    body: { type: String },
    imgSrc: { type: String }
  },
  created() {
    if (typeof DISQUS == 'undefined')
    {
      /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
      // Disqus configuration
      var disqus_shortname = APIKEY.Disqus.shortname;
      var newIdentifier = this.num;
      var newTitle = this.title;
      window.disqus_config = function () {
        this.page.url = window.location.protocol + '//' + window.location.host + window.location.pathname;
        this.page.identifier = 'language/' + newIdentifier;
        this.page.title = newTitle;
      };
      
      /* * * DON'T EDIT BELOW THIS LINE * * */  
      (function() {
          var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
          dsq.src = 'http://' + disqus_shortname + '.disqus.com/embed.js';
          (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
      })();
    }
  },
  computed: {
    currentUserEmail() {
      return MainRepository.loginManager.getCurrentUser().m_email;
    }
  },
  mounted() {
    window.scrollTo(0, 0);

    // Parse markdown to html
    var showdown  = require('showdown'),
    converter = new showdown.Converter(),
    text      = this.body,
    html      = converter.makeHtml(text);
    var languagebody = document.getElementById('languagebody');
    languagebody.innerHTML = html + languagebody.innerHTML;

    if (typeof DISQUS != 'undefined')
    {
      /**
       *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
       *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
        /* * * Disqus Reset Function * * */
      var newIdentifier = this.num;
      var newTitle = this.title;
      DISQUS.reset({
        reload: true,
        config: function () {
          this.page.url = window.location.protocol + '//' + window.location.host + window.location.pathname;
          this.page.identifier = 'language/' + newIdentifier;
          this.page.title = newTitle;
        }
      });
    }
  },
  methods: {
    go(target) {
      this.$router.push(target);
    },
    modifyLanguage() {
      this.$router.push({ name: 'languagemodify',
                          params: {
                            num: this.num,
                            title: this.title,
                            body: this.body,
                            email: this.email,
                            imgSrc: this.imgSrc
                          }})
    },
    deleteLanguage() {
      Api(BASE_URL).delete('/rest/language/' + this.num);
      this.go({ name: "language",
                params: {
                  num: this.num,
                  title: this.title,
                  body: this.body,
                  email: this.email
                }})
    }
  }
};
</script>

<style>
#category {
  color: white;
  padding-left: 20px;
  padding-top: 20px;
  font-size: 25px;
}
.languageTitle {
  color: white;
}
.languageDate {
  font-size: 20px;
}
.fade-enter-active {
  transition: transform .5s, opacity 1s;
  transform: translateY(0px) scaleY(1);
}
.fade-enter {
  opacity: 0;
  transform: translateY(30px) scaleY(0);
}
</style>
