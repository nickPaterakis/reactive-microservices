import { React, useState, useEffect } from 'react';
import ReactRouterPropTypes from 'react-router-prop-types';
import { useDispatch, useSelector } from 'react-redux';
import { DateRangePicker } from 'react-dates';
import { BsSearch } from 'react-icons/bs';
import moment from 'moment';
import { withRouter } from 'react-router-dom';
import { getAllCountries } from '../../api/CountriesService';
import GuestsDropdown from '../UI/GuestsDropdown';
import Autocomplete from '../UI/Autocomplete';
import { 
  setDates,
  setGuests,
  setLocation,
} from '../../store/actions/propertyActions';
import { MAX_GUEST_NUMBER } from '../../constants/systemConstants';

const SearchBar = ({ history }) => {
  const dispatch = useDispatch();
  const [focused, setFocusedInput] = useState();
  const [open, setOpen] = useState(false);
  const location = useSelector((state) => state.searchParameters.location);
  const dates = useSelector((state) => state.searchParameters.dates);
  const guests = useSelector((state) => state.searchParameters.guests);
  const [countries, setCountries] = useState([]);
  const handleOpen = () => setOpen(!open);

  const handleSearch = () => {
    if (location && dates.startDate && dates.endDate && guests > 0) {
      history.push('/properties');
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      const result = await getAllCountries();
      setCountries(
        result.data.map((country) => country.name),
      );
    };
  
    fetchData();
  }, []);

  const handleLocation = (loc) => dispatch(setLocation(loc));
  
  const removeGuest = () => dispatch(setGuests(guests >= 1 ? guests - 1 : guests));
  const addGuest = () => dispatch(setGuests(guests <= MAX_GUEST_NUMBER ? guests + 1 : guests));

  console.log(countries);
  return (
    <div className="search-bar">

      <div className="search-bar__input">
        <p className="search-bar__label"> Location </p>
        <Autocomplete 
          handleLocation={handleLocation}
          countries={countries}
        />
      </div>

      <div className="search-bar__date-picker">
        <p className="search-bar__label check-in">Check in</p>
        <p className="search-bar__label check-out">Check out</p>
        <DateRangePicker
          startDate={dates.startDate ? moment(dates.startDate) : dates.startDate}
          startDateId="start-date"
          endDate={dates.endDate ? moment(dates.endDate) : dates.endDate}
          endDateId="end-date"
          small={true}
          onDatesChange={({ startDate, endDate }) => dispatch(setDates({ startDate, endDate }))}
          focusedInput={focused}
          keepOpenOnDateSelect={true}
          onFocusChange={(focusedInput) => setFocusedInput(focusedInput)}
        />
      </div>

      <div className="search-bar__dropdown-menu">
        <div role="button" tabIndex={0} className="btn btn--guests-dropdown btn--white prevent-selection" onClick={() => handleOpen()} onKeyPress={handleOpen}>
          <div className="search-bar__label">Guests</div>
          <div className="search-bar__placeholder">Add guests</div>
        </div>
        <GuestsDropdown
          open={open}
          handleOpen={() => setOpen(false)}
          guests={guests}
          removeGuest={removeGuest}
          addGuest={addGuest}
        />
      </div>

      <div className="search-bar__button">
        <button type="button" className="btn btn--search" onClick={handleSearch}>
          <BsSearch size={20} />
        </button>
      </div>
    </div>
  );
};

SearchBar.propTypes = {
  history: ReactRouterPropTypes.history.isRequired,
};

export default withRouter(SearchBar);
