import httpClient from "./interceptor";
import { failedAuth, gotAuth } from "../reducers/authentication";

export const authenication = (credentials) => async (dispatch) => {
    try {
      const { data } = await httpClient.post("/user/authentication", credentials);
      dispatch(gotAuth(data));
      localStorage.setItem("auth-token", data.token);
    } catch (error) {
      dispatch(failedAuth(error));
      localStorage.removeItem("auth-token")
    }
};

export const getDetails = (username) => async (dispatch) => {
  try {
    alert(username);

    const { data } = await httpClient.get("/user/details?username=" + username);
    alert('success');
  } catch (error) {
    alert('failed');
  }
};