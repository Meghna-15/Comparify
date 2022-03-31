import React from "react";
import SearchBar from "./SearchBar";
import ProductList from "./ProductList";
import httpClient from "../../store/thunk/interceptor";

class SearchProduct extends React.Component {
  state = { image: [] };
  onSearchSubmit = async (term) => {
    const response = await httpClient.get(`search/photos?query=${term}`, {  
    });

    this.setState({ image: response.data.results });
  };
  
  render() {
    return (
      <div className="ui container" style={{ marginTOp: "10px" }}>
       <Search/>
        <ProductList />
       
      </div>
    );
  }
}

export default SearchProduct;
