import React from "react";

class ProductRecord extends React.Component {
  constructor(props) {
    super(props);
  }
  // componentDidMount() {
  //   this.imageRef.current.addEventListener("load", this.setSpans);
  // }
  // setSpans = () => {
  //   const height = this.imageRef.current.clientHeight;
  //   const spans = Math.ceil(height / 10);
  //   this.setState({ spans });
  // };
  render() {
    // const { } = this.props.image;
    return (
     
        <tr>
          <td>Apples</td>
          <td>A</td>
          <td>B</td>
          <td>C</td>
        </tr>
     
    );
  }
}
export default ProductRecord;
