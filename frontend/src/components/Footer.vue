<template>
  <v-footer dark height="auto">
    <v-card class="flex " flat tile>
      <v-card-title class="teal">
        <v-container class="p">
          <v-layout align-center row wrap>
            <v-flex xs12 sm6>
              <v-container>
                <v-layout class="mb-3">
                  <v-flex xs8 align-self-center>
                    <v-img :src="weatherInfo.weatherIconUrl" height="70px" width="70px" class="ml-auto mr-auto"></v-img>
                  </v-flex>
                  <v-flex xs4 align-self-center>
                    <v-layout column>
                      <v-flex>
                        <h2>{{weatherInfo.currentTemp}}도</h2>
                      </v-flex>
                      <v-flex>
                        <h2>{{weatherInfo.description}}</h2>
                      </v-flex>
                      <v-flex>
                        <h2>{{weatherInfo.city}}, {{weatherInfo.country}}</h2>
                      </v-flex>
                    </v-layout>
                  </v-flex>
                </v-layout>
                <hr/>
                <v-layout class="mt-3">
                  <v-flex xs4>
                    <v-layout column>
                      <v-flex align-self-center>
                        <v-img src="/static/icons/windIcon.png" height="50px" width="50px" class="ml-auto mr-auto"></v-img>
                      </v-flex>
                      <v-flex align-self-center>
                        <h2 class="ml-auto mr-auto">{{weatherInfo.windSpeed}} m/s</h2>
                      </v-flex>
                    </v-layout>
                  </v-flex>
                  <v-flex xs4>
                    <v-layout column>
                      <v-flex align-self-center>
                        <v-img src="/static/icons/humidityIcon.png" height="50px" width="50px" class="ml-auto mr-auto"></v-img>
                      </v-flex>
                      <v-flex align-self-center>
                        <h2 class="ml-auto mr-auto">{{weatherInfo.humidity}} %</h2>
                      </v-flex>
                    </v-layout>
                  </v-flex>
                  <v-flex xs4>
                    <v-layout column>
                      <v-flex align-self-center>
                        <v-img src="/static/icons/cloudIcon.png" height="50px" width="50px" class="ml-auto mr-auto"></v-img>
                      </v-flex>
                      <v-flex align-self-center>
                        <h2 class="ml-auto mr-auto">{{weatherInfo.cloud}} %</h2>
                      </v-flex>
                    </v-layout>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-flex>
            <v-flex xs12 sm6>
              <v-layout justify-center>
                <h2>Get connected with us on social networks!</h2>
              </v-layout>
              <v-layout justify-center>
                <v-btn v-for="icon in icons" :key="icon" class="mx-3" dark icon>
                  <v-icon size="24px">{{ icon }}</v-icon>
                </v-btn>
              </v-layout>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-title>
      <v-card-actions class="grey darken-3 justify-center">
        &copy;2019 —&nbsp;
        <strong>SSAFY 19.07.08 - 19.07.19 https://lab.ssafy.com/joytjddn/webmobile-sub2</strong>
      </v-card-actions>
    </v-card>
  </v-footer>
</template>

<script>
import WeatherService from "@/services/WeatherService";
export default {
  name: "Footer",
  data: () => ({
    icons: [
      "fab fa-facebook",
      "fab fa-twitter",
      "fab fa-google-plus",
      "fab fa-linkedin",
      "fab fa-instagram"
    ],
    weatherInfo: {
      currentTemp: 0,
      humidity: 0,
      description: "",
      weatherIconUrl: "",
      windSpeed: 0,
      country: "",
      city: "",
      cloud: ""
    }
  }),
  async created() {
    var info = await WeatherService.getWeatherInfo();
    this.weatherInfo.currentTemp = Math.round(info.data.main.temp - 273.15);
    this.weatherInfo.humidity = info.data.main.humidity;
    this.weatherInfo.description = info.data.weather[0].description;
    this.weatherInfo.weatherIconUrl =
      "http://openweathermap.org/img/w/" + info.data.weather[0].icon + ".png";
    this.weatherInfo.windSpeed = info.data.wind.speed;
    this.weatherInfo.country = info.data.sys.country;
    this.weatherInfo.city = info.data.name;
    this.weatherInfo.cloud = info.data.clouds.all;
  }
};
</script>
<style>
.p {
  padding: 10px;
}
</style>
