import axios from 'axios';
import {
  PROPERTY_LIST_REQUEST,
  PROPERTY_LIST_SUCCESS,
  PROPERTY_LIST_FAIL,
  SEARCH_SET_LOCATION,
  SEARCH_SET_DATES,
  SEARCH_SET_GUESTS,
} from '../../constants/propertyConstants';

export const listProperties = (
  location = '',
  checkIn = '',
  checkOut = '',
  guestNumber = '',
  currentPage = 1,
) => async (dispatch) => {
  try {
    dispatch({ type: PROPERTY_LIST_REQUEST });

    const { data } = await axios.get(
      `http://localhost:8765/booking/api/v1/properties/search?location=${ 
        location 
      }&checkIn=${ 
        checkIn 
      }&checkOut=${ 
        checkOut  
      }&guestNumber=${  
        guestNumber
      }&currentPage=${  
        1}`,
    );

    console.log(data);
    dispatch({ type: PROPERTY_LIST_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: PROPERTY_LIST_FAIL, payload: error.message });
  }
};

export const setLocation = (data) => (dispatch) => {
  dispatch({ type: SEARCH_SET_LOCATION, payload: data });
};

export const setDates = (data) => (dispatch) => {
  dispatch({ type: SEARCH_SET_DATES, payload: data });
};

export const setGuests = (data) => (dispatch) => {
  dispatch({ type: SEARCH_SET_GUESTS, payload: data });
};
