import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import EdiText from 'react-editext';
import ProfileImage from '../assets/images/i.jpg';
import { setFirstName } from '../store/actions/userActions';

function Account() {
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user);
  const [firstNameMode, SetFirstNameMode] = useState('view-container');
  const [lastNameMode, SetLastNameMode] = useState('view-container');
  const [emailAddressMode, SetEmailAddressMode] = useState('view-container');
  const [phoneMode, SetPhoneMode] = useState('view-container');

  const handleSave = (firstName) => {
    dispatch(setFirstName(firstName));
  };

  const changeFirstNameFieldToEditMode = () => {
    SetFirstNameMode('edit-container');
  };

  const changeFirstNameFieldToViewMode = () => {
    SetFirstNameMode('view-container');
  };

  const changeLastNameFieldToEditMode = () => {
    SetLastNameMode('edit-container');
  };

  const changeLastNameFieldToViewMode = () => {
    SetLastNameMode('view-container');
  };

  const changeEmailAddressFieldToEditMode = () => {
    SetEmailAddressMode('edit-container');
  };

  const changeEmailAddressFieldToViewMode = () => {
    SetEmailAddressMode('view-container');
  };

  const changePhoneFieldToEditMode = () => {
    SetPhoneMode('edit-container');
  };

  const changePhoneFieldToViewMode = () => {
    SetPhoneMode('view-container');
  };

  return (
    <main className="account">
      <div className="account__container">
        {/* <div className="profile-title">
          Account
        </div> */}
        <div className="account__wrapper">
          <div className="account__left-column">
            <div className="account__profile-image">
              <img src={ProfileImage} alt="profile" />
            </div>
            <div className="account__welcome-message">
              Welcome back, 
              {' '}
              <br />
              {user.firstName}
            </div>
          </div>
          <div className="account__right-column">
            <div className="account__right-column__title">
              Preferences
            </div>
            <div className="edit-text">
              <div className="edit-text__title">
                First name
              </div>
              <EdiText 
                type="text" 
                value={user.firstName ? user.firstName : 'None'} 
                onSave={handleSave}
                onCancel={changeFirstNameFieldToViewMode}
                onEditingStart={changeFirstNameFieldToEditMode}
                editButtonContent="edit"
                cancelButtonContent="Cancel"
                saveButtonContent="Save"
                editButtonClassName="edit-button"
                cancelButtonClassName="cancel-button"
                saveButtonClassName="save-button"
                viewContainerClassName={firstNameMode} 
                editOnViewClick={true}
              />
            </div>
            <div className="edit-text">
              <div className="edit-text__title">
                Last name
              </div>
              <EdiText 
                type="text" 
                value={user.lastName ? user.lastName : 'None'} 
                onSave={handleSave}
                onCancel={changeLastNameFieldToViewMode}
                onEditingStart={changeLastNameFieldToEditMode}
                editButtonContent="edit"
                cancelButtonContent="Cancel"
                saveButtonContent="Save"
                editButtonClassName="edit-button"
                cancelButtonClassName="cancel-button"
                saveButtonClassName="save-button"
                viewContainerClassName={lastNameMode}  
                editOnViewClick={true}
              />
            </div>
            <div className="edit-text">
              <div className="edit-text__title">
                First name
              </div>
              <EdiText 
                type="text" 
                value={user.email ? user.email : 'None'} 
                onSave={handleSave}
                onCancel={changeEmailAddressFieldToViewMode}
                onEditingStart={changeEmailAddressFieldToEditMode}
                editButtonContent="edit"
                cancelButtonContent="Cancel"
                saveButtonContent="Save"
                editButtonClassName="edit-button"
                cancelButtonClassName="cancel-button"
                saveButtonClassName="save-button"
                viewContainerClassName={emailAddressMode}
                editOnViewClick={true}
              />
            </div>
            <div className="edit-text">
              <div className="edit-text__title">
                Phone
              </div>
              <EdiText 
                type="text" 
                value={user.phone ? user.phone : 'None'} 
                onSave={handleSave}
                onCancel={changePhoneFieldToViewMode}
                onEditingStart={changePhoneFieldToEditMode}
                editButtonContent="edit"
                cancelButtonContent="Cancel"
                saveButtonContent="Save"
                editButtonClassName="edit-button"
                cancelButtonClassName="cancel-button"
                saveButtonClassName="save-button"
                viewContainerClassName={phoneMode}
                editOnViewClick={true}
              />
            </div>
            
          </div>
        </div>
      </div>
    </main>
  );
}

export default Account;
