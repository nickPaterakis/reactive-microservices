import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import ReactRouterPropTypes from 'react-router-prop-types';
import moment from 'moment';
import { DATE_FORMAT_1 } from '../../constants/systemConstants';
import { deleteReservationById } from '../../api/ReservationService';

const ExtraPropTypes = require('react-extra-prop-types');

function ReservationCard({ reservation, history }) {
  const { keycloak } = useKeycloak();
  const days = moment(reservation.checkOut).diff(reservation.checkIn, 'days');
  const today = moment().toDate();

  const handleView = () => {
    history.push(`/view-property/${reservation.propertyId}`);
  };

  const handleDelete = async () => {
    await deleteReservationById(keycloak.token, reservation.id);
    window.location.reload();
  };

  return (
    <div className="reservation-card">
      <div className="reservation-card__header">
        <h1 className="reservation-card__title">{reservation.propertyReservationDataDto.title}</h1>
        <div className="reservation-card__price">
          <span className="reservation-card__price-number">{`${days * reservation.propertyReservationDataDto.pricePerNight}€`}</span>
        </div>
      </div>
      <div className="reservation-card__informations">
        <div className="reservation-card__left-column">
          <div className="reservation-card__dates">
            <div className="reservation-card__check-in">
              <div className="reservation-card__check-in__text">
                Check-in
              </div>
              <div className="reservation-card__check-in__date bold">
                {moment(moment(reservation.checkIn).toDate()).format(DATE_FORMAT_1)}
              </div>
            </div>

            <div className="reservation-card__check-out">
              <div className="reservation-card__check-out__text">
                Check-out
              </div>
              <div className="reservation-card__check-out__date bold">
                {moment(moment(reservation.checkOut).toDate()).format(DATE_FORMAT_1)}
              </div>
            </div>
          </div>
        </div>
        <div className="reservation-card__right-column">
          <div className="reservation-card__owner-name">
            {'Owner\'s Name: '}
            <span className="bold">
              {`${reservation.userDto.firstName}  ${reservation.userDto.lastName}`}
            </span>
          </div>
          <div className="reservation-card__owner-email">
            {'Owner\'s Email: '}
            <span className="bold">
              {reservation.userDto.email}
            </span>
          </div>
          <div className="reservation-card__location">
            {'Location: '}
            <span className="bold">
              {reservation.propertyReservationDataDto.location}
            </span> 
          </div>
        </div>
      </div>
      <div className="reservation-card__operations">
        <button type="button" exact={true} className="btn btn--view-property" onClick={handleView}>
          <div className="delete-property__text">
            View Property
          </div>
        </button>
        {moment(today).isAfter(reservation.checkOut)
          ? null
          : (
            <button type="button" exact={true} className="btn btn--delete margin-left-small" onClick={handleDelete}>
              <div className="delete__text">
                Cancel
              </div>
            </button>
          )}
      </div>
    
    </div>
  );
}

ReservationCard.propTypes = {
  reservation: PropTypes.shape({
    userDto: PropTypes.shape({
      firstName: PropTypes.string,
      lastName: PropTypes.string,
      email: PropTypes.string,
    }).isRequired,
    propertyReservationDataDto: PropTypes.shape({
      location: PropTypes.string,
      pricePerNight: PropTypes.number,
      title: PropTypes.string,
    }).isRequired,
    id: ExtraPropTypes.uuid.isRequired,
    propertyId: PropTypes.number.isRequired,
    checkIn: PropTypes.string.isRequired,
    checkOut: PropTypes.string.isRequired,
  }).isRequired,
  history: ReactRouterPropTypes.history.isRequired,
};

export default withRouter(ReservationCard);
