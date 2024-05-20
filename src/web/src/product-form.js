import {useEffect, useState} from "react";

function ProductForm(props) {

    const maxDescriptionLength = 300

    const [productName, setProductName] = useState("")
    const [pathToImage, setPathToImage] = useState("")
    const [description, setDescription] = useState("")
    const [shop, setShop] = useState("")

    const [isFormValid, setIsFormValid] = useState(false)
    const [feedback, setFeedback] = useState("")

    useEffect(() => {
        if (productName && pathToImage && shop && description) {
            setIsFormValid(true)
        } else {
            setIsFormValid(false)
        }
    }, [productName, pathToImage, shop, description]);

    const onSubmitHandler = event => {
        event.preventDefault()

        const newProduct = {
            productName: productName,
            pathToImage: pathToImage,
            description: description,
            shop: shop
        }

        console.log(JSON.stringify(newProduct))

        fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/products`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newProduct)
        }).then(r => r.json())
            .then(json => setFeedback(json))
            .finally(() => {
                props.onNewProduct(newProduct)
                setProductName("")
                setPathToImage("")
                setDescription("")
                setShop("")
            })
    }

    return (
        <>
            <form onSubmit={onSubmitHandler}>
                <div style={
                    {
                        display: "flex",
                        flexDirection: "column",
                        gap: "10px", // Adds spacing between elements
                        maxWidth: "300px", // Limits the width of the form
                        margin: "auto", // Centers the form horizontally
                        padding: "20px", // Adds padding inside the form
                        border: "1px solid #ccc", // Adds a border around the form
                        borderRadius: "5px" // Rounds the corners of the form
                    }
                }>
                    <input type="text" value={productName} placeholder="Nazev"
                           onChange={e => setProductName(e.target.value)}
                           style={{padding: "10px", fontSize: "16px"}}/>
                    <input type="text" value={pathToImage} placeholder="Cesta k obrazku"
                           onChange={e => setPathToImage(e.target.value)}
                           style={{padding: "10px", fontSize: "16px"}}/>
                    <input type="text" value={shop} placeholder="Obchod"
                           onChange={e => setShop(e.target.value)}
                           style={{padding: "10px", fontSize: "16px"}}/>
                    <textarea value={description} placeholder="Popis"
                              onChange={e => setDescription(e.target.value)}
                              style={{padding: "10px", fontSize: "16px", height: "150px", resize: "none"}}
                              maxLength={maxDescriptionLength}/>
                    <input type="submit" value="Submit"
                           style={{padding: "10px", fontSize: "16px", cursor: "pointer"}}
                           disabled={!isFormValid}/>
                </div>
            </form>

            {feedback && <div>{JSON.stringify(feedback)}</div>}
        </>
    )
}

export default ProductForm;