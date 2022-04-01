import { Grid } from "@mui/material";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import product from "../../store/thunk/productThunkCreators";
import ProductCard from "./ProductCard";

const ProductList = (props) => {
  const products = useSelector((state) => state.product.search);
  console.log(products);


  return (
    <>
      {products.length > 0 && (
        <div>
          <Grid container spacing={3}>
          {products.map((product) => {
            return (
              <ProductCard key={product.recordId} data={product} />
            );
          })}
          </Grid>
        </div>
      )}
    </>
  );
};

export default ProductList;
