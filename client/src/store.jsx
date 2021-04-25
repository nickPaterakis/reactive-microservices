import {
  createStore, combineReducers, applyMiddleware,
} from 'redux';
import thunk from 'redux-thunk';
import { 
  propertyListReducer,
  searchPropertyReducer,
} from './store/reducers/propertyReducer';

const reducer = combineReducers({
  propertyList: propertyListReducer,
  searchParameters: searchPropertyReducer,
});

const store = createStore(
  reducer,
  applyMiddleware(thunk),
);

export default store;
