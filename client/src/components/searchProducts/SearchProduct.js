import React from "react";
import SearchBar from "./SearchBar";
import ProductList from "./ProductList";

class SearchProduct extends React.Component {
  
 btoa
    
  render() {
    return (
      <div className="ui container" style={{ marginTOp: "10px" }}>
        <SearchBar>
          <ProductList values={this.props} />
        </SearchBar>
      </div>
    );
  }
}

export default SearchProduct;
