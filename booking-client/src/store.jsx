import {
  createStore, combineReducers, applyMiddleware,
} from 'redux';
import thunk from 'redux-thunk';
import throttle from 'lodash/throttle';
import { loadState, saveState } from './utils/localStorage';
import { 
  searchPropertyReducer,
} from './store/reducers/propertyReducer';
import { userReducer } from './store/reducers/userReducer';

const reducer = combineReducers({
  user: userReducer,
  searchParameters: searchPropertyReducer,
});

const persistedState = loadState();

const store = createStore(
  reducer,
  persistedState,
  applyMiddleware(thunk),
);

store.subscribe(throttle(() => {
  saveState({
    searchParameters: store.getState().searchParameters,
  });
}, 1000));

export default store;
