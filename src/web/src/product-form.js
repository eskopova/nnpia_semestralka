import { useEffect, useState } from "react";
import api from './api'; // Import the configured Axios instance
import "./styles/form.css";

function ProductForm() {
    const maxDescriptionLength = 300;

    const [productName, setProductName] = useState("");
    const [pathToImage, setPathToImage] = useState("");
    const [description, setDescription] = useState("");
    const [shop, setShop] = useState("");
    const [category, setCategory] = useState("");

    const [shops, setShops] = useState([]);
    const [categories, setCategories] = useState([]);
    const [isFormValid, setIsFormValid] = useState(false);
    const [feedback, setFeedback] = useState("");

    useEffect(() => {
        if (productName && pathToImage && shop && description) {
            setIsFormValid(true);
        } else {
            setIsFormValid(false);
        }
    }, [productName, pathToImage, shop, description]);

    useEffect(() => {
        fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/shops`)
            .then(response => response.json())
            .then(data => setShops(data))
            .catch(error => console.error('Error fetching shops:', error));
    }, []);

    useEffect(() => {
        fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/product-categories`)
            .then(response => response.json())
            .then(data => setCategories(data))
            .catch(error => console.error('Error fetching categories:', error));
    }, []);

    const onSubmitHandler = event => {
        event.preventDefault();

        const newProduct = {
            productName: productName,
            pathToImage: pathToImage,
            description: description,
            shopName: shop,
            category: category
        };

        api.post('/products', newProduct)
            .then(response => setFeedback(response.data))
            .catch(error => console.error('Error creating product:', error))
            .finally(() => {
                setProductName("");
                setPathToImage("");
                setDescription("");
                setShop("");
                setCategory("");
            });
    };

    return (
        <>
            <form onSubmit={onSubmitHandler}>
                <div className="form-container">
                    <input
                        className="input-field"
                        type="text"
                        value={productName}
                        placeholder="Nazev"
                        onChange={e => setProductName(e.target.value)}
                    />
                    <input
                        className="input-field"
                        type="text"
                        value={pathToImage}
                        placeholder="Obrazek"
                        onChange={e => setPathToImage(e.target.value)}
                    />
                    <select
                        className="input-field"
                        value={shop}
                        onChange={e => setShop(e.target.value)}
                    >
                        <option value="">Vyberte obchod</option>
                        {shops.map(s => (
                            <option key={s} value={s}>{s}</option>
                        ))}
                    </select>
                    <select
                        className="input-field"
                        value={category}
                        onChange={e => setCategory(e.target.value)}
                    >
                        <option value="">Vyberte kategorii</option>
                        {categories.map(c => (
                            <option key={c} value={c}>{c}</option>
                        ))}
                    </select>
                    <textarea
                        className="description-field"
                        value={description}
                        placeholder="Popis"
                        onChange={e => setDescription(e.target.value)}
                        maxLength={maxDescriptionLength}
                    />
                    <input
                        className="button"
                        type="submit"
                        value="Submit"
                        disabled={!isFormValid}
                    />
                </div>
            </form>

            {feedback && <div>{JSON.stringify(feedback)}</div>}
        </>
    );
}

export default ProductForm;
