import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import ReactPaginate from 'react-paginate';
import { MdKeyboardArrowLeft, MdKeyboardArrowRight } from 'react-icons/md';
import moment from 'moment';
import { DATE_FORMAT, PER_PAGE } from '../constants/systemConstants';
import PropertyCard from '../components/UI/PropertyCard';
import Spinner from '../components/UI/Spinner';
import { listProperties } from '../store/actions/propertyActions';

const Properties = () => {
  const propertyList = useSelector((state) => state.propertyList);
  const { propertiesPage, status, error } = propertyList;
  const location = useSelector((state) => state.searchParameters.location);
  const dates = useSelector((state) => state.searchParameters.dates);
  const guests = useSelector((state) => state.searchParameters.guests);
  const [currentPage, setCurrentPage] = useState(0);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(listProperties(
      location,
      moment(dates.startDate.toDate()).format(DATE_FORMAT),
      moment(dates.endDate.toDate()).format(DATE_FORMAT),
      guests,
      currentPage,
    ));
  }, [currentPage]);

  const handlePageClick = ({ selected: selectedPage }) => {
    setCurrentPage(selectedPage);
  };
  
  if (status === 'loading') {
    return (
      <Spinner />
    );
  } 

  if (status === 'failed') {
    return (
      <div>
        {error}
      </div>
    );
  }

  if (status === 'succeeded') {
    return (
      <div>
        {propertiesPage}
      </div>
    );
  }

  const properties = propertiesPage.content;
  const { totalElements } = propertiesPage;
  const pageCount = Math.ceil(totalElements / PER_PAGE);

  console.log(currentPage);

  return (
    <div className="properties">
      <section className="result-section">
        <div className="properties-header  u-margin-bottom-medium">
          <h1 className="properties-header__title">{`${location}: ${propertiesPage.totalElements} properties found ` }</h1>
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
