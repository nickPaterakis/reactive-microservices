import instance from './custom-axios';
import bearerAuth from '../utils/BearerAuth';

export const saveUserDetailsMe = (token, userExtra) => instance.post('http://localhost:8765/booking/api/v1/users/me', userExtra, {
  headers: { Authorization: bearerAuth(token) },
});

export const getUserDetailsMe = (token) => instance.get('http://localhost:8765/booking/api/v1/users/me', {
  headers: { Authorization: bearerAuth(token) },
});

export const getUserProperties = (token) => instance.get('http://localhost:8765/booking/api/v1/properties/user', {
  headers: { Authorization: bearerAuth(token) },
});
