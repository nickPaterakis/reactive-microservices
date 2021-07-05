import React from 'react';
import PropTypes from 'prop-types';
import Header from '../../components/Header';
import Aux from '../Auxiliary';

const Layout = ({ children }) => (
  <Aux>
    <Header />
    {children}
  </Aux>
);

Layout.propTypes = {
  children: PropTypes.element.isRequired,
};

export default Layout;
