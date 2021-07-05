import React from 'react';
import PropTypes from 'prop-types';
import moment from 'moment';
import { DATE_FORMAT_1 } from '../../constants/systemConstants';

function SummaryCard({
  pricePerNight, handleReservation, checkIn, checkOut, guests,
}) {
  const days = moment(checkOut).diff(checkIn, 'days');

  return (
    <div className="summary-card">
      <div className="summary-card__price">
        <span className="summary-card__price__number">{`€${pricePerNight}`}</span>
        {' / night'}
      </div>

      <div className="summary-card__dates">
        <div className="summary-card__check-in">
          <div className="summary-card__check-in__text">
            Check-in
          </div>
          <div className="summary-card__check-in__date">
            {moment(moment(checkIn).toDate()).format(DATE_FORMAT_1)}
          </div>
        </div>

        <div className="summary-card__check-out">
          <div className="summary-card__check-out__text">
            Check-out
          </div>
          <div className="summary-card__check-out__date">
            {moment(moment(checkOut).toDate()).format(DATE_FORMAT_1)}
          </div>
        </div>
      </div>

      <div className="summary-card__guests">
        <span className="summary-card__guests__title">Guests:</span>
        <span className="summary-card__guests__number">{` ${guests} ${guests === 1 ? 'guest' : 'guests'}`}</span>
      </div>

      <button type="button" className="btn btn--reserve" aria-label="reserve" onClick={handleReservation}>Reserve</button>

      <div className="summary-card__warn">You won&apos;t be charged yet</div>

      <div className="summary-card__price-per-night">
        <span className="summary-card__price-per-night__text">{`€${pricePerNight} x ${days} ${days === 1 ? 'night' : 'nights'}`}</span>
        <span className="summary-card__price-per-night__total-price">{`€${days * pricePerNight}`}</span>
      </div>

      <div className="summary-card__service-price">
        <span className="summary-card__service-price__text">Service fee</span>
        <span className="summary-card__service-price__price">€10</span>
      </div>

      <div className="summary-card__total-price">
        <span className="summary-card__total-price__text">Total</span>
        <span className="summary-card__total-price__number">{`€${days * pricePerNight + 10}`}</span>
      </div>

    </div>
  );
}

SummaryCard.propTypes = {
  handleReservation: PropTypes.func.isRequired,
  pricePerNight: PropTypes.number.isRequired,
  checkIn: PropTypes.string.isRequired,
  checkOut: PropTypes.string.isRequired,
  guests: PropTypes.number.isRequired,
};

export default SummaryCard;
