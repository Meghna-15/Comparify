import React from "react";
import ProductRecord from "./ProductRecord";
// import "../css/ImageList.css";

const ProductList = (props) => {
//   const products = props.products.map((product) => {
//     return <ProductRecord product={product} />;
//   });
  return (
    <div>
      <table class="ui inverted green table">
        <thead>
          <tr>
            <th>Product</th>
            <th>Retail stores</th>
            <th>brandi</th>
            <th>volume</th>
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
