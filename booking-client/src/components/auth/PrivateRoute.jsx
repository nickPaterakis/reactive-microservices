import React from 'react';
import { Redirect, Route } from 'react-router-dom';
import AuthorizedFunction from '../../utils/AuthorizedFunction';

const PrivateRoute = ({ children, roles, ...rest }) => {
  const auth = AuthorizedFunction(roles);
  return (
    <Route {...rest}>
      {() => (auth
        ? children
        : <Redirect to={{ pathname: '/' }} />)}
    </Route>
  ); 
};

export default PrivateRoute;
