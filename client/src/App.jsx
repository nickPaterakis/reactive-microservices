import React from 'react';
import 'react-dates/lib/css/_datepicker.css';
import './styles/main.scss';
import { Route, Switch } from 'react-router-dom';
import 'slick-carousel/slick/slick.css'; 
import 'slick-carousel/slick/slick-theme.css';
import 'react-dates/initialize';
import Home from './pages/Home';
import Layout from './hoc/Layout';
import PrivateRoute from './components/auth/PrivateRoute';
import Properties from './pages/Properties';
import Reservations from './pages/Reservations';
import Profile from './pages/Profile';
import Property from './pages/Property';

function App() {
  const routes = (
    <Switch>
      <Route path="/properties">
        <Properties />
      </Route>
      <Route path="/property/:id">
        <Property />
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
