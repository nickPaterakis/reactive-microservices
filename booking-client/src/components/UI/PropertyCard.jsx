/* eslint-disable global-require */
/* eslint-disable no-undef */
/* eslint-disable arrow-body-style */
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { useSelector } from 'react-redux';
import moment from 'moment';
import image from '../../assets/images/Properties/Hungary/budapest/1/72301aa8-696d-4a61-8f97-850da90e0042.jpg';
import aux from '../../hoc/Auxiliary';

const PropertyCard = ({ property }) => {
  const dates = useSelector((state) => state.searchParameters.dates);
  const days = moment(dates.endDate).diff(dates.startDate, 'days');
  // const [dynamicImage, setImage] = useState();
  // const images = require(`../../assets/images/Properties/${property.image}`);
  // const dynamicImage = images(`./${property.image}`);
  // eslint-disable-next-line import/no-dynamic-require
  // const image = require(`../../assets/images/Properties/${property.image}`);

  // import(`../../assets/images/Properties/${property.image}`).
  // then((imageg) => console.log(imageg));
  // const loadImage = (imageName) => {
  //   import('../../assets/images/Properties/Hungary/
  // budapest/1/5e7de441-9971-4cd7-a4ac-9aedd594debd.jpg').then(({ image }) => {
  //     console.log(image);
  //     setImage({
  //       image,
  //     });
  //   });
  // };

  // loadImage(property.image);

  return (
    <Link to={`/property/${property.id}`} className="link card_link">
      <div className="property-card">
        <div className="property-card__image-container">
          <img className="property-card__image" src={image} alt={property.title} />
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
  property: PropTypes.objectOf,
};

PropertyCard.defaultProps = {
  property: {},
};

export default PropertyCard;
