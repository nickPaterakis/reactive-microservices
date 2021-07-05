import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import ReactPaginate from 'react-paginate';
import { MdKeyboardArrowLeft, MdKeyboardArrowRight } from 'react-icons/md';
import moment from 'moment';
import { DATE_FORMAT, PER_PAGE } from '../constants/systemConstants';
import PropertyCard from '../components/UI/PropertyCard';
import Spinner from '../components/UI/Spinner';
import { searchProperties } from '../api/PropertyService';

const Properties = () => {
  const [propertiesPage, setPropertiesPage] = useState();
  const location = useSelector((state) => state.searchParameters.location);
  const dates = useSelector((state) => state.searchParameters.dates);
  const guests = useSelector((state) => state.searchParameters.guests);
  const [currentPage, setCurrentPage] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      const { data } = await searchProperties(
        location,
        moment(moment(dates.startDate).toDate()).format(DATE_FORMAT),
        moment(moment(dates.endDate).toDate()).format(DATE_FORMAT),
        guests,
        currentPage,
      );
      
      setPropertiesPage({
        data,
      });
    };
  
    fetchData();
  }, [currentPage]);

  const handlePageClick = ({ selected: selectedPage }) => {
    window.scrollTo(0, 0);
    setCurrentPage(selectedPage);
  };

  if (!propertiesPage) {
    return <Spinner />;
  }

  const { properties } = propertiesPage.data;
  const { totalElements } = propertiesPage.data;
  const pageCount = Math.ceil(totalElements / PER_PAGE);

  return (
    <div className="properties">
      <section className="result-section">
        <div className="properties-header">
          <h1 className="properties-header__title">{`${location}: ${totalElements} properties found `}</h1>
        </div>
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

export default Properties;
