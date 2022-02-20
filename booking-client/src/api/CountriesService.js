import axios from 'axios';
import { config } from '../constants/systemConstants';

export function getCountries(name) {
  return axios.get(`${config.url.API_PROPERTY_URL}/countries/${name}`); 
}

export function getAllCountries() {
  return axios.get(`${config.url.API_PROPERTY_URL}/countries/all`); 
}
