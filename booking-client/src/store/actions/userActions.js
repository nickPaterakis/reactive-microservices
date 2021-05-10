import { 
  SET_EMAIL, 
  SET_FIRSTNAME, 
  SET_LASTNAME, 
  SET_PHONE, 
  SET_USER,
} from '../../constants/userConstants';

export const setUser = (data) => (dispatch) => {
  dispatch({ type: SET_USER, payload: data });
};

export const setFirstName = (data) => (dispatch) => {
  dispatch({ type: SET_FIRSTNAME, payload: data });
};

export const setLastName = (data) => (dispatch) => {
  dispatch({ type: SET_LASTNAME, payload: data });
};

export const setEmail = (data) => (dispatch) => {
  dispatch({ type: SET_EMAIL, payload: data });
};

export const setPhone = (data) => (dispatch) => {
  dispatch({ type: SET_PHONE, payload: data });
};
