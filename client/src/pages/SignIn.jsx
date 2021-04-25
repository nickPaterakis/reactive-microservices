import React, { useCallback, useEffect, useState } from 'react';
import Keycloak from 'keycloak-js';
import SignInForm from '../components/SignInComponents/SignInForm';

const SignIn = () => {
  const handleGoogleLogin = useCallback(async () => {
    const qParams = [
      `redirect_uri=${Google.REDIRECT_URI}`,
      `scope=${Google.SCOPE}`,
      'login_hint=paramsinghvc@gmail.com',
      'prompt=consent',
      'state=google',
    ].join('&');
    
    try {
      const response = await fetch(`/api/auth-url/google?${qParams}`);
      const url = await response.text();
      window.location.assign(url);
    } catch (e) {
      console.error(e);
    }
  }, []);

  const [state, setState] = useState({ keycloak: null, authenticated: false });

  useEffect(() => {
    const keycloak = Keycloak('/keycloak.json');
    keycloak.init({ onLoad: 'login-required' }).then((authenticated) => {
      setState({ keycloak, authenticated });
    });
  }, []);

  if (state.keycloak) {
    if (state.authenticated) {
      return (
        <div>
          <p>
            This is a Keycloak-secured component of your application.
          </p>
        </div>
      ); 
    } return (<div>Unable to authenticate!</div>);
  }

  return (
    <main className="sign-in">
      
      <h1 className="sign-in__title">
        Sing in to
        {' '}
        <span className="logo logo--sign-in">Booking</span>
      </h1>
      <div className="sign-in__dashboard u-padding-small">
        <button type="button" className="btn btn--sign-in u-margin-bottom-small" onClick={handleGoogleLogin}>
          Continue with Google
        </button>
        <SignInForm />
        <p className="sign-in__create-an-account">
          New to Booking?
          {' '} 
          <a href="/">Create an account</a>
          .
        </p>
      </div>

    </main>
  ); 
};

export default SignIn;
