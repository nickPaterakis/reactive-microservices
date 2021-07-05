import Keycloak from 'keycloak-js';
import { config } from '../constants/systemConstants';

const keycloakConfig = {
  url: `${config.url.KEYCLOAK_BASE_URL}/auth/`, 
  realm: 'booking', 
  clientId: 'booking-react-app-client',
};
const keycloak = new Keycloak(keycloakConfig);
export default keycloak;
