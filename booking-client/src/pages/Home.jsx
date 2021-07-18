import React, { useEffect } from 'react';
import SearchBar from '../components/HomeComponents/SearchBar';
import { removeState } from '../utils/localStorage';

const home = () => {
  useEffect(() => {
    removeState();
  });
  return (
    <main className="home">
      <img className="home__background" src="https://storage.cloud.google.com/bookinguniwa/images/backgrounds/john-towner-UO02gAW3c0c-unsplash.jpg?authuser=1" alt="background" />
      <SearchBar />
    </main>
  );
};

export default home;
