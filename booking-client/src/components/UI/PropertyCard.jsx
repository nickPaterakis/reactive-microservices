/* eslint-disable global-require */
/* eslint-disable no-undef */
/* eslint-disable arrow-body-style */
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { useSelector } from 'react-redux';
import moment from 'moment';
import { config } from '../../constants/systemConstants';

const PropertyCard = ({ property }) => {
  const dates = useSelector((state) => state.searchParameters.dates);
  const days = moment(dates.endDate).diff(dates.startDate, 'days');

  return (
    <Link to={`/property/${property.id}`} className="link card_link">
      <div className="property-card">
        <div className="property-card__image-container">
          <img className="property-card__image" src={property.image ? config.url.PROPERTY_IMAGES_URL + property.image : image} alt={property.title} />
        </div>
        <div className="property-card__description">
          <h1 className="property-card__title">{property.title}</h1>
          <h1 className="property-card__sub-title">
            {property.propertyType}
            {' in '}
            {property.country}
          </h1>
          <div className="property-card__property-characteristics">
            {`${property.maxGuestNumber} guests ${property.bedroomNumber} bedrooms ${property.bathNumber} bath`}
          </div>
          <div className="property-card__amenities">
            {property.amenities.map((amenity, index) => (
              <aux key={amenity.id}>
                <span>{amenity}</span>
                {index < property.amenities.length - 1 ? <span>{' · '}</span> : null}
              </aux>
            ))}
          </div>
          <div className="property-card__price">
            {'€ '}
            <span className="property-card__price__number bold">{property.pricePerNight}</span>
            {' / night'}
          </div>
          <div className="property-card__total-price">
            {'€ '}
            <span className="property-card__total-price__number bold">{days * property.pricePerNight}</span>
            {' total'}
          </div>
        </div>
      </div>
    </Link>
  );
};

PropertyCard.propTypes = {
  property: PropTypes.shape({
    id: PropTypes.number.isRequired,
    amenities: PropTypes.arrayOf,
    bathNumber: PropTypes.number.isRequired,
    bedroomNumber: PropTypes.number.isRequired,
    maxGuestNumber: PropTypes.number.isRequired,
    pricePerNight: PropTypes.number.isRequired,
    propertyType: PropTypes.string.isRequired,
    country: PropTypes.string.isRequired,
    guestSpace: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
  }).isRequired,
};

export default PropertyCard;
