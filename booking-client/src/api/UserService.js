import axios from 'axios';
import instance from './custom-axios';
import bearerAuth from '../utils/BearerAuth';

export const saveUserDetailsMe = (token, userExtra) => instance.post('http://localhost:8082/users/me', userExtra, {
  headers: { Authorization: bearerAuth(token) },
});

export const getUserDetailsMe = (token) => instance.get('http://localhost:8082/users/me', {
  headers: { Authorization: bearerAuth(token) },
});

export const imageUpload = (formData) => axios.post('http://localhost:8082/users/imageUpload', formData, {
  headers: { 
    'Content-Type': 'multipart/form-data',
    // Authorization: bearerAuth(token),
  },
});

export const updateUser = (token, user) => instance.put('http://localhost:8082/users/update', user, {
  headers: { Authorization: bearerAuth(token) },
});
