import React from 'react';
import Slider from 'react-slick';
import PropTypes from 'prop-types';
import { config } from '../../constants/systemConstants';

function PropertySlider({ images }) {
  const settings = {
    dots: true,
    fade: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  };
  
  return (
    <Slider {...settings}>
      {images ? images.map((image) => (
        <div>
          <img className="property-slider__image" src={config.url.PROPERTY_IMAGES_URL + image} alt="" />
        </div>
      )) : null}
    </Slider>
  );
}

PropertySlider.propTypes = {
  images: PropTypes.arrayOf.isRequired,
};

export default PropertySlider;
