import React from 'react';
import Aux from '../../hoc/Auxiliary';

const spinner = () => (
  <Aux>
    <div className="spinner-container">
      <div className="spinner">
        <div className="double-bounce1" />
        <div className="double-bounce2" />
      </div>
    </div>
  </Aux> 
);

export default spinner;
