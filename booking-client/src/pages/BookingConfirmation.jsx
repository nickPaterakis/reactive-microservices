import React from 'react';
import ReactRouterPropTypes from 'react-router-prop-types';
import { withRouter } from 'react-router-dom';
import like from '../assets/icons/like.svg';

const BookingConfigramtion = ({ history }) => (
  <div className="booking-confirmation">
    <div className="like-section">
      <div className="like-cycle">
        <img className="like-icon" src={like} alt="like" />
      </div>
    </div>
    <div className="confirmation-section">
      <h1 className="confirmation-section__title">
        Thank you!
      </h1>
      <div className="confirmation-section__text">
        Your reservation has been sent successfully
      </div>
      <div className="confirmation-section__button">
        <button type="button" className="btn btn--home-page" aria-label="home-page" onClick={() => history.push('/')}>Take me to Home Page</button>
      </div>
    </div>

  </div>
);

BookingConfigramtion.propTypes = {
  history: ReactRouterPropTypes.history.isRequired,
};

export default withRouter(BookingConfigramtion);
