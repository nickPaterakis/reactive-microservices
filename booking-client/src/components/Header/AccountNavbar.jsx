import React from 'react';
import { NavLink } from 'react-router-dom';

function AccountNavbar() {
  return (
    <nav className="account-navbar">
      <ul className="account-navbar__list">
        <li className="account-navbar__item">
          <NavLink to="/profile/account" activeClassName="selected">Account</NavLink>
        </li>
        <li className="account-navbar__item">
          <NavLink to="/profile/my-reservations" activeClassName="selected">Bookings</NavLink>
        </li>
        <li className="account-navbar__item">
          <NavLink to="/profile/myproperties" activeClassName="selected">My properties</NavLink>
        </li>
      </ul>
      
    </nav>
  );
}

export default AccountNavbar;
