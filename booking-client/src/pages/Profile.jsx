import React from 'react';
import {
  Switch,
  useRouteMatch,
} from 'react-router-dom';
import PrivateRoute from '../components/auth/PrivateRoute';
import AccountNavbar from '../components/Header/AccountNavbar';
import Account from './Account';
import MyReservations from './MyReservations';
import MyProperties from './MyProperties';

function Profile() {
  const { path } = useRouteMatch();

  return (
    <main className="profile">
      <AccountNavbar />
      <Switch>
        <PrivateRoute roles={['BOOKING_USER']} path={`${path}/account`}>
          <Account />
        </PrivateRoute>
        <PrivateRoute roles={['BOOKING_USER']} path={`${path}/myproperties`}>
          <MyProperties />
        </PrivateRoute>
        <PrivateRoute roles={['BOOKING_USER']} path={`${path}/my-reservations`}>
          <MyReservations />
        </PrivateRoute>
      </Switch>
    </main>
  );
}

export default Profile;
