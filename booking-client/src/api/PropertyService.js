import axios from 'axios';
import instance from './custom-axios';
import bearerAuth from '../utils/BearerAuth';
import { config } from '../constants/systemConstants';

export function searchProperties(location, startDate, endDate, guests, currentPage) {
  return axios.get(
    `${config.url.API_PROPERTY_URL}/properties/search?location=${ 
      location 
    }&checkIn=${ 
      startDate
    }&checkOut=${ 
      endDate
    }&guestNumber=${  
      guests
    }&currentPage=${  
      currentPage}`,
  );
}

export const getProperty = (propertyId) => axios.get(`${config.url.API_PROPERTY_URL}/properties/property/${propertyId}`);

export const createListing = (formData, token) => axios.post(`${config.url.API_PROPERTY_URL}/properties/create-property`, formData, {
  headers: { 
    'Content-Type': ['multipart/form-data', 'application/json'],
    Authorization: bearerAuth(token),
  },
});

export const deleteListing = (propertyId, token) => axios.delete(`${config.url.API_PROPERTY_URL}/properties/delete/${propertyId}`, {
  headers: { 
    Authorization: bearerAuth(token),
  },
});

export const getUserProperties = (currentPage, token) => instance.get(`${config.url.API_PROPERTY_URL}/properties/my-properties/${currentPage}`, {
  headers: { Authorization: bearerAuth(token) },
});
