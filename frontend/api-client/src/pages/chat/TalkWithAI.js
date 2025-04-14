import React, { useState } from "react";
import api from "../../services/api";

function TalkWithAI() {

    const [prompt, setPrompt] = useState('');
    const [chatResponse, setChatResponse] = useState('');

    const AskAi = async () => {

        try {
            const response = await api.get(`ask-ai-options`, {
                params: { prompt }
            })
            const data = await response.data;
            console.log(data);
            setChatResponse(data);
        } catch (error) {
            console.log("Error generate response: ", error);
        }
    }
    
    return (
        <div>
            <h2>Pergunte a IA</h2>
            <input
                type="text"
                value={prompt}
                onChange={(e) => setPrompt(e.target.value)}
                placeholder="Enter a prompt for AI"
            />
            <button onClick={AskAi}>Peguntar</button>
            <div className="outPut">
                <p>{chatResponse}</p>
            </div>
        </div>
    )
}
export default TalkWithAI