import { React, useState } from 'react';
import CreatePropertyForm from '../components/CreatePropertyComponents/CreatePropertyForm';
import Spinner from '../components/UI/Spinner';

const CreateProperty = () => {
  const [load, setLoad] = useState(false);

  const loadHandler = (value) => {
    setLoad(value);
  };

  return (
    <div className="create-property">
      {load ? <Spinner /> : null }
      <div className="create-property__container">
        <div className="create-property__header">
          Create a new listing 
        </div>
        <div className="create-property__form-container">
          <CreatePropertyForm loadHandler={loadHandler} />
        </div>
      </div>
   
    </div>
  );
};

export default CreateProperty;
