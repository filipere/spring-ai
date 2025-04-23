import axios from "axios"


const api = axios.create({
    baseURL: 'http://localhost:8081/ai',
    dowloadURL: 'http://localhost:8081/ai/download',
    withCredentials: true
});

export default api;