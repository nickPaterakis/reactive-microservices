/* eslint-disable arrow-body-style */
import React from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { useSelector } from 'react-redux';
import image from '../../assets/images/Properties/Hungary/budapest/1/72301aa8-696d-4a61-8f97-850da90e0042.jpg';
import aux from '../../hoc/Auxiliary';

const PropertyCard = ({ property }) => {
  // const dates = useSelector((state) => state.searchParameters.dates);
  // const days = dates.endDate.diff(dates.startDate, 'days');
  
  // const images = require.context('../../assets/images/Properties/', true);
  // const dynamicImage = images(`./${property.image}`);

  // const requestImageFile = require.context('../../images', true);
  // const dynamicImage = requestImageFile(`./${property.image}`);
  // <img className="card__image" src={requestImageFile(`./${image}`)} alt={property.title} />

  // <Link to={`/property/${property.id}`} className="card card--property">
  //     <img classNpame="card__image" src={image} alt={property.title} />
  //   </Link>
  // console.log(dynamicImage);
  // const image = '3.jpg';
  return (
    <Link to={`/property/${property.id}`} className="link card_link">
      <div className="card card--property">
        <div className="card__image-container">
          <img className="card__image" src={image} alt={property.title} />
        </div>
        <div className="card__description">
          <h1 className="card__title">{property.title}</h1>
          <h1 className="card__sub-title">
            {property.propertyType}
            {' in '}
            {property.country}
          </h1>
          <div className="card__property-characteristics">
            {`${property.maxGuestNumber} guests ${property.bedroomNumber} bedrooms ${property.bathNumber} bath`}
          </div>
          <div className="card__amenities">
            {property.amenities.map((amenity, index) => (
              <aux key={amenity.id}>
                <span>{amenity}</span>
                {index < property.amenities.length - 1 ? <span>{' · '}</span> : null}
              </aux>
            ))}
          </div>
          <div className="card__price">
            {'€ '}
            <span className="card__price__number bold">{property.pricePerNight}</span>
            {' / night'}
          </div>
          {/* <div className="card__total-price">
          {'€ '}
          <span className="card__total-price__number bold">{days * property.pricePerNight}</span>
          {' total'}
        </div> */}
        </div>
      </div>
    </Link>
  );
};

PropertyCard.propTypes = {
  property: PropTypes.objectOf,
};

PropertyCard.defaultProps = {
  property: {},
};

export default PropertyCard;
