export const MAX_ADULT_NUMBER = 16;
export const MAX_CHILD_NUMBER = 10;
export const MAX_ROOM_NUMBER = 30;

export const DATE_FORMAT = 'YYYY-MM-DD';

export const DATE_FORMAT_1 = 'YYYY/MM/DD';
export const PER_PAGE = 5;

const prod = {
  url: {
    // KEYCLOAK_BASE_URL: 'https://keycloak.herokuapp.com',
    // API_BASE_URL: 'https://myapp.herokuapp.com',
  },
};

const dev = {
  url: {
    KEYCLOAK_BASE_URL: 'http://localhost:8080',
    API_BASE_URL: 'http://localhost:8765',
  },
};

export const config = /* process.env.NODE_ENV === 'development' ? */dev;
/* : prod */
