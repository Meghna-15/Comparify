import React from "react";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LandingPage from "./components/landing/LandingPage";
import Login from "./components/login/Login";
import Menus from "./components/side-navigation/Menus";
import AuthGuard from "./guard/AuthGuard";
import Register from "./components/register";

const AppRoutes = (props) => {

  return (
    <Router basename={process.env.REACT_APP_BASE_HREF}>
      <Routes>
        <Route path="/" element={<LandingPage />} >
          <Route index element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Route>
        <Route element={<AuthGuard/>}>
          <Route path="/home" element={<Menus />} />
        </Route>
      </Routes>
    </Router>
  );
  
};

export default AppRoutes;
