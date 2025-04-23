import React, { useState } from "react";
import ReactMarkDown from "react-markdown";
import api from "../../services/api";

function RecipeGenerator() {
    const [ingredients, setIngredients] = useState('');
    const [cuisine, setCuisine] = useState('Any');
    const [dietaryRestrictions, setDietaryRestrictions] = useState('');

    const [downloadResponse, setDownloadResponse] = useState('');
    const [fileName, setFileName] = useState('');
    const [servico, setServico] = useState('');

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

    const handleDownload = async () => {
        try {
            const url = `http://localhost:8081/ai/download/receita`;
            const response = await fetch(url);
    
            if (!response.ok) {
                throw new Error("Erro ao baixar o arquivo");
            }
    
            const blob = await response.blob();
            const downloadUrl = window.URL.createObjectURL(blob);
            const link = document.createElement("a");
            link.href = downloadUrl;
            link.download = fileName;
            document.body.appendChild(link);
            link.click();
            link.remove();
            window.URL.revokeObjectURL(downloadUrl);
        } catch (error) {
            console.error("Erro ao realizar download:", error);
        }
    };

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
            <button onClick={handleDownload}>Download</button>
            <div className="outPut">
                <p>{downloadResponse}</p>
            </div>
        </div>
    )
}
export default RecipeGenerator