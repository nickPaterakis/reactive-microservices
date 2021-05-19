/* eslint-disable array-callback-return */
/* eslint-disable consistent-return */
import {
  React, useState, useRef, useMemo, 
} from 'react';
import Select from 'react-select';
import countryList from 'react-select-country-list';
import ReactRouterPropTypes from 'react-router-prop-types';
import { useSelector } from 'react-redux';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import { createListing } from '../../api/PropertyService';
import Input from '../UI/Input';
import TextArea from '../UI/TextArea';
import validate from '../../utils/Validate';
import ErrorIcon from '../../assets/icons/warning.svg';
import Checkbox from '../UI/Checkbox';

const CreatePropertyForm = ({ history, loadHandler }) => {
  const [createPropertyForm, setCreatePropertyForm] = useState({
    formIsValid: false,
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
        touched: false,
      },
      maxGuestNumber: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
        touched: false,
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
        touched: false,
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
        touched: false,
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
        touched: false,
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
        touched: false,
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
        touched: false,
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
        touched: false,
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
        touched: false,
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
        touched: false,
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
        touched: false,
      },
      postCode: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
        touched: false,
      },
      streetName: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
        touched: false,
      },
      streetNumber: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
        touched: false,
      },
      
    },
  });
  const user = useSelector((state) => state.user);

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

  const countryOptions = useMemo(() => countryList().getData(), []);

  const changeHandler = (event, actionMeta) => {
    if (event != null) {
      const { name } = event.target ? event.target : actionMeta;
      let value;
  
      if (event.value) {
        value = name === 'country' ? event.label : event.value;
      } else {
        value = event.target.value;
      } 
  
      console.log(value);
      const updatedControls = {
        ...createPropertyForm.formControls,
      };

      const updatedFormElement = {
        ...updatedControls[name],
      };

      updatedFormElement.value = value;
      updatedFormElement.touched = true;
      updatedFormElement.validation = validate(value, updatedFormElement.validationRules);

      updatedControls[name] = updatedFormElement;
  
      // if (updatedControls[name]) {
      //   updatedControls[name].value = value;
      // }

      let formIsValid = true;
      for (const formElementId in updatedControls) {
        if (Object.prototype.hasOwnProperty.call(updatedControls, formElementId)) {
          formIsValid = updatedControls[formElementId].validation.valid && formIsValid;
        }
      } 
  
      setCreatePropertyForm({
        formControls: updatedControls,
        formIsValid,
      });
    }
  };

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

    const formData = {};

    for (const formElementId in createPropertyForm.formControls) {
      if (Object.prototype.hasOwnProperty.call(createPropertyForm.formControls, formElementId)) {
        if (formElementId === 'amenities') {
          formData[formElementId] = createPropertyForm
            .formControls[formElementId].value.filter((amenity) => amenity.isChecked);
          formData[formElementId] = formData[formElementId].map((amenity) => amenity.value);
        } else if (createPropertyForm.formControls[formElementId].value) {
          formData[formElementId] = createPropertyForm.formControls[formElementId].value;
        } 
      }
    }

    formData.ownerId = user.id;
    formData.image = 'Greece/athens/1/b3d49bbd-d287-44e3-a938-07a8721263f4.jpg';
    console.log(formData);
    loadHandler(true);

    createListing(formData).then(() => {
      loadHandler(false);
      history.push('/profile/myproperties');
    }).catch(() => {
      loadHandler(false);
    });
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
          && createPropertyForm.formControls.propertyType.touched
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
          name="maxGuestNumber"
          className="input input--form"
          label="How many guests can host in your property?*"
          ref={createPropertyForm.formControls.maxGuestNumber.ref}
          labelStyle="label"
          value={createPropertyForm.formControls.maxGuestNumber.value}
          onChange={changeHandler}
          touched={createPropertyForm.formControls.maxGuestNumber.touched}
          valid={createPropertyForm.formControls.maxGuestNumber.validation.valid}
          errorMessage={createPropertyForm.formControls.maxGuestNumber.validation.errorMessage}
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
          && createPropertyForm.formControls.bedroomNumber.touched 
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
                name="postCode"
                ref={createPropertyForm.formControls.postCode.ref}
                value={createPropertyForm.formControls.postCode.value}
                onChange={changeHandler}
                valid={createPropertyForm.formControls.postCode.validation.valid}
                errorMessage={createPropertyForm.formControls.postCode.validation.errorMessage}
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
          {!createPropertyForm.formControls.postCode.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.postCode.validation.errorMessage}
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
                name="streetName"
                ref={createPropertyForm.formControls.streetName.ref}
                value={createPropertyForm.formControls.streetName.value}
                onChange={changeHandler}
                valid={createPropertyForm.formControls.streetName.validation.valid}
                errorMessage={createPropertyForm.formControls.streetName.validation.errorMessage}
              />
            </label>
          </div>
          
          <div className="double-input__right-input">
            <label className="label">
              Number*
              <input
                className="input input--form"
                type="text"
                name="streetNumber"
                ref={createPropertyForm.formControls.streetNumber.ref}
                value={createPropertyForm.formControls.streetNumber.value}
                onChange={changeHandler}
                valid={createPropertyForm.formControls.streetNumber.validation.valid}
                errorMessage={createPropertyForm.formControls.streetNumber.validation.errorMessage}
              />
            </label>
          </div>

          {!createPropertyForm.formControls.streetName.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.streetName.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
          {!createPropertyForm.formControls.streetNumber.validation.valid 
            ? (
              <div className="error">
                <p className="error-message">
                  <img className="error-icon" src={ErrorIcon} alt="error" />
                  {createPropertyForm.formControls.streetNumber.validation.errorMessage}
                </p>
              </div>
            ) 
            : null}
        </div>
        {/* disabled={!createPropertyForm.formIsValid} */}

        <button className={`btn btn--submit ${createPropertyForm.formIsValid ? null : 'btn--submit-disabled'}`} type="submit">
          <span className="btn__font">Create</span>
        </button>
      </form>
    </div>
  );
};

CreatePropertyForm.propTypes = {
  history: ReactRouterPropTypes.history.isRequired,
  loadHandler: PropTypes.func.isRequired,
};

export default withRouter(CreatePropertyForm);
