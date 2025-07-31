import axios from './axios';

export function login(account, password) {
  return axios.post('/auth/login', { account, password });
}
