export const MAX_GUEST_NUMBER = 16;
export const DATE_FORMAT = 'YYYY-MM-DD';
export const DATE_FORMAT_1 = 'YYYY/MM/DD';
export const PER_PAGE = 5;

const prod = {
  url: {
    KEYCLOAK_BASE_URL: 'http://35.224.233.154:8080',
    API_PROPERTY_URL: 'http://property-service:8081',
    API_RESERVATION_URL: 'http://reservation-service:8083 ',
    API_USER_URL: 'http://user-service:8084',
    PROPERTY_IMAGES_URL: 'http://property-service:8081/image/',
    USER_IMAGES_URL: 'http://user-service:8084/users/image/',
  },
  KEYCLOAK_CLIENT_ID: 'client-service-client-prod',
};

const dev = {
  url: {
    KEYCLOAK_BASE_URL: 'http://localhost:8090',
    API_BASE_URL: 'http://localhost:8765',
    API_PROPERTY_URL: 'http://localhost:8081',
    API_RESERVATION_URL: 'http://localhost:8083',
    API_USER_URL: 'http://localhost:8084',
    PROPERTY_IMAGES_URL: 'http://localhost:8081/image/',
    USER_IMAGES_URL: 'http://localhost:8084/users/image/',
  },
  KEYCLOAK_CLIENT_ID: 'client-service-client-dev',
};

export const config = process.env.NODE_ENV === 'development' ? dev : prod;
