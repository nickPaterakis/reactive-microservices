import axios from 'axios';
import instance from './custom-axios';
import bearerAuth from '../utils/BearerAuth';

export const getProperty = (propertyId) => axios.get(`http://localhost:8081/properties/property/${propertyId}`);

export const createListing = (formData) => axios.post('http://localhost:8081/properties/create-property', formData, {
  headers: { 
    'Content-Type': ['multipart/form-data', 'application/json'],
    
  },
});

export const deleteListing = (propertyId) => axios.delete(`http://localhost:8081/properties/delete/${propertyId}`);

export function searchProperties(location, startDate, endDate, guests, currentPage) {
  return axios.get(
    `http://localhost:8081/properties/search?location=${ 
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

export const getUserProperties = (currentPage, token) => instance.get(`http://localhost:8081/properties/${currentPage}`, {
  headers: { Authorization: bearerAuth(token) },
});
