import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const state = {

  id:''

};

const mutations = {
  incCount(id) {

    state.id = id;
  }
};

const store = new Vuex.Store({
  state,
  mutations:mutations
});

export default store;
