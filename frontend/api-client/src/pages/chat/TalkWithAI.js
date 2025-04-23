import React, { useState } from "react";
import api from "../../services/api";

function TalkWithAI() {

    const [prompt, setPrompt] = useState('');
    const [chatResponse, setChatResponse] = useState('');
    
    const [downloadResponse, setDownloadResponse] = useState('');
    const [fileName, setFileName] = useState('');
    const [servico, setServico] = useState('');

    const AskAi = async () => {

        try {
            const response = await api.get(`ask-ai-options`, {
                params: { prompt }
            })

            const { resposta, nomeArquivo } = response.data;
            console.log("Resposta:", resposta);
            console.log("Arquivo gerado:", nomeArquivo);

            
            const data = await response.data;
            console.log(data);
            setChatResponse(data);
        } catch (error) {
            console.log("Error generate response: ", error);
        }
    }

    const handleDownload = async () => {
        try {
            const url = `http://localhost:8081/ai/download/chat_options`;
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
            <button onClick={handleDownload}>Download</button>
            <div className="outPut">
                <p>{downloadResponse}</p>
            </div>
        </div>
    )
}
export default TalkWithAI