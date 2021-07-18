export const MAX_GUEST_NUMBER = 16;
export const DATE_FORMAT = 'YYYY-MM-DD';
export const DATE_FORMAT_1 = 'YYYY/MM/DD';
export const PER_PAGE = 5;

const prod = {
  url: {
    KEYCLOAK_BASE_URL: 'http://34.123.156.85:8080',
    API_PROPERTY_URL: 'http://34.135.212.25',
    API_RESERVATION_URL: 'http://34.135.212.25',
    API_USER_URL: 'http://34.135.212.25',
    PROPERTY_IMAGES_URL: 'http://34.135.212.25/image/',
    USER_IMAGES_URL: 'http://34.135.212.25/users/image/',
  },
  KEYCLOAK_CLIENT_ID: 'client-service-client-prod',
};

const dev = {
  url: {
    // KEYCLOAK_BASE_URL: 'http://localhost:8090',
    KEYCLOAK_BASE_URL: 'http://34.123.156.85:8080',
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
