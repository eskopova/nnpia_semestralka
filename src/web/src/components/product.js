import { useNavigate } from "react-router-dom";

function Product(props) {
    let navigate = useNavigate();
    const product = props.product;

    return (
        <div style={{
            width: "200px",
            border: "1px solid #e0e0e0",
            display: "flex",
            flexDirection: "column",
            margin: "10px",
            padding: "10px",
            borderRadius: "10px",
            boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
            transition: "transform 0.2s",
            backgroundColor: "#ffffff",
            cursor: "pointer"
        }}
             onClick={() => navigate(`/products/${product.id}`)}
             onMouseEnter={(e) => e.currentTarget.style.transform = 'scale(1.05)'}
             onMouseLeave={(e) => e.currentTarget.style.transform = 'scale(1)'}
        >
            <div style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                height: "150px",
                overflow: "hidden" }}>
                <img src={"/img/" + product.pathToImage} alt={product.productName} style={{
                    height: "100%",
                    width: "100%",
                    objectFit: "contain",}} />
            </div>
            <h2 style={{
                width: "100%",
                height: "60px",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                fontSize: "1rem",
                textAlign: "center",
                marginTop: "10px"
            }}>{product.productName}</h2>
        </div>
    );
}

export default Product;