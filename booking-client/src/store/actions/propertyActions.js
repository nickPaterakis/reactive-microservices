import axios from 'axios';
import {
  SEARCH_SET_LOCATION,
  SEARCH_SET_DATES,
  SEARCH_SET_GUESTS,
} from '../../constants/propertyConstants';

export const setLocation = (data) => (dispatch) => {
  dispatch({ type: SEARCH_SET_LOCATION, payload: data });
};

export const setDates = (data) => (dispatch) => {
  dispatch({ type: SEARCH_SET_DATES, payload: data });
};

export const setGuests = (data) => (dispatch) => {
  dispatch({ type: SEARCH_SET_GUESTS, payload: data });
};
