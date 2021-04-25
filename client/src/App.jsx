import React from 'react';
import 'react-dates/lib/css/_datepicker.css';
import './styles/main.scss';
import { Route, Switch } from 'react-router-dom';
import { useKeycloak } from '@react-keycloak/web';
import 'react-dates/initialize';
import Home from './pages/Home';
import Layout from './hoc/Layout';
import PrivateRoute from './components/auth/PrivateRoute';
import Properties from './pages/Properties';
import Reservations from './pages/Reservations';
import Profile from './pages/Profile';
import { getUserProperties } from './api/UserService';

function App() {
  const { keycloak, initialized } = useKeycloak();

  console.log(keycloak);

  // if (keycloak.authenticated) {
  //   const userId = 1;
  //   console.log(getUserProperties(keycloak.token, userId));
  // }

  const routes = (
    <Switch>
      <Route path="/properties">
        <Properties />
      </Route>
      <PrivateRoute roles={['user']} path="/profile">
        <Profile />
      </PrivateRoute>
      <Route path="/reservations">
        <Reservations />
      </Route>
      <Route path="/">
        <Home />
      </Route>
    </Switch>
  );
  return (
    <div className="App">
      <Layout>
        {routes}
      </Layout>
    </div>
  );
}

export default App;
