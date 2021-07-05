import React, { useEffect, useState } from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { withRouter } from 'react-router-dom';
import ReactRouterPropTypes from 'react-router-prop-types';
import ReactPaginate from 'react-paginate';
import { MdKeyboardArrowLeft, MdKeyboardArrowRight } from 'react-icons/md';
import MyPropertyCard from '../components/UI/MyPropertyCard';
import { PER_PAGE } from '../constants/systemConstants';
import { getUserProperties } from '../api/PropertyService';
import Spinner from '../components/UI/Spinner';
import houseIcon from '../assets/icons/house.svg';

const MyProperties = ({ history }) => {
  const { keycloak } = useKeycloak();
  const [propertiesPage, setPropertiesPage] = useState();
  const [currentPage, setCurrentPage] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      const response = await getUserProperties(currentPage, keycloak.token);
      setPropertiesPage(response.data);
    };
    fetchData();
  }, [currentPage]);

  const handlePageClick = ({ selected: selectedPage }) => {
    window.scrollTo(0, 0);
    setCurrentPage(selectedPage);
  };

  const handleCreatePropertyButton = () => {
    history.push('/create-property');
  };
  
  if (!propertiesPage) {
    return <Spinner />;
  }

  const { properties } = propertiesPage;
  const { totalElements } = propertiesPage;
  const pageCount = Math.ceil(totalElements / PER_PAGE);

  return (
    <div className="my-properties">
      {Array.isArray(properties) && properties.length
        ? (
          <div className="my-properties__container">
            <div className="my-properties__wrapper">
              <div className="my-properties__header">
                <div className="my-properties__title">
                  {totalElements === 1 ? `You Have ${totalElements} Property` : `You Have ${totalElements} Properties`}
                </div>
                <button type="button" className="btn btn--create-property" onClick={handleCreatePropertyButton}>
                  Create A New Listing
                </button>
              </div>
              <section className="result-section">
                <div className="properties_list">
                  {properties.map((element) => {
                    console.log(element); 
                    return <MyPropertyCard property={element} key={element.id} />;
                  })}
                </div>
  
                <div className="pagination__container u-margin-top-medium">
                  <ReactPaginate
                    previousLabel={<MdKeyboardArrowLeft />}
                    nextLabel={<MdKeyboardArrowRight />}
                    breakLabel="..."
                    breakClassName="break-me"
                    pageCount={pageCount}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={PER_PAGE}
                    onPageChange={handlePageClick}
                    containerClassName="pagination"
                    previousLinkClassName="pagination__link"
                    nextLinkClassName="pagination__link"
                    disabledClassName="pagination__link--disabled"
                    activeClassName="pagination__link--active"
                  />
  
                </div>
              </section>
            </div>
          </div>
        )
        : (
          <div className="my-properties__empty-container">
            <img className="my-properties__reservation-icon" src={houseIcon} alt="Reservation Icon" />
            <div className="my-properties__empty-title">Your listings live here</div>
            <p className="my-properties__empty-text">
              This page shows all of your listings.
            </p>
            <button type="button" className="btn btn--create-property" onClick={handleCreatePropertyButton}>
              Create A New Listing
            </button>
          </div>
        )}
     
    </div>
  );
};

MyProperties.propTypes = {
  history: ReactRouterPropTypes.history.isRequired,
};

export default withRouter(MyProperties);
