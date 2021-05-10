import React from 'react';
import Slider from 'react-slick';
import greeceAthensErm01 from '../../assets/images/Properties/Greece/athens/1/greece-athens-erm-01.jpg';
import greeceAthensErm02 from '../../assets/images/Properties/Greece/athens/1/greece-athens-erm-02.jpg';
import greeceAthensErm03 from '../../assets/images/Properties/Greece/athens/1/greece-athens-erm-03.jpg';
import greeceAthensErm04 from '../../assets/images/Properties/Greece/athens/1/greece-athens-erm-04.jpg';

function PropertySlider() {
  const baseUrl = '../../assets/images/Properties/Greece/athens/1';

  // const settings = {
  //   customPaging(i) {
  //     return (
  //       <a>
  //         <img src={`${baseUrl}/greece-athens-erm-0${i + 1}.jpg`} alt="" />
  //       </a>
  //     );
  //   },
  //   dots: true,
  //   dotsClass: 'slick-dots slick-thumb',
  //   infinite: true,
  //   speed: 500,
  //   slidesToShow: 1,
  //   slidesToScroll: 1,
  // };
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
      <div>
        <img src={greeceAthensErm01} alt="" />
      </div>
      <div>
        <img src={greeceAthensErm02} alt="" />
      </div>
      <div>
        <img src={greeceAthensErm03} alt="" />
      </div>
      <div>
        <img src={greeceAthensErm04} alt="" />
      </div>
      {/* <div>
        <img src={`${baseUrl}/greece-athens-erm-03.jpg`} alt="" />
      </div>
      <div>
        <img src={`${baseUrl}/greece-athens-erm-04.jpg`} alt="" />
      </div> */}
    </Slider>
  );
}

export default PropertySlider;
