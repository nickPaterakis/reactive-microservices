import React, { useState } from 'react';
import ReactRouterPropTypes from 'react-router-prop-types';
import { useSelector } from 'react-redux';
import { useKeycloak } from '@react-keycloak/web';
import { Link, withRouter } from 'react-router-dom';
import AccountDropdown from '../UI/AccountDropdown';
import noImageProfile from '../../assets/images/no-image-profile.png';
import { config } from '../../constants/systemConstants';

const Header = ({ location }) => {
  const { keycloak } = useKeycloak();
  const [open, setOpen] = useState(false);
  const user = useSelector((state) => state.user);
  let navStyle = '';
  let logoStyle = '';
  
  const handleLogIn = () => {
    keycloak.login();
  };

  const handleOpen = () => setOpen(!open);

  if (location.pathname === '/reservation') {
    navStyle = 'header--display-none';
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
                      <span className="btn__image"><img src={user.profileImage ? config.url.USER_IMAGES_URL + user.profileImage : noImageProfile} alt="profile" /></span>
                      <span className="btn__text">{user.firstName}</span>
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
  location: ReactRouterPropTypes.location.isRequired,
};

export default withRouter(Header);
