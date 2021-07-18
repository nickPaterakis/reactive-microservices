import axios from 'axios';
import { config } from '../constants/systemConstants';

export function getCountries(name) {
  return axios.get(`${config.url.API_PROPERTY_URL}/countries/${name}`); 
}

const receiveAllCountries = () => axios.get('http://35.193.38.160:8765/countries');

export default receiveAllCountries;
