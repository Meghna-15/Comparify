import React from "react";
import ProductRecord from "./ProductRecord";
import { useDispatch, useSelector } from "react-redux";
import product from "../../store/thunk/productThunkCreators";

const ProductList = (props) => {

  const  products  = useSelector((state) => state.product.search);
  console.log (products);
  
  return (
    <div>
      <table class="ui inverted green table">
        <thead>
          <tr>
            <th>Product</th>
            <th>Retail stores</th>
            <th>brandi</th>
            <th>volume</th>
            <th>price</th>
          </tr>
        </thead>
        <tbody>
          <ProductRecord />
        </tbody>
      </table>
    </div>
  );
};

export default ProductList;
