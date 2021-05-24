import Keycloak from 'keycloak-js';

const keycloakConfig = {
  url: 'http://127.0.0.1:8080/auth', 
  realm: 'booking', 
  clientId: 'booking-react-app-client',
};
const keycloak = new Keycloak(keycloakConfig);
export default keycloak;
