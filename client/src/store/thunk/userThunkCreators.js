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
    const { data } = await httpClient.get("/user/details?username=" + username);
    localStorage.setItem("email", data.email);
    localStorage.setItem("firstName", data.firstName);
    localStorage.setItem("lastName", data.lastName);
  } catch (error) {
    alert(error);
  }
};