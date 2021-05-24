import axios from 'axios';

const executeCountriesService = (name) => axios.get(`http://localhost:8765/countries/${name}`);

const receiveAllCountries = () => axios.get('http://localhost:8765/countries');

export default { executeCountriesService, receiveAllCountries };
