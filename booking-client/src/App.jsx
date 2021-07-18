import React from 'react';
import 'react-dates/lib/css/_datepicker.css';
import './styles/main.scss';
import { Route, Switch } from 'react-router-dom';
import { ReactKeycloakProvider } from '@react-keycloak/web';
import { useDispatch } from 'react-redux';
import keycloak from './utils/keycloak';
import { getUserDetailsMe } from './api/UserService';
import 'slick-carousel/slick/slick.css'; 
import 'slick-carousel/slick/slick-theme.css';
import 'react-dates/initialize';
import Home from './pages/Home';
import Layout from './hoc/Layout';
import PrivateRoute from './components/auth/PrivateRoute';
import Properties from './pages/Properties';
import Profile from './pages/Profile';
import Property from './pages/Property';
import BookingConfirmation from './pages/BookingConfirmation';
import { setUser } from './store/actions/userActions';
import Spinner from './components/UI/Spinner';
import CreateProperty from './pages/CreateProperty';
import NoMatch from './pages/NoMatch';

require('dotenv').config();

function App() {
  const initOptions = { pkceMethod: 'S256' };
  const dispatch = useDispatch();

  const handleOnEvent = async (event) => {
    if (event === 'onAuthSuccess') {
      if (keycloak.authenticated) {
        try {
          const response = await getUserDetailsMe(keycloak.token);
          dispatch(setUser(response.data));
        } catch (er) {
          console.log(er);
        }
      }
    }
  };

  console.log(process.env.NODE_ENV);

  const routes = (
    <Switch>
      <Route path="/properties">
        <Properties />
      </Route>
      <Route path="/view-property/:id">
        <Property type="view" />
      </Route>
      <Route path="/property/:id">
        <Property type="reserve" />
      </Route>
      <PrivateRoute roles={['user']} path="/profile">
        <Profile />
      </PrivateRoute>
      <PrivateRoute roles={['user']} path="/create-property">
        <CreateProperty />
      </PrivateRoute>
      <PrivateRoute roles={['user']} path="/reservation">
        <BookingConfirmation />
      </PrivateRoute>
      <Route exact={true} path="/">
        <Home />
      </Route>
      {/* <Route path="*">
        <NoMatch />
      </Route> */}
    </Switch>
  );
  return (
    <div className="App">
      <ReactKeycloakProvider 
        authClient={keycloak}
        initOptions={initOptions}
        LoadingComponent={<Spinner />}
        onEvent={(event, error) => handleOnEvent(event, error)}
      >
        <Layout>
          {routes}
        </Layout>
      </ReactKeycloakProvider>
   
    </div>
  );
}

export default App;
