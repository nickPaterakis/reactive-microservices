import {
  React, useState, useRef, useMemo, 
} from 'react';
import Select from 'react-select';
import countryList from 'react-select-country-list';
import Input from '../UI/Input';
import TextArea from '../UI/TextArea';
import validate from '../../utils/Validate';
import ErrorIcon from '../../assets/icons/warning.svg';
import Checkbox from '../UI/Checkbox';

const CreatePropertyForm = (props) => {
  const [createPropertyForm, setCreatePropertyForm] = useState({
    formControls: {
      propertyType: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      guestMaxNumber: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      bedroomNumber: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      bathNumber: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      amenities: {
        value: [
          { id: 1, value: 'Essentials (towels, bed, soap, toilet paper, and pillows)', isChecked: false },
          { id: 2, value: 'Wi-Fi', isChecked: false },
          { id: 3, value: 'TV', isChecked: false },
          { id: 4, value: 'Air Conditioning', isChecked: false },
          { id: 5, value: 'Iron', isChecked: false },
          { id: 6, value: 'Hair Dryer', isChecked: false },
          { id: 7, value: 'Breakfast', isChecked: false },
          { id: 8, value: 'Coffee', isChecked: false },
          { id: 9, value: 'Tea', isChecked: false },
          { id: 10, value: 'Indoor Fireplaces', isChecked: false },
        ],
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      guestSpace: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      title: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      description: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      pricePerNight: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      country: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      city: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      zipCode: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      street: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      number: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
    },
  });

  const propertyTypeOptions = [
    { 
      value: 'Apartment', 
      label: 'Apartment', 
    },
    { 
      value: 'House', 
      label: 'House', 
    },
    { 
      value: 'Secondary unit', 
      label: 'Secondary unit', 
    },
    { 
      value: 'Unique space', 
      label: 'Unique space', 
    },
    { 
      value: 'Bed and breakfast', 
      label: 'Bed and breakfast', 
    },
  ];

  const changeHandler = (event, actionMeta) => {
    if (event != null) {
      const { name } = event.target ? event.target : actionMeta;
      let value;
  
      if (event.value) {
        value = event.value;
      } else {
        value = event.target.value;
      } 
  
      const updatedControls = {
        ...createPropertyForm.formControls,
      };
  
      if (updatedControls[name]) {
        updatedControls[name].value = value;
      }
  
      setCreatePropertyForm({
        formControls: updatedControls,
      });
    }
  };
  const countryOptions = useMemo(() => countryList().getData(), []);

  const handleCheckbox = (event) => {
    const updatedControls = {
      ...createPropertyForm.formControls,
    };

    updatedControls.amenities.value.forEach((amenity) => {
      if (amenity.value === event.target.value) {
        amenity.isChecked = event.target.checked;
      }
    });

    setCreatePropertyForm({
      formControls: updatedControls,
    });
  };

  const formSubmitHandler = (event) => {
    event.preventDefault();

    const updatedControls = {
      ...createPropertyForm.formControls,
    };

    let formIsValid = true;
    for (const formElementId in updatedControls) {
      if (updatedControls[formElementId].validation) {
        updatedControls[formElementId].validation = validate(
          updatedControls[formElementId].value,
          updatedControls[formElementId].validationRules,
        );
    
        if (!updatedControls[formElementId].validation.valid && formIsValid) {
          updatedControls[formElementId].ref.current.focus();
        }
        formIsValid = updatedControls[formElementId].validation.valid && formIsValid;
      } 
    }

    if (formIsValid) {
      const formData = {};

      for (const formElementId in updatedControls) {
        if (updatedControls[formElementId].value) {
          formData[formElementId] = updatedControls[formElementId].value;
        } 
      }

      //   props.loadHandler(true);
      //   sendApplication(formData).then(() => {
      //     for (const formElementId in updatedControls) {
      //       if (Object.prototype.hasOwnProperty.call(updatedControls, formElementId)) {
      //         if (updatedControls[formElementId].value) {
      //           updatedControls[formElementId].value = '';
      //         } 
      //       }
      //     }

      //     props.modalHandler(true);
      //     props.loadHandler(false);

    //     setCreatePropertyForm({
    //       formControls: updatedControls,
    //     });
    //   }).catch(() => {
    //     props.modalHandler(false);
    //     props.loadHandler(false);
    //   });
    // } else {
    //   setCreatePropertyForm({
    //     formControls: updatedControls,
    //   });
    }
  };

  return (
    <div className="create-property-form">
      <form className="form" onSubmit={formSubmitHandler}>
        
        <div className="form-group">
          <label className="label">
            What type of property you have?*
          </label>
          <Select 
            className="select-input"
            onChange={changeHandler}
            ref={createPropertyForm.formControls.propertyType.ref}
            isClearable={true}
            isSearchable={true}
            name="propertyType"
            options={propertyTypeOptions} 
            theme={(theme) => ({
              ...theme,
              colors: {
                ...theme.colors,
                primary25: '#f7f7f7',
                primary50: '#ffea97',
                primary: '#ffd52f',
              },
            })}
          />
          {!createPropertyForm.formControls.propertyType.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.propertyType.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
        </div>

        <Input
          name="guestMaxNumber"
          className="input input--form"
          label="How many guests can host in your property?*"
          ref={createPropertyForm.formControls.guestMaxNumber.ref}
          labelStyle="label"
          value={createPropertyForm.formControls.guestMaxNumber.value}
          onChange={changeHandler}
          valid={createPropertyForm.formControls.guestMaxNumber.validation.valid}
          errorMessage={createPropertyForm.formControls.guestMaxNumber.validation.errorMessage}
        />

        <div className="form-group">
          <label className="label" htmlFor="legend">What type of space your guests will have in your property?*</label>
          <div className="radio-group">
            <label className="radio__container">
              <input
                type="radio"
                value="Entire place"
                name="guestSpace"
                checked={createPropertyForm.formControls.guestSpace.value === 'Entire place'}
                ref={createPropertyForm.formControls.guestSpace.ref}
                onChange={changeHandler}
              />
              <span className="checkmark" />
              Entire place
            </label>
            <label className="radio__container">
              <input
                type="radio"
                value="Private room"
                name="guestSpace"
                checked={createPropertyForm.formControls.guestSpace.value === 'Private room'}
                ref={createPropertyForm.formControls.guestSpace.ref}
                onChange={changeHandler}
              />
              <span className="checkmark" />
              Private room
            </label>
            <label className="radio__container">
              <input
                type="radio"
                value="Shared room"
                name="guestSpace"
                checked={createPropertyForm.formControls.guestSpace.value === 'Shared room'}
                ref={createPropertyForm.formControls.guestSpace.ref}
                onChange={changeHandler}
              />
              <span className="checkmark" />
              Shared room
            </label>
          </div>
          {!createPropertyForm.formControls.guestSpace.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.guestSpace.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
        </div>

        <div className="double-input">

          <div className="double-input__left-input">
            <label className="label">
              Bedroom Number*
              <input
                className="input input--form"
                type="text"
                name="bedroomNumber"
                ref={createPropertyForm.formControls.bedroomNumber.ref}
                value={createPropertyForm.formControls.bedroomNumber.value}
                onChange={changeHandler}
                valid={createPropertyForm.formControls.bedroomNumber.validation.valid}
                errorMessage={createPropertyForm.formControls.bedroomNumber.validation.errorMessage}
              />
            </label>
          </div>
          <div className="double-input__right-input">
            <label className="label">
              Bath Number*
              <input
                className="input input--form"
                type="text"
                name="bathNumber"
                ref={createPropertyForm.formControls.bathNumber.ref}
                value={createPropertyForm.formControls.bathNumber.value}
                onChange={changeHandler}
                valid={createPropertyForm.formControls.bathNumber.validation.valid}
                errorMessage={createPropertyForm.formControls.bathNumber.validation.errorMessage}
              />
            </label>
          </div>

          {!createPropertyForm.formControls.bedroomNumber.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.bedroomNumber.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
          {!createPropertyForm.formControls.bathNumber.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.bathNumber.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
        </div>

        <div className="form-group">
          <label className="label">
            What amenities your property have?*
          </label>

          <div className="checkbox-list">
            {createPropertyForm.formControls.amenities.value.map((amenity) => (
              <Checkbox
                key={amenity.id}
                name={amenity.value} 
                value={amenity.value}
                category="amenity" 
                onChange={handleCheckbox} 
                isChecked={amenity.isChecked ? 'true ' : ''}
              />
            ))}
            {!createPropertyForm.formControls.amenities.validation.valid 
              ? (
                <div className="error">
                  <p className="error-message">
                    <img className="error-icon" src={ErrorIcon} alt="error" />
                    {createPropertyForm.formControls.amenities.validation.errorMessage}
                  </p>
                </div>
              ) 
              : null}
          </div>
        </div>

        <Input
          name="pricePerNight"
          className="input input--form input--price"
          label="Price Per Night*"
          ref={createPropertyForm.formControls.pricePerNight.ref}
          labelStyle="label"
          value={createPropertyForm.formControls.pricePerNight.value}
          onChange={changeHandler}
          valid={createPropertyForm.formControls.pricePerNight.validation.valid}
          errorMessage={createPropertyForm.formControls.pricePerNight.validation.errorMessage}
        />

        <Input
          name="title"
          className="input input--form"
          label="Title*"
          ref={createPropertyForm.formControls.title.ref}
          labelStyle="label"
          value={createPropertyForm.formControls.title.value}
          onChange={changeHandler}
          valid={createPropertyForm.formControls.title.validation.valid}
          errorMessage={createPropertyForm.formControls.title.validation.errorMessage}
        />

        <TextArea
          name="description"
          className="text-area text-area--form"
          label="Description*"
          ref={createPropertyForm.formControls.description.ref}
          labelStyle="label"
          value={createPropertyForm.formControls.description.value}
          onChange={changeHandler}
          valid={createPropertyForm.formControls.description.validation.valid}
          errorMessage={createPropertyForm.formControls.description.validation.errorMessage}
        />

        <div className="form-group">
          <label className="label">
            Country*
          </label>
          <Select 
            className="select-input"
            onChange={changeHandler}
            ref={createPropertyForm.formControls.country.ref}
            isClearable={true}
            isSearchable={true}
            name="country"
            options={countryOptions} 
            theme={(theme) => ({
              ...theme,
              colors: {
                ...theme.colors,
                primary25: '#f7f7f7',
                primary50: '#ffea97',
                primary: '#ffd52f',
              },
            })}
          />
          {!createPropertyForm.formControls.country.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.country.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
        </div>

        <div className="double-input">

          <div className="double-input__left-input">
            <label className="label">
              City*
              <input
                className="input input--form"
                type="text"
                name="city"
                ref={createPropertyForm.formControls.city.ref}
                value={createPropertyForm.formControls.city.value}
                onChange={changeHandler}
                valid={createPropertyForm.formControls.city.validation.valid}
                errorMessage={createPropertyForm.formControls.city.validation.errorMessage}
              />
            </label>
          </div>
          
          <div className="double-input__right-input">
            <label className="label">
              Zip Code*
              <input
                className="input input--form"
                type="text"
                name="zipCode"
                ref={createPropertyForm.formControls.zipCode.ref}
                value={createPropertyForm.formControls.zipCode.value}
                onChange={changeHandler}
                valid={createPropertyForm.formControls.zipCode.validation.valid}
                errorMessage={createPropertyForm.formControls.zipCode.validation.errorMessage}
              />
            </label>
          </div>

          {!createPropertyForm.formControls.city.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.city.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
          {!createPropertyForm.formControls.zipCode.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.zipCode.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
        </div>

        <div className="double-input">

          <div className="double-input__left-input">
            <label className="label">
              Street*
              <input
                className="input input--form"
                type="text"
                name="street"
                ref={createPropertyForm.formControls.street.ref}
                value={createPropertyForm.formControls.street.value}
                onChange={changeHandler}
                valid={createPropertyForm.formControls.street.validation.valid}
                errorMessage={createPropertyForm.formControls.street.validation.errorMessage}
              />
            </label>
          </div>
          
          <div className="double-input__right-input">
            <label className="label">
              Number*
              <input
                className="input input--form"
                type="text"
                name="number"
                ref={createPropertyForm.formControls.number.ref}
                value={createPropertyForm.formControls.number.value}
                onChange={changeHandler}
                valid={createPropertyForm.formControls.number.validation.valid}
                errorMessage={createPropertyForm.formControls.number.validation.errorMessage}
              />
            </label>
          </div>

          {!createPropertyForm.formControls.street.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.street.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
          {!createPropertyForm.formControls.number.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.number.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
        </div>

        <button className="btn btn--submit" type="submit">
          <span className="btn__font">Create</span>
        </button>
      </form>
    </div>
  );
};

export default CreatePropertyForm;
