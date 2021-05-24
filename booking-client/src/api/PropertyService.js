import axios from 'axios';

export const getProperty = (propertyId) => axios.get(`http://localhost:8765/properties/property/${propertyId}`);

export const createListing = (listing) => axios.post('http://localhost:8765/properties/create', listing);

export const deleteListing = (propertyId) => axios.delete(`http://localhost:8765/properties/delete/${propertyId}`);
