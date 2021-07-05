import React, { useEffect } from 'react';
import SearchBar from '../components/HomeComponents/SearchBar';
import { removeState } from '../utils/localStorage';

const home = () => {
  useEffect(() => {
    removeState();
  });
  return (
    <main className="home">
      <SearchBar />
    </main>
  );
};

export default home;
