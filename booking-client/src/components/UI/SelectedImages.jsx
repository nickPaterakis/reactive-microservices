import React from 'react';
import PropTypes from 'prop-types';

const SelectedImages = ({ images }) => (
  <>
    {
      images
        ? (
          <div className="select-images__images">
            {images.map((image) => (
              <div className="select-images__image">
                <img src={URL.createObjectURL(image)} alt="profile" />
                <div className="select-images__image__text">{image.name}</div>
              </div>
            ))}
        
          </div>
        ) : null
    }
  </>
);

SelectedImages.propTypes = {
  images: PropTypes.arrayOf(PropTypes.object).isRequired,
};

export default SelectedImages;
