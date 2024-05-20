import {useState} from "react";

function Product(props) {

    return (<div style={{
        width: "150px",
        border: "1px solid black",
        display: "flex",
        flexDirection: "column",
        margin: "5px",
        padding: "5px"
    }}>
        <div><img src={props.product.image} height={150} width={150}/></div>
        <h2 style={
            {
                width: "140px",
                height: "60px"
            }
        }>{props.product.name}</h2>
    </div>)
}

export default Product;