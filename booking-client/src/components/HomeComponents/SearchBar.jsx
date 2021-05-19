import { React, useState } from 'react';
import PropTypes from 'prop-types';
import { useDispatch, useSelector } from 'react-redux';
import { DateRangePicker } from 'react-dates';
import { BsSearch } from 'react-icons/bs';
import moment from 'moment';
import { withRouter } from 'react-router-dom';
import GuestsDropdown from '../UI/GuestsDropdown';
import Autocomplete from '../UI/Autocomplete';
import { 
  setDates,
  setGuests,
  setLocation,
} from '../../store/actions/propertyActions';
import {
  DATE_FORMAT, MAX_ADULT_NUMBER, MAX_CHILD_NUMBER, MAX_ROOM_NUMBER, 
} from '../../constants/systemConstants';

const SearchBar = ({ history }) => {
  const dispatch = useDispatch();
  const [focused, setFocusedInput] = useState();
  const [open, setOpen] = useState(false);
  const [children, setChild] = useState(0);
  const [rooms, setRooms] = useState(0);
  const location = useSelector((state) => state.searchParameters.location);
  const dates = useSelector((state) => state.searchParameters.dates);
  const guests = useSelector((state) => state.searchParameters.guests);

  // dates.startDate = '';
  // dates.endDate = '';
  console.log(dates);

  const handleOpen = () => setOpen(!open);

  const handleSearch = () => {
    if (location && dates.startDate && dates.endDate && guests > 0) {
      // dispatch(listProperties(
      //   location,
      //   moment(moment(dates.startDate).toDate()).format(DATE_FORMAT),
      //   moment(moment(dates.endDate).toDate()).format(DATE_FORMAT),
      //   guests,
      // ));
      history.push('/properties');
    }
  };

  const handleLoaction = (loc) => dispatch(setLocation(loc));

  const removeAdult = () => dispatch(setGuests(guests >= 1 ? guests - 1 : guests));
  const addAdult = () => dispatch(setGuests(guests <= MAX_ADULT_NUMBER ? guests + 1 : guests));
  const removeChild = () => setChild(children >= 1 ? children - 1 : children);
  const addChild = () => setChild(children <= MAX_CHILD_NUMBER ? children + 1 : children);
  const removeRoom = () => setRooms(rooms >= 1 ? rooms - 1 : rooms);
  const addRoom = () => setRooms(rooms <= MAX_ROOM_NUMBER ? rooms + 1 : rooms);

  return (
    <div className="search-bar">

      <div className="search-bar__input">
        <p className="search-bar__label"> Location </p>
        <Autocomplete 
          handleLocation={handleLoaction}
        />
      </div>

      <div className="search-bar__date-picker">
        <p className="search-bar__label check-in">Check in</p>
        <p className="search-bar__label check-out">Check out</p>
        <DateRangePicker
          startDate={moment(dates.startDate)}
          startDateId="start-date"
          endDate={moment(dates.endDate)}
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
          adults={guests}
          removeAdult={removeAdult}
          addAdult={addAdult}
          childrenNumber={children}
          removeChild={removeChild}
          addChild={addChild}
          rooms={rooms}
          removeRoom={removeRoom}
          addRoom={addRoom}
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
  history: PropTypes.func.isRequired,
};

export default withRouter(SearchBar);
