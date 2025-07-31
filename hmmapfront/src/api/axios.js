import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 5000,
});

instance.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
}, error => Promise.reject(error));

instance.interceptors.response.use(
  response => response.data,
  error => {
    alert(error.response?.data?.message || '请求失败');
    return Promise.reject(error);
  }
);

export default instance;
