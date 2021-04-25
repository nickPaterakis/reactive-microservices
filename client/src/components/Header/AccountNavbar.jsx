import React from 'react';
import { Link } from 'react-router-dom';

function AccountNavbar() {
  return (
    <nav className="account-navbar">
      <ul className="account-navbar__list">
        <li className="account-navbar__item">
          <Link to="/profile/account">Account</Link>
        </li>
      </ul>
      <ul className="account-navbar__list">
        <li className="account-navbar__item">
          <Link to="/profile/account">Bookings</Link>
        </li>
      </ul>
      <ul className="account-navbar__list">
        <li className="account-navbar__item">
          <Link to="/profile/account">Reviews</Link>
        </li>
      </ul>
      <ul className="account-navbar__list">
        <li className="account-navbar__item">
          <Link to="/profile/myproperties">My properties</Link>
        </li>
      </ul>
      
    </nav>
  );
}

export default AccountNavbar;
