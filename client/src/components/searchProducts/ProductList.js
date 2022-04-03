import { Grid } from "@mui/material";
import React from "react";
<<<<<<< HEAD
import {  useSelector } from "react-redux";
=======
import {  useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { gotItem } from "../../store/reducers/search";
>>>>>>> 0fb7a8b8be9159f5777bcd4be44d1216fa941c6f
import ProductCard from "./ProductCard";

const ProductList = (props) => {
  const products = useSelector((state) => state.product.search);
<<<<<<< HEAD

=======
  const dispatch = useDispatch()
  const navigate = useNavigate();

  const handleOnClick = (itemId) => {
    dispatch(gotItem(itemId))
    navigate("compare");
  }
>>>>>>> 0fb7a8b8be9159f5777bcd4be44d1216fa941c6f

  return (
    <>
      {products.length > 0 && (
        <div>
          <Grid container spacing={3}>
          {products.map((product) => {
            return (
<<<<<<< HEAD
              <ProductCard key={product.recordId} data={product} />
=======
              <Grid item sx={{cursor: "pointer"}} onClick={() => handleOnClick(product.itemId)} >
                <ProductCard key={product.recordId} data={product}  />
              </Grid>
>>>>>>> 0fb7a8b8be9159f5777bcd4be44d1216fa941c6f
            );
          })}
          </Grid>
        </div>
      )}
    </>
  );
};

export default ProductList;
