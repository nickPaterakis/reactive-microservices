import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { ReactKeycloakProvider } from '@react-keycloak/web';
import keycloak from './utils/keycloak';
import App from './App';
import store from './store';
import Spinner from './components/UI/Spinner';
import { getUserDetailsMe, saveUserDetailsMe } from './api/UserService';

const initOptions = { pkceMethod: 'S256' };

const handleOnEvent = async (event, error) => {
  if (event === 'onAuthSuccess') {
    if (keycloak.authenticated) {
      let response = await getUserDetailsMe(keycloak.token);
      // const userExtra = {
      //   firstName: keycloak.tokenParsed.given_name,
      //   lastName: keycloak.tokenParsed.family_name,
      //   email: keycloak.tokenParsed.email,
      // };
      // let response = await saveUserDetailsMe(keycloak.token, userExtra);
      // console.log(`UserExtra created for ${keycloak.tokenParsed.email}`);
      if (response.status === 500) {
        const userExtra = {
          firstName: keycloak.tokenParsed.given_name,
          lastName: keycloak.tokenParsed.family_name,
          email: keycloak.tokenParsed.email,
        };
        response = await saveUserDetailsMe(keycloak.token, userExtra);
        console.log(`UserExtra created for ${keycloak.tokenParsed.email}`);
      }
      // keycloak.email = response.data.email;
      console.log(response.data);
    }
  }
};

ReactDOM.render(
  <React.StrictMode>
    <ReactKeycloakProvider 
      authClient={keycloak}
      initOptions={initOptions}
      LoadingComponent={<Spinner />}
      onEvent={(event, error) => handleOnEvent(event, error)}
    >
      <Provider store={store}>
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </Provider>
    </ReactKeycloakProvider>
  </React.StrictMode>,
  document.getElementById('root'),
);
