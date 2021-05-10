/* eslint-disable arrow-body-style */
import React from 'react';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import image from '../../assets/images/Properties/Hungary/budapest/1/72301aa8-696d-4a61-8f97-850da90e0042.jpg';

const MyPropertyCard = ({ property, history }) => {
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

  const handleEdit = () => {
    history.push(`/profile/myproperties/${property.id}`);
  };

  const handleView = () => {
    history.push(`/property/${property.id}`);
  };

  const handleDelete = () => {
    history.push(`/property/${property.id}`);
  };

  return (
    <div className="my-property-card">
      <div className="my-property-card__image-container">
        <img className="my-property-card__image" src={image} alt={property.title} />
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
          <button type="button" exact={true} className="btn btn--edit-property" onClick={handleEdit}>
            <div className="edit-property__text">
              Edit
            </div>
          </button>
          <button type="button" exact={true} className="btn btn--view-property" onClick={handleView}>
            <div className="delete-property__text">
              View
            </div>
          </button>
          <button type="button" exact={true} className="btn btn--delete-property" onClick={handleDelete}>
            <div className="delete-property__text">
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
  property: PropTypes.objectOf,
  history: PropTypes.func.isRequired,
};

MyPropertyCard.defaultProps = {
  property: {},
};

export default withRouter(MyPropertyCard);
