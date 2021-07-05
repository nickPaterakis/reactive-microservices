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
import SelectedImages from '../UI/SelectedImages';

const CreatePropertyForm = ({ history, loadHandler }) => {
  const [createPropertyForm, setCreatePropertyForm] = useState({
    isFormValid: false,
    formControls: {
      propertyType: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: false,
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
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          onlyNumbers: true,
        },
        touched: false,
      },
      bedroomNumber: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          onlyNumbers: true,
        },
        touched: false,
      },
      bathNumber: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          onlyNumbers: true,
        },
        touched: false,
      },
      amenities: {
        value: [
          { id: 1, value: 'Essentials (towels, bed, soap, toilet paper, and pillows)', isChecked: true },
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
          valid: false,
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
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          maxLength: 100,
        },
        touched: false,
      },
      description: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          maxLength: 250,
        },
        touched: false,
      },
      pricePerNight: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          onlyNumbersAndDecimalNumbers: true,
        },
        touched: false,
      },
      country: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          maxLength: 80,
        },
        touched: false,
      },
      city: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          maxLength: 30,
        },
        touched: false,
      },
      postCode: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          onlyNumbers: true,
          maxLength: 30,
        },
        touched: false,
      },
      streetName: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          maxLength: 30,
        },
        touched: false,
      },
      streetNumber: {
        value: '',
        ref: useRef(null),
        validation: {
          valid: false,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
          onlyNumbers: true,
        },
        touched: false,
      },
      images: {
        value: [],
        validation: {
          valid: false,
          errorMessage: '',
        },
      },
    },
  });
  const user = useSelector((state) => state.user);
  const inputEl = useRef(null); 

  const propertyTypeOptions = [
    {
      value: { id: 1, name: 'House' },
      label: 'House',
    },
    {
      value: { id: 2, name: 'Apartment' },
      label: 'Apartment',
    },
    {
      value: { id: 3, name: 'Secondary unit' },
      label: 'Secondary unit',
    },
    {
      value: { id: 4, name: 'Unique space' },
      label: 'Unique space',
    },
    {
      value: { id: 5, name: 'Bed and breakfast' },
      label: 'Bed and breakfast',
    },
  ];

  const guestSpaceOptions = [
    {
      value: { id: 1, name: 'Entire place' },
      label: 'Entire place',
    },
    {
      value: { id: 2, name: 'Private room' },
      label: 'Private room',
    },
    {
      value: { id: 3, name: 'Shared room' },
      label: 'Shared room',
    },
  ];

  const countryOptions = useMemo(() => countryList().getData(), []);

  const isFormValid = (formData) => {
    let ifv = true;
    for (const formElementId in formData) {
      if (Object.prototype.hasOwnProperty.call(formData, formElementId)) {
        ifv = formData[formElementId].validation.valid && ifv;
      }
    }
    return ifv;
  };

  const changeHandler = (event, actionMeta) => {
    if (event != null) {
      const { name } = event.target ? event.target : actionMeta;
      let value;

      if (event.value) {
        value = name === 'country' ? { id: event.value, name: event.label } : event.value;
      } else {
        value = event.target.value;
      }

      const updatedControls = {
        ...createPropertyForm.formControls,
      };

      const updatedFormElement = {
        ...updatedControls[name],
      };

      let val;
      if ((value !== null) && (typeof value === 'object')) {
        val = value.name;
      } else {
        val = value;
      }
 
      updatedFormElement.value = value;
      updatedFormElement.touched = true;
      updatedFormElement.validation = validate(val, updatedFormElement.validationRules);

      console.log(updatedFormElement.validation);
      updatedControls[name] = updatedFormElement;

      // if the form is valid the create button will become available
      const ifv = isFormValid(updatedControls);

      console.log(ifv);
      setCreatePropertyForm({
        formControls: updatedControls,
        isFormValid: ifv,
      });
    }
  };

  const handleCheckbox = (event) => {
    const updatedControls = {
      ...createPropertyForm.formControls,
    };
    
    updatedControls.amenities.value.forEach((amenity) => {
      if (amenity.value === event.target.value && amenity.id !== 1) {
        amenity.isChecked = event.target.checked;
      }
    });

    setCreatePropertyForm({
      formControls: updatedControls,
    });
  };

  const handleSelectedImages = (event) => {
    const files = Array.from(event.target.files);

    const updatedControls = {
      ...createPropertyForm.formControls,
    };

    updatedControls.images.value = files;

    createPropertyForm.formControls
      .images.validation.valid = updatedControls.images.value.length > 0;

    const ifv = isFormValid(updatedControls);

    setCreatePropertyForm({
      formControls: updatedControls,
      isFormValid: ifv,
    });
  };

  const formSubmitHandler = (event) => {
    event.preventDefault();

    const formData = {};
    const fd = new FormData();

    for (const formElementId in createPropertyForm.formControls) {
      if (Object.prototype.hasOwnProperty.call(createPropertyForm.formControls, formElementId)) {
        if (formElementId === 'amenities') {
          formData[formElementId] = createPropertyForm
            .formControls[formElementId].value.filter((amenity) => amenity.isChecked);
          formData[formElementId] = formData[formElementId]
            .map((amenity) => ({ id: amenity.id, name: amenity.value }));
        } else if (formElementId === 'images') {
          createPropertyForm.formControls[formElementId].value.forEach((file, i) => {
            fd.append('file', file);
          });
        } else if (createPropertyForm.formControls[formElementId].value) {
          formData[formElementId] = createPropertyForm.formControls[formElementId].value;
        }
      }
    }
    formData.ownerId = user.id;

    fd.append('property', JSON.stringify(formData));

    loadHandler(true);
    createListing(fd).then(() => {
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
            onChange={(changeHandler)}
            ref={createPropertyForm.formControls.propertyType.ref}
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
          <label className="label">
            What type of space your guests will have in your property?*
          </label>
          <Select
            className="select-input"
            onChange={changeHandler}
            ref={createPropertyForm.formControls.guestSpace.ref}
            isSearchable={true}
            name="guestSpace"
            options={guestSpaceOptions}
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
          {!createPropertyForm.formControls.guestSpace.validation.valid
            && createPropertyForm.formControls.guestSpace.touched
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
          && createPropertyForm.formControls.bathNumber.touched
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
          touched={createPropertyForm.formControls.pricePerNight.touched}
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
          touched={createPropertyForm.formControls.title.touched}
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
          touched={createPropertyForm.formControls.description.touched}
          errorMessage={createPropertyForm.formControls.description.validation.errorMessage}
        />

        <div className="form-group">
          <div className="select-images">
            <div className="select-images__button">
              <input 
                style={{ display: 'none' }}
                type="file" 
                name="files"
                id="files"
                onChange={handleSelectedImages} 
                ref={inputEl}
                multiple={true}
              />
              <button className="btn btn--select-images" type="button" onClick={() => inputEl.current.click()}>Select Images</button>
            </div>
            <div className="select-images__text">
              You should select at least an image 
              <br />
              If you select new images the existings will be removed
              <br />
              .jpg .png
            </div>
          </div>
          <SelectedImages images={createPropertyForm.formControls.images.value} />
        </div>

        <div className="form-group">
          <label className="label">
            Country*
          </label>
          <Select
            className="select-input"
            onChange={changeHandler}
            ref={createPropertyForm.formControls.country.ref}
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
            && createPropertyForm.formControls.country.touched
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
              <Input
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
              <Input
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
            && createPropertyForm.formControls.city.touched
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
              && createPropertyForm.formControls.postCode.touched
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
            && createPropertyForm.formControls.streetName.touched
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
             && createPropertyForm.formControls.streetNumber.touched
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
        {console.log(createPropertyForm.isFormValid)}
        <button disabled={!createPropertyForm.isFormValid} className={`btn btn--submit ${createPropertyForm.isFormValid ? null : 'btn--submit-disabled'}`} type="submit">
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
