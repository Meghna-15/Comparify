import React from "react";
import Select from "@material-ui/core/Select";
import { Button, TextField, Box, Grid, MenuItem } from "@material-ui/core";
import { useFormik } from "formik";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { authenication } from "../../store/thunk/userThunkCreators";
import useStyles from "../../hooks/use-styles";
import { useNavigate } from "react-router-dom";
import httpClient from "../../store/thunk/interceptor";



const SearchBar  {
  <Grid container className={classes.root}>
      <Box>
        <form onSubmit={formik.handleSubmit}>
          <Grid>
            <Grid item>
              <TextField
                fullWidth
                margin="normal"
                variant="outlined"
                id="userIdentifier"
                name="userIdentifier"
                label="User Identifier"
                autoComplete="off"
                value={formik.values.userIdentifier}
                onChange={formik.handleChange}
                error={
                  formik.touched.userIdentifier &&
                  Boolean(formik.errors.userIdentifier)
                }
                helperText={
                  formik.touched.userIdentifier && formik.errors.userIdentifier
                }
                inputProps={{
                  autoComplete: "off",
                }}
              />
            </Grid>
            <>

   
  
  
}
export default SearchBar;
