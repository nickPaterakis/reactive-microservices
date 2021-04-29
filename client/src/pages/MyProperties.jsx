import React, { useEffect, useState } from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { useDispatch, useSelector } from 'react-redux';
import ReactPaginate from 'react-paginate';
import { MdKeyboardArrowLeft, MdKeyboardArrowRight } from 'react-icons/md';
import PropertyCard from '../components/UI/PropertyCard';
import { PER_PAGE } from '../constants/systemConstants';
import { getUserProperties } from '../api/UserService';

const MyProperties = () => {
  // const [currentPage, setCurrentPage] = useState(0);
  const { keycloak, initialized } = useKeycloak();
  const [propertiesPage, setPropertiesPage] = useState();
  const [currentPage, setCurrentPage] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      const response = await getUserProperties(keycloak.token);
      console.log(response);
      setPropertiesPage(response.data);
    };
    fetchData();
  }, []);

  const handlePageClick = ({ selected: selectedPage }) => {
    setCurrentPage(selectedPage);
  };

  // const handlePageClick = ({ selected: selectedPage }) => {
  //   setCurrentPage(selectedPage);
  // };
  
  if (!propertiesPage) {
    return (<div />);
  }

  const { properties } = propertiesPage;
  const { totalElements } = propertiesPage;
  const pageCount = Math.ceil(totalElements / PER_PAGE);

  return (
    <div className="properties">
      <section className="result-section">
        <div className="properties_list">
          {properties.map((element) => <PropertyCard property={element} key={element.id} />)}
        </div>

        <div className="pagination__container u-margin-top-medium">
          <ReactPaginate
            previousLabel={<MdKeyboardArrowLeft />}
            nextLabel={<MdKeyboardArrowRight />}
            breakLabel="..."
            breakClassName="break-me"
            pageCount={pageCount}
            marginPagesDisplayed={2}
            pageRangeDisplayed={5}
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
  );
};

export default MyProperties;
