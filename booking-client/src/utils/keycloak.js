import Keycloak from 'keycloak-js';

const keycloakConfig = {
  url: 'http://localhost:8090/auth', 
  realm: 'booking', 
  clientId: 'booking-react-app-client',
};
const keycloak = new Keycloak(keycloakConfig);
export default keycloak;
