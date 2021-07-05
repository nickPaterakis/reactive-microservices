import axios from 'axios';

export function getCountries(name) {
  return axios.get(`http://localhost:8081/countries/${name}`); 
}

const receiveAllCountries = () => axios.get('http://localhost:8765/countries');

export default receiveAllCountries;
