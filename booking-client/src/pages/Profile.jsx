import React from 'react';
import {
  Switch,
  Route,
  Link,
  useParams,
  useRouteMatch,
} from 'react-router-dom';
import PrivateRoute from '../components/auth/PrivateRoute';
import AccountNavbar from '../components/Header/AccountNavbar';
import Account from './Account';
import MyProperties from './MyProperties';
import MyProperty from './MyProperty';

function Profile() {
  const { path } = useRouteMatch();

  return (
    <main className="profile">
      <AccountNavbar />
      <Switch>
        <PrivateRoute roles={['user']} path={`${path}/account`}>
          <Account />
        </PrivateRoute>
        <PrivateRoute roles={['user']} path={`${path}/myproperties/:id`}>
          <MyProperty />
        </PrivateRoute>
        <PrivateRoute roles={['user']} path={`${path}/myproperties`}>
          <MyProperties />
        </PrivateRoute>
      </Switch>
    </main>
  );
}

export default Profile;
