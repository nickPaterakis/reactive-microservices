/* eslint-disable arrow-body-style */
import React from 'react';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import ReactRouterPropTypes from 'react-router-prop-types';
import { deleteListing } from '../../api/PropertyService';
import image from '../../assets/images/Properties/Hungary/budapest/1/72301aa8-696d-4a61-8f97-850da90e0042.jpg';
import { config } from '../../constants/systemConstants';

const MyPropertyCard = ({ property, history }) => {
  const handleView = () => {
    history.push(`/view-property/${property.id}`);
  };

  const handleDelete = async () => {
    await deleteListing(property.id);
    window.location.reload();
  };

  return (
    <div className="my-property-card">
      <div className="my-property-card__image-container">
        <img className="my-property-card__image" src={property.image ? config.url.IMAGES_URL + property.image : image} alt={property.title} />
      </div>
      <div className="my-property-card__description">
        <h1 className="my-property-card__title">{property.title}</h1>
        <h1 className="my-property-card__sub-title">
          {property.propertyType}
          {' in '}
          {property.country}
        </h1>
        <div className="my-property-card__property-characteristics">
          {`${property.maxGuestNumber} guests ${property.bedroomNumber} bedrooms ${property.bathNumber} bath`}
        </div>
        <div className="my-property-card__amenities">
          {property.amenities.map((amenity, index) => (
            <aux key={amenity.id}>
              <span>{amenity}</span>
              {index < property.amenities.length - 1 ? <span>{' · '}</span> : null}
            </aux>
          ))}
        </div>
        <div className="my-property-card__operations">
          <button type="button" exact={true} className="btn btn--view-property margin-right-small" onClick={handleView}>
            <div className="delete-property__text">
              View
            </div>
          </button>
          <button type="button" exact={true} className="btn btn--delete" onClick={handleDelete}>
            <div className="delete__text">
              Delete
            </div>
          </button>
        </div>
        <div className="my-property-card__price">
          {'€ '}
          <span className="property-card__price__number bold">{property.pricePerNight}</span>
          {' / night'}
        </div>
      </div>
    </div>
  );
};

MyPropertyCard.propTypes = {
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
  history: ReactRouterPropTypes.history.isRequired,
};

export default withRouter(MyPropertyCard);
