import { React, useState, useRef } from 'react';
import CreatePropertyForm from '../components/CreatePropertyComponents/CreatePropertyForm';

const CreateProperty = () => (
  <div className="create-property">
    <div className="create-property__container">
      <div className="create-property__header">
        Create a new listing 
      </div>
      <div className="create-property__form-container">
        <CreatePropertyForm />
      </div>
    </div>
   
  </div>
);

export default CreateProperty;
