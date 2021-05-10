import { 
  SET_EMAIL,
  SET_FIRSTNAME, 
  SET_LASTNAME, 
  SET_PHONE,
  SET_USER,
} from '../../constants/userConstants';

const userDetails = {
  firstName: '', 
  lastName: '',
  email: '', 
  phone: '', 
};

const userReducer = (state = userDetails, action) => {
  switch (action.type) {
    case SET_USER: 
      return action.payload;
    case SET_FIRSTNAME:
      return { ...state, firstName: action.payload };
    case SET_LASTNAME:
      return { ...state, lastName: action.payload };
    case SET_EMAIL:
      return { ...state, email: action.payload };
    case SET_PHONE:
      return { ...state, phone: action.payload };
    default:
      return state;
  }
};

export { 
  userReducer, 
};
