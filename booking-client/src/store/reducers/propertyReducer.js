import {
  PROPERTY_LIST_REQUEST,
  PROPERTY_LIST_SUCCESS,
  PROPERTY_LIST_FAIL,
  SEARCH_SET_LOCATION,
  SEARCH_SET_DATES,
  SEARCH_SET_GUESTS,
} from '../../constants/propertyConstants';

const searchParameters = {
  location: '', 
  dates: {
    startDate: null,
    endDate: null,
  }, 
  guests: 0,
};

const searchPropertyReducer = (state = searchParameters, action) => {
  switch (action.type) {
    case SEARCH_SET_LOCATION:
      return { ...state, location: action.payload };
    case SEARCH_SET_DATES:
      return { ...state, dates: action.payload };
    case SEARCH_SET_GUESTS:
      return { ...state, guests: action.payload };
    default:
      return state;
  }
};

export { 
  searchPropertyReducer, 
};
