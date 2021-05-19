import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { useKeycloak } from '@react-keycloak/web';
import { Link, withRouter } from 'react-router-dom';
import AccountDropdown from '../UI/AccountDropdown';
import ProfileImage from '../../assets/images/i.jpg';

const Header = ({ location }) => {
  const { keycloak } = useKeycloak();
  const [open, setOpen] = useState(false);
  let navStyle = '';
  let logoStyle = '';
  const handleLogIn = () => {
    keycloak.login();
  };

  const handleOpen = () => setOpen(!open);

  if (location.pathname !== '/') {
    navStyle = 'header';
    logoStyle = 'link logo logo--header';
  } else {
    navStyle = 'header';
    logoStyle = 'link logo logo--header';
  }
  
  return (
    <header className={navStyle}>
      <Link to="/" className={logoStyle}>
        Booking
      </Link>
      {keycloak && !keycloak.authenticated
                && (
                <Link exact={true} to="/signin" onClick={handleLogIn} className="link link--sign-in">
                  <span className="link__text--sign-in">
                    sign-in
                  </span>
                </Link>
                )}

      {keycloak && keycloak.authenticated
                && (
                  <div className="dropdown">
                    <div role="button" tabIndex={0} className="btn btn--account-dropdown prevent-selection" onClick={() => handleOpen()} onKeyPress={handleOpen}>
                      <span className="btn__image"><img src={ProfileImage} alt="profile" /></span>
                      <span className="btn__text">{keycloak.tokenParsed.given_name}</span>
                    </div>
                    <AccountDropdown 
                      open={open}
                      handleOpen={() => setOpen(false)}
                    />
                  </div>
                )}
      
    </header>
  );
};

Header.propTypes = {
  location: PropTypes.arrayOf.isRequired,
};

export default withRouter(Header);
