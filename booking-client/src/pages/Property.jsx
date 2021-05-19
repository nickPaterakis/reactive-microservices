import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import PropertySlider from '../components/PropertyComponents/PropertySlider';
import { getProperty } from '../api/PropertyService';
import Spinner from '../components/UI/Spinner';
import ProfileImage from '../assets/images/i.jpg';
import SummaryCard from '../components/UI/SummaryCard';

function Property() {
  const { id } = useParams();
  const [property, setProperty] = useState();

  useEffect(() => {
    const fetchData = async () => {
      const response = await getProperty(id);
      console.log(response.data);
      setProperty(response.data);
    };
    fetchData();
  }, []);

  if (!property) {
    return <Spinner />;
  }

  return (
    <main className="property">
      <header className="property-header">
        <h1 className="property-header__title">
          {property.title}
        </h1>
        <div className="property-header__location">
          {`${property.address.city}, ${property.address.country}`}
        </div>
      </header>
     
      <section className="property-slider">
        <PropertySlider />
      </section>

      <section className="property-information">
        <div className="property-information__left-column">
          <div className="property-overview padding-bottom-small">
            <div className="property-overview__left-column">
              <div className="property-overview__host-name">
                {`${property.propertyType} hosted by ${property.ownerFirstName} ${property.ownerLastName}`}
              </div>
              <div className="property-overview__characteristics">
                {`${property.maxGuestNumber} guests · ${property.bedroomNumber} bedrooms · ${property.bathNumber} bath`}
              </div>
            </div>
            <div className="property-overview__right-column">
              <div className="property-overview__host-image">
                <img src={ProfileImage} alt="profile" />
              </div>
            </div>
          </div>
          <div className="property-description margin-top-small padding-bottom-small">
            <h1 className="property-description__title margin-bottom-extra-small">Overview</h1>
            <p className="property-description__text">
              {property.description}
            </p>
          </div>
          <div className="property-location margin-top-small padding-bottom-small">
            <h1 className="property-location__title margin-bottom-extra-small">Location</h1>
            <div className="property-location__text">
              {`${property.address.streetNumber}. ${property.address.streetName}, ${property.address.city}, ${property.address.country}`}
            </div>
          </div>
          <div className="property-amenities margin-top-small padding-bottom-small">
            <h1 className="property-amenities__title margin-bottom-extra-small">Amenities</h1>
            <ul className="property-amenities__amenities">
              {property.amenities.map((amenity, index) => (
                <li className="property-amenities__amenity" key={amenity}>
                  {amenity}
                </li>
              ))}
            </ul>
          </div>
        </div>
   
        <div className="property-information__right-column">
          <SummaryCard 
            pricePerNight={property.pricePerNight}
          />
        </div>
      </section>
       
    </main>
  );
}

export default Property;
