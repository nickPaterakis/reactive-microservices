import React from 'react';
import PropTypes from 'prop-types';
// import { Switch } from 'react-router-dom';
import Header from '../../components/Header';
import Footer from '../../components/Footer';
import Aux from '../Auxiliary';

const Layout = ({ children }) => (
  <Aux>
    <Header />
    {children}
    <Footer />
  </Aux>
);

Layout.propTypes = {
  children: PropTypes.element.isRequired,
};

export default Layout;
