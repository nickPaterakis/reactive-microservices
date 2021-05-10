import axios from 'axios';

const getProperty = (propertyId) => axios.get(`http://localhost:8765/booking/api/v1/properties/property/${propertyId}`);

export default getProperty;
