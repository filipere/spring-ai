import React, { useState } from "react";
import ReactMarkDown from "react-markdown";
import api from "../../services/api";

function RecipeGenerator() {
    const [ingredients, setIngredients] = useState('');
    const [cuisine, setCuisine] = useState('Any');
    const [dietaryRestrictions, setDietaryRestrictions] = useState('');

    const [recipe, setRecipe] = useState('');

    const createRecipe = async () => {

        try {
            const response = await api.get(`recipe-creator`, {
                params: { ingredients, 
                          cuisine, 
                          dietaryRestrictions }
            })
            const data = await response.data;
            console.log(data);
            setRecipe(data);
        } catch (error) {
            console.log("Error generate recipe: ", error);
        }
    }

    return (
        <div>
            <h2>Gerar Receita</h2>
            <input
                type="text"
                value={ingredients}
                onChange={(e) => setIngredients(e.target.value)}
                placeholder="Enter a ingredients (comma saparated)"
            />
            <input
                type="text"
                value={cuisine}
                onChange={(e) => setCuisine(e.target.value)}
                placeholder="Enter a cuisine type"
            />
            <input
                type="text"
                value={dietaryRestrictions}
                onChange={(e) => setDietaryRestrictions(e.target.value)}
                placeholder="Enter a dietary restrictions"
            />

            <button onClick={createRecipe}>Gerar receita</button>
            <div className="outPut">
                <ReactMarkDown>{recipe}</ReactMarkDown>
            </div>
        </div>
    )
}
export default RecipeGenerator