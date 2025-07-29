{
  path: '/',
  name: 'Home',
  component: () => import('../views/HomeView.vue')
},
{
  path: '/search',
  name: 'Search',
  component: () => import('../views/SearchResult.vue')
},
{
  path: '/auth',
  name: 'LoginRegister',
  component: () => import('../views/LoginRegister.vue')
}
