import axios from 'axios';
import { config } from '../constants/systemConstants';

const instance = axios.create({
  // baseURL: config.url.API_BASE_URL,
});

instance.interceptors.response.use((response) => response, (error) => {
  if (error.response?.status === 500) {
    return { status: error.response.status };
  }
  return Promise.reject(error.response);
});

export default instance;
