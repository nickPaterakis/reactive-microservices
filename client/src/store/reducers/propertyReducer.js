import {
  PROPERTY_LIST_REQUEST,
  PROPERTY_LIST_SUCCESS,
  PROPERTY_LIST_FAIL,
  SEARCH_SET_LOCATION,
  SEARCH_SET_DATES,
  SEARCH_SET_GUESTS,
} from '../../constants/propertyConstants';

// const initialState = {
//   status: 'loading',
//   properties: [],
//   error: null,
// };

const searchParameters = {
  location: '', 
  dates: {
    startDate: null,
    endDate: null,
  }, 
  guests: 0,
};

const propertyListReducer = (state = { propertiesPage: {} }, action) => {
  switch (action.type) {
    case PROPERTY_LIST_REQUEST:
      return { status: 'loading', propertiesPage: {} };
    case PROPERTY_LIST_SUCCESS:
      return { status: 'succeeded', propertiesPage: action.payload };
    case PROPERTY_LIST_FAIL: 
      return { status: 'failed', error: action.payload };
    default: 
      return state;
  }
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
  propertyListReducer, 
  searchPropertyReducer, 
};
