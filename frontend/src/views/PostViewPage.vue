<template>
  <div>
    <!-- Header image and title-->
    <clazy-load :src="'https://source.unsplash.com/5brvJbR1Pn8/1600x900'">
      <div class="preloader" slot="placeholder">
        <img src="/static/img/loading_spinner.gif" height="300px" width="100%" />
      </div>
      <v-img aspect-ratio="1.7" :src="'https://source.unsplash.com/5brvJbR1Pn8/1600x900'" height="300px" width="100%" gradient="rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)">
        <div id="category">
          Post
        </div>
        <v-layout align-center justify-center row fill-height>
          <v-flex text-xs-center>
            <span class="text-shadow display-2 font-weight-light">
              <transition appear name="fade">
                <div class="postTitle" style="line-height:1.2em;">{{ title }}</div>
              </transition>
              <transition appear name="fade">
                <div class="postTitle postDate">{{ date }}</div>
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
          <div id="postbody">
            {{ body }}
          </div>
          <v-layout justify-start>
            <v-btn
              color="blue darken-3"
              class="white--text"
              @click="go({ name: 'post'})"
            >
              List
              <v-icon>list</v-icon>
            </v-btn>
            <template v-if="currentUserEmail == email">
              <v-spacer></v-spacer>
              <v-btn 
                color="blue darken-3"
                class="white--text"
                @click="modifyPost()"
              >
                Edit
                <v-icon>edit</v-icon>
              </v-btn>
              <v-btn
                color="blue darken-3"
                class="white--text"
                @click="deletePost()"
              >
                Delete
                <v-icon>delete</v-icon>
              </v-btn>
            </template>
          </v-layout>
        </div>
      </transition>
      <!-- Post Footer Area using Disqus -->
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
  name: "PostViewPage",
  props: {
    num: [Number, String],
    email: { type: String },
    date: { type: String },
    title: { type: String },
    body: { type: String },
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
        this.page.identifier = 'post/' + newIdentifier;
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
          this.page.identifier = 'post/' + newIdentifier;
          this.page.title = newTitle;
        }
      });
    }
  },
  methods: {
    go(target) {
      this.$router.push(target);
    },
    modifyPost() {
      this.$router.push({ name: 'postmodify',
                          params: {
                            num: this.num,
                            title: this.title,
                            body: this.body,
                            email: this.email,
                          }})
    },
    deletePost() {
      Api(BASE_URL).delete('/rest/Post/' + this.num);
      this.go({ name: "post" });
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
.postTitle {
  color: white;
}
.postDate {
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
