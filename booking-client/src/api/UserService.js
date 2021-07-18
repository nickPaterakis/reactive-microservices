import axios from 'axios';
import instance from './custom-axios';
import bearerAuth from '../utils/BearerAuth';
import { config } from '../constants/systemConstants';

export const saveUserDetailsMe = (token, userExtra) => instance.post(`${config.url.API_USER_URL}/users/me`, userExtra, {
  headers: { Authorization: bearerAuth(token) },
});

export const getUserDetailsMe = (token) => instance.get(`${config.url.API_USER_URL}/users/me`, {
  headers: { Authorization: bearerAuth(token) },
});

export const imageUpload = (formData, token) => axios.post(`${config.url.API_USER_URL}/users/imageUpload`, formData, {
  headers: { 
    'Content-Type': 'multipart/form-data',
    Authorization: bearerAuth(token),
  },
});

export const updateUser = (token, user) => instance.put(`${config.url.API_USER_URL}/users/update`, user, {
  headers: { Authorization: bearerAuth(token) },
});
