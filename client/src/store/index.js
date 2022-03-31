import { createStore, applyMiddleware, combineReducers } from "redux";
import loggerMiddleware from "redux-logger";
import thunkMiddleware from "redux-thunk";

import authentication from "./reducers/authentication";
import user from "./reducers/user";
import alert from "./reducers/alert";
import product from "./reducers/product";

const CLEAR_ON_LOGOUT = "CLEAR_ON_LOGOUT";

export const clearOnLogout = () => {
  return {
    type: CLEAR_ON_LOGOUT
  };
};

const appReducer = combineReducers({
  authentication, 
  user,
  alert,
  product
});

const rootReducer = (state, action) => {
  if (action.type === CLEAR_ON_LOGOUT) {
    state = undefined;
  }
  return appReducer(state, action);
};

export default createStore(rootReducer, applyMiddleware(thunkMiddleware, loggerMiddleware));
